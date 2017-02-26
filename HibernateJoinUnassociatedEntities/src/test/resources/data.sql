INSERT INTO book (id, publishingdate, title, version) VALUES (1, '2017-04-04', 'Hibernate Tips', 0);

INSERT INTO review (id, comment, fkBook) VALUES (1, 'This is a review', 1);
INSERT INTO review (id, comment, fkBook) VALUES (2, 'This is another review', 1);