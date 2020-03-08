insert into qh_users(login_username, login_password, nickname, user_des, create_date)
values('1246874542@qq.com','$2a$10$sXn41hKFt91MivvmrP5ty.dc1J1LqL.LX1Fff2ouCrPfMM36R/CB2',
'飞天徳', '开发管理员', '2020-03-08 16:55:03');

insert into qh_roles(role_type,role_name) values ('ROLE_MANAGER','管理员');

insert into qh_user_roles(u_id,r_id) values (2,1);