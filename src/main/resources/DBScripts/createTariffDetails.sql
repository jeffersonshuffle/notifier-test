-- Table: public.tariff_details

-- DROP TABLE public.tariff_details;

CREATE TABLE public.tariff_details
(
    id bigint NOT NULL DEFAULT nextval('tariff_details_id_seq'::regclass),
    tariff_id bigint NOT NULL,
    price_per_unit numeric NOT NULL,
    date_last_modified date NOT NULL,
    nomenclature_id bigint,
    CONSTRAINT tariff_details_pkey PRIMARY KEY (id),
    CONSTRAINT tariff_fk FOREIGN KEY (tariff_id)
        REFERENCES public.tariffs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tariff_details
    OWNER to postgres;

-- Trigger: price_changed

-- DROP TRIGGER price_changed ON public.tariff_details;

CREATE TRIGGER price_changed
    AFTER DELETE OR UPDATE 
    ON public.tariff_details
    FOR EACH ROW
    EXECUTE PROCEDURE public.process_price_audit();