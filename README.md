# JAVA1-project

# Movie Management Application


This repository contains a Java application for managing movie-related data, including movies, actors, directors, and more. The application stores data in a Microsoft SQL Server database and offers features such as user authentication, role-based access control, CRUD operations on entities, RSS parsing for data updates, and image management.


## Key Features:
### User Roles: 
The application supports two roles, Administrator and User.
### Authentication: 
Users can log in using a secure login form, and new users can register as Users.
### Administrator Functionality: 
Administrators can delete all data from the database and upload new data using an RSS parser.
### RSS Parsing: 
The RSS parser fetches data from specified URLs, saves it to the database, and downloads images to a local directory.
### CRUD Operations: 
Users can view and update entities (e.g., Movies, Actors, Directors) using organized forms.
### Data Consistency: 
The application ensures consistent image management when entities are updated or deleted.
### UI Organization: 
Forms are well-organized using components like JTabbedPane and JMenu.
### Table-Based Display: 
JTable and AbstractTableModel are utilized for displaying entities.
### Drag and Drop: 
Drag and drop functionality is implemented for certain entity associations.
### XML Download: 
Data can be downloaded as XML using the JAXB library.


## Requirements:
Java Development Kit (JDK)
Microsoft SQL Server
Libraries: JAXB, JSoup


## Getting Started:
### Clone the repository.
Set up the SQL Server and run the initialization DDL script to create tables.
Run the application and log in as an Administrator or User.
Explore the CRUD forms, use the RSS parser for updates, and manage entities and images.
