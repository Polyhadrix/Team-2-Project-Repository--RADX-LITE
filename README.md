# Team-A-Project-Repository
Honestly... I don't know what "dank" means in the context of internet jargon.

UPDATE: NOW WindowBuilder COMPATIBLE! =D 

Added `PatientPortal.java` which is a reworking of `PatientReg.java` that utilizes the java.swing package's "GridBagLayout" layout manager for an elastic layout and also introduces many helper functions to reduce code repetition.

Note: It is still a work in progress. I have yet to add the LayeredPanels effect present in the original, and still need to implement the functions for improved database communication. I hope to post an update with those tomorrow noon (4/21/2020).

# How to Run

## Start a Local Server
1. (Install and) Run XAMPP on your computer.
2. (In XAMPP) Click 'Start' on the `Apache` and `MySQL` Modules.

## Create the Database
1. (In XAMPP) On the `MySQL` module line, click `Admin`. This should open up your default browser to `phpMyAdmin`.

Option A: Copy-Paste Method
A1. (On another `gitHub` tab) Open the file `ris.sql` and copy the raw code.
A2. (In the `phpMyAdmin` tab) Click the `SQL` tab on the top menu bar.
A3. Paste the code from gitHub into the text area.
A4. Hit the 'Go' button on the bottom right.

Option B: Download-Import Method (if you already downloaded the entire git Branch in a zip file)
B1. (In `phpMyAdmin`) Click the 'Import' button on the top menu bar.
B2. Click the "Choose File".
B3. Click the `ris.sql` file.
B4. Scroll down and click the 'Go' button.

3. (In `phpMyAdmin`) Click the lefthand panel below the phpMyAdmin logo, click the green refresh arrow icon.
4. The Database `ris` should now appear near the bottom of your database list on the lefthand panel.
(You can explore it to see the database's tables.)

## Start the Project in Eclipse
1. In Eclipse, select your workspace, add a new project, and add the `PatientPortal.java` file to it.
(If you require more assistance in this part of the process, ask me.)

## JDBC [Java DataBase Connector] Driver
1. Go to https://dev.mysql.com/downloads/connector/j/
2. In the dropdown select `Platform Independent`
3. Download `mysql-connector-java-8.0.19.zip`
4. Unzip the folder somewhere stable (not your 'Downloads' folder where it may get deleted accidentally) -.-
(I put it in my Eclipse directory.)
5. Open Eclipse
6. In the lefthand panel (the "Project Explorer" panel), right-click the Project directory and go to "Build Path >" (third the way down) and Click "Add External Archives".
7. Browse into the unzipped `mysql-connector-java-8.0.19` folder and open the JAR file. 
8. You have just installed the official JDBC driver. Now eclipse can talk to databases.

You should now be able to edit and test the Java file as expected. 
(Remember: XAMPP needs to still be running in order for your server/database to exist.)
