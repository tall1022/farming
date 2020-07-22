drop table member;
create table member(
	m_id varchar2(20) primary key ,
	m_password varchar2(50) not null,
	m_name varchar2(50) not null,
	m_tel varchar2(50) not null,
	m_address1 varchar2(200) not null,
	m_address2 varchar2(200),
	m_zipcode varchar2(7) not null,
	m_rrn varchar2(15) not null,
	m_reg_date date default sysdate,
	m_del char(1) default 'n'
);

drop table board;
create table board( 
	bd_num number primary key, 
	bd_writer varchar2(20) not null,   
	bd_subject varchar2(50) not null,  
	bd_content varchar2(500) not null,             
	bd_readcount number default 0,  
	bd_password varchar2(12) not null,   
	bd_ref number not null,            
	bd_re_step number not null,    
	bd_re_level number not null,    
	bd_ip varchar2(20) not null, 
	bd_reg_date date not null,         
	bd_del char(1) default 'n'
);
select * from goods;
drop table goods;
create table goods (
	g_num number primary key,
	g_kind varchar2(20) DEFAULT NULL, /*category */
	g_name varchar2(50) DEFAULT NULL,
	g_content varchar2(3000) DEFAULT NULL,
	g_size varchar2(10) DEFAULT NULL,
	g_color varchar2(20) DEFAULT NULL,
	g_amount number DEFAULT NULL,
	g_price number DEFAULT NULL,
	g_image varchar2(100) default 'nothing.jpg',
	g_best number DEFAULT NULL,
	g_reg_date date DEFAULT NULL
);


/*
drop table images;
CREATE TABLE `images` (
	i_id number primary key AUTO_INCREMENT,
	i_filename varchar2(100) default 'nothing.jpg'
);
*/
CONSTRAINT FK_TOPICS FOREIGN KEY(lecturekey)

REFERENCES LECTURE(LID)

drop table admin;
create table admin (
	a_m_id varchar2(20) primary key,
	/* 해당아이디의 비번 불러오기. */
	a_m_pass varchar2(50) not null
);

drop table g_order;
create table g_order(
	o_num number primary key,
	o_g_num number DEFAULT NULL,
	o_trade_num varchar2(50) DEFAULT NULL,
	o_g_name varchar2(50) DEFAULT NULL,
	o_g_amount number DEFAULT NULL,
	o_g_size varchar2(10) DEFAULT NULL,
	o_g_color varchar2(20) DEFAULT NULL,
	o_m_id varchar2(20) DEFAULT NULL,
	o_receive_name varchar2(20) DEFAULT NULL,
	o_receive_addr1 varchar2(70) DEFAULT NULL,
	o_receive_addr2 varchar2(70) DEFAULT NULL,
	o_receive_phone varchar2(13) DEFAULT NULL,
	o_receive_mobile varchar2(13) DEFAULT NULL,
	o_memo varchar2(3000) DEFAULT NULL,
	o_sum_money number DEFAULT NULL,
	o_trade_type varchar2(20) DEFAULT NULL,
	o_trade_payer varchar2(20) DEFAULT NULL,
	o_trade_date date DEFAULT NULL,
	o_trans_num varchar2(50) DEFAULT NULL,
	o_date date DEFAULT NULL,
	o_status number DEFAULT NULL,
	FOREIGN KEY(o_g_num) REFERENCES goods(g_num),
	FOREIGN KEY(o_m_id) REFERENCES member(m_id)
);

drop table basket;
create table basket(
	b_num number PRIMARY KEY,
	b_m_id varchar(20) DEFAULT NULL,
	b_g_num number DEFAULT NULL,
	b_g_amount number DEFAULT NULL,
	b_g_size varchar(10) DEFAULT NULL,
	b_g_color varchar(20) DEFAULT NULL,
	b_date date DEFAULT NULL,
	FOREIGN KEY(b_m_id) REFERENCES member(m_id),
	FOREIGN KEY(b_g_num) REFERENCES goods(g_num)

);










