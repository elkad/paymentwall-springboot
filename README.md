Paymentwall Sample using Spring Boot
=========================================
This is sample project how to implement Paymentwall java Library to spring boot web application,
if you see build.gradle it will show you that it's using spring boot 2.1.4 starter web, using freemarker as template engine, and Paymentwall Java SDK.

Prerequisites:
==============
*	Java JDK-11 or higher
*	Gradle 5.2.1 or higher

Usage
=====
to run this sample, please at first crete account and register your project to https://www.paymentwall.com, get your app configuration and then change Paymentwall configuration in src/main/resource/application.properties of your project

		
    brick.public.key=xxxxxxxxxxxxxxxxxxProject Keyxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    brick.private.key=xxxxxxxxxxxxxxxxxxSecret Keyxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		

Then run it using maven :

		
	gradle bootRun
		

Test in your browser : 

		
	http://localhost:8080/payment/default
		

## Deploying to Heroku

```
$ heroku create
$ git push heroku master
$ heroku open
```
or

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)
