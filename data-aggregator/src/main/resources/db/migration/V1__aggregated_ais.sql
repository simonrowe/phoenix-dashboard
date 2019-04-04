
    create table aggregated_ai (
       id bigint not null auto_increment,
        ai_count integer,
        time_id bigint,
        primary key (id)
    );

    create table application_instance (
       id bigint not null auto_increment,
        app_guid_id varchar(50),
        foundation_id varchar(50),
        instances integer,
        time_id bigint,
        primary key (id)
    );

    create table time (
       id bigint not null auto_increment,
        time datetime,
        primary key (id)
    );

create index idx_guid_foundation on application_instance (app_guid_id, foundation_id);
create index idx_foundationId on application_instance (foundation_id);
create index idx_time on application_instance (time_id);

    alter table aggregated_ai
       add constraint FKapcw4sb8ks2wvrpu81ryc80pk
       foreign key (time_id)
       references time (id);

    alter table application_instance
       add constraint FKgqtbqkueiv9eko467x55o7q80
       foreign key (time_id)
       references time (id);
