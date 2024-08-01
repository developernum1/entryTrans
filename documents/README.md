## Project Setup Instruction

**Note: Software Requirements are as following !**
**Please make sure they are added to you environment varialbes and accessible in Command Prompt window!**
- Java 21.0.2
- Node.js v18.20.4
- npm 10.7.0
- MySQL 8.0.38


## 1. Download and unzip fyg.zip. e.g., to c:\fyg


## 2. Schema and Database setup
# Connect to MySQL server in a mysql client, such as MySQL workbench, with your root user
# Run following script to create Schema, tables, and several sample data
.\fyg\database\Entry SQL script.sql


## 3. Start Backend Application 
# Open a Command Prompt as Administrator, and navigate to directory: 
.\fyg\backend\target
# Run following command to start backend application
java -jar entrydemo-0.0.1-SNAPSHOT.jar

# Use URL [http://localhost:8080/api/transactions] with your browser to make sure it start successfully.


## 4. Start Frontend Application 
# Open a Command Prompt as Administrator, and navigate to directory: 
.\fyg\frontend
# Run following command to install packages required 
npm install

# Run following command to start frontend application, and wait a little while......
npm start

# A successfull message will show up, and a browser will be opened with Transaction Lists
# You can also use URL [http://localhost:3000] to access the application


## 5. Run Mock Data Generator Application 
***Run following script in mysql client, such as MySQL workbench, to clear dataset!**
.\fyg\mockgenerator\target\Clear Data for DataMock.sql

# Open a Command Prompt as Administrator, and navigate to directory: 
.\fyg\mockgenerator\target
# Run following command to generate mock dataset
java -jar mockgenerator-0.0.1-SNAPSHOT.jar  10000

**Note: the last number in the command line is how many records you want to generate.**
**You can start from a small number e.g. 10000,  then try bigger ones!**

## All source code are included in the unzipped folder, including backend, frontend, and Mock Data Generator!
## You may build all applications from scratch based on source code. Just have a try!!!
