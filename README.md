#RADIUS Server Authenticator

This is simple RADIUS Server authenticator with **RadAuthenticator** class with **authenticate** method.

All the parameters inside the **AuthRequest** are Mandatory.

Used test container with *freeradius/freeradius-server:latest*.

The following scenarios are validated

	1. user name with valid password
	2. user name with invalid password
	3. wrong RADIUS server configuration

###### To Build ######
Built the project using 

`mvn clean install`

##### Prerequisite #####
This requires docker to be installed and stable internet connection to pull the *freeradius/freeradius-server* image to execute tests successfully.

Ref:

[freeradius-server](https://hub.docker.com/r/freeradius/freeradius-server)

