create table if not exists STATION
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    primary key(id)
);

create table if not exists SECTIONS
(
    id bigint auto_increment not null,
    up_section_id bigint null,
    down_section_id bigint null,
    up_station_id bigint not null,
    down_station_id bigint not null,
    primary key(id),
    foreign key(up_section_id) references SECTIONS(id),
    foreign key(down_section_id) references SECTIONS(id),
    foreign key(up_station_id) references STATION(id),
    foreign key(down_station_id) references STATION(id)
);


create table if not exists LINE
(
    id bigint auto_increment not null,
    name varchar(255) not null unique,
    color varchar(20) not null,
    primary key(id)
);
