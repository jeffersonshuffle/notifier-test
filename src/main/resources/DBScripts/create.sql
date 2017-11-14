
-- Table: public.tariffs

-- DROP TABLE public.tariffs;

CREATE TABLE public.tariffs
(
    id   bigserial  PRIMARY KEY,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    date_created date NOT NULL DEFAULT now(),
    is_active boolean DEFAULT false
    
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- Table: public.users
CREATE TABLE public.users
(
    id   bigserial  PRIMARY KEY,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    tariff_id bigint NOT NULL,
    
    CONSTRAINT tariff_fk FOREIGN KEY (tariff_id)
        REFERENCES public.tariffs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


-- Table: public.nomenclature
CREATE TABLE public.nomenclature
(
    id   bigserial  PRIMARY KEY,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL
    
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-- Table: public.priceaudit
CREATE TABLE public.priceaudit
(
    id   bigserial  PRIMARY KEY,
    nomenclature_id bigint NOT NULL,
    priceperunit numeric NOT NULL,
    datelastmodified date NOT NULL,
    tariff_id bigint NOT NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;




-- Table: public.tariff_details

CREATE TABLE public.tariff_details
(
    id  bigserial  PRIMARY KEY,
    tariff_id bigint NOT NULL,
    price_per_unit numeric NOT NULL,
    date_last_modified date NOT NULL,
    nomenclature_id bigint,
    
    CONSTRAINT tariff_fk FOREIGN KEY (tariff_id)
        REFERENCES public.tariffs (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


-- Function: public.process_price_audit
-- for trigger price_changed
CREATE FUNCTION public.process_price_audit()
    RETURNS trigger
    LANGUAGE 'plpgsql'

AS $BODY$
 BEGIN
      UPDATE public.tariffs
		SET date_created=now()
		WHERE public.tariffs.id=OLD.tariff_id;
      INSERT INTO public.priceaudit select OLD.*;
      RETURN NULL;
  
    END;

$BODY$;

-- Trigger: public.price_changed
-- occurs when price_per_unit is changed in Table: tariff_details
CREATE TRIGGER price_changed
    AFTER DELETE OR UPDATE 
    ON public.tariff_details
    FOR EACH ROW
    EXECUTE PROCEDURE public.process_price_audit();
 
 -- FUNCTION: public.get_old_price(bigint, bigint)

-- DROP FUNCTION public.get_old_price(bigint, bigint);

CREATE OR REPLACE FUNCTION public.get_old_price(
	t_id bigint DEFAULT 1,
	n_id bigint DEFAULT 1)
    RETURNS numeric
    LANGUAGE 'sql'

AS $BODY$

select  price from
	(SELECT priceaudit.priceperunit as price,
    	row_number()  OVER ( ORDER BY priceaudit.datelastmodified DESC) as rownum
   	FROM priceaudit
 	where priceaudit.tariff_id=t_id
  		and priceaudit.nomenclature_id=n_id
	) audits
where rownum=1
limit 1;

$BODY$;

-- View: public.price_change_report

-- DROP VIEW public.price_change_report;

CREATE OR REPLACE VIEW public.price_change_report AS
 SELECT tariffs.id AS tariff_id,
    tariffs.name AS tariff,
    nomenclature.name AS nomenclature,
    COALESCE(get_old_price(tariffs.id, nomenclature.id), 0::numeric) AS old_price,
    tariff_details.price_per_unit AS new_price,
    tariff_details.date_last_modified AS newdate
   FROM tariffs
     JOIN tariff_details ON tariffs.id = tariff_details.tariff_id
     JOIN nomenclature ON tariff_details.nomenclature_id = nomenclature.id;


 

-- Table: public.notification_query
-- store notification addressee
	
CREATE TABLE public.notification_query
(
    id   bigserial  PRIMARY KEY,
    tariff_id bigint NOT NULL,
    user_id bigint NOT NULL,
    date_end date NOT NULL,
    date_start date NOT NULL
    
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;   
