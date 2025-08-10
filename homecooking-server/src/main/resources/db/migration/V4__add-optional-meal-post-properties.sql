alter table meal_posts
    add column price_czk_per_portion integer null;

alter table meal_posts
    add column kcal_per_portion integer null;

alter table meal_posts
    add column preparation_time_mins integer null;