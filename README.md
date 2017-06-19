# CSV Parser
This project is a simple project that has a web interface to upload
files for server and process data.
After the process the data is displayed on interface inside a table
using the library DataTables.

## Running

This is a Maven project that allow you to import in your IDE.
After import as a maven project and download all dependencies,
you can compile the code and run the main class Application.java

This application is a Spring boot application, so you need run the
configuration is minimum.

The context name is configured for csv-parser and the port is running
on default 8080 on a embedded tomcat, the project is packaged in a jar file.

So, you make a ```maven clean compile package``` you will find inside the Target folder
a jar file like ''csv_parser-${version}-SNAPSHOT.jar'', the ${version} is the 
version configured on pom.xml.

After that you can run '''java -jar ${jarName}''' and the application will
run.

If you prefer you can change the application.properties file inside
resources folder to specify a default port or context name.

After start the application, if you did not change configurations, you can access in your browse by address http://localhost:8080/csv-parser.
Else you have to adapt the URL to your port and context name.

## Folders

The project has to handle upload files, so when you start to upload your CSV file,
the project will create a folder CSV on users home folder to storage all files
that will be uploaded, by questions of time we do not handle multiples files with same name
and we process one file by time.

These questions are improvement that would be developed.





