# Add Product
# --- !Ups
create table Product (
  id varchar(128) primary key,
  name varchar(255) not null,
  width int,
  height int,
  weight int
);

insert into Product values ('TEST123', 'Test Product 123', null, null, null);
insert into Product values ('TEST234', 'Test Product 123', null, null, null);
insert into Product values ('TEST567', 'Test Product 123', null, null, null);
insert into Product values ('TEST890', 'Test Product 123', null, null, null);

# --- !Downs
drop table Product
