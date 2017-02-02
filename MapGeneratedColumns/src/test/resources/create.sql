CREATE TABLE author(id bigint NOT NULL, firstname character varying(255), lastname character varying(255), lastupdate timestamp without time zone, version integer NOT NULL, CONSTRAINT author_pkey PRIMARY KEY (id));
CREATE SEQUENCE hibernate_sequence INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;

CREATE OR REPLACE FUNCTION sync_lastupdate() RETURNS trigger AS $$ BEGIN NEW.lastupdate := NOW(); RETURN NEW; END; $$ LANGUAGE plpgsql;

CREATE TRIGGER sync_lastupdate BEFORE INSERT OR UPDATE ON author FOR EACH ROW EXECUTE PROCEDURE sync_lastupdate();