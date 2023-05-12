CREATE SCHEMA `hw_9` DEFAULT CHARACTER SET utf8;

create table authors
(
    id bigint auto_increment primary key,
    first_name varchar(45),
    last_name varchar(45),
    age int
);

create table books
(
    id bigint auto_increment primary key,
    name varchar(45)
);

create table book_author
(
    book_id bigint not null,
    author_id bigint not null,
    primary key (book_id, author_id),
    foreign key (book_id) references books(id),
    foreign key (author_id) references authors(id)
)