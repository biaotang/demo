create table user
(
    id bigint not null AUTO_INCREMENT comment '主键' primary key,
    age int null comment '年龄',
    name varchar(32) null comment '姓名',
    info varchar(1000) null comment '用户信息'
);