alter table meal_posts
    add column share_token varchar null;

create index ix_meal_posts_share_token on meal_posts (share_token);