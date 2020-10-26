create table store_api.purchase
(
  id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  customer_id bigint not null,
  description text not null,
  price decimal(10,2) not null,
  status varchar(20) not null,
  creation_date timestamp not null,
  end_date timestamp
);

alter table store_api.purchase add constraint fk_purchase_customer foreign key (customer_id) references customer(id);