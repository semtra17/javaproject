create schema if not exists `GRUPO-16-BDD-OO2-2021`;

use `GRUPO-16-BDD-OO2-2021`;

insert into user_role values(1,'2021-05-13', 'ROLE_ADMIN','ADMIN','2021-05-13');
insert into user_role values(2,'2021-05-13', 'ROLE_AUDITOR','AUDITOR','2021-05-13');

insert into user values(1, 11111111, 'admin@gmail.com', true, 'admin', 'admin','$2a$10$qoqhe.Nqr9jNnKsXdBWZ3edShJHwkbJni4dnbHHnpSMiFdKm5hZUW','admin',1);
insert into user values(2, 22222222, 'auditor@gmail.com', true, 'auditor', 'auditor','$2a$10$B9mfALrCwl7gNgksyajanuqhq2GpRAfcfTPN9iXrAAGN6BKB73hUy','auditor',2);

insert into persona values (1, 'Perez', 33333333, 'Sergio');
insert into persona values (2, 'Rodriguez', 44444444, 'Luis');
insert into persona values (3, 'Gonzalez', 55555555, 'Lucas');

insert into rodado values(1, 'SFO500', 'Renault 9');
insert into rodado values(2, 'AA359RQ', 'Fiat Palio');
insert into rodado values(3, 'AB382OL', 'Ford Ka');

insert into permiso values(1, '2021-06-09', 1);
insert into permiso values(2, '2021-06-10', 2);
insert into permiso values(3, '2021-06-11', 3);
insert into permiso values(4, '2021-06-09', 2);
insert into permiso values(5, '2021-06-20', 1);
insert into permiso values(6, '2021-06-24', 2);

insert into permiso_diario values('Medico', 1);
insert into permiso_diario values('Compras', 6);

insert into permiso_periodo values(20, true, 2, 1);
insert into permiso_periodo values(10, false, 3, 3);
insert into permiso_periodo values(15, true, 4, 1);
insert into permiso_periodo values(15, false, 4, 1);
insert into permiso_periodo values(19, false, 5, 2);

insert into lugar values(1, '1832', 'LOMAS DE ZAMORA');
insert into lugar values(2, '1675', 'CABA');
insert into lugar values(3, '7600', 'MAR DEL PLATA');
insert into lugar values(4, '1900', 'LA PLATA');
insert into lugar values(5, '1841', 'MONTE GRANDE');

insert into permisoxlugar values(1,1);
insert into permisoxlugar values(1,5);
insert into permisoxlugar values(2,2);
insert into permisoxlugar values(2,3);
insert into permisoxlugar values(3,2);
insert into permisoxlugar values(3,4);
insert into permisoxlugar values(4,5);
insert into permisoxlugar values(4,2);