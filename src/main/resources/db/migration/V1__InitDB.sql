create table feedbacks (id integer not null,
    description varchar(400) not null,
    grade integer not null,
    film integer not null,
    usr integer not null,
    primary key (id));

create table films (id integer not null,
    category varchar(30) not null,
    description varchar(400) not null,
    duration integer not null,
    pic varchar(100),
    title varchar(30) not null,
    primary key (id));

create table halls (id integer not null,
    address varchar(30) not null,
    name varchar(30) not null,
    places integer array,
    shifts float4 array,
    primary key (id));

create table seances (id integer not null,
    day integer not null,
    hour integer not null,
    minute integer not null,
    month integer not null,
    year integer not null,
    price integer not null,
    film integer not null,
    hall integer not null,
    primary key (id));

create table tickets (id integer not null,
    place integer not null,
    rown integer not null,
    seance integer not null,
    usr integer not null,
    primary key (id));

create table users (id integer not null,
    activation_code varchar(50),
    active boolean not null,
    email varchar(40) not null,
    password varchar(100) not null,
    role varchar(50) not null,
    primary key (id));

create sequence feedbacks_seq start with 1 increment by 50;
create sequence films_seq start with 1 increment by 50;
create sequence halls_seq start with 1 increment by 50;
create sequence seances_seq start with 1 increment by 50;
create sequence tickets_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;
alter table feedbacks add constraint FKgv8p3yeryy0nae6tt0efry841 foreign key (film) references films;
alter table feedbacks add constraint FKck7nkc2ed1wp1ht75dowigryw foreign key (usr) references users;
alter table seances add constraint FKi6mhdi1jk5rxcd3eieqrn0nd9 foreign key (film) references films;
alter table seances add constraint FK913olt421n49hwoj2y8swuqp8 foreign key (hall) references halls;
alter table tickets add constraint FKgcb69ad1x2oxv8nrjojb3asyx foreign key (seance) references seances;
alter table tickets add constraint FKlydnex8t5yd51viecfllce0cu foreign key (usr) references users;