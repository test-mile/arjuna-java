# Test Mile Arjuna-Java

Arjuna-Java is the client side/requester implementation in Java for development of test automation using Arjuna. It is a re-usable Java library for test automation, to be used primarily with TestNG. With minor tweaks, it can be used with any other test engine or custom test automation implementations.

The library is a generic client library for Arjuna, a Python based test automation framework developed by Rahul Verma (www.rahulverma.net). Arjuna is a based on FaaST (Framework as a Service for Testing) architecture that enables integration of multiple programming languages, multiple tools, GUI definition externalization, customized identification types and so on, with finer control of each component. Rahul as the Chief Consultant at Test Mile, has implemented smaller variants of this model across frameworks and organizations, or given advise around it. However Arjuna being a generic library has the most complete implementation of the said model, away from project specific contexts and constraints.

A Python version of this client lib is a part of the Arjuna project itself.

Documentation for Arjuna is in progress. You can can find the currrent help doc on Test Mile website at https://testmile.com/arjuna

### Setting Up Arjuna-Java on Windows 

1. Download Java8 64-bit from https://adoptopenjdk.net/archive.html
2. Download the latest Eclipse from https://www.eclipse.org/eclipseide/
3. Create a maven project
4. Update the properties in pom.xml to mention Java 1.8 as compiler.
    * `<maven.compiler.source>1.8</maven.compiler.source>`
    * `<maven.compiler.target>1.8</maven.compiler.target>`
5. Add arjuna-java dependency to the project's pom.
 ```xml
<dependency>
   <groupId>com.testmile</groupId>
   <artifactId>arjuna-java</artifactId>
   <version>0.1.1</version>
</dependency>
 ```
6. Download and install latest Python (3.5+) from https://python.org
    * If you are insterested to learn python Following are links for tutorials and docs.
    + https://docs.python.org/3.7/tutorial/index.html
    + https://docs.python.org/3.7/index.html
7. Install Git https://git-scm.com/downloads
8. Add the git bin path(C:\Program Files\Git\bin) to PATH environment variable.
9. Add python path to the PATH environment variable if it is not added.
10. Confirm the python version installed by running the command `python --version`
11. Install the arjuna-python binding using the following command
    * `pip install git+https://github.com/test-mile/arjuna.git@master`
12. Run the following command to create the project directory structure
    * `python -m arjuna create-project --non-unitee -p <Absolute directory path of the Java project>`
    * EXAMPLE
    * `python -m arjuna create-project --non-unitee -p C:\Users\test\eclipse-workspace\arjuna-demo`
