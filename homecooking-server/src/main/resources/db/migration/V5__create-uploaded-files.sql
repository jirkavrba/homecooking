create table uploaded_files
(
    id       serial  not null primary key,
    file_url varchar not null,
    user_id  integer not null,

    constraint fk_uploaded_files_user_id foreign key (user_id) references users (id)
);