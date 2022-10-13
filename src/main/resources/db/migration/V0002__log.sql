create table log (
    id serial primary key,
    numsessao integer,
    xmlenvio text,
    retornomodulo text,
    dt timestamp
);

alter table config add column logativado boolean default false;