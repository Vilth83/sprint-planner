# Benchmark

This benchmark intend to check about existing solutions regarding the needs, to ensure the project relevance.

## Goal

The main goal of this benchmark is to determine if the development of a sprint planner is relevant regarding existing tools.

## needs

the benchmark is done regarding following needs :
- a task (release / support) can be assigned to a specific member
- if the assignee is unavailable, the task can be switched to another assignee easily
- When switching assignee, the previous one is designated again for the next task (round robin)
- when a task is done, the assignee goes in the end of the queue
- assignees and team are informed of the selected member for each task
- if no one is found for a task (every one is unavailable) the team is informed


This benchmark will compare the following points :

-   performance
-   ergonomy
-   User experience
-   main functionalities

## compared tools

### cireson

[CIRESON release calendar](https://cireson.com/products/service-management/release-calendar/)

> cireson is a scheduling tool for release management. It works like a calendar with work items.

### Float

[Float](https://www.float.com/top-10-resource-scheduling-software-float.html)

### hubplanner

[hub planner](https://hubplanner.com/)

### Results
#### performance
cireson cannot be tested unless we contact the sales and ask for a free trial.
hubplanner has some latencies on scrolling on the calendar (horizontal scrolling).
float is fast and suffer no latencies.
#### ergonomy
float and hub planner provide responsive application, alongside with a cellphone app.
althought their responsivity, both suffer hard reading screen in cellphone because of the width of the calendars.
cireson is a desktop application.

#### user experience
Each other tool is charged, as sprint planner is a free, open-source tool.
float does not allow to create a task repeating for more than 365 days, at least in trial version.

#### functionalities
Every compared tools give access to scheluding functionalities if the form of a calendar.

they permit to create tasks and assign it, change assignees, but none of them offer the round robin implementation.
Changing assignees requires to change all other assignees manually if we want to respect the need.

### Conclusion
Even if some tools exist that grant team scheduling features, none really answer the specific functionalities of the sprintplanner.
We can say that we suffer no real concurrency, but it the other hands we cannot rely on others experience.
