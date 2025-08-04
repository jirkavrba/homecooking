create table meal_posts
(
    id               serial                   not null primary key,
    user_id          integer                  not null,
    title            varchar                  not null,
    description      text,
    image_url        varchar                  not null,
    ingredients_list text,
    recipe           text,
    rating           int,
    posted_at        timestamp with time zone not null
);

alter table meal_posts
    add constraint fk_meal_posts_user_id foreign key (user_id) references users (id);

create index on meal_posts (user_id);
create index on meal_posts (posted_at);
