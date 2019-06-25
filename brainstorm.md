# Brainstorm
> this part is a draft, gathering ideas, researchs and analysis of the need. It will be transformed into functional specs.

- [Idea](#idea)
- [Main functionalities](#main-functionalities)
- [Personas](#personas)
  * [Business analyst - admin](#business-analyst---admin)
  * [chapter manager - admin](#chapter-manager---admin)
  * [senior software engineer - user](#senior-software-engineer---user)
  * [junior software engineer - user](#junior-software-engineer---user)
- [First User Stories](#first-user-stories)
  * [connection](#connection)
  * [member creation](#member-creation)
  * [manage releaser of the week](#manage-releaser-of-the-week)
  * [manage release iteration](#manage-release-iteration)
  * [manage support team leader of the day](#manage-support-team-leader-of-the-day)
  * [help in release process](#help-in-release-process)
- [TBD : benchmark](#tbd---benchmark)
- [sitemap](#sitemap)
- [TBD : wireframes](#tbd---wireframes)
- [TBD : wiki](#tbd---wiki)

## Idea

Many teams are agile, and agility requires a good self organization.
Unfortunately, every developer has his own feature to develop, often in the urge, and agility can become hard to maintain.
Sprint-planner intends to help keep calm and be agile by taking care of sprint management and by organizing automatically
releasers and support designation.

See below the 4 main functionalities

## Main functionalities

-   manage releaser of the week
> select, find and change the releaser of the week. 
-   manage release iteration
> retrieve release version number and organize iteration pattern
-   manage support team leader of the day
> select, find and change the support team leader of the day.
-   Mail information system
> inform the team and the members of whom is releaser, support, of releaser version number and if noone has been found to take care of the release.

## Personas

### Business analyst - admin
![](https://github.com/Vilth83/sprint-planner/blob/master/bert-vaner.svg)
### chapter manager - admin
![](https://github.com/Vilth83/sprint-planner/blob/master/phil-mac.svg)
### senior software engineer - user
![](https://github.com/Vilth83/sprint-planner/blob/master/jay-devoe.svg)
### junior software engineer - user
![](https://github.com/Vilth83/sprint-planner/blob/master/terry-valliantown.svg)

## First User Stories

### connection

- create an account
- connect to the application
- have user rights to see stuff
- have admin rights to manage stuff


### member creation
-   [As an admin, I want to create a member, so that I can toggle him releaser/support](https://github.com/Vilth83/sprint-planner/issues/4)
-   [As an admin, I want to delete a member, so that I can deal with current members only](https://github.com/Vilth83/sprint-planner/issues/5)
-   [As an administrator, I want to modify a member, so that I can retrieve correct contacts](https://github.com/Vilth83/sprint-planner/issues/6)


### manage releaser of the week

- [As an admin, I want to create a releaser, so that I can select him to take care of the release](https://github.com/Vilth83/sprint-planner/issues/7)
- [As an admin, I want to delete a releaser, So that I am sure he won't be selected for release anymore](https://github.com/Vilth83/sprint-planner/issues/8)
- [As an user, I want to find current releaser, so that I can know who is in charge of this weeks release](https://github.com/Vilth83/sprint-planner/issues/9)
- [As an admin, I want to manually select releaser, So that I can choose who will do the release](https://github.com/Vilth83/sprint-planner/issues/11)
- [As a user, I want to select next releaser if current is unavailable](https://github.com/Vilth83/sprint-planner/issues/10)
- [As a releaser, I want to be picked automatically](https://github.com/Vilth83/sprint-planner/issues/12)

### manage release iteration

- register iteration pattern
- find current release number
- automatically increment release number
- manage exceptions   

### manage support team leader of the day

- set a member as support candidate
- unset a member as support candidate
- find support leader of the day
- manually switch support leader
- automatically update support leader

### help in release process
- chase releaser during different release tasks
- check release status
- provide a wiki of the release steps
- retrieve the wiki
- be informed team of the current releaser / support via mail
- be informed that I am the next releaser, support leader

## TBD : benchmark

## Sitemap
![](https://github.com/Vilth83/sprint-planner/blob/master/sitemap.png)

## TBD : wireframes

## TBD : wiki
