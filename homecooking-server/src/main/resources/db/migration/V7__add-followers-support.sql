create table followed_users
(
    id               serial  not null primary key,
    user_id          integer not null,
    followed_user_id integer not null,

    foreign key (user_id) references users (id),
    foreign key (followed_user_id) references users (id)
);

alter table users
    add column follow_code varchar not null default '';