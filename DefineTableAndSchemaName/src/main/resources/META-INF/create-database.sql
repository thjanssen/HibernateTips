CREATE SCHEMA IF NOT EXISTS bookstore;

CREATE TABLE bookstore.author (id bigint NOT NULL,firstname character varying(255),lastname character varying(255),version integer NOT NULL,CONSTRAINT author_pkey PRIMARY KEY (id));

CREATE SEQUENCE hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;