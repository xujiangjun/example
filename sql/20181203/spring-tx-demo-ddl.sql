use local;
drop table if exists tx_user;
create table tx_user (
  id bigint unsigned not null auto_increment comment '用户id',
  is_deleted tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除:0-未删除;1-已删除;',
  gmt_create datetime not null comment '创建时间',
  gmt_modified datetime comment '修改时间',
  nickname varchar(32) comment '昵称',
  username varchar(32) not null comment '账号',
  password varchar(32) not null comment '密码',
  primary key (id),
  unique key uk_username (username)
)engine = innodb default charset = utf8mb4 comment = '用户表';

drop table if exists tx_login_log;
create table tx_login_log (
  id bigint unsigned not null auto_increment comment '日志id',
  gmt_create datetime not null comment '创建时间',
  username varchar(32) not null comment '账号',
  last_ip varchar(15) comment '最近一次登录ip',
  primary key (id),
  key idx_username (username)
)engine = innodb default charset = utf8 comment = '登录日志表';

