create table magic_links
(
    id         serial                   not null primary key,
    token      varchar                  not null,
    user_id    integer                  not null,
    expiration timestamp with time zone not null
);

create unique index on magic_links (token);
create index on magic_links (expiration);

alter table magic_links
    add constraint
        magic_link_user_id foreign key (user_id) references users (id);


