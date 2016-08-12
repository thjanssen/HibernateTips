INSERT INTO author (id, firstname, lastname, version) VALUES (1, 'Joshua', 'Bloch', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (2, 'Gavin', 'King', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (3, 'Christian', 'Bauer', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (4, 'Gary', 'Gregory', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (5, 'Thorben', 'Janssen', 0);
INSERT INTO author (id, firstname, lastname, version) VALUES (6, 'John', 'Doe', 0);

INSERT INTO publication (id, publishingdate, title, version, DTYPE) VALUES (1, '2008-05-08', 'Effective Java', 0, 'Book');
INSERT INTO publication (id, publishingdate, title, version, DTYPE) VALUES (2, '2015-10-01', 'Java Persistence with Hibernate', 0, 'Book');
INSERT INTO publication (id, publishingdate, title, version, DTYPE) VALUES (3, '2016-05-04', 'Java Weekly 32/16', 0, 'BlogPost');

INSERT INTO publicationauthor (publicationid, authorid) VALUES (1, 1);
INSERT INTO publicationauthor (publicationid, authorid) VALUES (2, 2);
INSERT INTO publicationauthor (publicationid, authorid) VALUES (2, 3);
INSERT INTO publicationauthor (publicationid, authorid) VALUES (2, 4);
INSERT INTO publicationauthor (publicationid, authorid) VALUES (3, 5);