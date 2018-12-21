drop table if exists message;

create table message (
    id int primary key auto_increment,
    message json
);
