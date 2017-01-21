CREATE OR REPLACE FUNCTION calculate(IN x double precision, IN y double precision, OUT sum double precision) RETURNS double precision AS $BODY$ BEGIN sum = x + y; END; $BODY$ LANGUAGE plpgsql VOLATILE COST 100; 
ALTER FUNCTION calculate(double precision, double precision) OWNER TO postgres;
  
CREATE TABLE author(id bigint NOT NULL, firstname character varying(255), lastname character varying(255), version integer NOT NULL, CONSTRAINT author_pkey PRIMARY KEY (id));