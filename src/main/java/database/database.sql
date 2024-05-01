create database StudentAccommodation;

use StudentAccommodation;
CREATE TABLE USERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    role VARCHAR(10) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    passwordHash VARCHAR(300) NOT NULL,
    salt VARCHAR(255) NOT NULL
);

CREATE TABLE ROOM (
    roomID INT AUTO_INCREMENT PRIMARY KEY,
    roomType VARCHAR(1) NOT NULL,
    capacity INT NOT NULL,
    floor INT NOT NULL
);

CREATE TABLE APPLICATION (
    applicationID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT,
    applicationDate DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    faculty VARCHAR(255) NOT NULL,
    yearOfStudies INT NOT NULL,
    major VARCHAR(100) NOT NULL,
    averageGrade DECIMAL(4, 2) NOT NULL, 
    FOREIGN KEY (userID) REFERENCES USERS(id)
);

CREATE TABLE ALLOCATION (
    allocationID INT AUTO_INCREMENT PRIMARY KEY,
    applicationID INT,
    roomID INT,
    allocationDate DATE NOT NULL,
    userID int not null,  -- admin 
    FOREIGN KEY (applicationID) REFERENCES APPLICATION(applicationID),
    FOREIGN KEY (roomID) REFERENCES ROOM(roomID),
	FOREIGN KEY (userID) REFERENCES Users(id)
);


-- Inserting data into USERS table
INSERT INTO USERS (firstname, lastName, gender, role, email, passwordHash, salt)
VALUES 
    ('John', 'Doe', 'M', 'student', 'john.doe@example.com', 'passwordhash1', 'salt1'),
    ('Jane', 'Smith', 'F', 'student', 'jane.smith@example.com', 'passwordhash2', 'salt2'),
    ('Admin', 'Admin', 'M', 'admin', 'admin@example.com', 'adminpasswordhash', 'adminsalt');

-- Inserting data into ROOM table
INSERT INTO ROOM (roomType, capacity, floor)
VALUES 
    ('F', 2, 1),
    ('F', 1, 2),
    ('M', 2, 3);

-- Inserting data into APPLICATION table
INSERT INTO APPLICATION (userID, applicationDate, status, faculty, yearOfStudies, major, averageGrade)
VALUES 
    (1, '2024-04-28', 'pending', 'Engineering', 2, 'Computer Science', 3.75),
    (2, '2024-04-27', 'approved', 'Medicine', 3, 'Biology', 3.85),
    (1, '2024-04-26', 'pending', 'Business', 1, 'Finance', 3.6);

-- Inserting data into ALLOCATION table
INSERT INTO ALLOCATION (applicationID, roomID, allocationDate, userID)
VALUES 
    (1, 1, '2024-04-29', 3),
    (2, 2, '2024-04-29', 3);

