/*
 * CREAMOS LA BASE DE DATOS EN CASO DE NO EXISTIR
 * */
if (select COUNT(1) from sysdatabases where name = 'parcial1JSP') < 1
begin
	create database parcial1JSP
end

use parcial1JSP
go

/*
 * CREAMOS LA TABLA DE USUARIO EN CASO DE NO EXISTIR
 * */
if OBJECT_ID('users') is null
begin
	create table users(
		id        int identity(1,1) primary key,
		name      varchar(100) not null,
		lastname  varchar(100) not null,
		email     varchar(100) unique not null,
		username  varchar(100) unique not null,
		password  varchar(32)  not null
	)
end
go

/*
 * INSERTAMOS LOS DATOS DE PRUEBA
 **/

insert into parcial1JSP.dbo.users values ('Janeider Steven', 'Rojas Tobar', 'janeider.rojas@cun.edu.co', 'jrojas', 'Passw0rd1')
insert into parcial1JSP.dbo.users values ('Edwin', 'Bustos', 'edwin.bustos@cun.edu.co', 'edwin24', 'Passw0rd2')
insert into parcial1JSP.dbo.users values ('Sandra', 'Gonzales', 'sandra.gonzales@cun.edu.co', 'sandra18', 'Passw0rd3')
