create table chats(
    id varchar not null primary key,
    creation_date timestamp default now()
);

create table chats_users(
    chat_id varchar not null,
    user_email varchar not null,
    primary key (chat_id, user_email),
    constraint fk_chat_id foreign key (chat_id) references chats(id)
);

create table messages(
    id varchar not null primary key,
    chat_id varchar not null,
    sender varchar not null,
    content text,
    creation_date timestamp default now()
);
