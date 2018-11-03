create table if not exists ingredients(
    id varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists tacos(
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists tacos_ingredients(
    tacos_id bigint not null,
    ingredients_id varchar(4) not null,
    primary key(tacos_id, ingredients_id),
    foreign key(tacos_id) references tacos(id),
    foreign key(ingredients_id) references ingredients(id)
);

create table if not exists orders(
    id identity,
    placedAt timestamp not null,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(30) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    creditCardNumber varchar(16) not null,
    creditCardExpiration varchar(5) not null,
    creditCardCVV varchar(3) not null
);

create table if not exists orders_tacos(
    tacos_id bigint not null,
    orders_id bigint not null,
    primary key(tacos_id, orders_id),
    foreign key(tacos_id) references tacos(id),
    foreign key(orders_id) references orders(id)
);