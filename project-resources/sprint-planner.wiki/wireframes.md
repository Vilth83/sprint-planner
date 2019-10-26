This page contains samples of web app navigation.
Each  main scenario will be presented with :
- Context : what the user want to do, and what we assume that have been done
- Releated User Stories : User Stories the wireframes answer to
- sitemaps : the path on the web app
- wireframes : the screens visited by the user to reach his goal

## Account Creation

### Context
this scenario is about a guest connecting into the app and creating an account.

We assume that :
- the guest has no account and want to create one

Steps:
  * connect to the homepage
  * click on the "register" button
  * fill the register form
  * validate the registration

### Related User Stories
Related User Stories :
- [As a guest, I want to access the app without login, so that I can have main informations without login](https://github.com/Vilth83/sprint-planner/issues/24)
- [As a guest, I want to create an account, so that I can access user restricted features](https://github.com/Vilth83/sprint-planner/issues/21)
- [As a guest, I want to find current support, so that I can know who to call in case of emergency](https://github.com/Vilth83/sprint-planner/issues/27)
-  [As a guest, I want to find current releaser, so that I can know who is in charge of this weeks release](https://github.com/Vilth83/sprint-planner/issues/9)

### Sitemap
![registration sitemap](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/sitemaps/scenarii/account-creation.png)

### Wireframes
- the guest user connect to the homepage.

![homepage](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/wireframes/account-creation/guest-homepage.png)

- the guest user has clicked on the "register" button

![registerpage](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/wireframes/account-creation/registration.png)

## Login
### Context
this scenario represent the log in to the application. There are two differents cases :
- the guest becomes a user
- the guest becomes an admin

We assume that :
- an account is already existing with either user or admin role
- the guest as already reached the home page and clicked on the log in button

Steps:
- fill the modal form
- click on the "log in" button (confirming the form)
- reach the dedicated homepage

### Releated User Stories
- [As a user, I want to sign in the application, So that I can access user specific functionalities](https://github.com/Vilth83/sprint-planner/issues/22)
- [As an admin, I want to login, So that I can access admin restricted features](https://github.com/Vilth83/sprint-planner/issues/23)
### sitemaps
#### as an admin
![login form](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/sitemaps/scenarii/admin-login.png)
#### as a user
![login form](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/sitemaps/scenarii/user-login.png)

### Wireframes
- the guest fills the log in form
![login form](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/wireframes/login/login-page.png)
- the guest becomes a user and is redirected to user homepage
![user homepage](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/wireframes/login/user-home.png)

- the guest becomes an admin and is redirected to the admin homepage
![admin homepage](https://github.com/Vilth83/sprint-planner/blob/master/project-resources/wireframes/login/admin-home.png)
