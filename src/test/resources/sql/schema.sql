CREATE TABLE `users_0` (
  `uid`  int  NOT NULL  COMMENT '唯一标识',
	`mobileNo`  varchar(20)   DEFAULT NULL COMMENT '手机号',
	`password`  varchar(56)   NULL COMMENT '密码',
	`nickName`  varchar(255)  NULL COMMENT '昵称',
	`photo`  varchar(255)  NULL COMMENT '头像',
	`status`  tinyint NOT NULL DEFAULT '0' COMMENT '̬状态 1:普通用户，3:会员用户，8:黑名单用户，9:标记删除',
	`area` varchar(64) NULL COMMENT '所在城市',
	`sex`  tinyint NOT NULL DEFAULT '0' COMMENT '״性别 0:未知，1:男性，2:女性',
	`birthday` date NULL comment '生日',
	`email` varchar(64)  NULL COMMENT '邮箱',
	`userName` varchar(20)  NULL COMMENT '真实姓名',
	`userKey` varchar(18)  NULL COMMENT '身份证号',
	`lastModifyTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
	`registDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
	`cinemaFavorites`  varchar(255) NULL comment '用户收藏的影院ID列表，用逗号分隔',
	PRIMARY KEY (`uid`),
	UNIQUE KEY (`mobileNo`)
);

CREATE TABLE `OpenID_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
	`OpenID` varchar(64) NOT NULL COMMENT '第三方ID(OpenID或者UnionID)',
	`OtherID` tinyint NOT NULL  COMMENT '第三方平台ID(10：新浪微博:，11：微信，12:QQ，13:手机号, 20:微票UID, 30:UnionID)',
	`nickName` varchar(255) COMMENT '第三方昵称',
	`Photo` varchar(255) NULL COMMENT '第三方头像',
	`createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次注册时间',
	`cinemaFavorites` varchar(255) comment '用户收藏的影院ID列表，用逗号分隔',
	`bindingStatus` tinyint DEFAULT 0 comment '0：未绑定手机号 1：绑定手机号',
	`lastLoginTime` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP   COMMENT '最近登陆时间',
	`status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：1:普通用户，3:会员用户，8:黑名单用户，9:标记删除',
	PRIMARY key (id),
	UNIQUE KEY ( OpenID,OtherID )
);

CREATE TABLE `idrelation_0` (
  `id`    int  NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `child_id`    varchar(64) NOT NULL COMMENT '子ID',
  `child_idtype`    int(6) not null  COMMENT '子id类型',
  `parent_id`    varchar(64) NOT NULL COMMENT '父ID',
  `parent_idtype`    int(6) NOT NULL COMMENT '父ID类型',
  `updatetime`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX(child_id,parent_id)
);

CREATE TABLE `idRelationHistory_0` (
  `id`    int  NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `parent_id`    varchar(64) NOT NULL COMMENT '父ID',
  `parent_idtype`    int(6) not null  COMMENT '父ID类型',
  `child_id`    varchar(64) NOT NULL COMMENT '子ID',
  `child_idtype`    int(6) NOT NULL COMMENT '子ID类型',
  `binding_status` tinyint NOT NULL  COMMENT '绑定状态(0：未绑定，1：绑定)',
  `updatetime`    timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX(parent_id,child_id)
);

CREATE TABLE `usertag_0` (
  `rid` int NOT NULL AUTO_INCREMENT COMMENT '自增主键rid即行id',
  `id`    varchar(64) NOT NULL COMMENT 'id',
  `idtype`    int(6) not null  COMMENT 'id类型',
  `tag`    varchar(20480) NOT NULL COMMENT '用户标签（JSON格式）',
  PRIMARY KEY (rid),
  UNIQUE INDEX(id,idtype)
);

CREATE TABLE `userdynamictag_0` (
  `rid` int NOT NULL AUTO_INCREMENT COMMENT '自增主键rid即行id',
  `id`   varchar(64) NOT NULL COMMENT 'id',
  `idtype`   int(6) not null  COMMENT 'id类型',
  `tag`  varchar(20480) NOT NULL COMMENT '用户营销标签（JSON格式）',
  PRIMARY KEY (rid),
  UNIQUE INDEX(id,idtype)
);

CREATE TABLE `OpenId2ReceiverMobile_0` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `openId`    varchar(64) NOT NULL COMMENT '第三方账号',
  `mobileNo`  varchar(20) not null COMMENT '手机号',
  `serviceType`    int(4) not null  COMMENT '发码手机所属业务类型',
  `isVerified`    tinyint(1) not null  COMMENT '手机号是否经过验证',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次创建时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '最近更新时间',
  PRIMARY KEY (id),
  UNIQUE KEY (openId, mobileNo, serviceType)
);

CREATE TABLE `ReceiverMobile2OpenId_0` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `mobileNo`  varchar(20) not null COMMENT '手机号',
  `openId`    varchar(64) NOT NULL COMMENT '第三方账号',
  PRIMARY KEY (id),
  UNIQUE KEY (mobileNo, openId)
);

CREATE TABLE `mobile2uid_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `mobileNo`  varchar(20)   not null COMMENT '手机号',
  `uid`  int NOT NULL  COMMENT '唯一标识',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  PRIMARY KEY (id),
  UNIQUE INDEX   ( mobileNo ),
  UNIQUE INDEX   ( uid )
);

CREATE TABLE `mobile2guid_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `mobileNo`  varchar(20)   not null COMMENT '手机号',
  `guid`  varchar(32) NOT NULL  COMMENT '用户唯一标识',
  PRIMARY KEY (id),
  UNIQUE INDEX   ( mobileNo )
);

CREATE TABLE `guid2mobile_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `guid`  varchar(32) NOT NULL  COMMENT '用户唯一标识',
  `mobileNo`  varchar(20)   not null COMMENT '手机号',
  `isMember` tinyint(1) not null  COMMENT '手机号是否绑定会员身份',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '最近一次修改时间',
  PRIMARY KEY (id),
  UNIQUE INDEX   ( guid )
);

CREATE TABLE `guid2openid_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `guid`  varchar(32) NOT NULL  COMMENT '用户唯一标识',
  `openId` varchar(64) NOT NULL  COMMENT '用户第三方Id',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '最近一次修改时间',
  PRIMARY KEY (id),
  INDEX   ( guid )
);

CREATE TABLE `openId2guid_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `openId`  varchar(64)   not null COMMENT '用户第三方Id',
  `guid`  varchar(32) NOT NULL  COMMENT '用户唯一标识',
  PRIMARY KEY (id),
  INDEX   ( openId )
);

CREATE TABLE `MemberOpsHistory_0` (
  `memberId`    varchar(64) NOT NULL COMMENT '会员id',
  `userIp`  varchar(40)   DEFAULT NULL COMMENT '会员所用IP',
  `mobileNo`  varchar(20)   DEFAULT NULL COMMENT '操作手机号',
  `opType`    int(4) not null  COMMENT '操作类型，目前为0：登录-1：修改手机号',
  `opTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间' ,
  INDEX ( memberId )
);

CREATE TABLE `userdevice_0` (
  `rid` int NOT NULL AUTO_INCREMENT COMMENT '自增主键rid即行id',
  `id`    varchar(64) NOT NULL COMMENT 'id',
  `idType`    int(6) not null  COMMENT 'id类型',
  `deviceId`    varchar(255) NOT NULL COMMENT '用户设备Id,如IMEI的内容',
  `deviceIdType`    int(6) not null  COMMENT '用户设备Id的类型,如IMEI',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次使用时间',
  `updatetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '最近使用时间',
  PRIMARY KEY (rid),
  UNIQUE KEY (id,deviceId)
);

CREATE TABLE `vipcardinfo_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `cardNo`   varchar(64) NOT NULL COMMENT '会员卡号',
  `cardType`   int(4) NOT NULL  COMMENT '会员卡片类型',
  `memberId`   varchar(64) NOT NULL COMMENT '会员id',
  `totalUsed`   int(10) NULL DEFAULT NULL  COMMENT '资格总使用次数',
  `startCountDate`  TIMESTAMP NULL DEFAULT NULL COMMENT '资格使用起始限定时间' ,
  `endCountDate`  TIMESTAMP NULL DEFAULT NULL COMMENT '资格使用结束限定时间' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开卡时间' ,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '会员卡信息改变时间' ,
  PRIMARY KEY (id),
  UNIQUE INDEX(cardNo)
);

CREATE TABLE `vipcardorderinfo_0` (
  `id` int  not null auto_increment COMMENT '自增主键id',
  `orderId`   varchar(64) NOT NULL COMMENT '使用会员卡下单的订单号',
  `openId`   varchar(64) NOT NULL COMMENT '第三方账号',
  `cardNo`   varchar(64) NOT NULL COMMENT '会员卡号',
  `lockedNum`  int(4) NOT NULL  COMMENT '锁定会员卡资格数量',
  `startCountDate`  TIMESTAMP NULL DEFAULT NULL COMMENT '资格使用起始限定时间' ,
  `endCountDate`  TIMESTAMP NULL DEFAULT NULL COMMENT '资格使用结束限定时间' ,
  `frequencyLimitation`   int(6) NULL DEFAULT NULL  COMMENT '剩余库存',
  `lockTime`  TIMESTAMP  NULL DEFAULT NULL COMMENT '   锁定会员卡资格的时间' ,
  `releaseTime`  TIMESTAMP NULL DEFAULT NULL COMMENT '释放会员卡资格的时间' ,
  `consumeTime`  TIMESTAMP NULL DEFAULT NULL COMMENT '消费会员卡资格的时间' ,
  `refundTime`  TIMESTAMP  NULL DEFAULT NULL COMMENT '回退会员卡资格的时间' ,
  `status`   tinyint  NULL DEFAULT NULL COMMENT '会员卡使用状态（0：锁定，1：释放，2：消费，3：回退）',
  PRIMARY KEY (id),
  UNIQUE INDEX(orderId)
);

CREATE TABLE `uid_gen_backup_0` (
  `id` int(11) unsigned NOT NULL auto_increment COMMENT '自增主键id',
  `uid` int(11) NOT NULL COMMENT 'uid数值',
  `add_time` int(11) COMMENT '记录插入时间',
  PRIMARY KEY (id)
);