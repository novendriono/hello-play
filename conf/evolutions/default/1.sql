# Add Product
# --- !Ups
create table Product (
  id varchar(36) primary key,
  name varchar(255) not null,
  width int,
  height int,
  weight int,
  creation_date timestamp default CURRENT_TIMESTAMP
);

--- Sample Products
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 1', 123, 52, 321);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 2', 201, 32, 124);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 3', 125, 12, 422);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 4', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 5', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 6', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 7', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 8', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 9', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 10', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 11', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 12', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 13', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 14', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 15', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 16', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 17', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 18', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 19', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 20', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 21', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 22', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 23', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 24', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 25', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 26', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 27', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 28', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 29', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 30', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 31', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 32', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 33', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 34', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 35', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 36', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 37', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 38', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 39', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 40', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 41', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 42', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 43', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 44', 122, 92, 112);
insert into Product(id, name, width, height, weight) values (RANDOM_UUID(), 'Product 45', 122, 92, 112);

# --- !Downs
drop table Product
