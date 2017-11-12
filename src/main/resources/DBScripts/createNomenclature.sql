-- Table: public.nomenclature

-- DROP TABLE public.nomenclature;

CREATE TABLE public.nomenclature
(
    id bigint NOT NULL DEFAULT nextval('nomenclature_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT nomenclature_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.nomenclature
    OWNER to postgres;