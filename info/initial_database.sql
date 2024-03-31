if db_id('cskh_kham_rang') is not null -- kiem tra database, neu chua thi tao moi
begin
	-- tao database voi ten la cskh_kham_rang
	drop database cskh_kham_rang;
end;

create database cskh_kham_rang;
go
use cskh_kham_rang;
-- tao cac bang
create table "user" (
	user_id int not null primary key,
	username varchar(30),
	password varchar(100),
	full_name varchar(150),
	email varchar(100),
	phone_number varchar(10)
);

create table "admin" (
	admin_id int not null primary key,
	username varchar(30),
	password varchar(100),
	full_name varchar(150),
	email varchar(100)
);

create table schedule (
	schedule_id int not null primary key,
	title varchar(200),
	"description" varchar(1000),
	booking_datetime datetime,
	user_id int,
	dentist_id int
);

create table calling_history (
	calling_history_id int not null primary key,
	phone_number varchar(10),
	"status" int,
	"description" varchar(100),
	start_datetime datetime,
	end_datetime datetime,
	user_id int,
	record_id int
);

create table record (
	record_id int not null primary key,
	content varchar(5000),
	calling_history_id int
);

create table dentist (
	dentist_id int not null primary key,
	full_name varchar(100),
	"status" int,
	start_working_time time,
	end_working_time time
);

-- tao cac khoa ngoai
alter table calling_history
add constraint FK_USER_CALLINGHISTORY
foreign key (user_id) references "user"(user_id);

alter table calling_history
add constraint FK_RECORD_CALLINGHISTORY
foreign key (record_id) references record(record_id);

alter table record
add constraint FK_CALLINGHISTORY_RECORD
foreign key (calling_history_id) references calling_history(calling_history_id);

alter table schedule
add constraint FK_USER_SCHEDULE
foreign key (user_id) references "user"(user_id);

alter table schedule
add constraint FK_DENTIST_SCHEDULE
foreign key (dentist_id) references dentist(dentist_id);

-- them data vao cac bang
insert into "user" values 
(1, 'user1', '123456', 'user 1', 'user1@gmail.com', '0123456789'),
(2, 'user2', '123456', 'user 2', 'user2@gmail.com', '0123456789'),
(3, 'user3', '123456', 'user 3', 'user3@gmail.com', '0123456789');

insert into "admin" values
(1, 'admin1', '123456', 'admin', 'admin@gmail.com');

insert into dentist values 
(1, 'dentist 1', 1, '08:00:00', '17:00:00'),
(2, 'dentist 2', 2, '10:30:00', '19:00:00'),
(3, 'dentist 3', 3, '17:30:00', '23:00:00'),
(4, 'dentist 4', 4, '12:30:00', '18:30:00'),
(5, 'dentist 5', 5, '10:00:00', '16:00:00');

insert into schedule values 
(1, 'Hen kham rang', 'Bi sau rang', '2024-03-31 09:00:00', 2, 1),
(2, 'Hen kham rang', 'Bi sau rang', '2024-04-01 16:00:00', 3, 5),
(3, 'Hen kham rang', 'Bi sau rang', '2024-04-15 20:30:00', 1, 3);