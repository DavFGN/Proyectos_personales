create table Usuario (
codigo serial,
nombre varchar(30),
pass varchar(30) not null,
fecha timestamp,
administrador boolean not null,
ban boolean,
primary key (codigo)
);

insert into Usuario (codigo, nombre, pass, fecha, administrador, ban) values (0, 
'Admin', 'a', '2019-04-20 19:36:37.07', TRUE, FALSE);

insert into Usuario (nombre, pass, fecha, administrador, ban) values ('David', 'a', 
'2019-04-20 19:36:37.07', TRUE, FALSE);

insert into Usuario (nombre, pass, fecha, administrador, ban) values ('Pepe', 'b', 
'2019-04-20 19:36:37.07', TRUE, FALSE);

insert into Usuario (nombre, pass, fecha, administrador, ban) values ('PruebaB', 'b', 
'2019-04-20 19:36:37.07', FALSE, TRUE);

create table Registro (
codigo serial,
cod_usuario int,
fecha_ult timestamp,
ip varchar(100),
primary key (codigo),
foreign key (cod_usuario)
references Usuario (codigo)
);

insert into Registro (cod_usuario, fecha_ult, ip) values (1, 
'2019-04-20 19:36:37.07', '192.168.1.1');

insert into Registro (cod_usuario, fecha_ult, ip) values (2, 
'2019-04-20 19:36:37.07', '192.168.1.1');

insert into Registro (cod_usuario, fecha_ult, ip) values (3, 
'2019-04-20 19:36:37.07', '192.168.1.1');

create table Partida (
codigo serial,
fecha timestamp,
primary key (codigo)	
);

create table ganador (
ganador int,
partida int,
foreign key (ganador) references Usuario (codigo),
foreign key (partida) references Partida (codigo)
);

create table perdedor (
perdedor int,
partida int,
foreign key (perdedor) references Usuario (codigo),
foreign key (partida) references Partida (codigo)
);

create table Tipo (
codigo int,
nombre varchar(30),
primary key (codigo)
);

insert into tipo values (1, 'viento');
insert into tipo values (2, 'fuego');
insert into tipo values (3, 'agua');
insert into tipo values (4, 'tierra');
insert into tipo values (5, 'luz');
insert into tipo values (6, 'oscuridad');

create table Luchador (
codigo varchar(5),
tipo int,
ataque int,
defensa int,
velocidad int,
posf1 int,
posf2 int,
bonusA int,
bonusT int,
bonusD int,
bonusE int,
bonusV int,
bonusS int,
primary key (codigo),
foreign key (tipo) references tipo(codigo)
);

insert into Luchador values ('L1', 1, 200, 20, 600, 1, 6, 0, 25, 0, 0, 25, 0);
insert into Luchador values ('L2', 1, 400, 10, 500, 2, 3, 15, 10, 0, 0, 10, 15);
insert into Luchador values ('L3', 2, 350, 30, 350, 1, 5, 15, 15, 10, 0, 10, 0);
insert into Luchador values ('L4', 2, 600, 25, 150, 4, 5, 25, 25, 0, 0, 0, 0);
insert into Luchador values ('L5', 3, 500, 40, 100, 1, 6, 10, 15, 15, 10, 0, 0);
insert into Luchador values ('L6', 3, 350, 35, 300, 1, 2, 8, 0, 9, 0, 8, 25);
insert into Luchador values ('L7', 4, 300, 40, 300, 4, 6, 10, 0, 10, 0, 5, 25);
insert into Luchador values ('L8', 4, 300, 60, 100, 2, 5, 5, 25, 20, 0, 0, 0);
insert into Luchador values ('L9', 5, 400, 20, 400, 3, 5, 15, 10, 0, 0, 10, 15);
insert into Luchador values ('L10', 5, 350, 35, 300, 3, 4, 10, 0, 15, 25, 0, 0);
insert into Luchador values ('L11', 6, 300, 50, 200, 4, 5, 20, 0, 0, 25, 5, 0);
insert into Luchador values ('L12', 6, 200, 20, 600, 1, 4, 20, 25, 5, 0, 0, 0);

create table Equipo (
codigo serial,
partida int,
jugador int,
luchador varchar(5),
foreign key (jugador) references Usuario (codigo),
foreign key (partida) references Partida (codigo),
foreign key (luchador) references Luchador (codigo)
);

create table correo (
codigo serial,
asunto varchar(30),
contenido varchar(500),
fecha timestamp,
administrador boolean,
primary key (codigo)
);

create table envio (
envio int,
correo int,
foreign key (envio) references Usuario (codigo),
foreign key (correo) references Correo (codigo)
);

create table remitente (
remitente int,
correo int,
foreign key (remitente) references Usuario (codigo),
foreign key (correo) references Correo (codigo)
);