
CREATE TABLE users(
  id_user integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  first_name character varying(20) NOT NULL,
  last_name character varying(20) NOT NULL,
  user_login character(10) NOT NULL,
  password character(32) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id_user) )
WITH (OIDS=FALSE);
ALTER TABLE users OWNER TO postgres;
  
CREATE TABLE lot (
  id_lot integer NOT NULL DEFAULT nextval('lot_id_seq'::regclass),
  code integer NOT NULL,
  name character varying(30) NOT NULL,
  finish_date timestamp without time zone NOT NULL,
  start_price numeric(10,2) NOT NULL,
  descriptions text,
  state character varying(10) NOT NULL,
  id_user integer NOT NULL,
  CONSTRAINT lot_pkey PRIMARY KEY (id_lot),
  CONSTRAINT fk_lot FOREIGN KEY (id_user)
      REFERENCES users (id_user) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE )
WITH (OIDS=FALSE);
ALTER TABLE lot OWNER TO postgres;
  
CREATE TABLE bid (
  id_bid integer NOT NULL DEFAULT nextval('bid_id_seq'::regclass),
  rate numeric(10,2) NOT NULL,
  is_winning_bid boolean NOT NULL,
  date_adding timestamp without time zone NOT NULL,
  id_lot integer NOT NULL,
  id_user integer NOT NULL,
  CONSTRAINT bid_pkey PRIMARY KEY (id_bid),
  CONSTRAINT fk_bid_lot FOREIGN KEY (id_lot)
      REFERENCES lot (id_lot) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_bid_user FOREIGN KEY (id_user)
      REFERENCES users (id_user) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE )
WITH (OIDS=FALSE);
ALTER TABLE bid OWNER TO postgres;

CREATE TABLE sequence (
  seq_name character varying(50) NOT NULL,
  seq_count numeric(38,0),
  CONSTRAINT sequence_pkey PRIMARY KEY (seq_name) )
WITH (OIDS=FALSE);
ALTER TABLE sequence OWNER TO postgres;

CREATE SEQUENCE user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100000002
  CACHE 1;
ALTER TABLE user_id_seq
  OWNER TO postgres;
  
CREATE SEQUENCE lot_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000000000000000010
  CACHE 1;
ALTER TABLE lot_id_seq
  OWNER TO postgres;
  
CREATE SEQUENCE bid_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10000000000000006
  CACHE 1;
ALTER TABLE bid_id_seq
  OWNER TO postgres;


