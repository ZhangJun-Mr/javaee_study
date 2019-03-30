create table sys_user (
  id       varchar(64)  not null primary key
  comment 'id',
  user_name varchar(32)  not null
  comment '用户名',
  password varchar(256) not null
  comment '密码',
  nick_name varchar(64) comment '昵称',
  create_date timestamp default current_timestamp
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  comment '用户表';