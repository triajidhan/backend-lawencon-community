-- tabel file
CREATE TABLE tb_file(
	id varchar(36),
	file text,
	ext varchar(5),
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null,
	is_active bool not null default true
);

ALTER TABLE tb_file 
	ADD CONSTRAINT tb_file_pk PRIMARY KEY(id);

-- tabel role 
CREATE TABLE tb_role(
	id varchar(36),
	role_code varchar(5) not null,
	role_name varchar(50) not null unique,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_role
	ADD CONSTRAINT tb_role_pk PRIMARY KEY(id);

ALTER TABLE tb_role 
	ADD CONSTRAINT tb_role_role_code_bk UNIQUE(role_code);

-- tabel tipe postingan (polling, biasa, premium)
CREATE TABLE tb_post_type(
	id varchar(36),
	post_type_code varchar(5) not null unique,
	post_type_name varchar(50) not null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_post_type
	ADD CONSTRAINT tb_post_type_pk PRIMARY KEY(id);

ALTER TABLE tb_post_type 
	ADD CONSTRAINT tb_post_type_post_type_code_bk UNIQUE(post_type_code);
	
-- tabel tipe kegiatan (event, course)
CREATE TABLE tb_activity_type(
	id varchar(36),
	activity_type_code varchar(5) not null unique,
	activity_type_name varchar(50) not null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null,
	is_active bool not null default true
);

ALTER TABLE tb_activity_type
	ADD CONSTRAINT tb_activity_type_pk PRIMARY KEY(id);

ALTER TABLE tb_activity_type 
	ADD CONSTRAINT tb_activity_type_activity_type_code_bk UNIQUE(activity_type_code);


-- tabel industry
CREATE TABLE tb_industry(
	id varchar(36),
	
	industry_code varchar(5) not null,
	industry_name varchar(30) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_industry 
	ADD CONSTRAINT tb_industry_pk PRIMARY KEY(id);

ALTER TABLE tb_industry 
	ADD CONSTRAINT tb_industry_industry_code_bk UNIQUE(industry_code);

ALTER TABLE tb_industry 
	ADD CONSTRAINT tb_industry_code_industry_name_ck UNIQUE(industry_code,industry_name);

-- tabel position
CREATE TABLE tb_position(
	id varchar(36),
	
	position_code varchar(5) not null,
	position_name varchar(30) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_position 
	ADD CONSTRAINT tb_position_pk PRIMARY KEY(id);

ALTER TABLE tb_position 
	ADD CONSTRAINT tb_position_position_code_bk UNIQUE(position_code);

ALTER TABLE tb_position 
	ADD CONSTRAINT tb_position_code_position_name_ck UNIQUE(position_code,position_name);

-- table balance
CREATE TABLE tb_balance(
    id varchar(36),

    total_balance double precision default 0,
    
    created_by varchar(36) not null ,
    created_at timestamp without time zone not null default now(),
    updated_by varchar(36),
    updated_at timestamp without time zone,
    versions int not null ,
    is_active bool not null default true
);

ALTER TABLE tb_balance
	ADD CONSTRAINT tb_balance_activity_pk PRIMARY KEY(id);

-- tabel user
CREATE TABLE tb_user(
	id varchar(36),
	
	full_name varchar(30) not null,	
	email varchar(50) not null unique,
	pass text not null,
	
	company varchar(100) null,
	status_subscribe boolean null,
	
	industry_id varchar(36) null,
	position_id varchar(36) null,
	
	balance_id varchar(36) null,
	role_id varchar(36) not null,
	file_id varchar(36) null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_pk PRIMARY KEY(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_email_bk UNIQUE(email);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_balance_fk FOREIGN KEY(balance_id)
	REFERENCES tb_balance(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_role_fk FOREIGN KEY(role_id)
	REFERENCES tb_role(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_industry_fk FOREIGN KEY(industry_id)
	REFERENCES tb_industry(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_position_fk FOREIGN KEY(position_id)
	REFERENCES tb_position(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_full_name_email_ck UNIQUE(full_name,email);

-- tabel article
CREATE TABLE tb_article(
	id varchar(36),
	
	article_code varchar(5) not null unique,
	title varchar(50) not null,
	contents text,
	
	file_id varchar(36) null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_article 
	ADD CONSTRAINT tb_article_pk PRIMARY KEY(id);

ALTER TABLE tb_article 
	ADD CONSTRAINT tb_article_article_code_bk UNIQUE(article_code);

ALTER TABLE tb_article 
	ADD CONSTRAINT tb_article_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);


-- tabel post
CREATE TABLE tb_post(
	id varchar(36),
	post_code varchar(5) not null unique,
	title varchar(50) not null,
	contents text,
	
	title_poll text null,
	post_type_id varchar(36) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_post 
	ADD CONSTRAINT tb_post_pk PRIMARY KEY(id);

ALTER TABLE tb_post 
	ADD CONSTRAINT tb_post_tb_post_bk UNIQUE(post_code);

ALTER TABLE tb_post 
	ADD CONSTRAINT tb_post_tb_post_type_id_fk FOREIGN KEY(post_type_id)
	REFERENCES tb_post_type(id);

-- tabel post attachment
CREATE TABLE tb_post_attachment(
	id varchar(36),
	
	post_id varchar(36) not null,
	file_id varchar(36) null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_post_attachment 
	ADD CONSTRAINT tb_post_attachment_pk PRIMARY KEY(id);

ALTER TABLE tb_post_attachment 
	ADD CONSTRAINT tb_post_attachment_tb_post_fk FOREIGN KEY(post_id)
	REFERENCES tb_post(id);

ALTER TABLE tb_post_attachment 
	ADD CONSTRAINT tb_post_attachment_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);


-- tabel polling
CREATE TABLE tb_polling(
	id varchar(36),
	
	poll_content varchar(50) not null,
	total_poll int default 0,
	
	post_id varchar(36) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_polling 
	ADD CONSTRAINT tb_polling_pk PRIMARY KEY(id);

ALTER TABLE tb_polling 
	ADD CONSTRAINT tb_polling_tb_post_fk FOREIGN KEY(post_id)
	REFERENCES tb_post(id);

-- Bookmark
CREATE TABLE tb_bookmark(
	id varchar(36),
	
	user_id varchar(36) not null,
	post_id varchar(36) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_bookmark 
	ADD CONSTRAINT tb_bookmark_pk PRIMARY KEY(id);

ALTER TABLE tb_bookmark 
	ADD CONSTRAINT tb_bookmark_tb_user_fk FOREIGN KEY(user_id)
	REFERENCES tb_user(id);

ALTER TABLE tb_bookmark 
	ADD CONSTRAINT tb_bookmark_tb_post_fk FOREIGN KEY(post_id)
	REFERENCES tb_post(id);

-- Like
CREATE TABLE tb_like(
	id varchar(36),
	
	user_id varchar(36) not null,
	post_id varchar(36) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_like 
	ADD CONSTRAINT tb_like_pk PRIMARY KEY(id);

ALTER TABLE tb_like 
	ADD CONSTRAINT tb_like_tb_user_fk FOREIGN KEY(user_id)
	REFERENCES tb_user(id);

ALTER TABLE tb_like 
	ADD CONSTRAINT tb_like_tb_post_fk FOREIGN KEY(post_id)
	REFERENCES tb_post(id);

-- comment
CREATE TABLE tb_comment(
	id varchar(36),
	
	comment_body text not null,
	
	user_id varchar(36) not null,
	post_id varchar(36) not null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_comment 
	ADD CONSTRAINT tb_comment_pk PRIMARY KEY(id);

ALTER TABLE tb_comment 
	ADD CONSTRAINT tb_comment_tb_user_fk FOREIGN KEY(user_id)
	REFERENCES tb_user(id);

ALTER TABLE tb_comment 
	ADD CONSTRAINT tb_comment_tb_post_fk FOREIGN KEY(post_id)
	REFERENCES tb_post(id);

-- tabel post attachment
CREATE TABLE tb_comment_attachment(
	id varchar(36),
	
	comment_id varchar(36) not null,
	file_id varchar(36) null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_comment_attachment 
	ADD CONSTRAINT tb_comment_attachment_pk PRIMARY KEY(id);

ALTER TABLE tb_comment_attachment 
	ADD CONSTRAINT tb_comment_attachment_tb_post_fk FOREIGN KEY(comment_id)
	REFERENCES tb_comment(id);

ALTER TABLE tb_comment_attachment 
	ADD CONSTRAINT tb_comment_attachment_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);

-- table activity
CREATE TABLE tb_activity(
	id varchar(36),
	
	activity_code varchar(5) not null unique,
	title varchar(50) not null,
	--description text,
	provider varchar(100) not null,
	locations text,
	begin_schedule timestamp without time zone not null,
	finish_schedule timestamp without time zone not null,
	price double precision null,
	
	activity_type_id varchar(36) not null,
	file_id varchar(36) null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_activity
	ADD CONSTRAINT tb_activity_pk PRIMARY KEY(id);

ALTER TABLE tb_activity 
	ADD CONSTRAINT tb_activity_tb_activity_activity_code_bk UNIQUE(activity_code);

ALTER TABLE tb_activity 
	ADD CONSTRAINT tb_activity_tb_activity_type_fk FOREIGN KEY(activity_type_id)
	REFERENCES tb_activity_type(id);

ALTER TABLE tb_activity 
	ADD CONSTRAINT tb_activity_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);


-- table payment subscribe
CREATE TABLE tb_payment_subscribe(
	id varchar(36),
	
	payment_code varchar(5) not null unique,
	price double precision null,
	approve boolean default false,
	
	user_id varchar(36) not null,
	file_id varchar(36) null,
	
	created_by varchar(36) not null ,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null ,
	is_active bool not null default true
);

ALTER TABLE tb_payment_subscribe
	ADD CONSTRAINT tb_payment_subscribe_pk PRIMARY KEY(id);

ALTER TABLE tb_payment_subscribe 
	ADD CONSTRAINT tb_payment_subscribe_tb_payment_subscribe_code_bk UNIQUE(payment_code);

ALTER TABLE tb_payment_subscribe 
	ADD CONSTRAINT tb_payment_subscribe_tb_user_fk FOREIGN KEY(user_id)
	REFERENCES tb_user(id);

ALTER TABLE tb_payment_subscribe 
	ADD CONSTRAINT tb_payment_subscribe_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);

-- table payment activity detail 
CREATE TABLE tb_payment_activity_detail(
    id varchar(36),

    payment_code varchar(5) not null unique,
    net double precision null,
    approve boolean default false,

    activity_id varchar(36) not null,
    file_id varchar(36) null,

    created_by varchar(36) not null,
    created_at timestamp without time zone not null default now(),
    updated_by varchar(36),
    updated_at timestamp without time zone,
    versions int not null,
    is_active bool not null default true
);

ALTER TABLE tb_payment_activity_detail
    ADD CONSTRAINT tb_payment_activity_detail_pk PRIMARY KEY(id);

ALTER TABLE tb_payment_activity_detail
    ADD CONSTRAINT tb_payment_activity_detail_tb_payment_activity_code_bk UNIQUE(payment_code);
   
ALTER TABLE tb_payment_activity_detail 
    ADD CONSTRAINT tb_activity_detail_tb_activity_fk FOREIGN KEY(activity_id)
    REFERENCES tb_activity(id);

ALTER TABLE tb_payment_activity_detail 
	ADD CONSTRAINT tb_activity_detail_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);
   
-- table status polling 
CREATE TABLE tb_polling_status(
    id varchar(36),

    polling_id varchar(5) not null,

    created_by varchar(36) not null,
    created_at timestamp without time zone not null default now(),
    updated_by varchar(36),
    updated_at timestamp without time zone,
    versions int not null,
    is_active bool not null default true
);

ALTER TABLE tb_polling_status
    ADD CONSTRAINT tb_polling_status_pk PRIMARY KEY(id);
   
ALTER TABLE tb_polling_status 
    ADD CONSTRAINT tb_polling_status_tb_polling_fk FOREIGN KEY(polling_id)
    REFERENCES tb_polling(id);

--- DATA MANIPULATION LANGUAGE
   
---- INSERT ROLE
INSERT INTO tb_role(id,role_code,role_name,created_by,versions) VALUES
('system','SYS','System','system-user-id',0);

INSERT INTO tb_role(id,role_code,role_name,created_by,versions) VALUES
('super','SA','Super Admin','system-user-id',0),
('admin','A','Admin','system-user-id',0),
('member','M','Member','system-user-id',0);

select * from tb_role;
--
-- INSERT POSITION
INSERT INTO tb_position(id,position_code,position_name,created_by,versions) VALUES 
('administration','ADM','Administration','admin-user-id',0),
('programmer','PG','Programmer','admin-user-id',0),
('human resource','HR','Human resource','admin-user-id',0),
('marketing','M','Marketing','admin-user-id',0),
('cleaning service','CLS','Cleaning Service','admin-user-id',0),
('customer service','CS','Customer Service','admin-user-id',0),
('teknisi jaringan','TKJ','Teknisi Jaringan','admin-user-id',0),
('perawat','P','Perawat','admin-user-id',0),
('pekerja rumah tangga','PRT','Pekerja Rumah Tangga','admin-user-id',0),
('fullstack developer','FD','Fullstack Developer','admin-user-id',0),
('it support','ITS','IT Support','admin-user-id',0);

select * from tb_position;

-- INSERT INDUSTRY
INSERT INTO tb_industry(id,industry_code,industry_name,created_by,versions) VALUES 
('kesehatan','KES','Kesehatan','admin-user-id',0),
('pendidikan','PDD','Pendidikan','admin-user-id',0),
('transportasi','TPT','Transportasi','admin-user-id',0),
('perikanan','PIK','Perikanan','admin-user-id',0),
('hiburan','HBN','Hiburan','admin-user-id',0),
('penginapan','PNP','Penginapan','admin-user-id',0),
('perbankan','PBK','Perbankan','admin-user-id',0),
('manajement talenta','MJT','Manajement Talenta','admin-user-id',0),
('peternakan','PNK','Peternakan','admin-user-id',0),
('perkebunan','PBN','Perkebunan','admin-user-id',0),
('perumahan','PMN','Perumahan','admin-user-id',0);

select * from tb_industry;

-- INSERT USER SYSTEM, SUPER ADMIN, ADMIN
INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions) VALUES
('system-user-id','system','system@mail.com',12345,'system','system-user-id',0);

--- super
INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('super-1','','','super-user-id',now(),0);

INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions, file_id) VALUES
('super-user-id','super','super@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','super','system-user-id',0,'super-1');

--- admin
INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('admin-1','','','super-user-id',now(),0);

INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions,file_id) VALUES
('admin-user-id','admin','admin@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','admin','super-user-id',0,'admin-1');

select * from tb_user;

-- tb balance
select * from tb_balance tb;

INSERT INTO tb_balance(id,total_balance,created_by,created_at,versions) VALUES
('id-1',0,'super-user-id',now(),0),
('id-2',0,'super-user-id',now(),0),
('id-3',0,'super-user-id',now(),0),
('id-4',0,'super-user-id',now(),0),
('id-5',0,'super-user-id',now(),0),
('id-6',0,'super-user-id',now(),0),
('id-7',0,'super-user-id',now(),0),
('id-8',0,'super-user-id',now(),0),
('id-9',0,'super-user-id',now(),0),
('id-10',0,'super-user-id',now(),0),
('id-11',0,'super-user-id',now(),0);

-- INSERT FILE
select * from tb_file tf;

INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('id-1','','','super-user-id',now(),0),
('id-2','','','super-user-id',now(),0),
('id-3','','','super-user-id',now(),0),
('id-4','','','super-user-id',now(),0),
('id-5','','','super-user-id',now(),0),
('id-6','','','super-user-id',now(),0),
('id-7','','','super-user-id',now(),0),
('id-8','','','super-user-id',now(),0),
('id-9','','','super-user-id',now(),0),
('id-10','','','super-user-id',now(),0),
('id-11','','','super-user-id',now(),0);

-- INSERT USER MEMBER
INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions,position_id,industry_id,balance_id,file_id,company,status_subscribe) VALUES
('member-user-id-1','member 1','m1@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','kesehatan','id-1','id-1','BPJS',false),
('member-user-id-2','member 2','m2@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'programmer','pendidikan','id-2','id-2','Zenius',false),
('member-user-id-3','member 3','m3@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'cleaning service','transportasi','id-3','id-3','KRL',false),
('member-user-id-4','member 4','m4@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'customer service','hiburan','id-4','id-4','Evos',false),
('member-user-id-5','member 5','m5@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','perumahan','id-5','id-5','Waskita',false),
('member-user-id-6','member 6','m6@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','pendidikan','id-6','id-6','Ruang Guru',false),
('member-user-id-7','member 7','m7@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','transportasi','id-7','id-7','Lion Air',false),
('member-user-id-8','member 8','m8@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','penginapan','id-8','id-8','Oyo',false),
('member-user-id-9','member 9','m9@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'administration','peternakan','id-9','id-9','Agrindo',false),
('member-user-id-10','member 10','m10@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'programmer','hiburan','id-10','id-10','RRQ',false),
('member-user-id-11','member 11','m11@mail.com','$2a$10$QReWrBqM79lNRjzjvMTGr.j75z6dtvtC4K6e5wDVv.ukxtRAWL0L.','member','system-user-id',0,'programmer','hiburan','id-11','id-11','Tencent',false);

select * from tb_user;

--- INSERT POST TYPE
INSERT INTO tb_post_type(id,post_type_code,post_type_name,created_by,versions) values
('regular-type','REQ','Regular','system',0),
('polling-type','POL','Polling','system',0),
('premium-type','PRE','Premium','system',0);
--

-- INSERT ACTIVITY TYPE
INSERT INTO tb_activity_type(id,activity_type_code,activity_type_name,created_by,versions) VALUES
('event','E','Event','system-user-id',0),
('course','C','Course','system-user-id',0);

select * from tb_activity_type;

-- INSERT tb_activity
INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('course-1','','','super-user-id',now(),0),
('course-2','','','super-user-id',now(),0),
('course-3','','','super-user-id',now(),0),
('course-4','','','super-user-id',now(),0),
('course-5','','','super-user-id',now(),0),
('event-1','','','super-user-id',now(),0),
('event-2','','','super-user-id',now(),0),
('event-3','','','super-user-id',now(),0),
('event-4','','','super-user-id',now(),0),
('event-5','','','super-user-id',now(),0),
('event-6','','','super-user-id',now(),0);

-- description belum ditambahhkan
insert into tb_activity(id,activity_code,title,provider,locations,begin_schedule,finish_schedule,price,activity_type_id,created_by,created_at,versions,file_id) values
('course-1','c1','Bootcamp java','lawencon','Jakarta','2022-11-01 08:00:00','2022-11-15 15:00:00',100000,'course','member-user-id-1',now(),0,'course-1'),
('course-2','c2','Golang Flazzh','Xsis','Jakarta','2022-12-01 08:00:00','2023-03-05 15:00:00',300000,'course','member-user-id-2',now(),0,'course-2'),
('course-3','c3','Laravel','Factury','Yogyakarta','2023-01-01 08:00:00','2023-05-05 15:00:00',0,'course','member-user-id-3',now(),0,'course-3'),
('course-4','c4','.Net','Microsoft','Jakarta','2023-01-01 08:00:00','2023-05-05 15:00:00',3000000,'course','member-user-id-4',now(),0,'course-4'),
('course-5','c5','CCNA','Cisco','Bandung','2022-12-01 08:00:00','2023-03-05 15:00:00',500000,'course','member-user-id-7',now(),0,'course-5'),
('event-1','e1','Job Fair','Jayakarta','Jakarta','2023-04-01 08:00:00','2023-04-20 15:00:00',0,'event','member-user-id-5',now(),0,'event-1'),
('event-2','e2','Seminar Kecerdasan Buatan','Universitas Pamulang','Tangerang Selatan','2023-04-01 08:00:00','2023-04-01 13:00:00',50000,'event','member-user-id-3',now(),0,'event-2'),
('event-3','e3','Seminar Edukasi Berpolitik','Universitas Pamulang','Tangerang Selatan','2023-04-10 08:00:00','2023-04-10 13:00:00',50000,'event','member-user-id-3',now(),0,'event-3'),
('event-4','e4','Seminar Machine Learning','Universitas Pamulang','Tangerang Selatan','2023-04-01 08:00:00','2023-04-01 13:00:00',50000,'event','member-user-id-3',now(),0,'event-4'),
('event-5','e5','Seminar Block Chain','Universitas Pamulang','Tangerang Selatan','2023-04-28 08:00:00','2023-04-28 13:00:00',50000,'event','member-user-id-3',now(),0,'event-5'),
('event-6','e6','Seminar Kehidupan Bermasyarakat','Universitas Pamulang','Tangerang Selatan','2023-04-29 08:00:00','2023-04-29 13:00:00',50000,'event','member-user-id-3',now(),0,'event-6');

select * from tb_activity ta;

-- payment subscribe

INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('pay-4','','','super-user-id',now(),0),
('pay-5','','','super-user-id',now(),0),
('pay-7','','','super-user-id',now(),0),
('pay-8','','','super-user-id',now(),0),
('pay-9','','','super-user-id',now(),0),
('pay-10','','','super-user-id',now(),0);

INSERT INTO tb_payment_subscribe(id,payment_code,price,approve,user_id,created_by,created_at,versions,file_id) VALUES
('pay-1','p1',50000,false,'member-user-id-1','member-user-id-1',now(),0,null),
('pay-2','p2',50000,false,'member-user-id-2','member-user-id-2',now(),0,null),
('pay-3','p3',50000,false,'member-user-id-3','member-user-id-3',now(),0,null),
('pay-4','p4',50000,true,'member-user-id-4','member-user-id-4',now(),0,'pay-4'),
('pay-5','p5',50000,true,'member-user-id-5','member-user-id-5',now(),0,'pay-5'),
('pay-6','p6',50000,false,'member-user-id-6','member-user-id-6',now(),0,null),
('pay-7','p7',50000,true,'member-user-id-7','member-user-id-7',now(),0,'pay-7'),
('pay-8','p8',50000,true,'member-user-id-8','member-user-id-8',now(),0,'pay-8'),
('pay-9','p9',50000,true,'member-user-id-9','member-user-id-9',now(),0,'pay-9'),
('pay-10','p10',50000,true,'member-user-id-10','member-user-id-10',now(),0,'pay-10');

select * from tb_payment_subscribe tps;


-- payment activity
INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('pa-4','','','super-user-id',now(),0),
('pa-5','','','super-user-id',now(),0),
('pa-7','','','super-user-id',now(),0),
('pa-8','','','super-user-id',now(),0),
('pa-9','','','super-user-id',now(),0),
('pa-10','','','super-user-id',now(),0);

INSERT INTO tb_file(id,file,ext,created_by,created_at,versions) VALUES
('pa-14','','','super-user-id',now(),0),
('pa-15','','','super-user-id',now(),0),
('pa-17','','','super-user-id',now(),0),
('pa-18','','','super-user-id',now(),0),
('pa-19','','','super-user-id',now(),0),
('pa-20','','','super-user-id',now(),0);

INSERT INTO tb_payment_activity_detail (id,payment_code,net,approve,activity_id,created_by,created_at,versions,file_id) VALUES
('pay-1','p1',50000,false,'event-1','member-user-id-1',now(),0,null),
('pay-2','p2',50000,false,'event-1','member-user-id-2',now(),0,null),
('pay-4','p4',50000,true,'event-1','member-user-id-4',now(),0,'pa-4'),
('pay-5','p5',50000,true,'event-1','member-user-id-5',now(),0,'pa-5'),
('pay-6','p6',50000,false,'event-1','member-user-id-6',now(),0,null),
('pay-7','p7',50000,true,'event-1','member-user-id-7',now(),0,'pa-7'),
('pay-8','p8',50000,true,'event-1','member-user-id-8',now(),0,'pa-8'),
('pay-9','p9',50000,true,'event-1','member-user-id-9',now(),0,'pa-9'),
('pay-10','p10',50000,true,'event-1','member-user-id-10',now(),0,'pa-10'),
('pay-12','p12',100000.0,false,'course-1','member-user-id-2',now(),0,null),
('pay-14','p14',100000.0,true,'course-1','member-user-id-4',now(),0,'pa-14'),
('pay-15','p15',100000.0,true,'course-1','member-user-id-5',now(),0,'pa-15'),
('pay-16','p16',100000.0,false,'course-1','member-user-id-6',now(),0,null),
('pay-17','p17',100000.0,true,'course-1','member-user-id-7',now(),0,'pa-17'),
('pay-18','p18',100000.0,true,'course-1','member-user-id-8',now(),0,'pa-18'),
('pay-19','p19',100000.0,true,'course-1','member-user-id-9',now(),0,'pa-19'),
('pay-20','p20',100000.0,true,'course-1','member-user-id-10',now(),0,'pa-20');

select * from tb_payment_activity_detail tpad;

/**
-- INSERT tb_artikel (disarankan dengan web langsung)
insert into tb_article(id,article_code,title,contents,created_by,versions) VALUES
('artikel-01','AR1','Kerbau','Kerbau adalah ','admin-user-id',0),
('artikel-02','AR2','Sapi','Sapi adalah ','admin-user-id',0),
('artikel-03','AR3','Cacing','Cacing adalah ','admin-user-id',0),
('artikel-04','AR4','Php','Php adalah hewan ','admin-user-id',0),
('artikel-05','AR5','Golang','Golang adalah ','admin-user-id',0),
('artikel-06','AR6','Kertas','Kertas adalah ','admin-user-id',0),
('artikel-07','AR7','Kayu','Kayu adalah hewan ','admin-user-id',0),
('artikel-08','AR8','Manusia','Manusia adalah ','admin-user-id',0),
('artikel-09','AR9','Sepakbola','Sepakbola adalah ','admin-user-id',0),
('artikel-10','AR10','Sungai','Sungai adalah','admin-user-id',0),
('artikel-11','AR11','Titik','Titik adalah ','admin-user-id',0);


-- INSERT POST (disarankan dengan web langsung)
insert into tb_post(id,post_code,title,contents,post_type_id,created_by,versions) values
('post-01','P1','Post 1','Kerbau adalah ','admin-user-id',0),
('post-02','P2','Post 2','Sapi adalah ','admin-user-id',0),
('post-03','P3','Post 3','Cacing adalah ','admin-user-id',0),
('post-04','P4','Post 4','Php adalah hewan ','admin-user-id',0),
('post-05','P5','Post 5','Golang adalah ','admin-user-id',0),
('post-06','P6','Post 6','Kertas adalah ','admin-user-id',0),
('post-07','P7','Post 7','Kayu adalah hewan ','admin-user-id',0),
('post-08','P8','Post 8','Manusia adalah ','admin-user-id',0),
('post-09','P9','Post 9','Sepakbola adalah ','admin-user-id',0),
('post-10','P10','Post 10','Sungai adalah','admin-user-id',0),
('post-11','P11','Post 11','Titik adalah ','admin-user-id',0);
*/

-- SELECT DATA CUSTOM --
--  USER 
 --get by email or role code
 SELECT 
 tu.id, full_name, email, pass, company,
 ti.id, ti.industry_code, ti.industry_name,
 tp.id, tp.position_code, tp.position_name,
 tr.id, tr.role_code, tr.role_name,
 file_id,
 tb.id,tb.total_balance,
 tu.versions, tu.is_active FROM tb_user tu 
 LEFT JOIN tb_industry ti ON tu.industry_id = ti.id 
 LEFT JOIN tb_position tp ON tu.position_id = tp.id 
 INNER JOIN tb_role tr ON tu.role_id = tr.id 
 LEFT JOIN tb_file tf ON tu.file_id = tf.id 
 INNER JOIN tb_balance tb ON tu.balance_id = tb.id
 WHERE role_code  = 'A';
 
-- total user
select count(id) as total_user from tb_user;

-- total admin
select count(tu.id) as total_user from tb_user tu
INNER JOIN tb_role tr ON tu.role_id = tr.id
WHERE tr.role_code = 'S';

-- total user subscribe
select count(tu.id) as total_user from tb_user tu
INNER JOIN tb_role tr ON tu.role_id = tr.id
WHERE 
tr.role_code = 'M' AND 
tu.status_subscribe = true;

-- select  role by role code
select id, versions, role_code, role_name from tb_role tr;

-- select  industry by industry code 
select id, versions, industry_code, industry_name from tb_industry ti;

-- select position by position code 
select id, versions, position_code, position_name from tb_position tp;


--POST
	-- getbypostcode()
	-- id, post_code, title, content, title_poll, post_type_id, post_type
select 
tp.id as tp_id, post_code, title, contents, title_poll,
tpt.id as tpt_id ,tpt.post_type_code, tpt.post_type_name,
tp.created_by, tp.versions 
from tb_post tp 
INNER JOIN tb_post_type tpt  ON tp.post_type_id = tpt.id;
--WHERE post_code = 'PR';


-- getbyuser() > ambil createdBy
-- id, post_code, title, content, title_poll, post_type_id, post_type
select 
tp.id as tp_id, post_code, title , contents, title_poll,
tpt.id as tpt_id ,tpt.post_type_code, tpt.post_type_name,
tp.created_by, tp.versions 
from tb_post tp 
INNER JOIN tb_post_type tpt  ON tp.post_type_id = tpt.id
WHERE tp.created_by = '1';


-- ARTICLE
-- getbypostcode()
-- id, article_code, title, file_id,create_by,create_at,versions
select 
id, article_code, title, file_id,created_by,created_at,versions
from tb_article ta;--  WHERE article_code  = 'VC';

--
select 
tp.id as tp_id, post_code, title, contents, title_poll,
tpt.id as tpt_id ,tpt.post_type_code, tpt.post_type_name,
tp.created_by, tp.versions 
from tb_post tp 
INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id
WHERE post_code = 'PR';

-- 
select 
tp.id as tp_id, post_code, title, contents, title_poll,
tpt.id as tpt_id ,tpt.post_type_code, tpt.post_type_name,
tp.created_by, tp.versions 
from tb_post tp 
INNER JOIN tb_post_type tpt ON tp.post_type_id = tpt.id
WHERE tp.created_by = '1';


-- ACTIVITY
-- getByActivityCode()
SELECT 
ta.id as ta_id,ta.activity_code, ta.title,ta.provider,ta.provider,ta.locations,begin_schedule,finish_schedule,price,
file_id,
tat.id as tat_id,tat.activity_type_code,tat.activity_type_name,
ta.created_by,ta.created_at,ta.versions 
FROM tb_activity ta
INNER JOIN  
tb_activity_type tat  ON ta.activity_type_id = tat.id;
--WHERE activity_code  = 'c1';

-- getByActivityTypeId
SELECT * 
FROM tb_activity ta 
INNER JOIN  
tb_activity_type tat  ON ta.activity_type_id = tat.id;
--where tat.id = 'kjjm';

-- get by created by
-- getByActivityTypeId
SELECT * 
FROM tb_activity ta 
INNER JOIN  
tb_activity_type tat  ON ta.activity_type_id = tat.id;
--where tat.created_by = '1';

-- POLLING ()

select * from tb_polling tp; 

-- getByPostId
SELECT 
tpol.id as tpol_id,poll_content,total_poll,
tpos.id as tpos_id, tpos.post_code, tpos.title, tpos.title_poll,
tpos.created_by, tpos.created_at,tpos.versions 
FROM tb_polling tpol 
INNER JOIN tb_post tpos ON  tpos.id  = tpol.post_id
WHERE tpos.id = 'cbvc';

-- getByCreatedBy
SELECT 
tpol.id as tpol_id,poll_content,total_poll,
tpos.id as tpos_id, tpos.post_code, tpos.title, tpos.title_poll,
tpos.created_by, tpos.created_at,tpos.versions 
FROM tb_polling tpol 
INNER JOIN tb_post tpos ON  tpos.id  = tpol.post_id
WHERE tpos.created_by = 'admin-user';

-- BOOKMARK

-- getbyuserid()
SELECT * 
FROM tb_bookmark tb
INNER JOIN tb_user tu ON tu.id = tb.user_id
INNER JOIN tb_post tp ON tp.id = tb.post_id 
WHERE user_id  = '1dd4ad4e-bec9-48fb-9e60-21b9025b3299';

-- getbypost()
SELECT count(tb.id) 
FROM tb_bookmark tb
INNER JOIN tb_user tu ON tu.id = tb.user_id
INNER JOIN tb_post tp ON tp.id = tb.post_id;
--WHERE post_id  = 'kfdd';


-- LIKE
-- getbyuserid()
SELECT * 
FROM tb_bookmark tb
INNER JOIN tb_user tu ON tu.id = tb.user_id
INNER JOIN tb_post tp ON tp.id = tb.post_id 
WHERE user_id  = '1dd4ad4e-bec9-48fb-9e60-21b9025b3299';

-- getbypost()
SELECT count(tb.id) 
FROM tb_bookmark tb
INNER JOIN tb_user tu ON tu.id = tb.user_id
INNER JOIN tb_post tp ON tp.id = tb.post_id;
--WHERE post_id  = 'kfdd';

-- COMMENT
SELECT * 
FROM tb_comment tc
INNER JOIN tb_user tu ON tu.id = tc.user_id
INNER JOIN tb_post tp ON tp.id = tc.post_id;

-- PAYMENT ACTIVITY DETAIL
SELECT * 
FROM tb_payment_activity_detail tpad
INNER JOIN tb_activity ta ON ta.id = tpad.activity_id
INNER JOIN tb_activity_type tat ON tat.id = ta.activity_type_id;

-- PAYMENTSUBSCRIBE
SELECT * 
FROM tb_payment_subscribe tps;


-- Setiap select data ditambahkan is_active = true
 
Select * from tb_post LEFT JOIN tb_like on tb_like.post_id = tb_post.id
LEFT JOIN tb_bookmark on tb_bookmark.post_id  = tb_post.id;

-- userLike
Select count(*), (SELECT id  from tb_like where user_id = '1dd4ad4e-bec9-48fb-9e60-21b9025b3299' AND post_id = 'kjhghh') as like_id  FROM tb_like WHERE post_id = 'kjhghh';	

-- user bookmark
Select count(*), (SELECT id from tb_bookmark where user_id = '1dd4ad4e-bec9-48fb-9e60-21b9025b3299' AND post_id = 'kjhghh') as bookmark_id  FROM tb_bookmark;


SELECT count(*) FROM tb_like tl INNER JOIN tb_user tu ON tu.id = tl.user_id INNER JOIN tb_post tp ON tp.id = tl.post_id WHERE post_id  = :user AND tl.is_active = true;

SELECT count(tl.id) FROM tb_like tl INNER JOIN tb_user tu ON tu.id = tl.user_id INNER JOIN tb_post tp ON tp.id = tl.post_id WHERE post_id  = 'kjhghh' AND tl.is_active = true;
  
SELECT ta.id as ta_id,ta.activity_code, ta.title,ta.provider,ta.locations,begin_schedule,finish_schedule,price, file_id, tat.id as tat_id,tat.activity_type_code,tat.activity_type_name, ta.created_by,ta.created_at,ta.versions, ta.is_active FROM tb_activity ta INNER JOIN tb_activity_type tat ON ta.activity_type_id = tat.id WHERE activity_code = 'c1' AND ta.is_active = true;

 
 -- menghitung payment berdasarkan tipe activity 
select * 
from tb_payment_activity_detail tpad
inner join tb_activity_type tat ON tpad.activity_id = tat.id;


-- jumlah member yang mengikuti event 
-- report informasi member yang mengikuti event atau course 
-- pada periode tertentu(tanggal dimulainya event)

-- No. , type, title, start date, total partisipasi

select * from tb_payment_activity_detail tpad;

select * from tb_activity ta;


-- 
select title,tu.full_name as member_create, activity_type_name, begin_schedule, partisipant from tb_activity as ta_prime 
INNER JOIN (
	SELECT
	activity_id, count(activity_id) as partisipant
	FROM (
		select 
		ta.id as activity_id
		from tb_payment_activity_detail tpad 
		INNER JOIN tb_activity ta ON tpad.activity_id = ta.id
		WHERE (begin_schedule between '2022-04-01' AND '2023-04-10') 
		AND tpad.approve = true
	) tb_pay
	GROUP BY 1
) tb_partisipant ON ta_prime.id = tb_partisipant.activity_id
INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id
INNER JOIN tb_user tu ON ta_prime.created_by = tu.id;



-- jumlah keuntungan yang mengikuti event
select title,tu.full_name as member_create, activity_type_name, begin_schedule, total_income from tb_activity as ta_prime 
INNER JOIN (
	SELECT activity_id, sum(net) as total_income
	FROM (
		select ta.id as activity_id, net
		from tb_payment_activity_detail tpad 
		INNER JOIN tb_activity ta ON tpad.activity_id = ta.id
		WHERE (begin_schedule between '2022-04-01' AND '2023-04-10') 
		AND tpad.approve = true
	) tb_pay
	GROUP BY 1
) tb_partisipant ON ta_prime.id = tb_partisipant.activity_id
INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id
INNER JOIN tb_user tu ON ta_prime.created_by = tu.id;


-- di activity code di ganti menjadi activityTypeId (count)
-- ganti nilai tipe integer ke objek yang menampung nilai int




--- DATA MANIPULATION LANGUAGE
   
---- INSERT ROLE
INSERT INTO tb_role(id,role_code,role_name,created_by,versions) VALUES
('system-123','SYS','System','system-123',0);


INSERT INTO tb_role(id,role_code,role_name,created_by,versions) VALUES
('super-admin-123','SA','Super Admin','system-123',0),
('admin-123','A','Admin','system-123',0),
('member-123','M','Member','system-123',0);

--
select * from tb_role;

delete from tb_role;
--

-- INSERT USER SUPER ADMIN
INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions) VALUES
('system','system','system@mail.com',12345,'system-123','system-user',0);

select * from tb_user;

--- INSERT POST TYPE
INSERT INTO tb_post_type(id,post_type_code,post_type_name,created_by,versions) values
('reguler-type-123','REG','Reguler','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0),
('polling-type-123','POL','Polling','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0),
('premium-type-123','PRE','Premium','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0);

INSERT INTO tb_activity_type(id,activity_type_code,activity_type_name,created_by,versions) VALUES
('event-123','E','Event','system',0),
('course-123','C','Course','system',0);

--
/**
select * from tb_post_type tpt;
--

INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions) VALUES
('admin-user','admin','admin@mail.com','$2a$10$CWMOKeU2KBJE0lUTEkM5zebDrFj7dFe3fHNGavmFiIcKLBnlHAU5S','admin-123','system-user',0);

SELECT * FROM tb_user;

INSERT INDUSTRIAL
INSERT INTO tb_industry(id,industry_code,industry_name,created_by,versions) VALUES
('start-up-123','SU','Start Up','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0),
('corporate-123','C','Corporated','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0);

select * from tb_industry;

--- INSERT FILE 
--INSERT INTO tb_file(id,file,ext,created_by,versions) VALUES
--('wedsfvgfd','SHP','Shoppee',1,0),('sWRFEGF5','TP','Tokopedia',1,0);

select * from tb_file;

--- INSERT POSITION
INSERT INTO tb_position(id,position_code,position_name,created_by,versions) VALUES
('administrator-123','ADM','Administrator','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0),
('programmer-123','PG','Programmer','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0),
('trainer-123','TR','Trainer','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0);

select * from tb_position tp;

-- INSERT ACTIVITY
INSERT INTO tb_activity_type(id,activity_type_code,activity_type_name,created_by,versions) VALUES
('event-123','E','Event',1,0),('course-123','C','Course',1,0);

select * from tb_activity_type tat;


-- INSERT BALANCE
select * from tb_balance tb;

-- INSERT USER
INSERT INTO tb_user(id,full_name,email,pass,company, status_subscribe,industry_id, position_id, balance_id, role_id ,created_by,versions) VALUES
('sdfc','gangan','gan@mail.com',12345,'CCN',false,'sWRFEGF5','sfdghxghfub','sssss','bvsdngmjnhgv',1,0);

INSERT INTO tb_user(id,full_name,email,pass,company, status_subscribe,industry_id, position_id, balance_id, role_id ,created_by,versions) VALUES
('buggj','wanwan','wan@mail.com',12345,'CCN',false,'sWRFEGF5','sfdghxghfub','sssss','bvsdngmjnhgv',1,0);

INSERT INTO tb_user(id,full_name,email,pass,company, status_subscribe,industry_id, position_id, balance_id, role_id ,created_by,versions) VALUES
('huggj','sansan','san@mail.com',12345,'CCN',false,'sWRFEGF5','sfdghxghfub','sssss','nmjkgyi',1,0);

select * from tb_user;

-- INSERT tb_activity
insert into tb_activity(id,activity_code,title,provider,locations,begin_schedule,finish_schedule,price,activity_type_id,created_by,versions) values
('course-01','c1','Bootcamp java','lawencon','Jakarta','2022-11-01 08:00:00','2022-11-05 15:00:00',200000,'course-123','admin-user',0),
('event-02','c2','Jobfair','visited','Jakarta','2022-11-01 08:00:00','2022-11-05 15:00:00',200000,'event-123','admin-user',0);

select * from tb_activity ta
inner join tb_activity_type tat on ta.activity_type_id  = tat.id;

-- tb_artikel
insert into tb_article(id,article_code,title,contents,created_by,versions) VALUES
('sdfsd','VC','Kerbau','Kerbau adalah hewan berkaki 4',1,0);


select * from tb_article ta;


-- INSERT post
---(post biasa/premium)
insert into tb_post(id,post_code,title,contents,post_type_id,created_by,versions) values
('kjhghh','KC','Kucing','Kucing adalah hewan berkaki 4','normal-type-123','admin-user',0);

insert into tb_post(id,post_code,title,contents,title_poll,post_type_id,created_by,versions) values
('cbvc','PR','Event','','Apakah ada yang ingin mengikuti event','polling-type-123','admin-user',0);

insert into tb_post(id,post_code,title,contents,title_poll,post_type_id,created_by,versions) values
('cbv','RR','Event','','Apakah ada yang ingin mengikuti course','polling-type-123','admin-user',0);

insert into tb_post(id,post_code,title,contents,post_type_id,created_by,versions) values
('kfdd','AY','Ayam','Ayam adalah hewan berkaki 2','normal-type-123','admin-user',0);

select * from tb_post;


-- INSERT polling tp 
INSERT INTO tb_polling(id,poll_content,total_poll,post_id,created_by,versions) values
('pol-4','A',23,'cbv','admin-user',0),
('pol-5','B',23,'cbv','admin-user',0),
('pol-6','C',23,'cbv','admin-user',0);

select * from tb_polling tp;

-- INSERT BOOKMARK
select * from tb_post tp;

select * from tb_user;

INSERT INTO tb_bookmark (id,user_id,post_id,created_by,versions) values
('qw1','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','kjhghh','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('q2','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','kfdd','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('qw3','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','cbvc','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('qw4','3cd88ebf-6130-4f6f-8ac5-4b1913a5716d','kfdd','3cd88ebf-6130-4f6f-8ac5-4b1913a5716d',0);

select * from tb_bookmark tb;

-- INSERT LIKES
INSERT INTO tb_like  (id,user_id,post_id,created_by,versions) values
('qw1','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','kjhghh','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('q2','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','kfdd','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('qw3','1dd4ad4e-bec9-48fb-9e60-21b9025b3299','cbvc','1dd4ad4e-bec9-48fb-9e60-21b9025b3299',0),
('qw4','3cd88ebf-6130-4f6f-8ac5-4b1913a5716d','kfdd','3cd88ebf-6130-4f6f-8ac5-4b1913a5716d',0);

select * from tb_like tl;

*/


--- INSERT FILE 
--INSERT INTO tb_file(id,file,ext,created_by,versions) VALUES
--('wedsfvgfd','SHP','Shoppee',1,0),('sWRFEGF5','TP','Tokopedia',1,0);

select * from tb_file;

--- INSERT POSITION
INSERT INTO tb_position(id,position_code,position_name,created_by,versions) VALUES
('hr-123','HRD','Human RD','e53b7bb7-663d-4140-ba1f-6e3f6472f286',0),
('frontend-123','FRT','Frontend','e53b7bb7-663r-4140-ba1f-6eaf6472f286',0),
('backend-123','BCK','Backend','e53b7bb7-663d-4340-ba1f-6eaf6472f286',0),
('tester-123','TST','Tester','e53b7bb7-763d-4140-ba1f-6eaf6472f286',0),
('ui-ux-123','UIX','UI UX Design','e53b7bb7-693d-4140-ba1f-6eaf6472f286',0),
('devops-123','DEV','Dev-Ops','e53b7bb7-663d-4140-ba1g-6eaf6472f286',0);

select * from tb_position tp;

INSERT INTO tb_industry(id,industry_code,industry_name,created_by,versions) VALUES
('start-up-123','STU','Start Up','e53b7bb7-663d-4140-ba1f-6eaf6472f286',0),
('corporate-123','COR','Corporated','e53b7bb7-6637-4140-ba1f-6eaf6472f286',0),
('technologi-123','TECH','Technologi','e53b7bb8-663d-4140-ba1f-6eaf6472f286',0),
('food-123','FOO','Food','e53b7bb7-663d-4140-ga1f-6eaf6472f286',0),
('entertaint-123','ENT','Entertaint','e53b7bb7-i63d-4140-ba1f-6eaf6472f286',0),
('education-123','EDU','Education','e53b7bb7-663d-6140-ba1f-6eaf6472f286',0),
('farmation-123','FAR','Farmation','e53b7bb7-663d-4w40-ba1f-6eaf6472f286',0),
('transportation-123','TRA','Transportation','e53bmbb7-663d-4140-ba1f-6eaf6472f286',0);


-- INSERT ACTIVITY
INSERT INTO tb_activity_type(id,activity_type_code,activity_type_name,created_by,versions) VALUES
('event-123','E','Event',1,0),('course-123','C','Course',1,0);

INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions) VALUES
('zzz','zzz','zzz@mail.com','zzz','admin-123','system-user',0),
('xxx','xxx','xxx@mail.com','xxx','admin-123','system-user',0),
('ccc','ccc','ccc@mail.com','ccc','admin-123','system-user',0),
('vvv','vvv','vvv@mail.com','vvv','admin-123','system-user',0),
('bbb','bbb','bbb@mail.com','bbb','admin-123','system-user',0),
('nnn','nnn','nnn@mail.com','nnn','admin-123','system-user',0),
('mmm','mmm','mmm@mail.com','mmm','admin-123','system-user',0),
('fff','fff','fff@mail.com','fff','admin-123','system-user',0),
('ggg','ggg','ggg@mail.com','ggg','admin-123','system-user',0),
('hhh','hhh','hhh@mail.com','hhh','admin-123','system-user',0);


-- 
select title,tu.full_name as member_create, activity_type_name, begin_schedule, partisipant from tb_activity as ta_prime 
INNER JOIN (
	SELECT
	activity_id, count(activity_id) as partisipant
	FROM (
		select 
		ta.id as activity_id
		from tb_payment_activity_detail tpad 
		INNER JOIN tb_activity ta ON tpad.activity_id = ta.id
		WHERE (begin_schedule between '2022-04-01' AND '2023-04-10') 
		AND tpad.approve = true
	) tb_pay
	GROUP BY 1
) tb_partisipant ON ta_prime.id = tb_partisipant.activity_id
INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id
INNER JOIN tb_user tu ON ta_prime.created_by = tu.id;



-- jumlah keuntungan yang mengikuti event
select title,tu.full_name as member_create, activity_type_name, begin_schedule, total_income from tb_activity as ta_prime 
INNER JOIN (
	SELECT activity_id, sum(net) as total_income
	FROM (
		select ta.id as activity_id, net
		from tb_payment_activity_detail tpad 
		INNER JOIN tb_activity ta ON tpad.activity_id = ta.id
		WHERE (begin_schedule between '2022-04-01' AND '2023-04-10') 
		AND tpad.approve = true
	) tb_pay
	GROUP BY 1
) tb_partisipant ON ta_prime.id = tb_partisipant.activity_id
INNER JOIN tb_activity_type tat ON tat.id = ta_prime.activity_type_id
INNER JOIN tb_user tu ON ta_prime.created_by = tu.id;



