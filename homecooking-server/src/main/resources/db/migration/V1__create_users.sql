create table users
(
    id         serial  not null primary key,
    username   varchar not null,
    avatar_url varchar not null,
    discord_id varchar not null unique,
    token      varchar
);

create unique index on users(discord_id);
create unique index on users(token);
