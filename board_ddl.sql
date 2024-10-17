CREATE DATABASE project2;

USE project2;

-- 회원 엔터티
CREATE TABLE project2.members
(
   user_id   varchar(100) PRIMARY KEY,
   user_pwd   varchar(100) NOT NULL,
   email      varchar(100) NOT NULL,
   user_add   varchar(100) NOT NULL,
   phone varchar(100) NOT NULL,
   birth datetime not null,
   roles varchar(50) DEFAULT 'ROLE_USER',
   enabled char(1) DEFAULT '1'
  		, CONSTRAINT members_roles CHECK(roles IN ('ROLE_USER', 'ROLE_ADMIN'))
		, CONSTRAINT members_enabled CHECK(enabled IN ('1', '0'))
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
   answer_no int auto_increment primary key,
   csno int ,
   answer varchar(1000) not null,
   CONSTRAINT csanswer_csno FOREIGN key(csno) references project2.cs (csno) ON DELETE CASCADE
);

drop table project2.csanswer;

-- FAQ 엔터티
create table project2.faq
(
   faq_no int auto_increment primary key,
   question varchar(1000) not null,
   answer varchar(1000) not null
);

drop table project2.faq;

-- 지역 엔터티
create table project2.regions
(
   r_code varchar(5) primary key,
   region varchar(100) not null
);

-- 메인 서비스 엔터티
create table project2.mainservice
(
   main_no int auto_increment primary key,
   main_date datetime default current_timestamp,
   r_code varchar(5),
   production int not null,
   temperatue decimal(2) not null,
   CONSTRAINT mainservice_rcode FOREIGN key(r_code) references project2.regions (r_code) ON DELETE CASCADE
);

drop table project2.mainservice;

-- 국가 엔터티
create table project2.countries
(
   c_code varchar(5) primary key,
   country varchar(100) not null
);

-- 수출입 엔터티
create table project2.trade
(
   trade_no int auto_increment primary key,
   trade_date datetime default current_timestamp,
   c_code varchar(5) ,
   r_code varchar(5) ,
   export int not null,
   import int not null,
   CONSTRAINT trade_ccode FOREIGN key(c_code) references project2.countries (c_code) ON DELETE CASCADE,
   CONSTRAINT trade_rcode FOREIGN key(r_code) references project2.regions (r_code) ON DELETE CASCADE
);

drop table project2.trade;

-- 수요 엔터티
create table project2.demands
(
   demand_no int auto_increment primary key,
   demand_date datetime default current_timestamp,
   usa_yk decimal(2),
   usa_gk decimal(2),
   usa_gs decimal(2),
   jp_yk decimal(2),
   jp_ik decimal(2),
   jp_gk decimal(2),
   demand int not null
);

drop table project2.demands;