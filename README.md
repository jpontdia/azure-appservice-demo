# azure-eventhubs
*Microsoft Azure App Service Demo with Spring Boot 2.6.3. Deployment using maven plugin*

## Table of contents
1. [Objectives](#Objectives)
2. [Prerequisites](#prerequisites)
3. [Build and test the application](#build-and-test-the-application)
4. [Deployment to Azure](#deployment-to-azure)
5. [Recommended content](#recommended-content)

## Objectives
1. Create a Spring boot microservice and deploy it to Microsoft Azure as an App Service and using the maven azure-webapp-maven-plugin

## Prerequisites
* An Azure subscription
* Azure Cli installed and authenticated
* Java Development Kit (JDK) 11
* Apache Maven, version 3.8 or later.

## Build and test the application

Download the code and build your Spring Boot application with Maven and run it; for example:
~~~bash
mvn clean package -Dmaven.test.skip=true
mvn spring-boot:run
~~~

Once your application is running, you can use CURL to test the application, for example (using line continuation character for windows):
~~~bash
curl --location --request POST 'http://localhost:8080/creditlimit' ^
--header 'Content-Type: application/json' ^
--header 'charset: ISO-8859-1' ^
--data-raw '{
    "accountId": "4127778000004797",
    "amount": "1000000",
    "sourceSystem": "WebCustomer",
    "messageIdentifier": "137"
}'
~~~

You should see a similar json response in the client:
~~~json
{
   "updateRequest": {
      "accountId": "4127778000004797",
      "amount": "1000000",
      "sourceSystem": "WebCustomer",
      "messageIdentifier": "136"
   },
   "changeDate": "2022-01-27T10:15:43.0286947-06:00"
}
~~~

## Deployment to Azure

In the command prompt, run the Maven command below to configure the deployment section in pom.xml
~~~bash
mvn com.microsoft.azure:azure-webapp-maven-plugin:2.3.0:config
~~~

The command will prompt several values to configure your service, at the end you should receive a summary like this:
~~~bash
Please confirm webapp properties
Subscription Id : ********-****-****-****-************
AppName : azure-appservice-demo-*************
ResourceGroup : azure-appservice-demo-*************-rg
Region : centralus
PricingTier : F1
OS : Linux
Java : Java 11
Web server stack: Java SE
Deploy to slot : false
Confirm (Y/N) [Y]: y
[INFO] Saving configuration to pom.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:30 min
[INFO] Finished at: 2022-01-31T12:34:14-06:00
~~~

The configuration will be added in your pom.xml. With the next maven command We can deploy to Azure:
~~~bash
mvn package -DskipTests azure-webapp:deploy
~~~

You should receive a summary like this:
~~~bash
Auth type: AZURE_CLI
Default subscription: Azure subscription 1(********-****-****-****-************)
Username: *******.*****.*****@gmail.com
[INFO] Subscription: Azure subscription 1(********-****-****-****-************)
[INFO] Creating web app azure-appservice-demo-*************...
[INFO] Creating resource group(azure-appservice-demo-*************-rg) in region (centralus)...
[INFO] Successfully created resource group (azure-appservice-demo-*************-rg).
[INFO] Creating app service plan asp-azure-appservice-demo-1643653878085...
[INFO] Successfully created app service plan asp-azure-appservice-demo-**********
[INFO] Successfully created Web App azure-appservice-demo-1643653878085.
[INFO] Trying to deploy external resources to azure-appservice-demo-*************...
[INFO] Successfully deployed the resources to azure-appservice-demo-*************
[INFO] Trying to deploy artifact to azure-appservice-demo-*************...
[INFO] Deploying (C:\workspace\dev\poc\azure-appservice-demo\target\app.jar)[jar]  ...
[INFO] Successfully deployed the artifact to https://azure-appservice-demo-*************.azurewebsites.net
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:45 min
[INFO] Finished at: 2022-01-31T13:03:42-06:00
[INFO] ------------------------------------------------------------------------
~~~

Test the application as described in [Build and test the application](#build-and-test-the-application) changing URL of the service
from http://localhost:8080/creditlimit to https://azure-appservice-demo-*************.azurewebsites.net
(use the URL provided when the service was deployed).


## Recommended content
* [Quickstart: Create a Java app on Azure App Service](https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux)
* [Maven Plugin for Azure App Service](https://github.com/microsoft/azure-maven-plugins/blob/master/azure-webapp-maven-plugin/README.md)
* [App Service pricing](https://azure.microsoft.com/en-us/pricing/details/app-service/windows/)

  