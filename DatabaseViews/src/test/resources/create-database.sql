CREATE TABLE author(id bigint NOT NULL, firstname character varying(255), lastname character varying(255), version integer NOT NULL, CONSTRAINT author_pkey PRIMARY KEY (id));

CREATE TABLE book(id bigint NOT NULL, publishingdate date, title character varying(255), version integer, CONSTRAINT book_pkey PRIMARY KEY (id));

CREATE TABLE bookauthor(bookid bigint NOT NULL, authorid bigint NOT NULL, CONSTRAINT bookauthor_pkey PRIMARY KEY (bookid, authorid), CONSTRAINT fk1vpkcbic0iljxqxu629o36mpc FOREIGN KEY (bookid) REFERENCES book (id));

CREATE OR REPLACE VIEW bookview AS SELECT b.id, b.publishingdate, b.title, b.version, string_agg(a.name, ', '::text) AS authors FROM book b JOIN ( SELECT (a_1.firstname::text || ' '::text) || a_1.lastname::text AS name, b_1.id AS bookid FROM book b_1 JOIN bookauthor ba ON b_1.id = ba.bookid JOIN author a_1 ON a_1.id = ba.authorid) a ON a.bookid = b.id GROUP BY b.id;