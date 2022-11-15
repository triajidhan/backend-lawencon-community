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

-- tabel status subcsribe (sub, unsub)
CREATE TABLE tb_status_subscribe(
	id varchar(36),
	status_subscribe_code varchar(5) not null unique,
	status_subscribe_name varchar(50) not null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null,
	is_active bool not null default true
);

ALTER TABLE tb_status_subscribe
	ADD CONSTRAINT tb_status_subscribe_pk PRIMARY KEY(id);

ALTER TABLE tb_status_subscribe 
	ADD CONSTRAINT tb_status_subscribe_status_subscribe_code_bk UNIQUE(status_subscribe_code);


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


-- tabel verifikasi
CREATE TABLE tb_verification(
	id varchar(36),
	verification_code varchar(5) not null unique,
	verification_status boolean not null,
	
	created_by varchar(36) not null,
	created_at timestamp without time zone not null default now(),
	updated_by varchar(36),
	updated_at timestamp without time zone,
	versions int not null,
	is_active bool not null default true
);

ALTER TABLE tb_verification
	ADD CONSTRAINT tb_verification_pk PRIMARY KEY(id);

ALTER TABLE tb_verification 
	ADD CONSTRAINT tb_verification_code_bk UNIQUE(verification_code);


-- tabel user
CREATE TABLE tb_user(
	id varchar(36),
	
	full_name varchar(30) not null,	
	email varchar(50) not null unique,
	pass text not null,
	
	company varchar(100) null,
	total_balance double precision null,
	
	industry_id varchar(36) null,
	position_id varchar(36) null,
	
	role_id varchar(36) not null,
	file_id varchar(36) null,
	status_subscribe_id varchar(36) null,
	verification_id varchar(36) null,
	
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
	ADD CONSTRAINT tb_user_tb_file_fk FOREIGN KEY(file_id)
	REFERENCES tb_file(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_role_fk FOREIGN KEY(role_id)
	REFERENCES tb_role(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_status_subscribe_fk FOREIGN KEY(status_subscribe_id)
	REFERENCES tb_status_subscribe(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_industry_fk FOREIGN KEY(industry_id)
	REFERENCES tb_industry(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_position_fk FOREIGN KEY(position_id)
	REFERENCES tb_position(id);

ALTER TABLE tb_user 
	ADD CONSTRAINT tb_user_tb_verification_fk FOREIGN KEY(verification_id)
	REFERENCES tb_verification(id);


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
	status_subscribe_id varchar(36) not null,
	
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
	ADD CONSTRAINT tb_post_tb_subscribe_fk FOREIGN KEY(status_subscribe_id)
	REFERENCES tb_status_subscribe(id);

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
	
	created_by varchar(36) not null ,
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


-- table balance
CREATE TABLE tb_balance(
    id varchar(36),

    total_balance double precision null,
 	user_id varchar(36) not null,
    
    created_by varchar(36) not null ,
    created_at timestamp without time zone not null default now(),
    updated_by varchar(36),
    updated_at timestamp without time zone,
    versions int not null ,
    is_active bool not null default true
);

ALTER TABLE tb_balance
	ADD CONSTRAINT tb_balance_activity_pk PRIMARY KEY(id);

ALTER TABLE tb_balance 
    ADD CONSTRAINT tb_balance_activity_tb_activity_fk FOREIGN KEY(user_id)
    REFERENCES tb_user(id);

-- table payment activity detail 
CREATE TABLE tb_payment_activity_detail(
    id varchar(36),

    payment_code varchar(5) not null unique,
    net double precision null,
    approve boolean default false,

    activity_id varchar(36) not null,

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
    
--- DML 
--INSERT INTO tb_role(role_code,role_name) VALUES
--('SYS','System'),('SA','Super Admin'),('L','Lecturer'),('S','Student');
--
--select * from tb_role;
--
--INSERT INTO tb_post_type (post_type_code,post_type_name) VALUES
--('Po','Polling'),
--('N','Normal'),
--('Pr','Premium');
--
--select * from tb_post_type;
--
--INSERT INTO tb_activity_type (activity_type_code,activity_type_name) VALUES
--('E','Event'),
--('C','Course');
--
--select * from tb_activity_type;
--
--INSERT INTO tb_status_subscribe (status_subscribe_code,status_subscribe_name) VALUES
--('S','Subscribe'),
--('Un','Unsubscribe');
--
--select * from tb_status_subscribe; 