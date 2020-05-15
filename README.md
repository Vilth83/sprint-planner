![javaBuild](https://github.com/Vilth83/sprint-planner/workflows/Java%20CI/badge.svg?push) ![nodeBuild](https://github.com/Vilth83/sprint-planner/workflows/Node%20CI/badge.svg?push)

![sprintplanner-banner](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/img/linkedin_banner_image_2.png)
A tool to manage sprint releases and support teams

## Database
The application is provided with an H2 Database. No configuration is needed, the schema is already created and configuration tables are filled. Hence the only configuration to be done is to adapt credentials (user / password) if needed.

However you can use another database. MySql is provided as an option. To use a MySQL database, please follow the steps below:
- Edit application.yml :
  - Comment H2 related configuration
  - Uncomment / adapt MySQL related configuration
- execute de sprintplanner.ddl script on your MySQL database to create schema and tables
- execute sprintplanner.dml script to fill configuration tables

You are free to choose your own database system. If so, as provided sql scripts are written in MySQL grammar, they may have to be adapted to another SQL grammar.
