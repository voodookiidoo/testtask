
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;



CREATE  DATABASE   transport_db  WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C' ;


ALTER DATABASE transport_db OWNER TO voodookiidoo;

\connect transport_db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE  if not exists public.transport_table (
    id integer NOT NULL,
    name character varying(255),
    brand character varying(255),
    year_out integer,
    trailer boolean,
    type character varying(255),
    category character varying(255),
    gov_number character varying(255)
);


ALTER TABLE public.transport_table OWNER TO voodookiidoo;


ALTER TABLE public.transport_table ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.transport_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



INSERT INTO public.transport_table (id, name, brand, year_out, trailer, type, category, gov_number) OVERRIDING SYSTEM VALUE VALUES (7, 'somesome', 'asdasdasd', 2000, true, 'aboba', 'adoda', '12');
INSERT INTO public.transport_table (id, name, brand, year_out, trailer, type, category, gov_number) OVERRIDING SYSTEM VALUE VALUES (4, 'newmodel', 'asdasdsa', 2000, true, 'tyyype', 'susnum', '12');



SELECT pg_catalog.setval('public.transport_table_id_seq', 14, true);



ALTER TABLE ONLY public.transport_table
    ADD CONSTRAINT transport_table_pk PRIMARY KEY (id);
