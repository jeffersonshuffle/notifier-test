COPY public.tariffs
FROM 'tariffs.csv' DELIMITER ',';

COPY public.users
FROM 'users.csv' DELIMITER ',';

COPY public.nomenclature
FROM 'nomenclature.csv' DELIMITER ',';

COPY public.tariff_details
FROM 'details.csv' DELIMITER ',';

