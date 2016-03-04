# MyGWT [![Build Status](https://travis-ci.org/FedirChuiko/MyGWT.svg?branch=master)](https://travis-ci.org/FedirChuiko/MyGWT)

###Installation
Build with maven (mvn install or mvn package in project root directory), and then deploy to web app server.

###Used technologies and frameworks:
GWT, spring4gwt (lib for easy gwt-spring integration)
Spring Data JPA repositories (easy data access for small DB ),
H2 Database (Java based DB, lightweight and you don't have to install it separately.)
(As for now h2 is in memory mode, but you can persist data on disk by adjusting persistance.xml file.),
Maven ,JUnit (I started develoment by writing test, but service/server layer is so thin, that tests looks really ridiculous,
and I am really not familiar with GWT layer testing. That's why application have only 3 unit test.)
