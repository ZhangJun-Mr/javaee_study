create table t_red_packet (
  id          int           not null auto_increment
  comment '红包编号',
  user_id     int           not null
  comment '发红包用户',
  amount      decimal(2)    not null
  comment '红包金额',
  send_date   timestamp     not null
  comment '发红包时间',
  total       int           not null
  comment '小红包总数',
  unit_amount decimal(2)    not null
  comment '单个小红包金额',
  stock       int           not null
  comment '剩余小红包个数',
  version     int default 0 not null
  comment '版本',
  note        varchar(256)  not null
  comment '备注',
  primary key clustered(id)
)
  comment '红包表';

create table t_user_red_packet (
  id            int       not null  auto_increment
  comment '编号',
  red_packet_id int       not null
  comment '红包编号',
  user_id       int       not null
  comment '抢红包用户编号',
  amount        decimal   not null
  comment '抢红包金额',
  grab_time     timestamp not null
  comment '抢红包时间',
  note          int       not null
  comment '备注',
  primary key clustered(id)
)
  comment '用户抢红包表';

insert into t_red_packet(user_id, amount, send_date, total, unit_amount, stock, note)
values (1, 200000.00, now(), 20000, 10.00, 20000, '20万元金额，2万个小红包，每个10元');