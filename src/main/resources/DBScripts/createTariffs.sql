-- Table: public.tariffs

-- DROP TABLE public.tariffs;

CREATE TABLE public.tariffs
(
    id bigint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    date_created date NOT NULL DEFAULT now(),
    is_active boolean DEFAULT false,
    CONSTRAINT tariffs_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tariffs
    OWNER to postgres;