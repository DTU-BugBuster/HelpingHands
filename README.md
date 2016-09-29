# HelpingHands
<img src="/app/src/main/res/drawable-xxhdpi/hhicon.png" width="150" height="150"> 

Android App for helping women in danger by sending danger notifications to nominated contacts

###### This project was developed by a team of six people (including me) for our college's final semester Design Lab. (College : Institue of Engineering & Management , Kolkata)

### Problem Statement and Solution

The Woman's safety app will address the following concerns of women :
* When in danger, it's not very convenient and safe to call for help over the phone
* When in danger, tracing the victim for recovery is difficult without any definite knowledge of her locations
* The app used by the victim might be stopped by the criminal.

The solutions that we intend to provide are:
* When the mobile is shaken and the acceleration of the device is greater than the threshold value , an email notification is sent to prenominated contacts along with her location details.
* A sms notification is sent to prenominated contacts along with her location details seeking for help.
* The user would be able to set a secret PIN to prevent the stopping of sending notifications if the criminal snatches away the phone.

### Source Code
The Java files are under /src/com/helpinghands which conatisn three directories :
* boundary - contains classes that perform the GUI interaction
* control - contains classes taht perform logical operations and controls program flow
* entity - contains classes taht performs database operations and classes like "Contacts.java" that define an entity

### Docs
The /docs folder contain the SRS, QA Plan , Project Plan, Gantt Chart, Work Breakdown Structure document

To view our project wiki (as a pdf ) please check the INKAMP_Main - DesignLab.pdf file.

### Installation
The HelpingHands.apk file under /bin is the APK file which can be trasnferred to any Android phone and installed by clicking it and following the instructions on screen
Before installing, please turn ON "Enable Third Party Apps installation" permission through the phone's settings menu

### Contact
For providing feedback, improvement suggestions and pointing out bugs mail me at aloygupta1993@gmail.com
