# Test Mile Arjuna-Java

Arjuna-Java is the client side/requester implementation in Java for development of test automation using Arjuna. It is a re-usable Java library for test automation, to be used primarily with TestNG. With minor tweaks, it can be used with any other test engine or custom test automation implementations.

The library is a generic client library for Arjuna, a Python based test automation framework developed by Rahul Verma (www.rahulverma.net). Arjuna is a based on FaaST (Framework as a Service for Testing) architecture that enables integration of multiple programming languages, multiple tools, GUI definition externalization, customized identification types and so on, with finer control of each component. Rahul as the Chief Consultant at Test Mile, has implemented smaller variants of this model across frameworks and organizations, or given advise around it. However Arjuna being a generic library has the most complete implementation of the said model, away from project specific contexts and constraints.

A Python version of this client lib is a part of the Arjuna project itself.

Documentation for Arjuna is in progress. You can can find the currrent help doc on Test Mile website at https://testmile.com/arjuna

### Setting Up Arjuna-Java

1. Install Arjuna as per instructions at this link: https://github.com/test-mile/arjuna/blob/master/README.md#arjuna-installation
2. Download Java8 64-bit from https://adoptopenjdk.net/archive.html
3. Download the latest Eclipse from https://www.eclipse.org/eclipseide/
4. Create a maven project
5. Update the properties in pom.xml to mention Java 1.8 as compiler.
    * `<maven.compiler.source>1.8</maven.compiler.source>`
    * `<maven.compiler.target>1.8</maven.compiler.target>`
6. Add arjuna-java dependency to the project's pom.
 ```xml
<dependency>
   <groupId>com.testmile</groupId>
   <artifactId>arjuna-java</artifactId>
   <version>0.1.1</version>
</dependency>
 ```
7. Create project skeleton by running the following command:
    * `python -m arjuna create-project --non-unitee -p <Absolute directory path of the Java project>`
    * EXAMPLE
    * `python -m arjuna create-project --non-unitee -p C:\Users\test\eclipse-workspace\arjuna-demo`
