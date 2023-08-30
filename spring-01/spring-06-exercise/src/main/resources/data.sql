insert into author (id, name)
values (1, 'Tolstoy');
insert into author (id, name)
values (2, 'Chekhov');
insert into author (id, name)
values (3, 'Twen');

insert into genre (id, name)
values (1, 'Detective');
insert into genre (id, name)
values (2, 'Roman');
insert into genre (id, name)
values (3, 'Drama');

insert into book(name)
values ('Test');

insert into book_author(book_id, author_id)
values (1,1);
insert into book_author(book_id, author_id)
values (1,2);
insert into book_genre(book_id, genre_id)
values (1,1);
insert into book_genre(book_id, genre_id)
values (1,2);

insert into comment (text, book_id)
values ('So So', 1);