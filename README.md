# Enterprise Аpplication Аuction
The project is presented as web services based on JAX-WS technology and has a multi-layer architecture. Integration layer is implemented in accordance JPA specification (Eclipselink). Business logic layer uses the libraries Quartz. The project used Apache Log4j for logging project errors and events.

 Enterprise application auction include three project:
	- web dynamic project Auction (web service);
	- java project ClientAuction (client);
	- vaadin 6 project AuctionUIApp(user interface).

The requirements:
 - Java: jdk1.8.0_20, jre1.8.0_40;
 - PostgreSQL 9.3;
 - apache-tomcat-8.0.20.

Installation:

1. Download project:
git clone https://github.com/marichka-shulga/Enterprise-application-auction.git

2. Create data base:
Create a database "auction” in PostgreSQL and execute SQL code Enterprise-application-auction/Auction/dbSchema/dbModelNew.sql to create tables.
You can also download the sample data from auction.backup.

3. Build:
ant -f common-build.xml
As you add additional libraries is not necessary, as they are inside the projects.

4. Deploy:
After building project Auction archived in 
Enterprise-application-auction/Auction/build/lib/AuctionService.war. 
After building project AuctionUIApp archived in 
Enterprise-application-auction/AuctionUIApp/build/lib/AuctionUI.war.
ClientAuction archived in the jar file and goes to the project AuctionUIApp as a library. 
Archives AuctionUI.war and AuctionService.war need to put in the folder of your server apache-tomcat-8.0.20/webapps/ and run Apache Tomcat, it will automatically run the war archives.
