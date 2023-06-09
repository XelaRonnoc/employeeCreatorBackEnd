# Employee Creator Backend (Spring)

## Requirements / Purpose

### MVP

-   Create a web application to create, list, modify and delete employees.
-   The application should consit of a Spring RESTful API(this repo) and A React Typescript frontend (LINK EMPLOYEE CREATOR FRONTEND).
-   The Database will be created in MySQL and the Schema for which can be anything (that works).
-   e2e and unit tests must be created.
-   An API Logging strategy must be implemented
-   An Error handling strategy must be employed

### Purpose of Project

The purpose of this project is to further develop my skills utilising a Spring backend communicationg with a MySQL DB.
Especially in the areas of: - Spring backend testing (Unit and E2E) - Spring API creation/utilisation - Spring project setup - Refresh knowledge of designing simple DB Schema - Develop skills of pulling information to front end from multiple related tables - Develop skills in DB interaction from Spring (i.e. DB seeding) - Building and Deploying Spring backends

### Stack

The stack utilised is a Spring and MySQL backend with a React Type Script front end.
This was chosen in order to be able to both improve my skills using Spring (and Java in general) as well as continue to further improve in my ability in React and Typescript front ends. Additionally It will improve skills in E2E testing both from frontend and backend and testing in Java which i have less experience in than I do testing in JavaScript.

---

## Build Steps

To Do:

-   how to build / run project
-   use proper code snippets if there are any commands to run

---

## Design Goals / Approach

The Design Goals of this project was to build and develop a roubust and easily usable API for the front end application and make the API modular allowing for it to be further expanded/utilised in the future for reference and future projects/improvements.

I would have liked to approach this project in a more TDD way however due to this being my first real spring project I decided it would be more valueable to get the application running and working first before implementing tests. This has been an effective approach that has allowed me to rapidly develop the project to MVP and now take my time learning and understanding testing principles within java and spring whilst having complete software

---

## Features

This project provides the API for the Employee Creator frontend repo allowing it to communicate with a MySQL Database utilising the following endpoints:
  - GET - returns all employees
  - GET{ID} - returns employee of specified ID
  - DELETE{ID} - deletes employee with specified ID
  - PUT {ID} - Allows for Updates to employees and related tables. PUT was chosen instead of PATCH as a PUT update method allowed for more easily achived autofilling forms for editing employees in the front end improving the user experience.
  - POST{ID} - for creating new employees and related fields.

---

## Known issues

-   tests for the POST and PUT requests have fairly poor scope 

---

## Future Goals

-   Implement Improved backend and E2E testing for this application.
-   Deploy front and backend to provide a live version of the application and build instructions

---

## Change logs

### 09/06/2023 - Added Additonal Backend tests
- Added tests for PUT and POST endpoints

### 08/06/2023 - Added Inital Backend tests
- Added tests for the findAll and findById endpoints in controller and service layer

### 06/06/2023 - Made adjustments to schema,
- changed schema so that the date is now saved as LocalDate not Date to avoid time zone issues

### 05/06/2023 - added some basic tests
- added smoketest and some bare bones tests to backend to ensure backend testing worked as expected

### 04/06/2023 - added some validation to contracts schema

-  added not null validator to startDate on contracts schema

### 03/06/2023 - Removed PATCH endpoint, fixed Race Condtion

-   removed unused and non-functional PATCH endpoint, has been replaced with PUT
-   had a race condition on new entry creation with the interaction of various tables, this was now solved by ensuring that address and contract were saved before employee

### 02/06/2023 - Bug Fixes for PUT and DELETE endpoints

-   Fixed bug that resulted in no cascading of updates or delete to the addresses or contracts tables.

### 01/06/2023 - PUT endpoint creation, reDesign of db schema

-   added PUT endpoint allowing for front end autofill forms and single form component for edit and add new employee
-   added new address table and linked to employees
-   added new contract table and linked to employees
-   new tables work well on new creation but on update the employee table updates correctly doesn't updated linked records in addres or contract table and instead creates new records.
-   additionally delete method does not affect linked tables as it should currently

### 31/05/2023 - Exception Handling, Update endpoint creation

-   Added exception handling for get delete by id and update by id.
-   Added update by ID endpoint.

### 30/05/2023 - Spring Backend Setup and Creation of endpoints

-   Set up Spring backend to MySQL DB
-   Set up Git Repository
-   Set up Employee end points for GET (all), DELETE (by ID), POST (new employee)
-   Utilised Model Mapper for DTO to Database Object translation.

---

## What did you struggle with?

-   Implementing Backend tests has been difficult and I have encountered many challenges and blockers whilst attempting to learn how to achieve this in the Spring Backend. Through perseverance I have made some progress in developing backend tests however the tests for POST and PUT are still of poor scope and this is intended to be improved upon when I have more time.

---

## Licensing Details

To Do:

-   What type of license are you releasing this under?

---

## Further details, related projects, reimplementations

-   See front end at https://github.com/XelaRonnoc/employeeCreatorFrontEnd
