DROP SCHEMA IF EXISTS Company;
CREATE DATABASE Company charset utf8;
USE Company;

DROP TABLE IF EXISTS BU;
CREATE TABLE BU(
BU_id smallint(5) AUTO_INCREMENT NOT NULL,
BU_name varchar(25) NOT NULL,
PRIMARY KEY (BU_id)
);

INSERT INTO BU(BU_name) VALUES ("Evolve"), ("Smart"), ("Gov"), ("Enterprise");

DROP TABLE IF EXISTS Project;
CREATE TABLE Project(
Project_id smallint(5) AUTO_INCREMENT NOT NULL,
Project_Name varchar(100) NOT NULL,
BU_id smallint(5) NOT NULL,
PRIMARY KEY (Project_id),
FOREIGN KEY (BU_id)
REFERENCES BU(BU_id)
);

INSERT INTO Project(Project_name, BU_id) VALUES ("HCM Team", 2), ("DFID", 4), ("Velocity Team", 1);

DROP TABLE IF EXISTS JobTitles;
CREATE TABLE JobTitles(
Employee_type_id smallint(5) AUTO_INCREMENT NOT NULL,
Job_title varchar(20),
PRIMARY KEY (Employee_type_id)
);

INSERT INTO JobTitles(job_title) VALUES ("HR"), ("Finance"), ("Sales"), ("Talent Manager");

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(
Employee_number smallint(5) AUTO_INCREMENT NOT NULL,
Employee_name varchar(20) NOT NULL,
Address varchar(50),
National_insurance_number varchar(10),
Bank_account_IBAN varchar(15) NOT NULL,
Starting_salary decimal(10, 2) NOT NULL,
Employee_type_id smallint(5) NOT NULL,
Commission_rate tinyint(3) DEFAULT 0,
Total_sales int(10) DEFAULT 0,
PRIMARY KEY (Employee_number),
FOREIGN KEY (Employee_type_id)
REFERENCES JobTitles(Employee_type_id)
);

INSERT INTO Employees(Employee_name, Address, National_insurance_number, Bank_account_IBAN, 
Starting_salary, Employee_type_id, Commission_rate, Total_sales) 
VALUES ("Clarke Kent", "17 Banjo Road, Belfast", "TM477828G", "IBN3488585555", 16500.75, 3, 20, 1500),
("Bill Clinton", "226 Jolly Road, Belfast", "TM557828G", "IBN3488585566", 16500.75, 3, 20, 2200);
INSERT INTO Employees(Employee_name, Address, National_insurance_number, Bank_account_IBAN, 
Starting_salary, Employee_type_id) VALUES 
("Billy Bob", "1a Bramblewood Court, Belfast", "TM667828G", "IBN3488585577", 16500.75, 2),
("Jane Doe", "121 Who Knows, Middleofnowhere", "TM777828G", "IBN3488585588", 16500.75, 2),
("Louis Lane", "34 Nightlife, Belfast", "TM887828G", "IBN3488585599", 21500.75, 4),
("Jenny Lane", "3 Upper Crescent, Belfast", "TM555528G", "IBN8888585599", 21500.75, 4),
("Jimmy Joe", "54 Ukelele Lane, Belfast", "TM997828G", "IBN3488589999", 21500.75, 1);


DROP TABLE IF EXISTS EmployeeProject;
CREATE TABLE EmployeeProject(
Project_id smallint(5),
Employee_number smallint(5),
PRIMARY KEY (Project_id, Employee_number),
FOREIGN KEY (Project_id)
REFERENCES Project(Project_id),
FOREIGN KEY (Employee_number)
REFERENCES Employees(Employee_number)
);

INSERT INTO EmployeeProject(Project_id, Employee_number) VALUES (2, 1), (2, 2), (1, 3), (3, 4), (2, 5), (3, 6), (1, 7);


DROP TABLE IF EXISTS Login;
CREATE TABLE Login(
Username varchar(30) NOT NULL,
Password varchar(30) NOT NULL,
Employee_number smallint(5) NOT NULL,
Employee_type_id smallint(5) NOT NULL,
PRIMARY KEY (Username, Password),
FOREIGN KEY (Employee_number)
REFERENCES Employees(Employee_number),
FOREIGN KEY (Employee_type_id)
REFERENCES JobTitles(Employee_type_id)
);

INSERT INTO Login(Username, Password, Employee_number, Employee_type_id) 
VALUES ("bclinton", "password", 2, 3),
("bbob", "password", 3, 2),
("jjoe", "password", 7, 1),
("jlane", "password", 6, 4);


/*
DROP TABLE IF EXISTS Sales;
CREATE TABLE Sales(
Sales_employee_number smallint(5) NOT NULL,
Commission_rate tinyint(3) DEFAULT 0,
Total_sales int(10) DEFAULT 0,
PRIMARY KEY (Sales_employee_number),
FOREIGN KEY (Sales_employee_number)
REFERENCES Employees(Employee_number)
);
*/