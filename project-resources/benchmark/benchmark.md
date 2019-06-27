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

### Float

[Float](https://www.float.com/top-10-resource-scheduling-software-float.html)

### hubplanner

[hub planner](https://hubplanner.com/)

### Results
#### Performance
 - Cireson
 
 the tool cannot be tested unless we contact the sales and ask for a free trial.

- Hubplanner

the connection to the user interface is quick, despite some latencies when navigating from a screen to another.

the calendar suffer some latencies when horizontal scrolling.

Hubplanner starts in a bit more than 10ms even if it has way more elements to display than float (cf below)

!hub-start](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/benchmark/hub-start.PNG)

- Float

the application is pretty quick after starting, but take a long time to start.

The app takes 35 ms to start, although its interface is pretty sober and empty.

![float-start](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/benchmark/float-start.PNG)


>**Sprint-planner** will be a **lightweight, low latency** web application. The technical stack will be chosen giving **priority** to **security** and **speed**. Design and tehcnical choices of **sprint-planner** will be made to keep the **start time below 1.5 seconds**.

#### ergonomics
float and hub planner provide responsive application, alongside with a cellphone app.
despite their responsivity, both suffer hard reading screen in cellphone because of the width of the calendars.

![float-phone]

![hub-phone]

- float

float has a sober interface, with simple and explicit buttons.

It is easy to apprehend for simple tasks, but it's hard to see the full potential of this application as they are listed on the web site.

The whole interface looks more like a phone one than a web app.

![float-ui](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/benchmark/float-interface.PNG)

The mouse capturing is quite confusing : a right click outside the calendar will trigger the web app right click.

We have to go on the header to get the browsers right click.

![float-rightclick-issue](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/benchmark/float-rightclick-issue.PNG)

- Hubplanner

hub planner provides multiple functionalities listed on the screen.
One can see the potentialof this app, but at the cost of a little confusing interface.

![hub-ui](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/benchmark/hub-interface.PNG)

float and hub planner provide responsive application, alongside with a cellphone app.
despite their responsivity, both suffer hard reading screen in cellphone because of the width of the calendars.

![float-phone]

![hub-phone]

- cireson

cireson is a desktop application. 
given the difficulties to get a free trial, we can only rely on their website video presentation and cannot judge ergonomics.

[cireson-video-link]()

>**Sprint-planner** will be a **responsive web application**, without an app. The screen is based on grids, **without the problem of a too long calendar screen**.

#### user experience
Arriving on float UI is a little disappointing, especially after reading their [commercial site](https://www.float.com/top-10-resource-scheduling-software-float.html).
float does not allow to create a task that repeats for more than 365 days, at least in trial version.

Hub Planner make visitor more confident about its capabilities but seems hard to use.

Both of them, as cireson are not free.

>**sprint planner is a free, open-source tool**. We intend to do a **userfriendly interface**, giving each functionalities in a **quick and simple way** to **ease tasks of the team**.
As main features are **automatic**, the application needs little configuration an maintenance and will **run on its own**.
The only task asked to the team is eventually to switch releaser / support, and this **action can be done in 1 click**.



#### functionalities
Every compared tools give access to scheluding functionalities if the form of a calendar.

they permit to create tasks and assign it, change assignees, but none of them offer the round robin implementation.

Changing assignees requires to change all other assignees manually if we want to respect the need.

>**Sprint-planner** will provide a **tool to manage a releaser**, his unavailability, **to ensure round robin** between releasers. It will **manage support leader designation**, unavailability of the team leader. The **rotation will be automated** but will also **stay modifiable**.

### Conclusion
Even if some tools exist that grant team scheduling features, none really answer the specific functionalities of the sprintplanner.

**Sprint-planner** is an innovation, answering a specific need encountered in agile teams.

We can say that we suffer no real concurrency, but it the other hand we cannot rely on others experience.
