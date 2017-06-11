This README file explains how to use the Vote System program.

## ---- WHAT IS IT? ----

A vote counting and results generating system to run as a web service with a HTTP RESTful api.



## ---- BUILD AND RUN ON THE COMMAND LINE ----

To build and run a project artifact, you can type the following:

~~~
$ mvn package

$ java -jar target/my-vote-system-0.1.0.jar

$ mvn spring-boot:run
~~~


There is an application properties file.

Currently the only property defined is the port in which the application runs.



## ---- HOW IT WORKS ----

To vote:

Send a GET request, providing in the URL the voter name and the candidate name.


Example for a voter voter Y to vote in candidate B:

~~~
http://localhost:8090/vote?candidate=B&voter=voterY
~~~


After the GET request a message is displayed indicating if the vote was successful

and shows the number of votes of each candidate so far. For example:

~~~
Your vote was successfully registered {A=0, B=1, C=0, D=0}
~~~

To retrieve the winner:

Send a GET request to the following URL

~~~
http://localhost:8090/result
~~~

This will display the name of the candidate with more votes (the winner), for example:

B



## ---- TEST ----

To test the code run the application, use:

From the browser:

~~~
http://localhost:8090/vote?candidate=B&voter=voterB

http://localhost:8090/result
~~~

From the command line:

~~~
curl (or wget) http://localhost:8090/vote?candidate=B&voter=voterB
~~~


## ---- ASSUMPTIONS ----

Voters and Candidates identified only by name:

It is assumed that candidates and users are fully identified only by name.


There is no authentication:

The voters are all registered and do not need to authenticate to vote.


GET requests:

To vote and to get the winner, a GET request must be performed.


Information in the GET URL:

The voters and candidate name are provided in the URL of the GET request.


No persistence:

All votes are kept in memory while the program is running.

A more complex system would have persistence in a database.


Candidates draw:

If two candidates are tied, only one is returned as the winner. This is done randomly.
