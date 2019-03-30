create table sys_user (
  id       varchar(64)  not null primary key
  comment 'id',
  userName varchar(32)  not null
  comment '用户名',
  password varchar(256) not null
  comment '密码',
  nickName varchar(64) comment '昵称'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  comment '用户表';