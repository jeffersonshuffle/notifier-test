-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tariff_id bigint NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT tariff_fk FOREIGN KEY (tariff_id)
        REFERENCES public.tariffs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;