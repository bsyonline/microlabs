drop database IF EXISTS security;
-- 用户认证数据库
create database security default character set utf8mb4;
use security;

-- used in tests that use HSQL
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGTEXT,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGTEXT,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGTEXT,
  refresh_token VARCHAR(256)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGTEXT,
  authentication LONGTEXT
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(256), authentication LONGTEXT
);

drop table if exists oauth_approvals;
create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

drop table if exists t_user;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `state` int(1) DEFAULT '0',
  `account_expired` int(1) DEFAULT '0',
  `password_expired` int(1) DEFAULT '0',
  `account_locked` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
);

drop table if exists t_role;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists t_permission;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists t_role_permission;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE
);

drop table if exists t_user_role;
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
);

INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('1a6642b4-622d-11eb-a707-00059a3c7a00', 'product_id', '$2a$10$hth8lOFJKEWIf5pUnFkdOuwcvZCVNE5mo8v4SKHOofBPhzAVCr1uy', 'read,write', 'client_credentials,implicit,authorization_code,refresh_token,password', 'http://localhost:1234/', NULL, NULL, NULL, NULL, 'false');
