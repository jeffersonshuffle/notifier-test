-- Table: public.priceaudit

-- DROP TABLE public.priceaudit;

CREATE TABLE public.priceaudit
(
    id bigint NOT NULL DEFAULT nextval('priceaudit_id_seq'::regclass),
    nomenclature_id bigint NOT NULL DEFAULT nextval('priceaudit_nomenclature_id_seq'::regclass),
    priceperunit numeric NOT NULL,
    datelastmodified date NOT NULL,
    tariff_id bigint NOT NULL DEFAULT nextval('priceaudit_tariff_id_seq'::regclass)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.priceaudit
    OWNER to postgres;

GRANT ALL ON TABLE public.priceaudit TO postgres;