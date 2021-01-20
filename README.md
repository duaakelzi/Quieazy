# Quieazy
Software Project Quiz App

### Installation guide for Intellij

### Step 1: Import project
The easiest is to import the project from git. To do so go to 
_File > New > Project from Version Control_. Enter the URL: https://github.com/duaakelzi/Quieazy. The project should be imported with Quieasy and QuieasyServer modules under Quieazy project folder.

### Step 2: Dependencies
The project needs the following dependencies: 
- **hibernate 5.4.*** and **mysql-connector 8.0.*** for the server,
- **javafx 15.0.*** and **junit** (4) for the client.

To add external libraries, go to _File > Project Structure > Libraries_. Click on _"+" -> Java_ and select the jars from your local PC. After modules were selected, make sure you _Apply_ your changes.

### Step 3: Miscellaneous
Make sure you gitignore the **.idea/*** ,  ***.log** ,   ***.class** files.

The launching files are: **Quieasy>src>application>Quieasy** for client and **QuieasyServer>src>application>QuieasyServer** for the server. To set the port for the server, go to its run configurations and set it to 3000.


> THAT IS ALL FOR THE SETUP <

### Step 4: Run
Now you can test the apps. To start, run the app "QuieasyServer" first. You should see "Quieasy server running on port 3000" on the console output. Then you can start the "Quieasy" app. You should see the "Log in" dialog.


Try to login as username: user@mail.com
                           password: 123

Other login attempts should fail.
