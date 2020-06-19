

use testexen; #testexen

drop table upload;
drop table student_exam;
drop table posts;
drop table message;
drop table exam_lecturers;
drop table chat;
drop table user;
drop table exam_materials;
drop table exam;

create table IF NOT EXISTS exam
(
    exam_id    int auto_increment
        primary key,
    start_time timestamp   not null,
    duration   int         not null,
    var_num    int         not null,
    exam_subj  varchar(60) not null
);

create table IF NOT EXISTS exam_materials
(
	material_id int auto_increment
		primary key,
	material_link varchar (200) not null,
	var int not null,
	exam_id int not null,
	constraint exam_materials_exam_exam_id_fk
		foreign key (exam_id) references exam (exam_id)
);

create table IF NOT EXISTS user
(
	Id int auto_increment
		primary key,
	Email varchar(60) not null,
	password_hash varchar(100) not null,
	status varchar(60) default 'Student' not null,
	name varchar(60) not null,
	last_name varchar(60) not null,
	constraint table_name_Email_uindex
		unique (Email),
	constraint table_name_password_hash_uindex
		unique (password_hash)
);

create table IF NOT EXISTS chat
(
	chat_id int auto_increment
		primary key,
	`User1` int not null,
	`User2` int not null,
	constraint Chat_user_Id_fk
		foreign key (`User1`) references user (Id),
	constraint Chat_user_Id_fk_2
		foreign key (`User2`) references user (Id)
);

create table IF NOT EXISTS exam_lecturers
(
	exam_id int not null,
	lecturer_id int not null,
	constraint exam_lectors_exam_exam_id_fk
		foreign key (exam_id) references exam (exam_id),
	constraint exam_lectors_user_Id_fk
		foreign key (lecturer_id) references user (Id)
);

create table IF NOT EXISTS message
(
	message_id int auto_increment
		primary key,
	`from` int not null,
	chat_id int not null,
	sent_date timestamp not null,
	text text not null,
	type varchar(60) not null,
	constraint message_chat_chat_id_fk
		foreign key (chat_id) references chat (chat_id),
	constraint message_user_Id_fk
		foreign key (`from`) references user (Id)
);

create table IF NOT EXISTS posts
(
	post_id int auto_increment
		primary key,
	exam_id int not null,
	from_id int not null,
	constraint posts_exam_exam_id_fk
		foreign key (exam_id) references exam (exam_id),
	constraint posts_user_Id_fk
		foreign key (from_id) references user (Id)
);

create table IF NOT EXISTS student_exam
(
	student_id int not null,
	exam_id int null,
	variant int not null,
	comp_index int not null,
	constraint student_exam_exam_exam_id_fk
		foreign key (exam_id) references exam (exam_id),
	constraint student_exam_user_Id_fk
		foreign key (student_id) references user (Id)
);

create table IF NOT EXISTS upload
(
    upload_id int auto_increment
        primary key,
    from_id   int          not null,
    exam_id   int          not null,
    var_id    int          not null,
    file_link varchar(200) not null,
    constraint upload_exam_exam_id_fk
        foreign key (exam_id) references exam (exam_id),
    constraint upload_user_Id_fk
        foreign key (from_id) references user (Id)
);

insert into exam (exam_id, start_time, duration, var_num, exam_subj)
           VALUES(1, STR_TO_DATE("2019-05-15 16:30", "%Y-%m-%d %H:%i"), 90, 3, 'test exam');
insert into exam_materials (material_id, material_link, var, exam_id)
           VALUES(1, './test.txt', 1, 1);
insert into user (Email, password_hash, name, last_name)
           VALUES('test1@freeuni.edu.ge', 1000, 'test1', 't1');
insert into user (Email, password_hash, name, last_name)
           VALUES('test2@freeuni.edu.ge', 1001, 'test2', 't2');
insert into chat (chat_id, User1, User2)
           VALUES(1, 1, 2);
insert into exam_lecturers (exam_id, lecturer_id)
           VALUES(1, 1);
insert into message (`from`, chat_id, sent_date, text, type)
           VALUES (1, 1, STR_TO_DATE("2020-07-05 03:50", "%Y-%m-%d %H:%i"), 'hello world', 'text');
insert into posts (exam_id, from_id)
           VALUES (1, 1);
insert into student_exam (student_id, exam_id, variant, comp_index)
           VALUES (1, 1, 1, 20);
insert into upload (from_id, exam_id, var_id, file_link)
           VALUES (1, 1, 1, './test.txt');
