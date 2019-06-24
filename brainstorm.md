# sprint-planner

A tool to manage sprint releases and support teams

# Sprint 0

## Idea / spitch

Many teams are agile, and agility requires a good self organization.
Unfortunately, every developer has his own feature to develop, often in the urge, and agility can become hard to maintain.
Sprint-planner intends to help keep calm and be agile by taking care of sprint management and by organizing automatically
releasers and support designation.
It also offers a release tracability so each member of the team can know in one click at which step of the release team is, by connecting to development tools
such as JENKINS, GITHUB and JIRA.
Sprint Planner is indeed both a scheduling and a scrum helping tool.

## Main functionalities

-   manage releaser of the week
-   manage release iteration
-   manage support team leader of the day
-   help in release process

## Personas

[link to personas](https://github.com/Vilth83/sprint-planner/blob/master/personas.pdf)

## First User Stories

-   connection

    - create an account
    - connect to the application
    - have user rights to see stuff
    - have admin rights to manage stuff


-   member creation
    -   [as an admin, I want to create a new team member](https://github.com/Vilth83/sprint-planner/issues/4)
    -   [as an admin, I want to delete an existing member](https://github.com/Vilth83/sprint-planner/issues/5)
    -   [as an admin, I want to modify an existing member](https://github.com/Vilth83/sprint-planner/issues/6)


-   manage releaser of the week

    - [As an admin, I want to create a releaser](https://github.com/Vilth83/sprint-planner/issues/7)
    - [As an admin, I want to delete a releaser](https://github.com/Vilth83/sprint-planner/issues/8)
    - [As a user, I want to find current releaser](https://github.com/Vilth83/sprint-planner/issues/9)
    - [As an admin, I want to manually select releaser](https://github.com/Vilth83/sprint-planner/issues/11)
    - [As a user, I want to select next releaser if current is unavailable](https://github.com/Vilth83/sprint-planner/issues/10)
    - [As a releaser, I want to be picked automatically](https://github.com/Vilth83/sprint-planner/issues/12)

-   manage release iteration

    - register iteration pattern
    - find current release number
    - automatically increment release number
    - manage exceptions
    
-   manage support team leader of the day

    - set a member as support candidate
    - unset a member as support candidate
    - find support leader of the day
    - manually switch support leader
    - automatically update support leader

-   help in release process
    - chase releaser during different release tasks
    - check release status
    - provide a wiki of the release steps
    - retrieve the wiki
    - be informed team of the current releaser / support via mail
    - be informed that I am the next releaser, support leader

### benchmark

We find a lot of scrum tools on internets. Those tools are more likely used to manage user stories, project advancement and costs than to manage team organization.
Plus, many of those tools are not free.
There are scheduling softwares existing, but they are not connected to JIRA, GITHUB or other developing tools.

below are some examples of existing sprintplanning tools
[sprint planning tools](https://thedigitalprojectmanager.com/best-scrum-tools/)
below are some examples of existing scheduling tools
[scheduling tools ](https://www.capterra.com/sem-compare/scheduling-software?gclid=Cj0KCQjw6cHoBRDdARIsADiTTzY_KkwQTS5t4kktryGjcCpKjxyUUhqjTIMnyLU2iwnO8XIqcGX0qdQaAjrcEALw_wcB)

## wireframes / sitemap

### TODO : wiki

### TODO : repo public + wiki + dashsboard
