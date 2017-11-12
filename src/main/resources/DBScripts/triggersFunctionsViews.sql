-- FUNCTION: public.get_old_price(bigint, bigint)

-- DROP FUNCTION public.get_old_price(bigint, bigint);

CREATE OR REPLACE FUNCTION public.get_old_price(
	t_id bigint DEFAULT 1,
	n_id bigint DEFAULT 1)
    RETURNS numeric
    LANGUAGE 'sql'

    COST 100
    VOLATILE 
    ROWS 0
AS $BODY$

select  price from
	(SELECT priceaudit.priceperunit as price,
    	row_number()  OVER ( ORDER BY priceaudit.datelastmodified DESC) as rownum
   	FROM priceaudit
 	where priceaudit.tariff_id=t_id
  		and priceaudit.nomenclature_id=n_id
	) audit
where rownum=1
limit 1;

$BODY$;

ALTER FUNCTION public.get_old_price(bigint, bigint)
    OWNER TO postgres;


-- FUNCTION: public.process_price_audit()

-- DROP FUNCTION public.process_price_audit();

CREATE FUNCTION public.process_price_audit()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF 
    ROWS 0
AS $BODY$
 BEGIN
      UPDATE public.tariffs
		SET date_created=now()
		WHERE public.tariffs.id=OLD.tariff_id;
      INSERT INTO public.priceaudit select OLD.*;
      RETURN NULL;
  
    END;

$BODY$;

ALTER FUNCTION public.process_price_audit()
    OWNER TO postgres;

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

ALTER TABLE public.price_change_report
    OWNER TO postgres;


