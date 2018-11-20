# Project

## To Build
The JAR is already built, if you want build it again run **mvn package** (mvn is required)

## Database Connection
To change the database connection, please update the file **config.properties** 

## Jar
There is a configuration to put the jar on root directory: **&lt;outputDirectory&gt;./&lt;/outputDirectory&gt;**
 
## Database Schema
The Database Schema is in the file **Table Schema.sql**

## SQL Queries
The SQL Queries asked are in the file **SQL queries for SQL test.sql**

##PS
The file **Result with args Result with args - --startDate=2017-01-01.150000 --duration=hourly --threshold=200.txt** contains the result of the execution of the command --startDate=2017-01-01.150000 --duration=hourly --threshold=200, in the instructions are expected only one IP but there are two. The other sample execution with threshold=500 returns more than one IP too, but it is too large to make an evidence file.
