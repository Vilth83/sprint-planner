![javaBuild](https://github.com/Vilth83/sprint-planner/workflows/Java%20CI/badge.svg?push) ![nodeBuild](https://github.com/Vilth83/sprint-planner/workflows/Node%20CI/badge.svg?push)

![sprintplanner-banner](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/img/linkedin_banner_image_2.png)
A tool to manage sprint releases and support teams

## Database
The application is provided with an H2 Database. No configuration is needed, the schema is already created and configuration tables are filled.

However you can use another database. MySql is provided as an option. To use a MySQL database, please follow the steps below :
- Comment or delete H2 related options in application.yml
- Uncomment MySQL options in application.yml
- execute de sprintplanner.ddl script on your MySQL database to create schema and tables
- execute sprintplanner.dml script to fill configuration tables
