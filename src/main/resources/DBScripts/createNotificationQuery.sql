-- Table: public.notification_query

-- DROP TABLE public.notification_query;

CREATE TABLE public.notification_query
(
    id bigint NOT NULL,
    tariff_id bigint NOT NULL,
    user_id bigint NOT NULL,
    date_end date NOT NULL,
    date_start date NOT NULL,
    CONSTRAINT notification_query_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.notification_query
    OWNER to postgres;