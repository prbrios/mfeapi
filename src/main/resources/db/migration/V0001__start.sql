create table config (
    id serial primary key,
    module varchar(150),
    libsdir varchar(255),
    codigoativacao varchar(150)
);

insert into config (module, libsdir, codigoativacao) values ('MFE', 'C:\MFE\mfe.dll', '123456789');