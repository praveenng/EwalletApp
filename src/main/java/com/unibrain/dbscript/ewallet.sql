/**********************************VIGNESH 31-05-2022****************************************/
/*create database "ewallet" and execute the following script*/

CREATE TABLE bank_master
(
   id SERIAL,
   bank_name VARCHAR (150)
);
ALTER TABLE bank_master ADD CONSTRAINT bank_master_id_pk PRIMARY KEY (id);

INSERT into bank_master values(default,'State Bank of India'); 
INSERT into bank_master values(default,'ICICI'); 
INSERT into bank_master values(default,'Canara Bank'); 
INSERT into bank_master values(default,'Syndicate Bank'); 
INSERT into bank_master values(default,'Vijaya Bank'); 
INSERT into bank_master values(default,'Corporation Bank'); 
INSERT into bank_master values(default,'Bank of Baroda');
INSERT into bank_master values(default,'Bank of India');
INSERT into bank_master values(default,'Bank of Maharashtra');
INSERT into bank_master values(default,'Central Bank of India');
INSERT into bank_master values(default,'Dena Bank');
INSERT into bank_master values(default,'Indian Bank');
INSERT into bank_master values(default,'Indian Overseas Bank');
INSERT into bank_master values(default,'IDBI Bank');
INSERT into bank_master values(default,'Oriental Bank of Commerce');
INSERT into bank_master values(default,'Punjab & Sindh Bank');
INSERT into bank_master values(default,'Punjab National Bank');
INSERT into bank_master values(default,'UCO Bank');
INSERT into bank_master values(default,'Union Bank of India');
INSERT into bank_master values(default,'United Bank of India');
INSERT into bank_master values(default,'Axis Bank');
INSERT into bank_master values(default,'Bandhan Bank');
INSERT into bank_master values(default,'Catholic Syrian Bank');
INSERT into bank_master values(default,'City Union Bank');
INSERT into bank_master values(default,'DCB Bank');
INSERT into bank_master values(default,'Dhanlaxmi Bank');
INSERT into bank_master values(default,'Federal Bank');
INSERT into bank_master values(default,'HDFC Bank');
INSERT into bank_master values(default,'IDFC Bank');
INSERT into bank_master values(default,'IndusInd Bank');
INSERT into bank_master values(default,'Jammu and Kashmir Bank');
INSERT into bank_master values(default,'Karnataka Bank');
INSERT into bank_master values(default,'Karur Vysya Bank');
INSERT into bank_master values(default,'Kotak Mahindra Bank');
INSERT into bank_master values(default,'Lakshmi Vilas Bank');
INSERT into bank_master values(default,'Nainital Bank');
INSERT into bank_master values(default,'RBL Bank');
INSERT into bank_master values(default,'South Indian Bank');
INSERT into bank_master values(default,'Tamilnad Mercantile Bank');
INSERT into bank_master values(default,'YES Bank');

create table users(
	id SERIAL,
	login_id VARCHAR(20) unique,
	password VARCHAR(150) not null,
	ewallet_id VARCHAR(8) unique,
	user_type INTEGER not null,
	individual_or_company_name VARCHAR(150),
	email_id VARCHAR(50) not null,
	mobile_number VARCHAR(20) not null,
	pan_number VARCHAR(10) null,
	adhar_number VARCHAR(14) null,
	account_number VARCHAR(18) null,
	account_holder_name VARCHAR(150) null,
	ifsc VARCHAR(11) null,
	bank_id INTEGER null,
	branch_name VARCHAR(150) null,
	created_date TIMESTAMP not null,
	status character not null
);	

ALTER TABLE users ADD CONSTRAINT users_id_pk PRIMARY KEY(id);
alter table users add constraint bank_id_fk foreign key (bank_id) references bank_master(id);
alter table users add column bank_file_name varchar(150) null;
alter table users add column wallet_balance numeric(20,2) default 0.00;

CREATE TABLE ewallet_logs(
	id SERIAL,
	activity_user_id VARCHAR(20),
	ewallet_id VARCHAR(8),
	ewallet_user_name VARCHAR(50),
	user_type INTEGER,
	log_date_time TIMESTAMP,
	log_subject VARCHAR(200),
	log_matter VARCHAR(4000),
	ip VARCHAR(100),
	browser VARCHAR(50),
	os VARCHAR(50),
	session_id VARCHAR(50)
);

alter table ewallet_logs add constraint ewallet_logs_id_pk primary key(id);

/**********************************KIRAN 08-06-2022****************************************/

CREATE TABLE ewallet_transactions(
	id SERIAL,
	site_name VARCHAR(50),
	department_id INTEGER,
	department_name VARCHAR(150),
	ewallet_user_id INTEGER,
	ewallet_login_id VARCHAR(20),
	ewallet_id VARCHAR(8),
	tender_or_auction_id INTEGER,
	tender_or_auction_number VARCHAR(150),
	tender_or_auction_item_id INTEGER,
	tender_or_auction_item_name VARCHAR(150),
	bidder_id INTEGER,
	bidder_name VARCHAR(150),
	payment_transaction_master_id INTEGER,
	module_id INTEGER,
	module_name VARCHAR(150),
	bank_id INTEGER,
	bank_name VARCHAR(150),
	payment_ewallet_reference VARCHAR(150),
	payment_primary_mode CHARACTER,
	payment_mode_id INTEGER,
	payment_initiated_amount numeric(20,2),
	payment_transaction_reference VARCHAR(150),
	payment_commission numeric(20,2),
	payment_amount_with_commission numeric(20,2),
	payment_actual_amount numeric(20,2),
	transaction_flag CHARACTER,
	debit_credit_flag CHARACTER,
	payment_bank_status INTEGER,
	payment_status INTEGER,
	payment_completion_status VARCHAR(50),
	payment_initiated_date timestamp,
	payment_reconciled_date timestamp,
	order_id varchar(150),
	ebid_payment_id INTEGER,
	current_balance numeric(20,2)
);
alter table ewallet_transactions add constraint ewallet_transactions_id_pk primary key(id);
alter table ewallet_transactions add constraint ewallet_user_id_fk foreign key (ewallet_user_id) references users(id);

CREATE TABLE ewallet_payment_bank_master (
    bank_id serial,
    bank_name VARCHAR(100),
    bank_display_name VARCHAR(100),
    bank_logo VARCHAR(100),
    bank_invoke_url VARCHAR(100),
    bank_requery_url VARCHAR(100),
    bank_implementation_type VARCHAR(100),
    intermediate_payment_gateway_url VARCHAR(100)
);

ALTER TABLE ewallet_payment_bank_master ADD CONSTRAINT payment_bank_master_id_pk PRIMARY KEY (bank_id);

INSERT INTO ewallet_payment_bank_master(bank_id, bank_name, bank_display_name, bank_logo,bank_invoke_url, bank_requery_url, 
bank_implementation_type, intermediate_payment_gateway_url)
VALUES(1,'RAZORPAY','RAZORPAY','razorpay.png','https://checkout.razorpay.com/v1/checkout.js','','Nodal','');


CREATE TABLE merchant_details (
   id serial primary key,
   merchant_name VARCHAR(200),
   merchant_icc VARCHAR(200),
   merchant_username VARCHAR(200),
   merchant_password VARCHAR(200),
   merchant_return_url VARCHAR(500),
   merchant_key VARCHAR(200),
   merchant_beneficiary_name VARCHAR(200),
   merchant_ifsc VARCHAR(200),
   merchant_bank VARCHAR(200),
   merchant_branch VARCHAR(200),
   prefix_code VARCHAR(10),
   salt VARCHAR(10),
   merchant_neft_bank VARCHAR(100),
   neft_account_type VARCHAR(20),
   neft_account_name VARCHAR(20),
   account_id VARCHAR(50)
);

CREATE TABLE merchant_master (
    id serial ,
    bank_id integer,
    payment_mode_id integer,
    merchant_nodal character varying(50),
    merchant_type character varying(5),
    merchant_nodal_name character varying(50),
    merchant_icc_id character varying(50),
    merchant_user_name character varying(50),
    merchant_password character varying(50),
    merchant_return_url character varying(100),
    merchant_code character varying(50),
    merchant_beneficiary_name character varying(100),
    merchant_ifsc character varying(50),
    merchant_bank character varying(50),
    merchant_branch character varying(100),
    merchant_enablement_flag character varying(5) DEFAULT false,
    prefix_code character varying(50),
    salt character varying(50),
    merchant_neft_bank VARCHAR(100),
   neft_account_type VARCHAR(20),
   neft_account_name VARCHAR(20),
   account_id VARCHAR(50)
);

ALTER TABLE merchant_master ADD CONSTRAINT merchant_master_id_pk PRIMARY KEY (id);

