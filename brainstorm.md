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

  | name   | Bert Vaner       |
  | ------ | ---------------- |
  | age    | 39               |
  | Job    | business analyst |
  | status | contractor       |

  Bert is in charge of functional needs. He also organize iterations, morning meetings and sprint debriefing.
  Each week, he looks in JIRA and GITHUB for features ongoing and send a mail to the team. He can also be a support team leader.

  _I spend a lot of time writing release mails, and crucial informations such as this weeks releaser are always to be defined... I need a tool to manage that for me !_

  | name   | Phil Mach       |
  | ------ | --------------- |
  | age    | 43              |
  | job    | Chapter manager |
  | status | Employee        |

  Phil is in charge of the team management. He manage several teams (known as Chapters), working on different projects joined together in a product interation.
  He have to ensure that Agile@Scale principles are respected.

  _I manage several agile teams, and hardly knows who is in charge of the release, or who is in charge of the support..._

  | name   | Jay Devoe                |
  | ------ | ------------------------ |
  | age    | 45                       |
  | job    | senior software engineer |
  | status | Employee                 |

  Jay is a senior software engineer working in the society for many years. He acts like a fireman on urging topics, and doesn't code that much anymore.
  He is the one the team relies on when a problem occurs, or when difficulties are encountered regarding release.

  _I want to have a precise insight of the release process : who is in charge, which step are we in, is everything fine or not?_

  -   Newcomer

  | name   | Terry Valliantown        |
  | ------ | ------------------------ |
  | age    | 35                       |
  | job    | junior software engineer |
  | status | intern                   |

  Terry is in an internship in the company. He is here to learn development and project management, such as releases, technical architecture.
  He wants to join the support team to improve his skills;

  _I have never made a release. A tool giving wiki and release step insights would be very helpful for me to learn._

  -   final User

  | name   | Andy Youzer     |
  | ------ | --------------- |
  | age    | 55              |
  | job    | audit inspector |
  | status | Employee        |

  Andy work in the audit service. He is the final user of an application currently in development by the team.
  As it's a new software, he needs to learn how to use it and is afraid not to have enough support.

  _When I use an application, I want to be sure that there is well organized support in case oof emergency_

  ## User Stories

  -   connection

      > create an account
      > connect to the application
      > have user rights to see stuff
      > have admin rights to manage stuff

  -   member creation

      > create a new team member
      > delete a former team member
      > modify a team member

  -   manage releaser of the week

      > set a member as releaser candidate
      > unset a member as releaser candidate
      > find releaser
      > manually choose releaser
      > manually switch releaser
      > automatically update releaser

  -   manage release iteration

      > register iteration pattern
      > find current release number
      > automatically increment release number
      > manage exceptions

  -   manage support team leader of the day

      > set a member as support candidate
      > unset a member as support candidate
      > find support leader of the day
      > manually switch support leader
      > automatically update support leader

  -   help in release process
      > chase releaser during different release tasks
      > check release status
      > provide a wiki of the release steps
      > retrieve the wiki
      > be informed team of the current releaser / support via mail
      > be informed that I am the next releaser, support leader



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
