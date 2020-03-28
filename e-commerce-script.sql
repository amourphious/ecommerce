use e_commerce_db;

-- to view content of tables
select * from identity_table;
select * from user;
select * from role;
select * from user_role_list;
select * from customer;
select * from seller;
select * from address;
select * from invoice;
select * from categories;
select * from product;
select * from product_variation;
select * from seller_product_list;

-- table description 
desc identity_table;
desc user;
desc role;
desc customer;
desc seller;
desc address;
desc categories;
desc invoice;

-- to delete the content of tables
SET foreign_key_checks = 0;
truncate identity_table;
truncate user;
truncate role;
truncate user_role_list;
truncate customer;
truncate seller;
truncate address;
truncate categories;
truncate identity_genrator;
truncate identity_table;
truncate invoice;
truncate product;
truncate seller_product_list;
SET foreign_key_checks = 1;

-- to drop  tables
SET foreign_key_checks = 0;
drop table user;
drop table role;
drop table customer;
drop table seller;
drop table address;
drop table categories;
SET foreign_key_checks = 1;

alter table customer drop user_user_id;