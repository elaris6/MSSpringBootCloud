CREATE TABLE IF NOT EXISTS public.hoteles
(
    id_hotel integer NOT NULL DEFAULT nextval('hoteles_id_hotel_seq'::regclass),
    nombre character varying(45) COLLATE pg_catalog."default" NOT NULL,
    categoria integer NOT NULL,
    precio double precision NOT NULL,
    disponible boolean NOT NULL,
    CONSTRAINT hoteles_pkey PRIMARY KEY (id_hotel)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.hoteles
    OWNER to dbuser;
    

CREATE TABLE IF NOT EXISTS public.vuelos
(
    id_vuelo integer NOT NULL DEFAULT nextval('vuelos_id_vuelo_seq'::regclass),
    compania character varying(45) COLLATE pg_catalog."default" NOT NULL,
    fecha date NOT NULL,
    precio double precision NOT NULL,
    plazas integer NOT NULL,
    CONSTRAINT vuelos_pkey PRIMARY KEY (id_vuelo)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vuelos
    OWNER to dbuser;


CREATE TABLE IF NOT EXISTS public.reservas
(
    id_reserva integer NOT NULL DEFAULT nextval('reservas_id_reserva_seq'::regclass),
    nombre_cli character varying(60) COLLATE pg_catalog."default" NOT NULL,
    docu_cli character varying(20) COLLATE pg_catalog."default" NOT NULL,
    hotel integer,
    vuelo integer,
    CONSTRAINT reservas_pkey PRIMARY KEY (id_reserva)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.reservas
    OWNER to dbuser;
    
    
"American Airlines",	"2021-11-09",	95.15,	150)
"Alitalia",	"2021-11-03",	65.15,	92)
"Iberia",	"2021-11-01",	105.15,	80)
"Air Europa",	"2021-10-30",	69.75,	100)
"Lufthansa",	"2021-10-29",	87.45,	150)
"Spainair",	"2021-10-27",	57.45,	120)
"Air Europa",	"2021-10-01",	75.8,	0)
"Lufthansa",	"2021-09-30",	100.65,	7)
"Iberia",	"2021-09-17",	45.7,	32)

"Ritz",	5,	350.89,	true)
"Plaza Castilla",	4,	139.51,	true)
"Hilton",	5,	299.3,	true)
"Eurostars",	5,	332.8,	true)
"Arturo Norte",	4,	181.2,	true)
"Posada Marinera",	3,	78.9,	true)
"Hotelito del Amor",	2,	0,	false)