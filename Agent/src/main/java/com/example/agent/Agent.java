package com.example.agent;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Starting the Agent");

        //instrumentation.addTransformer(new LogParametersClassTransformer());
        AgentBuilder agentBuilder = createAgentBuilder();
        agentBuilder.installOn(instrumentation);

    }

    private static AgentBuilder createAgentBuilder() {
        return new AgentBuilder.Default().type(ElementMatchers.nameStartsWith("org.example"))
                .transform((builder, typeDescription, classLoader) -> {
                    System.out.println(classLoader);
                    System.out.println("Transforming " + typeDescription);

                    return builder.visit(Advice.to(MyAdvice.class).on(ElementMatchers.any()));
                });
    }
}
