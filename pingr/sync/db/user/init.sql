create table users(
    email varchar(255) not null primary key,
    first_name varchar(255),
    last_name varchar(255),
    creation_date timestamp default now()   
);

create table friendships(
    user_email_1 varchar(255) not null,
    user_email_2 varchar(255) not null,
    primary key (user_email_1, user_email_2),
    constraint fk_user_email_1 foreign key (user_email_1) references users(email),
    constraint fk_user_email_2 foreign key (user_email_2) references users(email)
);

insert into users (email, first_name, last_name)
    values
        ('user1@email.com', 'user1', 'user 1 last name'),
        ('user2@email.com', 'user2', 'user 2 last name'),
        ('user3@email.com', 'user3', 'user 3 last name');

insert into friendships (user_email_1, user_email_2)
    values
        ('user1@email.com', 'user2@email.com');
