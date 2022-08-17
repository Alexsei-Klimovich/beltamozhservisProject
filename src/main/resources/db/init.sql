drop table if exists FLK_GRAFA cascade;
drop table if exists STRUCTURE_DOCUMENT CASCADE;
drop table if exists TAG_DOCUMENT CASCADE;
drop table if exists FLK_CHECKS CASCADE ;
drop table if exists KIND_DOCUMENT CASCADE;
drop table if exists KIND_M2M_STRUCTURE CASCADE;
drop table if exists FLK_TYPE_CONTROL CASCADE;

drop sequence if exists seq_flk_checks cascade;
drop sequence if exists seq_flk_grafa cascade;
drop sequence if exists seq_tag_document cascade;
drop sequence if exists seq_structure_document cascade;
drop sequence if exists seq_kind_document cascade;
drop sequence if exists seq_kind_m2m_structure cascade;
drop sequence if exists seq_flk_type_control cascade;

create sequence if not exists seq_flk_checks start 100000;
create table FLK_CHECKS (
                            ID numeric(19) PRIMARY KEY,
                            ID_GRAFA numeric(19) not null,
                            TO_TAG_DOC_ID numeric(19) not null,
                            CODE_CHECK varchar(25) ,
                            DESCRIPTION_CHECK varchar(700) ,
                            DESCRIPTION_ERROR varchar(600) ,
                            D_ON date,
                            D_OFF date
);

create sequence if not exists seq_flk_grafa start 100000;
create table FLK_GRAFA (
                           ID numeric(19) primary key,
                           ID_FORM varchar(20),
                           NAME_GRAFA varchar(700) ,
                           NAME_POLE varchar(700) ,
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

create sequence if not exists seq_kind_document start 100000;
create table KIND_DOCUMENT (
                                    ID numeric(19) primary key,
                                    CODE_ENG VARCHAR(10) ,
                                    CODE_RUS VARCHAR(10) ,
                                    DESCRIPTION varchar(450) ,
                                    DATE_ACTIVATE date,
                                    DATE_DEACTIVATE date
);

create sequence if not exists seq_kind_m2m_structure start 100000;
create table KIND_M2M_STRUCTURE (
                                  ID numeric(19) primary key,
                                  TO_KIND_ID numeric(19) ,
                                  TO_STRUCT_DOC_ID numeric(19) ,
                                  DATE_ACTIVATE date,
                                  DATE_DEACTIVATE date,
                                  TO_FLK_TYPE_CNTRL_ID numeric(19)
);

create sequence if not exists seq_flk_type_control start 100000;
create table FLK_TYPE_CONTROL (
                                    ID numeric(19) primary key,
                                    NAME_TYPE varchar(150) ,
                                    DESCRIPTION varchar(500) ,
                                    D_ON date,
                                    D_OFF date,
                                    IS_ACTIVE varchar(1),
                                    DEFAULT_CONTROL numeric(38),
                                    DATE_CREATE date,
                                    DATE_UPDATE date
);



ALTER TABLE FLK_CHECKS ALTER COLUMN ID SET DEFAULT nextval('seq_flk_checks');
ALTER TABLE FLK_GRAFA ALTER COLUMN ID SET DEFAULT nextval('seq_flk_grafa');
ALTER TABLE TAG_DOCUMENT ALTER COLUMN ID SET DEFAULT nextval('seq_tag_document');
ALTER TABLE STRUCTURE_DOCUMENT ALTER COLUMN ID SET DEFAULT nextval('seq_structure_document');
ALTER TABLE FLK_TYPE_CONTROL ALTER COLUMN ID SET DEFAULT nextval('seq_flk_type_control');
ALTER TABLE KIND_DOCUMENT ALTER COLUMN ID SET DEFAULT nextval('seq_kind_document');
ALTER TABLE KIND_M2M_STRUCTURE ALTER COLUMN ID SET DEFAULT nextval('seq_kind_m2m_structure');

ALTER table FLK_CHECKS  ADD constraint FK_CHECKS_GRAFA_ID foreign key (ID_GRAFA)
    references FLK_GRAFA (ID)
    on DELETE cascade;

ALTER table FLK_CHECKS  ADD constraint FK_CHECKS_TAG_ID foreign key (TO_TAG_DOC_ID)
    references TAG_DOCUMENT (ID)
    on DELETE cascade;

ALTER table TAG_DOCUMENT  ADD constraint FK_TO_STRDOC_ID foreign key (TO_STRDOC_ID)
    references STRUCTURE_DOCUMENT (ID)
    on DELETE cascade;

ALTER table KIND_M2M_STRUCTURE  ADD constraint FK_TO_FLK_TYPE_CONTROL foreign key (TO_FLK_TYPE_CNTRL_ID)
    references FLK_TYPE_CONTROL (ID)
    on DELETE cascade;

ALTER table KIND_M2M_STRUCTURE  ADD constraint FK_TO_KIND_M2M_STRUCTURE foreign key (TO_KIND_ID)
    references KIND_DOCUMENT (ID)
    on DELETE cascade;

ALTER table KIND_M2M_STRUCTURE  ADD constraint FK_TO_STRUCTURE_DOCUMENT foreign key (TO_STRUCT_DOC_ID)
    references STRUCTURE_DOCUMENT (ID)
    on DELETE cascade;