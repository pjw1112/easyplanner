show databases;

use jwpdev;

show tables;

-- Grade (회원 등급) 테이블 생성
CREATE TABLE IF NOT EXISTS Grade (
U_grade INT NOT NULL AUTO_INCREMENT,
U_grade_name VARCHAR(20) NOT NULL,
PRIMARY KEY (U_grade)
);

-- Grade (회원 등급) 테이블 에 등급 4개 넣기
insert into Grade values
(1,'admin'),
(2,'gold'),
(3,'silver'),
(4,'bronze');


-- Users (회원 정보) 테이블 생성
CREATE TABLE IF NOT EXISTS Users (
U_index INT NOT NULL auto_increment,
U_id VARCHAR(50) NOT NULL,
U_pass VARCHAR(50) not NULL,
U_email VARCHAR(50) not NULL,
U_birth VARCHAR(50) not NULL,
U_join_date VARCHAR(50) not NULL,
U_grade INT NOT NULL,
PRIMARY KEY (U_index),
FOREIGN KEY (U_grade) REFERENCES Grade (U_grade)
);


-- Schedule (일정) 테이블 생성
CREATE TABLE IF NOT EXISTS Schedule (
S_index int not null auto_increment,
start_date VARCHAR(50) NOT NULL,
end_date VARCHAR(50) NOT NULL,
content VARCHAR(500) not NULL,
create_date VARCHAR(50) not NULL,
ip VARCHAR(50) not NULL,
U_index INT NOT NULL,
PRIMARY KEY (S_index),
FOREIGN KEY (U_index) REFERENCES Users (U_index)
);


-- Inquire (문의) 테이블 생성
CREATE TABLE IF NOT EXISTS Inquire (
Q_index INT NOT NULL AUTO_INCREMENT,
q_date VARCHAR(50) NOT NULL,
q_content text not NULL,
U_index INT NOT NULL,
PRIMARY KEY (q_index),
FOREIGN KEY (U_index) REFERENCES Users (U_index)
);


-- Answer (답변) 테이블 생성
CREATE TABLE IF NOT EXISTS Answer (
A_index INT NOT NULL AUTO_INCREMENT,
a_date VARCHAR(50) not NULL,
a_content text not NULL,
Q_index INT NOT NULL,
PRIMARY KEY (A_index),
FOREIGN KEY (Q_index) REFERENCES Inquire (Q_index)
);