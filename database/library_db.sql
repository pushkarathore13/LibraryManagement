-- Create Database
CREATE DATABASE IF NOT EXISTS library_db;

USE library_db;

-- Drop Tables if already exist
DROP TABLE IF EXISTS login_history;
DROP TABLE IF EXISTS memberships;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS shifts;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS admins;

-- Students Table
CREATE TABLE students
(
    student_id INT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(100) UNIQUE NOT NULL,

    mobile VARCHAR(15) NOT NULL,

    gender VARCHAR(20) NOT NULL,

    occupation VARCHAR(100) NOT NULL,

    aadhaar_id VARCHAR(20) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    status VARCHAR(20) DEFAULT 'PENDING',

    membership_start DATE,

    membership_end DATE
);

-- Admin Table
CREATE TABLE admins
(
    admin_id INT PRIMARY KEY AUTO_INCREMENT,

    email VARCHAR(100) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL
);

-- Shift Table
CREATE TABLE shifts
(
    shift_id INT PRIMARY KEY AUTO_INCREMENT,

    shift_name VARCHAR(50),

    start_time TIME,

    end_time TIME
);

-- Seats Table
CREATE TABLE seats
(
    seat_id INT PRIMARY KEY AUTO_INCREMENT,

    floor_no INT,

    seat_number VARCHAR(20),

    status VARCHAR(20) DEFAULT 'AVAILABLE',

    shift_id INT,

    FOREIGN KEY (shift_id)
    REFERENCES shifts(shift_id)
);

-- Membership Table
CREATE TABLE memberships
(
    membership_id INT PRIMARY KEY AUTO_INCREMENT,

    student_id INT,

    seat_id INT,

    shift_id INT,

    start_date DATE,

    end_date DATE,

    FOREIGN KEY(student_id)
    REFERENCES students(student_id),

    FOREIGN KEY(seat_id)
    REFERENCES seats(seat_id),

    FOREIGN KEY(shift_id)
    REFERENCES shifts(shift_id)
);

-- Login History Table
CREATE TABLE login_history
(
    history_id INT PRIMARY KEY AUTO_INCREMENT,

    email VARCHAR(100),

    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

---------------------------------------------------
-- Default Admin
---------------------------------------------------

INSERT INTO admins(email,password)
VALUES
(
'admin@gmail.com',
'Admin@123'
);

---------------------------------------------------
-- Shifts
---------------------------------------------------

INSERT INTO shifts
(
shift_name,
start_time,
end_time
)

VALUES

(
'Morning',
'06:00:00',
'12:00:00'
),

(
'Afternoon',
'12:00:00',
'17:00:00'
),

(
'Evening',
'17:00:00',
'22:00:00'
),

(
'Whole Day',
'06:00:00',
'22:00:00'
);

---------------------------------------------------
-- Floor 1 Seats
---------------------------------------------------

INSERT INTO seats
(
floor_no,
seat_number,
status,
shift_id
)

VALUES

(1,'F1-S1','AVAILABLE',1),
(1,'F1-S2','AVAILABLE',2),
(1,'F1-S3','AVAILABLE',3),
(1,'F1-S4','AVAILABLE',4);

---------------------------------------------------
-- Floor 2 Seats
---------------------------------------------------

INSERT INTO seats
(
floor_no,
seat_number,
status,
shift_id
)

VALUES

(2,'F2-S1','AVAILABLE',1),
(2,'F2-S2','AVAILABLE',2),
(2,'F2-S3','AVAILABLE',3),
(2,'F2-S4','AVAILABLE',4);

---------------------------------------------------
-- Check Data
---------------------------------------------------

SELECT * FROM admins;

SELECT * FROM shifts;

SELECT * FROM seats;

SELECT * FROM students;