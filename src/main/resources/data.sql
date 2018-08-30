DELETE FROM User;
DELETE FROM Person;
DELETE FROM Expenses;

INSERT INTO User (id,username,password,fullname) VALUES(1,'jagadish', 'jagadish','Jagadish Anala');
INSERT INTO User (id,username,password,fullname) VALUES(2,'admin', 'admin','Admin');

INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(1,'Jagadish', 'A', '+11 1111 11 11', 'Jagadish.A@expenses.com',CURRENT_TIMESTAMP);
INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(2,'Anirudh', 'R', '+22 2222 22 22', 'Anirudh.R@expenses.com',CURRENT_TIMESTAMP);
INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(3, 'Kirthi', 'S', '+33 3333 33 33', 'Kirthi.S@expenses.com',CURRENT_TIMESTAMP);
INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(4, 'Rahul', 'B', '+44 4444 44 44', 'Rahul.Bx@expenses.com',CURRENT_TIMESTAMP);
INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(5, 'Shaamili', 'M', '+55 5555 55 55', 'Shaamili.M@expenses.com',CURRENT_TIMESTAMP);
INSERT INTO Person (id,first_name,last_name,phone,email,birth_date) VALUES(6, 'Shekhar', 'K', '+66 6666 66 66', 'Shekhar.K@expenses.com',CURRENT_TIMESTAMP);

INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(1,'For Lunch', 1,CURRENT_TIMESTAMP,560.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(2,'Entertainment Park Tickets', 2,CURRENT_TIMESTAMP,1800.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(3,'For Breakfast', 6,CURRENT_TIMESTAMP,326.50);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(4,'Train Tickets To Delhi', 5, CURRENT_TIMESTAMP,5200.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(5,'For Dinner', 4,CURRENT_TIMESTAMP,560.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(6,'For Hotel Booking', 3,CURRENT_TIMESTAMP,10500.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(7,'Delhi Local Bus Pass', 1,CURRENT_TIMESTAMP,500.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(8,'National Zoological Park In Delhi', 1,CURRENT_TIMESTAMP,1200.00);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(9,'Delhi Metro Train Pass', 4,CURRENT_TIMESTAMP,1800);
INSERT INTO Expenses (id,description,spentby,spentdate,amount) VALUES(10,'Miscellaneous', 4,CURRENT_TIMESTAMP,3990);
