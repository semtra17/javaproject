create schema if not exists `GRUPO-16-BDD-OO2-2021`;

use `grupo-16-BDD-OO2-2021`;

insert into user_role values(1,'2021-05-13', 'ROLE_ADMIN','ADMIN','2021-05-13');
insert into user_role values(2,'2021-05-13', 'ROLE_AUDITOR','AUDITOR','2021-05-13');

insert into user values(1, 11111111, 'admin@gmail.com', true, 'admin', 'admin','$2a$10$qoqhe.Nqr9jNnKsXdBWZ3edShJHwkbJni4dnbHHnpSMiFdKm5hZUW','admin',1);
insert into user values(2, 22222222, 'auditor@gmail.com', true, 'auditor', 'auditor','$2a$10$B9mfALrCwl7gNgksyajanuqhq2GpRAfcfTPN9iXrAAGN6BKB73hUy','auditor',2);



