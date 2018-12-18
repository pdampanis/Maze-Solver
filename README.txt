
Author: Panagiotis Dampanis

This program uses the breadth first algorithm to find a potential solution for a given maze.
If it finds one, it will print the maze along with the solution path.

How to run
=========================
This is a maven project that packages the code and produces a jar.
To execute this provide the jar file along with a file containing a maze.

In order to build the project run:
mvn clean package

In order to run it:
java -jar target/panos-dampanis-maze-challenge.jar src/Samples/medium_input.txt
