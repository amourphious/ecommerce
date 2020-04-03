use e_commerce_db;

-- to view content of tables

select * from address;
select * from cart;
select * from categories;
select * from customer;
select * from identity_table;
select * from invoice;
select * from order_product;
select * from order_status;
select * from product;
select * from product_variation;
select * from product_review;
select * from product_cart_id;
select * from role;
select * from seller;
select * from seller_product_list;
select * from user;
select * from user_role_list;

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
truncate address;
truncate categories;
truncate customer;
truncate invoice;
truncate product;
truncate product_review;
truncate product_variation;
truncate role;
truncate seller;
truncate seller_product_list;
truncate user;
truncate user_role_list;
SET foreign_key_checks = 1;

-- to drop  tables
SET foreign_key_checks = 0;
drop table address;
drop table cart;
drop table customer;
drop table categories;
drop table invoice;
drop table product;
drop table product_review;
drop table product_variation;
drop table role;
drop table seller;
drop table seller_product_list;
drop table user;
drop table user_role_list;
SET foreign_key_checks = 1;

alter table customer drop user_user_id;
alter table user drop token_expiry_date;
delete from user where user_id=293;
delete from seller where seller_id=293;
delete from user_role_list where u_id=293;