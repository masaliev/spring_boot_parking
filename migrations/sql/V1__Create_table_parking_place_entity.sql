--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3 (Debian 12.3-1.pgdg100+1)
-- Dumped by pg_dump version 12.3 (Debian 12.3-1.pgdg100+1)

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

--
-- Name: parking_place_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.parking_place_entity (
    id bigint NOT NULL,
    name character varying(255),
    status character varying(255)
);


ALTER TABLE public.parking_place_entity OWNER TO postgres;

--
-- Name: parking_place_entity parking_place_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--
ALTER TABLE public.parking_place_entity DROP CONSTRAINT IF EXISTS parking_place_entity_pkey;
ALTER TABLE ONLY public.parking_place_entity
    ADD CONSTRAINT parking_place_entity_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
