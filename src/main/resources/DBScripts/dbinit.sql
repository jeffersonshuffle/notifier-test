INSERT INTO public.tariffs(
	 name, date_created, is_active)
	VALUES 
    ('business','2017-11-15','t'),
    ('school','2017-11-15','t'),
('nogood','2017-11-15','f'),
('home','2017-11-15','t'),
('good','2017-11-16','t');

INSERT INTO public.tariff_details(
	tariff_id, price_per_unit, date_last_modified, nomenclature_id)
	VALUES (3,0.1,'2010-11-11',1),
     (4,0.2,'2011-11-11',1),
     (5,3,'2010-11-11',2),
    (5,1,'2010-11-11',1),
    (3,3,'2017-11-10',2),
     (4,6,'2017-11-10',2),
    (2,15,'2017-11-10',1),
     (2,15,'2017-11-10',2),
     (1,6.3,'2017-11-13',2),
     (1,6.3,'2017-11-13',1);
    
    INSERT INTO public.nomenclature(
	 name)
	VALUES ('call'),
('sms');

INSERT INTO public.users(
	 first_name, last_name, tariff_id)
	VALUES 
    ('petr','petr',1),
('ivan','ivan',1),
('vova','vova',2),
('guss','guss',2),
('anthony','anthony',5),
('siva','siva',1),
('jerry','jerry',4),
('tom','tom',3),
('smith','smith',5),
('peter','peter',4),
('fred','fred',5),
('bob','bob',5);