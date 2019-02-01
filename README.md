# WebContactSystem
A simple demo of Web Contact System - my attempt to display data from local databases in dynamic webpage through Java servlet.

## System Design:
![alt text](https://user-images.githubusercontent.com/42085040/52098431-ed6d4d00-259c-11e9-9526-ebfd1c78a3c7.png)

## How it looks like
All pages are dynamic pages except Add Contact Page - [addContact.html]

### Main page - shows all contacts:

![alt text](https://user-images.githubusercontent.com/42085040/52097731-2c4dd380-259a-11e9-84a1-8158aec74d3b.png)
 
### Add Contact Page:
![alt text](https://user-images.githubusercontent.com/42085040/52097746-3a9bef80-259a-11e9-9838-606ce67b3526.png)

### Edit Contact Page:
![alt text](https://user-images.githubusercontent.com/42085040/52097758-45568480-259a-11e9-865b-c084867723e6.png)
  
# Step by Step how to set it up:
## Step 1 - set up:
  1. MyEclipse 2014: IDE tool of your choice.. I have MyEclipse 2014 set up on my computer already
  2. Sublime Text: I use this to write addContact.html, ContactSystem.sql, and db.properties 
  3. MySQL
  4. Tomcat
  5. JavaSE

  
## Step 2 - create a table in the database and set up property file
SQL for your database, you can change the database name and information inside, but if you do that, you neeed to
change your property file and Java code too. 

ContactSystem.sql
```
create database webcontactsystem;

use webcontactsystem;

create table Contact (
	id int not null primary key auto_increment,
	firstName char(20) not null,
	lastName char(20) not null,
	gender char(1) not null,
	age tinyint not null,
	tel char(30),
	email char(30)
);
```

db.properties
```
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/webcontactsystem?useSSL=true
user=root
password=123456
```

## Step 3: Set up folder Tree 
![alt text](https://user-images.githubusercontent.com/42085040/52098536-610f5a00-259d-11e9-9a69-2329bd6f35e8.png)

## Step 3: You can start Coding now
You need to import the following libs:
beanutils
beanutils logging
mysql connector

My order of coding each files:
1. Contact.java
2. JDBCUtil.java
3. BaseDao.java
4. ContactDao.java
5. ContactDaoImplements.java
6. addContact.html
7. web.xml - MyEclipse 2014 generated it for me automatically, but you can write it yourself if you don't have one
8. AddContactServlet.java
9. ListContactServlet.java
10. DeleteContactServlet.java
11. QueryContactServlet.java
12. UpdateContactServlet.java

now you are done and you can start testing

## What to be mindful about..
1. database table field name to be consistant with your code.
2. do not miss a space when generating html in the servlet
    I made a mistake that missed a space here:
    ```
    html += "<form action=UpdateContactServlet?id=" + contact.getId() + "method=POST>";
    ```
    right before method=POST, it need a space, otherwise, it will not submit to UpdateContactServlet
    correct coding:
    ```
    html += "<form action=UpdateContactServlet?id=" + contact.getId() + " method=POST>";
    ```
    
 





































