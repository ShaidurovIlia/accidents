INSERT INTO rule (name) VALUES ('Rule. 1');
INSERT INTO rule (name) VALUES ('Rule. 2');
INSERT INTO rule (name) VALUES ('Rule. 3');

INSERT INTO type (name) VALUES ('Two vehicles');
INSERT INTO type (name) VALUES ('Vehicle and human');
INSERT INTO type (name) VALUES ('Vehicle and bicycle');

INSERT INTO accident(name, text, address, type_id) VALUES ('name 1', 'Description 1','Address 1', 1);
INSERT INTO accident(name, text, address, type_id) VALUES ('name 2', 'Description 2','Address 1', 2);
INSERT INTO accident(name, text, address, type_id) VALUES ('name 3', 'Description 3','Address 1', 3);

INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 1);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 3);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (2, 2);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (2, 3);
INSERT INTO accident_rule (accident_id, rule_id) VALUES (3, 3);