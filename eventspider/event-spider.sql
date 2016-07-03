USE eventspider;

CREATE TABLE IF NOT EXISTS Users (
user_id INT AUTO_INCREMENT,
email VARCHAR(255) UNIQUE,
password VARCHAR(32) NOT NULL,
first_name VARCHAR(255),
last_name VARCHAR(255),
role VARCHAR(30) DEFAULT 'Member',
PRIMARY KEY (user_id)
);