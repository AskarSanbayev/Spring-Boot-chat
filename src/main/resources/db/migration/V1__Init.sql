create sequence post_sequence start 1 increment 1;
create sequence user_sequence start 1 increment 1;
create sequence comment_sequence start 1 increment 1;

create table comment
(
    comment_id    int8 not null,
    creation_date timestamp,
    modified_date timestamp,
    comment_text  varchar(2048),
    author_id     int8,
    post_id       int8,
    primary key (comment_id)
);

create table friend_list
(
    user_id   int8 not null,
    friend_id int8 not null,
    primary key (user_id, friend_id)
);

create table post
(
    post_id            int8 not null,
    creation_date      timestamp,
    last_modified_date timestamp,
    post_text          varchar(2048),
    author_id          int8,
    primary key (post_id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table user_table
(
    user_id   int8         not null,
    age       int4 check (age >= 1),
    date      date,
    email     varchar(255),
    last_name varchar(255),
    login     varchar(255) not null,
    name      varchar(255),
    password  varchar(255) not null,
    primary key (user_id)
);

alter table if exists comment
    add constraint comment_author_fk foreign key (author_id) references user_table;
alter table if exists comment
    add constraint comment_post_fk foreign key (post_id) references post;
alter table if exists friend_list
    add constraint friend_id_fk foreign key (friend_id) references user_table;
alter table if exists friend_list
    add constraint user_id_fk foreign key (user_id) references user_table;
alter table if exists post
    add constraint post_author_fk foreign key (author_id) references user_table;
alter table if exists user_role
    add constraint role_author_fk foreign key (user_id) references user_table;
