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
('normal-type-123','N','Normal','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0),('polling-type-123','PO','Polling','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0),('premium-type-123','PRE','Premium','37e1be28-5d03-48fa-9a73-8bb00c4c67c4',0);

--
/**
select * from tb_post_type tpt;
--

INSERT INTO tb_user(id,full_name,email,pass,role_id ,created_by,versions) VALUES
('admin-user','admin','admin@mail.com','$2a$10$CWMOKeU2KBJE0lUTEkM5zebDrFj7dFe3fHNGavmFiIcKLBnlHAU5S','admin-123','system-user',0);

SELECT * FROM tb_user;

--- INSERT INDUSTRIAL
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

