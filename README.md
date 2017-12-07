There are 2 classes Student and Tutor.
If someone logs in as Admin then he will be considered as Tutor
Student -> 1:1 mapping with group
Group 1:n mapping with students and 1:n mapping with TutorialSessions

There is a class called security filter which can intercept and check if the user is logged in or not ! Making logging in 
mandatory.

I have removed the group logic we may have to rewrite again if we are going with similar structure.

Keep the business logic and object manipulation in servlet class itself / Ideally should crete a business layer !

Only do static operations in jsp and make copies of jsp for different tasks ! Single Reposibility.

Let me know what do you guys think of it ?


# group-4-9-attendance_tracking_system
TUM SE project: Java applet tracking attendance

## Get started

(from ex sheet 2)

### import to eclipse

Clone the project, navigate to its ```final``` directory

Run ```mvn eclipse:eclipse``` 

Open eclipse, *File > Import Existing Project* and import *textbook*

Enjoy

### launch server

```cd this/final```

\[```mvn clear```\]

```mvn appengine:devserver```

Open in browser http://localhost:8080 (or http://127.0.0.1:8080)

### play with it

Log in as admin, create some groups, edit them by joining them and then changing the fields down under. Then login as non-admin.
