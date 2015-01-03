# Grep - Find a word in a group of files

## How to run the tool

This simple project will look through all the files contained in a folder for a specific word and output the files that contain it. The command line arguments are as follow:

`grep.sh <word> <folder>`

## How to setup

The only exernal requirement for this project is Java. It has been tested with the Java 7 JDK. This dependency should be installed already with the latest Ubuntu, but if it's not, the command to install it is: 

`sudo apt-get install openjdk-7-jdk`

To run from the source, activator can be used, for which a launcher is included. To run project from activator, the following command can be used:

`activator "run <args>"` 

Note that if activator is not installed globally on *nix, activator needs to be prefixed with a leading `./`

Additionally, a runnable jar can be created by using:

`activator oneJar` 

This command will create a jar inside the project working directory: `target/scala-2.11/grep_2.11-1.0-one-jar.jar`. This jar can be run with java directly, such as:

`java -jar target/scala-2.11/grep_2.11-1.0-one-jar.jar <args>`

The unit tests can be executed by running:

`activator test`
