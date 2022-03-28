INSERT INTO PERSON(id, uid, email, name, surname, role, valid) VALUES(1, 'xjCuNxAorqblHw5syD1zpv2Zci32', 'test@pincio.it', 'Daniele1', null, 'USER', true);
INSERT INTO PERSON(id, uid, email, name, surname, role, valid) VALUES(2, 'ERiDyEUQV0abqiGKovW1a20bYGd2', 'test2@pincio.it', 'Daniele2', null, 'USER', true);
INSERT INTO PERSON(id, uid, email, name, surname, role, valid) VALUES(3, 'gzyS9mjHEnc9zi4pkgq9UAyPQuX2', 'test3@pincio.it', 'Daniele3', null, 'USER', true);
INSERT INTO PERSON(id, uid, email, name, surname, role, valid) VALUES(4, 'IuCydygfSVcBuceOd2qFHuimvua2', 'test4@pincio.it', 'Daniele4', null, 'USER', true);
INSERT INTO PERSON(id, uid, email, name, surname, role, valid) VALUES(5, 'Yv1Axe0SwlT1SlCEM4LPWjZd2js1', 'test5@pincio.it', 'Daniele5', null, 'USER', true);

INSERT INTO RACE_TYPE VALUES(1, 'Skates', 25);
INSERT INTO RACE_TYPE VALUES(2, 'Bike', 10);
INSERT INTO RACE_TYPE VALUES(3, 'Motorbike', 1.5);

INSERT INTO TEAM(id, name, type_id, country) VALUES(1, 'pirati', 1, 'IT');
INSERT INTO TEAM(id, name, type_id, country) VALUES(2, 'PincioBest', 1, 'IT');
INSERT INTO TEAM(id, name, type_id, country) VALUES(3, 'irriducibili', 1, 'IT');
INSERT INTO TEAM(id, name, type_id, country) VALUES(4, 'Los parasidos', 1, 'BL');

INSERT INTO PERSON_TEAM(team_id, person_id) VALUES(1, 1);
INSERT INTO PERSON_TEAM(team_id, person_id) VALUES(1, 2);
INSERT INTO PERSON_TEAM(team_id, person_id) VALUES(1, 3);
INSERT INTO PERSON_TEAM(team_id, person_id) VALUES(2, 4);
INSERT INTO PERSON_TEAM(team_id, person_id) VALUES(2, 5);

INSERT INTO RACE_DATA(id, end_datetime, end_photogroup, km_done, start_datetime, start_photogroup, team_id) VALUES
(1,	'2020-03-17 08:13:30', null, 15, '2020-03-17 07:13:30', null, 1),
(2,	'2020-03-18 08:13:30', null, 5, '2020-03-18 07:13:30', null, 1);

INSERT INTO TEAM_RACE_DATAS(team_id, race_datas_id) VALUES(1, 1);
INSERT INTO TEAM_RACE_DATAS(team_id, race_datas_id) VALUES(1, 2);

