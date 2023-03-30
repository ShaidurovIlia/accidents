CREATE TABLE IF NOT EXISTS accident (
    id serial primary key,
    name VARCHAR,
    text VARCHAR,
    address VARCHAR,
    type_id int not null references type(id)
);

CREATE TABLE IF NOT EXISTS rule (
    id serial primary key,
    name VARCHAR
);

CRETAE TABLE IF NOT EXISTS type (
    id serial primary key,
    name VARCHAR
);

CREATE TABLE IF NOT EXISTS accident_rule (
   accident_id int REFERENCES accident(id),
   rule_id int REFERENCES rule(id),
   PRIMARY KEY(accident_id, rule_id)
);