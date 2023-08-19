# SAP-HDFS-ORC
 This code is to read data from a table (PRODELEMENTS) in a HANA database and then save that data to the Hadoop Distributed File System (HDFS) in ORC format.
Overview
The purpose of this code is to read data from a table (PRCD_ELEMENTS) in a HANA database and then save that data to the Hadoop Distributed File System (HDFS) in ORC format.

Code Explanation
Imports:

Necessary libraries for working with Spark and datasets are imported.
Class Declaration:

A class named DataINTG is defined.
Main Method:

The main method is the entry point when this class is executed.
Database Connection Properties:

Connection details for the HANA database are defined. This includes the host, port, username, password, and constructed JDBC URL.
SparkSession Initialization:

A new SparkSession is created with the name "SparkConnector".
The .master("local[*]") indicates that the Spark job should run locally using all available cores (the [*] is a wildcard indicating "all").
JDBC Data Reading:

A Dataset<Row> (commonly known as a DataFrame) named df is created.
The data is read from the HANA database using the JDBC format.
The .option() methods are used to set various connection properties:
url: JDBC URL for the HANA database.
user & password: Credentials for authentication.
dbtable: Name of the table to read from.
currentSchema: Sets the default schema for this JDBC session. This is used to help Spark understand which schema the table resides in.
HDFS Path and Data Writing:

An HDFS path is constructed for the output data.
The DataFrame df is written to this HDFS path in the ORC (Optimized Row Columnar) format using the .write() method.
.mode(SaveMode.Overwrite) indicates that if the data already exists at the specified HDFS path, it should be overwritten.
.option("header", "true") ensures that the ORC file will contain headers.
SparkSession Closure:

The spark.stop() method is called to terminate the SparkSession and free up resources.
