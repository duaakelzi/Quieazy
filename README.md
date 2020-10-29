# Quieazy
Software Project Quiz App


-Import the projects "Quieasy" and "QuieasyServer" to Eclipse.

-To set the port for the server Right Click on the project "QuieasyServer" then:
 >Run As>Run Configurations

Now make sure "QuieasyServer" (embedded class file) is selected on the left.
Click on the "Arguments" tab and enter 3000 in the "Program arguments" field.
Click "Apply" and wait. "Close" when done.

Run the app "QieasyServer". You should see "Quieasy server running on port 3000" on the console output. Exit the app.

-Copy the folder "SQLite" to the root directory on your PC so that it is located at C:\SQLite

-Add the "sqlite-jdbc" driver to QuieasyServer Build Path as follows.
 Right Click on QuieasyServer->Build Path->Configure Build Path...->Add External Jar
 A file browser will open.
 Navigate to C:\SQLite\sqlite-jdbc-3.32.3.2 and select the file.
 Now click on "Apply and Close" and wait.

>> THAT IS ALL FOR THE SETUP <<

Now you can test the apps. Run "QuieasyServer" first.
While the server is up and running start "Quieasy". You should see the "Log in" dialog.

Try to login as username: user@example.com
                           password: 123

Other login attempts should fail.