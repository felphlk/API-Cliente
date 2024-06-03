CREATE TABLE public.client (
	id bigserial NOT NULL,
	cpf varchar(255) NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	CONSTRAINT client_pkey PRIMARY KEY (id)
);

INSERT INTO public.client (cpf,email,name) VALUES
	 ('123412','art@hotmail.com','Arthur'),
	 ('123123','art@hotmail.com','Bruno'),
	 ('123123','teste2@hotmail.com','Carlos'),
	 ('123123','teste5@hotmail.com','Dert'),
	 ('123123','teste512@hotmail.com','Eduardo'),
	 ('123123','kosakdso@hotmail.com','Felipe'),
	 ('123123','asdasdas@hotmail.com','Gabriel'),
	 ('123123','AAAAAAAAAAA@hotmail.com','Halam'),
	 ('123123','naoasod@hotmail.com','Igor'),
	 ('123123','SADASDASd@hotmail.com','Jorge');
