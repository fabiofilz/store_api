create table store_api.customer
(
  id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name varchar(100) not null,
  email varchar(255) not null,
  phone varchar(20)  not null
);