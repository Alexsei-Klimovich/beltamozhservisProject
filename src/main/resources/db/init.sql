drop table if exists FLK_GRAFA cascade;
drop table if exists STRUCTURE_DOCUMENT CASCADE;
drop table if exists TAG_DOCUMENT CASCADE;
drop table if exists FLK_CHECKS CASCADE ;

create table FLK_CHECKS (
                            ID numeric(19) PRIMARY KEY,
                            ID_GRAFA numeric(19) not null,
                            TO_TAG_DOC_ID numeric(19) not null,
                            CODE_CHECK varchar(25) not null,
                            DESCRIPTION_CHECK varchar(700) not null,
                            DESCRIPTION_ERROR varchar(600) not null,
                            D_ON date not null,
                            D_OFF date not null
);

create table FLK_GRAFA (
                           ID numeric(19) primary key,
                           ID_FORM numeric(20) not null,
                           NAME_GRAFA varchar(300) not null,
                           NAME_POLE varchar(300) not null,
                           PATH_XML varchar(500) not null,
                           foreign key (ID) references FLK_CHECKS(ID_GRAFA) on DELETE cascade
);

create table TAG_DOCUMENT (
                              ID numeric(19) primary key,
                              TO_STRDOC_ID numeric(19) not null,
                              NODE_NAME varchar(700) not null,
                              NODE_PATH varchar(700) not null,
                              PARENT_NAME varchar(700),
                              PARENT_PATH varchar(700),
                              PATTERN varchar(255),
                              PARENT_ID numeric(19),
                              foreign key (ID) references FLK_CHECKS(TO_TAG_DOC_ID) on delete cascade
);

create table STRUCTURE_DOCUMENT (
                                    ID numeric(19) primary key,
                                    SCHEMA_LOCATION varchar(700) not null,
                                    ROOT_ELEMENT varchar(700) not null,
                                    SCHEMA_VERSION varchar(50) not null,
                                    SCHEMA_NAME varchar(50) not null,
                                    foreign key (ID) references TAG_DOCUMENT(TO_STRDOC_ID) on delete cascade
);
