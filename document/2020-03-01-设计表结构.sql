show databases;
use hq_jiaoyiyuan;

show tables;


-- 用户表
create table qh_users(
u_id int(11)  primary key auto_increment comment '主键，自增',
login_username varchar(50) not null unique comment '登陆名（邮箱） 唯一',
login_password varchar(50) not null comment '登陆密码',
nickname   varchar(100) comment '昵称',
user_des   varchar(255) comment '用户描述',
create_date  date   comment '创建时间'
)engine=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';


