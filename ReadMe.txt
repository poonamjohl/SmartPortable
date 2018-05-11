
Implementation Details:

This application is built using Servlets, html, CSS, Mysql, MongoDb.
-There are three kind of users 1.Client 2. Sales Manager 3. Sales Person
-Users can place, cancel and update orders.
-Manager can add, delete and update products.
-Sales Person can modify, update or place order for client.
-When SmartPortable app is started by Tomcat server, it will display any 2 Tweets/lines on homepage along with links to the individual products that SmartPortable app can match of the offered deals by BestBuy 
-All user transactions, info and products are stored in Mysql database.
-Review are stored in MongoDb
-When app server restarts, products table in database will be repopulated based on product.xml file

To run this application:
1.create a csj1 folder and put all the files in it. Then add this folder in C:\apache-tomcat-7.0.34\webapps
2.Make sure Apache Tomcat, mySQL and Mongo db are up and running.
3.Go to browser, type localhost/csj1/SmartPortable, it will display the home page.
4.Navigate to different pages as described in demo.



