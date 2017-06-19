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

## The process

The logic of processing the files is simple, after upload a file
the web interface will request to process the data from this file and will send
a GET request for:

    http://localhost:8080/csv-parser/process?fileName=${fileName}

The file will be analyzed with the following rules:

Remove any records (rows) which meet both of the following conditions:

*	Have a Decision of 0
*	For each variable (column), no value falls between FMIN and FMAX.

Where FMIN and FMAX are the smallest and largest value for that variable across all records with a decision value of 1.

The result is a List of Strings that will be send for Client side,
the client side split the strings to DataTables structure that is required, an Array of Array of strings.
Each string`s array on result will be a line on datatables.

## DataTables - Why DataTables?

DataTables is a strong library on client side that has a large number of features and plugins.
With the default configuration we will have pagination, sort and search for each field inside the table.







