drop table if exists FLK_GRAFA cascade;
drop table if exists STRUCTURE_DOCUMENT CASCADE;
drop table if exists TAG_DOCUMENT CASCADE;
drop table if exists FLK_CHECKS CASCADE ;
drop sequence if exists seq_flk_checks cascade;
drop sequence if exists seq_flk_grafa cascade;
drop sequence if exists seq_tag_document cascade;
drop sequence if exists seq_structure_document cascade;


create sequence if not exists seq_flk_checks start 100000;
create table FLK_CHECKS (
                            ID numeric(19) PRIMARY KEY,
                            ID_GRAFA numeric(19) not null,
                            TO_TAG_DOC_ID numeric(19) not null,
                            CODE_CHECK varchar(25) ,
                            DESCRIPTION_CHECK varchar(700) ,
                            DESCRIPTION_ERROR varchar(600) ,
                            D_ON date ,
                            D_OFF date
);

create sequence if not exists seq_flk_grafa start 100000;
create table FLK_GRAFA (
                           ID numeric(19) primary key,
                           ID_FORM numeric(20),
                           NAME_GRAFA varchar(300) ,
                           NAME_POLE varchar(300) ,
                           PATH_XML varchar(500)
);

create sequence if not exists seq_tag_document start 100000;
create table TAG_DOCUMENT (
                              ID numeric(19) primary key,
                              TO_STRDOC_ID numeric(19) not null,
                              NODE_NAME varchar(700) ,
                              NODE_PATH varchar(700) ,
                              PARENT_NAME varchar(700),
                              PARENT_PATH varchar(700),
                              PATTERN varchar(255),
                              PARENT_ID numeric(19)
);

create sequence if not exists seq_structure_document start 100000;
create table STRUCTURE_DOCUMENT (
                                    ID numeric(19) primary key,
                                    SCHEMA_LOCATION varchar(700) ,
                                    ROOT_ELEMENT varchar(700) ,
                                    SCHEMA_VERSION varchar(50) ,
                                    SCHEMA_NAME varchar(50)
);

ALTER TABLE FLK_CHECKS ALTER COLUMN ID SET DEFAULT nextval('seq_flk_checks');
ALTER TABLE FLK_GRAFA ALTER COLUMN ID SET DEFAULT nextval('seq_flk_grafa');
ALTER TABLE TAG_DOCUMENT ALTER COLUMN ID SET DEFAULT nextval('seq_tag_document');
ALTER TABLE STRUCTURE_DOCUMENT ALTER COLUMN ID SET DEFAULT nextval('seq_structure_document');

ALTER table FLK_CHECKS  ADD constraint FK_CHECKS_GRAFA_ID foreign key (ID_GRAFA)
    references FLK_GRAFA (ID)
    on DELETE cascade;

ALTER table FLK_CHECKS  ADD constraint FK_CHECKS_TAG_ID foreign key (TO_TAG_DOC_ID)
    references TAG_DOCUMENT (ID)
    on DELETE cascade;

ALTER table TAG_DOCUMENT  ADD constraint FK_TO_STRDOC_ID foreign key (TO_STRDOC_ID)
    references STRUCTURE_DOCUMENT (ID)
    on DELETE cascade;
