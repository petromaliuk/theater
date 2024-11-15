create table feedbacks (id integer not null,
    description varchar(400) not null,
    grade integer not null,
    film integer not null,
    user integer not null,
    primary key (id)) engine=InnoDB;

create table films (id integer not null,
    category varchar(30) not null,
    description varchar(400) not null,
    duration integer not null,
    pic varchar(100),
    title varchar(30) not null,
    primary key (id)) engine=InnoDB;

create table halls (id integer not null,
    address varchar(30) not null,
    name varchar(30) not null,
    places varbinary(255),
    shifts varbinary(255),
    primary key (id)) engine=InnoDB;

create table seances (id integer not null,
    day integer not null,
    hour integer not null,
    minute integer not null,
    month integer not null,
    year integer not null,
    price integer not null,
    film integer not null,
    hall integer not null,
    primary key (id)) engine=InnoDB;

create table tickets (id integer not null,
    place integer not null,
    rown integer not null,
    seance integer not null,
    user integer not null,
    primary key (id)) engine=InnoDB;

create table users (id integer not null,
    activation_code varchar(50),
    active bit not null,
    email varchar(40) not null,
    password varchar(100) not null,
    role varchar(50) not null,
    primary key (id)) engine=InnoDB;

create table feedbacks_seq (next_val bigint) engine=InnoDB;
create table films_seq (next_val bigint) engine=InnoDB;
create table halls_seq (next_val bigint) engine=InnoDB;
create table seances_seq (next_val bigint) engine=InnoDB;
create table tickets_seq (next_val bigint) engine=InnoDB;
create table users_seq (next_val bigint) engine=InnoDB;
insert into feedbacks_seq values ( 1 );
insert into films_seq values ( 1 );
insert into halls_seq values ( 1 );
insert into seances_seq values ( 1 );
insert into tickets_seq values ( 1 );
insert into users_seq values ( 2 );
alter table feedbacks add constraint FKgv8p3yeryy0nae6tt0efry841 foreign key (film) references films (id);
alter table feedbacks add constraint FKck7nkc2ed1wp1ht75dowigryw foreign key (user) references users (id);
alter table seances add constraint FKi6mhdi1jk5rxcd3eieqrn0nd9 foreign key (film) references films (id);
alter table seances add constraint FK913olt421n49hwoj2y8swuqp8 foreign key (hall) references halls (id);
alter table tickets add constraint FKgcb69ad1x2oxv8nrjojb3asyx foreign key (seance) references seances (id);
alter table tickets add constraint FKlydnex8t5yd51viecfllce0cu foreign key (user) references users (id);