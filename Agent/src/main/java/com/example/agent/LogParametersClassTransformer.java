package com.example.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class LogParametersClassTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.contains("org/example")) {

            try {
                System.out.println("Loading " + className);
                ClassPool pool = ClassPool.getDefault();
                pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
                CtClass cclass = pool.get(className.replaceAll("/", "."));

                if (!cclass.isFrozen()) {
                    for (CtMethod currentMethod : cclass.getDeclaredMethods()) {
                        List<String> parameterNames = getMethodParams(currentMethod);

                        if (parameterNames == null || parameterNames.size() == 0) {
                            continue;
                        }

                        if(!Modifier.isStatic(currentMethod.getModifiers())) {
                            parameterNames.remove(0);
                            if(parameterNames.size() == 0)
                                continue;
                        }

                        currentMethod.insertBefore(createJavaString(currentMethod, className, parameterNames));

                    }
                    return cclass.toBytecode();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
         * if(className.contains("RandomClassCallingThings")) { String wrongBuffer =
         * "lsdfhklsdhfkljsdjfsdljfjlsdflsdjf"; classfileBuffer = wrongBuffer.getBytes(); return classfileBuffer; }
         */

        return null;
    }

    private List<String> getMethodParams(CtMethod method) {

        List<String> parameterNames = new ArrayList<>();
        MethodInfo mInfo = method.getMethodInfo();
        CodeAttribute codeAttribute = mInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);

        if (attr != null) {
            for (int i = 0; i < attr.tableLength(); i++) {
                try {
                    int name = attr.nameIndex(i);
                    String variableName = mInfo.getConstPool().getUtf8Info(name);
                    parameterNames.add(variableName);
                } catch (Exception e) {

                }
            }
        }
        return parameterNames;
    }

    private String createJavaString(CtMethod currentMethod, String className, List<String> parameterNames)
            throws NotFoundException {

        StringBuilder sb = new StringBuilder();
        sb.append("{StringBuilder sb = new StringBuilder");
        sb.append("(\"\\nMethod : \");");
        sb.append("sb.append(\"");
        sb.append(currentMethod.getName());
        sb.append("\");sb.append(\"\\nClass : \");");
        sb.append("sb.append(\"");
        sb.append(className);
        sb.append("\");");
        sb.append("sb.append(\"\\nParameter Values:\");");

        int i = 0;
        for (String param : parameterNames) {
            try {
                sb.append("sb.append(\"\\n  " + param + " : \");");
                sb.append("sb.append($args[" + i + "]);");
                i++;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        sb.append("System.out.println(\"\\n\"+sb.toString());}");
        return sb.toString();
    }

}

// Subclasses of LinkageError indicate that a class has some dependency on another class; however, the latter class has
// incompatibly changed after the compilation of the former class.
