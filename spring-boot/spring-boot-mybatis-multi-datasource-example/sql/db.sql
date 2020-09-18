drop table if exists user.t_user;
create table user.t_user
(
    id   int primary key auto_increment,
    name varchar(50) not null
);
insert into user.t_user (name)
values ('Tom'),
       ('Alice');

drop table if exists order.t_order;
create table order.t_order
(
    order_no     int primary key,
    create_time timestamp,
    user_id     int not null
);
insert into order.t_order (order_no, create_time, user_id)
values (1, sysdate(), 1),
       (2, sysdate(), 2);

commit;