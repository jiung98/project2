CREATE DATABASE project2;

USE project2;

-- 회원 엔터티
CREATE TABLE project2.members
(
   userid   varchar(100) PRIMARY KEY,
   userpwd   varchar(100) NOT NULL,
   email      varchar(100) NOT NULL,
   useradd   varchar(100) NOT NULL,
   phone varchar(100) NOT NULL,
   birth datetime not null,
   userrole varchar(50) DEFAULT 'ROLE_USER',
   enabled char(1) DEFAULT '1'
);

drop table project2.members ;

SELECT * FROM project2.members;

-- 공지사항 엔터티
CREATE TABLE project2.announce
(
   serno int AUTO_INCREMENT PRIMARY KEY,
   title varchar(100) NOT NULL,
   content varchar(1000) NOT NULL,
   postdate datetime DEFAULT current_timestamp
);

SELECT * FROM project2.announce;

-- 뉴스 엔터티
CREATE TABLE project2.news
(
   serno int AUTO_INCREMENT PRIMARY KEY,
   title varchar(100) NOT NULL,
   url varchar(1000) NOT NULL,
   postdate datetime DEFAULT current_timestamp,
   media varchar(100) NOT NULL
);

SELECT * FROM project2.news;

commit;

-- 고객지원 엔터티
create table project2.cs
(
   csno int auto_increment primary key,
   title varchar(1000) not null,
   content varchar(1000) not null,
   postdate datetime default current_timestamp   
);

-- 고객지원 답변 엔터티
create table project2.csanswer
(  
   answerno int auto_increment primary key,
   csno int not null,
   answer varchar(1000) not null,
   foreign key(csno) references project2.cs (csno)
);

-- FAQ 엔터티
create table project2.faq
(
   faqno int auto_increment primary key,
   question varchar(1000) not null,
   answer varchar(1000) not null
);

-- 지역 엔터티
create table project2.regions
(
   r_code varchar(5) primary key,
   region varchar(100) not null
);

-- 메인 서비스 엔터티
create table project2.mainservice
(
   mainno int auto_increment primary key,
   maindate datetime default current_timestamp,
   r_code varchar(5) not null,
   production int not null,
   temperatue decimal(2) not null,
   foreign key(r_code) references project2.regions (r_code)
);

-- 국가 엔터티
create table project2.countries
(
   c_code varchar(5) primary key,
   country varchar(100) not null
);

-- 수출입 엔터티
create table project2.trade
(
   tradeno int auto_increment primary key,
   tradedate datetime default current_timestamp,
   c_code varchar(5) not null,
   r_code varchar(5) not null,
   export int not null,
   import int not null,
   foreign key(c_code) references project2.countries (c_code),
   foreign key(r_code) references project2.regions (r_code)
);

-- 수요 엔터티
create table project2.demands
(
   demandno int auto_increment primary key,
   demanddate datetime default current_timestamp,
   usa_yk decimal(2),
   usa_gk decimal(2),
   usa_gs decimal(2),
   jp_yk decimal(2),
   jp_ik decimal(2),
   jp_gk decimal(2),
   demant int not null
);