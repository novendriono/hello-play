# Add Product
# --- !Ups
create table Product (
  id varchar(36) primary key,
  name varchar(255) not null,
  description varchar(255),
  published_date date
);

create table Variant (
  id varchar(36) primary key,
  product_id varchar(36) not null,
  sku varchar(255),
  option varchar(255),
  cost_in_cent int
);

--- Sample Products
insert into product values('4e13c0f7-475a-4572-83ed-a0d100ecdacc', 'Product 1', 'Product Description', null);
insert into product values('02f1c26e-db48-4887-9c34-dafdbcbe9913', 'Product 2', 'Product Description', null);
insert into product values('fbd2abe4-9e0b-4fb7-bab4-cafc390fb0dc', 'Product 3', 'Product Description', null);
insert into product values('1eb42657-daf9-4110-b689-6b34e985128e', 'Product 4', 'Product Description', null);
insert into product values('ca0946b0-2bf1-48d9-892e-5a34083dee7c', 'Product 5', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 6', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 7', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 8', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 9', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 10', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 11', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 12', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 13', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 14', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 15', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 16', 'Product Description', null);
insert into product values(RANDOM_UUID(), 'Product 17', 'Product Description', null);

insert into variant values(RANDOM_UUID(), '4e13c0f7-475a-4572-83ed-a0d100ecdacc', 'SKU00001S', 'Small', '20000000');
insert into variant values(RANDOM_UUID(), '4e13c0f7-475a-4572-83ed-a0d100ecdacc', 'SKU00001M', 'Medium', '20000000');
insert into variant values(RANDOM_UUID(), '4e13c0f7-475a-4572-83ed-a0d100ecdacc', 'SKU00001L', 'Large', '20000000');

# --- !Downs
drop table Product;
drop table Variant;
