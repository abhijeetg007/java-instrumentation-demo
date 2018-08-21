DemoApplication
=======

A simple tracing agent

Build:
/prod/tools/infra/gradle/latest/bin/gradle clean build --refresh-dependencies

Run:

java -javaagent:/codemill/gaikwad/projects/ad-hoc-projects/Agent/build/libs/Agent-0.1.jar -cp  build/libs/DemoApplication-0.1.jar org.example.callspy.example.RandomClassCallingThings

