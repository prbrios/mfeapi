alter table log drop column dt;

alter table log add column dtenvio timestamp;
alter table log add column dtretorno timestamp;