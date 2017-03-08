INSERT INTO author (id, firstname, lastname, version) VALUES (1, 'Thorben', 'Janssen', 0);

INSERT INTO book (id, publishingdate, title, version) VALUES (1, '2017-04-04', 'Hibernate Tips', 0);

INSERT INTO bookauthor (bookid, authorid) VALUES (1, 1);
