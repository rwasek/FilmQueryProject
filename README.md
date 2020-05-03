## Film Query Project - Week 7 Skill Distillery

### Overview

A command-line application that uses Object-Relational Mapping (ORM) and JDBC to use data that is retrieved from a SQL database that has multiple tables. The database is hosted locally through MAMP. The database involves common referential integrity -- being able to use foreign and primary keys to join multiple tables together to be able to produce the customized, desired output.  The data pulled from the SQL database is incorporated with Java code to construct a Java based Object-Oriented program.  The data is used to construct Film and Actor objects that can be called upon and referenced.  The user is able to search the film database by film ID or by typing in all or a part of the films title and/or description.  The displayed output returned to the screen comes from the formed Java Objects, which again were created with data from the SQL database.

### Technologies Used

- Eclipse

- Java

- JDBC

- Terminal Commands

- GitHub/Git

- Maven

- Apache

- MySQL/SQL

- MAMP


### Lessons Learned

- Using Java to send MySQL SELECT statements to the database based on user input, to gather data to form Java Objects that output to the console.

- Making a Java program reliant on Maven dependencies, having a SQL database running on a local server with MAMP, and using them all in conjunction with each other.

- Proper construction of JDBC code to be able to connect to a driver, log-in to a SQL database, send a MySQL SELECT statement to the Table to retrieve data and then turn around and store that data in relevant Java Object classes.

- Making sure connections get closed at the end of methods they are being used in to prevent multiple connection exceptions from being thrown.  Also making sure all methods throw SQL Exceptions to allow the code to run.

- Using "null" checks for Film/Actor Objects or Lists to display an appropriate error message if the user enters a Film ID or description that doesn't exist for example.

- Properly exiting the program smoothly without using System.Exit(0)
