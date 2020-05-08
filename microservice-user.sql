/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : microservice-user

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 08/05/2020 14:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action`  (
  `action_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `token` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作token',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回信息',
  `ip` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作ip地址',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `username` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态：正常，删除',
  PRIMARY KEY (`action_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES (47, '获取公钥', NULL, '', '{\"code\":200,\"data\":{\"publicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUJvaRJHwveS+sB9iguKDMd0lnRddWRK5vJHTa+tVVpmEqIV9B8nqTo02+TZvjqH1iNzaiZSXymRvfIICwofax+K0600PWz98OgpsdZtKKz8gO4/93JvlpvQq0hH33pYf/eEfZKQlGYyXQEc/NnNilMaxlBDRJLFnwWPSFZHnlywIDAQAB\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:05:33', '2020-05-08 14:05:33', '正常');
INSERT INTO `action` VALUES (48, '用户登录', NULL, 'user = UserLogin(username=admin, password=qdochOl+NgdhfxhOTPyOCuS99OhawW/QrBQfAETiCo6Tu13Hu5suUugUndT9ddo1pS4QzaLgNKolAiOHuibkovIqQYNpgCuWH0yh8OjZx1VW0bn1AsBBywCtPnonA9At8FQeeAX6ztQ2XKbdN4YJVKZdbrVXqxldpM8PHp3zpGc=)', '{\"code\":200,\"data\":{\"token\":\"nFofGKzWzqjMAPssdE1mDLCoc0Z673TVAFnpDLSoxdlghTy1rdP6o59xI57XzpTTX6o1k5C9y0Rw2lYAFVhbq8G5qoZs6xyMiWLyeGtJCXEz/O2Z7PLgq1JtBtRBBpcaVttffrtf37lfqJkUXmptSrJcqgIbSWGPwqhgskcu34w=\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:05:33', '2020-05-08 14:05:33', '正常');
INSERT INTO `action` VALUES (49, '获取公钥', NULL, '', '{\"code\":200,\"data\":{\"publicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUJvaRJHwveS+sB9iguKDMd0lnRddWRK5vJHTa+tVVpmEqIV9B8nqTo02+TZvjqH1iNzaiZSXymRvfIICwofax+K0600PWz98OgpsdZtKKz8gO4/93JvlpvQq0hH33pYf/eEfZKQlGYyXQEc/NnNilMaxlBDRJLFnwWPSFZHnlywIDAQAB\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:07:12', '2020-05-08 14:07:12', '正常');
INSERT INTO `action` VALUES (50, '用户登录', NULL, 'user = UserLogin(username=admin, password=UgbTGz2cLfvCFkkF7RUBgWP0PkgASlr3pFKx2+Q6apA0/XfaPa9NXE78iRstm4IhToJ3mAZ7aNF01kIihhGSanaN6+0u5mPLXRKe/fq9ap77Sr+w2tlIGJu//UOBwfokDuve/csucczj5ZHpP+kyhg5NDIQ/PClpBgLSRcypBlg=)', '{\"code\":200,\"data\":{\"token\":\"KPZ8xtoWmG9rE9ow8B2O62PBH24/DfNiFrKI48t3CJw8C1fno/dnEBp9le4bXSsQ3T5T5XunJAL1mmFR716fR1kqDFwykjFBblB+1iaCTh/f0oXSZ7ck+w5cP7L7f72hgepNuOomLr9J63K7z8e/yfqsRy2Z7t9CVvPQOUTQBUo=\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:07:42', '2020-05-08 14:07:42', '正常');
INSERT INTO `action` VALUES (51, '获取公钥', NULL, '', '{\"code\":200,\"data\":{\"publicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUJvaRJHwveS+sB9iguKDMd0lnRddWRK5vJHTa+tVVpmEqIV9B8nqTo02+TZvjqH1iNzaiZSXymRvfIICwofax+K0600PWz98OgpsdZtKKz8gO4/93JvlpvQq0hH33pYf/eEfZKQlGYyXQEc/NnNilMaxlBDRJLFnwWPSFZHnlywIDAQAB\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:10:07', '2020-05-08 14:10:07', '正常');
INSERT INTO `action` VALUES (52, '用户登录', NULL, 'user = UserLogin(username=admin, password=0UmvZL3f5bA+lwVUxmL1kxOS0fNNVUm7Y+8A5ggOB/UUqclu0W18ayuNxwqdq/DHPfn7+WiEPL07Gv7h03SQZZOWWyrFLqNlVhmKDkJlTIve3tJvzT9P+n7HJ6jed33ikzDgvOD40sm6F3J5nEdmY8dw/nw+Vf6uDVIb4Dia3lU=)', '{\"code\":200,\"data\":{\"token\":\"dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=\"},\"msg\":\"成功！\"}', '127.0.0.1', NULL, NULL, '2020-05-08 14:10:08', '2020-05-08 14:10:08', '正常');
INSERT INTO `action` VALUES (53, '根据用户token获取用户信息', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', 'request = org.apache.catalina.connector.RequestFacade@105b3f38', '{\"code\":200,\"data\":{\"data\":{\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"introduction\":\"\",\"name\":\"admin\",\"roles\":[\"admin\"]}},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:10:08', '2020-05-08 14:10:08', '正常');
INSERT INTO `action` VALUES (54, '根据用户token获取用户菜单', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', 'request = org.apache.catalina.connector.RequestFacade@105b3f38', '{\"code\":200,\"data\":{\"data\":[{\"alwaysShow\":false,\"children\":[{\"alwaysShow\":false,\"component\":\"/adminHome\",\"meta\":{\"breadcrumb\":true,\"noCache\":false,\"title\":\"后台主页面\"},\"name\":\"AdminHome\",\"path\":\"adminHome\"}],\"component\":\"Layout\",\"hidden\":true,\"path\":\"\"},{\"alwaysShow\":false,\"children\":[{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysUser\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-user\",\"noCache\":false,\"title\":\"用户管理\"},\"name\":\"SysUser\",\"path\":\"sysUser\"},{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysRoles\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-roles\",\"noCache\":false,\"title\":\"角色管理\"},\"name\":\"SysRoles\",\"path\":\"sysRoles\"},{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysAuthority\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-authority\",\"noCache\":false,\"title\":\"权限管理\"},\"name\":\"SysAuthority\",\"path\":\"sysAuthority\"}],\"component\":\"Layout\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"system\",\"noCache\":false,\"title\":\"系统管理\"},\"name\":\"System\",\"path\":\"/system\",\"redirect\":\"noRedirect\"}]},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:10:08', '2020-05-08 14:10:08', '正常');
INSERT INTO `action` VALUES (55, '树形权限列表', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', '', '{\"code\":200,\"data\":{\"data\":[{\"authorityId\":61,\"children\":[{\"authorityId\":62,\"children\":[],\"createTime\":\"2020-05-08T13:25:53\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户信息接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/user/info\"},{\"authorityId\":63,\"children\":[],\"createTime\":\"2020-05-08T13:26:33\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户菜单接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/getMenuList\"}],\"createTime\":\"2020-05-08T13:24:54\",\"display\":\"隐藏\",\"fAuthorityId\":0,\"name\":\"后台主页面\",\"sort\":1,\"type\":\"页面\",\"url\":\"/adminHome\"},{\"authorityId\":47,\"children\":[{\"authorityId\":60,\"children\":[],\"createTime\":\"2020-04-27T11:41:07\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-user\",\"name\":\"用户管理\",\"sort\":1,\"type\":\"页面\",\"url\":\"sysUser\"},{\"authorityId\":59,\"children\":[],\"createTime\":\"2020-04-27T09:39:51\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-roles\",\"name\":\"角色管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"sysRoles\"},{\"authorityId\":48,\"children\":[{\"authorityId\":65,\"children\":[],\"createTime\":\"2020-05-08T13:28:16\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"获取树形权限列表接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/authority/getTreeAuthorityList\"},{\"authorityId\":64,\"children\":[],\"createTime\":\"2020-05-08T13:27:09\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"添加权限接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/addAuthority\"},{\"authorityId\":66,\"children\":[],\"createTime\":\"2020-05-08T13:28:52\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"修改权限接口\",\"sort\":3,\"type\":\"按钮\",\"url\":\"/user/back/authority/updateAuthority\"}],\"createTime\":\"2020-04-24T21:00:41\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-authority\",\"name\":\"权限管理\",\"sort\":3,\"type\":\"页面\",\"url\":\"sysAuthority\"}],\"createTime\":\"2020-04-25T10:22:28\",\"display\":\"显示\",\"fAuthorityId\":0,\"icon\":\"system\",\"name\":\"系统管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"/system\"}]},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:10:08', '2020-05-08 14:10:08', '正常');
INSERT INTO `action` VALUES (56, '根据用户token获取用户信息', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', 'request = org.apache.catalina.connector.RequestFacade@105b3f38', '{\"code\":200,\"data\":{\"data\":{\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\",\"introduction\":\"\",\"name\":\"admin\",\"roles\":[\"admin\"]}},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:11:12', '2020-05-08 14:11:12', '正常');
INSERT INTO `action` VALUES (57, '根据用户token获取用户菜单', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', 'request = org.apache.catalina.connector.RequestFacade@105b3f38', '{\"code\":200,\"data\":{\"data\":[{\"alwaysShow\":false,\"children\":[{\"alwaysShow\":false,\"component\":\"/adminHome\",\"meta\":{\"breadcrumb\":true,\"noCache\":false,\"title\":\"后台主页面\"},\"name\":\"AdminHome\",\"path\":\"adminHome\"}],\"component\":\"Layout\",\"hidden\":true,\"path\":\"\"},{\"alwaysShow\":false,\"children\":[{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysUser\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-user\",\"noCache\":false,\"title\":\"用户管理\"},\"name\":\"SysUser\",\"path\":\"sysUser\"},{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysRoles\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-roles\",\"noCache\":false,\"title\":\"角色管理\"},\"name\":\"SysRoles\",\"path\":\"sysRoles\"},{\"alwaysShow\":false,\"children\":[],\"component\":\"/system/sysAuthority\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"sys-authority\",\"noCache\":false,\"title\":\"权限管理\"},\"name\":\"SysAuthority\",\"path\":\"sysAuthority\"}],\"component\":\"Layout\",\"hidden\":false,\"meta\":{\"breadcrumb\":true,\"icon\":\"system\",\"noCache\":false,\"title\":\"系统管理\"},\"name\":\"System\",\"path\":\"/system\",\"redirect\":\"noRedirect\"}]},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:11:12', '2020-05-08 14:11:12', '正常');
INSERT INTO `action` VALUES (58, '树形权限列表', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', '', '{\"code\":200,\"data\":{\"data\":[{\"authorityId\":61,\"children\":[{\"authorityId\":62,\"children\":[],\"createTime\":\"2020-05-08T13:25:53\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户信息接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/user/info\"},{\"authorityId\":63,\"children\":[],\"createTime\":\"2020-05-08T13:26:33\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户菜单接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/getMenuList\"}],\"createTime\":\"2020-05-08T13:24:54\",\"display\":\"隐藏\",\"fAuthorityId\":0,\"name\":\"后台主页面\",\"sort\":1,\"type\":\"页面\",\"url\":\"/adminHome\"},{\"authorityId\":47,\"children\":[{\"authorityId\":60,\"children\":[],\"createTime\":\"2020-04-27T11:41:07\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-user\",\"name\":\"用户管理\",\"sort\":1,\"type\":\"页面\",\"url\":\"sysUser\"},{\"authorityId\":59,\"children\":[],\"createTime\":\"2020-04-27T09:39:51\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-roles\",\"name\":\"角色管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"sysRoles\"},{\"authorityId\":48,\"children\":[{\"authorityId\":65,\"children\":[],\"createTime\":\"2020-05-08T13:28:16\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"获取树形权限列表接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/authority/getTreeAuthorityList\"},{\"authorityId\":64,\"children\":[],\"createTime\":\"2020-05-08T13:27:09\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"添加权限接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/addAuthority\"},{\"authorityId\":66,\"children\":[],\"createTime\":\"2020-05-08T13:28:52\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"修改权限接口\",\"sort\":3,\"type\":\"按钮\",\"url\":\"/user/back/authority/updateAuthority\"}],\"createTime\":\"2020-04-24T21:00:41\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-authority\",\"name\":\"权限管理\",\"sort\":3,\"type\":\"页面\",\"url\":\"sysAuthority\"}],\"createTime\":\"2020-04-25T10:22:28\",\"display\":\"显示\",\"fAuthorityId\":0,\"icon\":\"system\",\"name\":\"系统管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"/system\"}]},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:11:13', '2020-05-08 14:11:13', '正常');
INSERT INTO `action` VALUES (59, '树形权限列表', 'dP2Lr3P/xPv39D5ZZAAkIOoWpTGPtvuRfJtAYASq1c0rKk99TlmKNpjorrxPOkuN83ccP6Q1RwDoL3BW31GVvHUWjMVgELgNoTtCFuy0zFB+iQikswuSXhyT+r2zU5iDwE4clMUkHQ54npyf4Vg0y4MYIJi4J6zsLqVhG5o0kYY=', '', '{\"code\":200,\"data\":{\"data\":[{\"authorityId\":61,\"children\":[{\"authorityId\":62,\"children\":[],\"createTime\":\"2020-05-08T13:25:53\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户信息接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/user/info\"},{\"authorityId\":63,\"children\":[],\"createTime\":\"2020-05-08T13:26:33\",\"display\":\"显示\",\"fAuthorityId\":61,\"name\":\"获取用户菜单接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/getMenuList\"}],\"createTime\":\"2020-05-08T13:24:54\",\"display\":\"隐藏\",\"fAuthorityId\":0,\"name\":\"后台主页面\",\"sort\":1,\"type\":\"页面\",\"url\":\"/adminHome\"},{\"authorityId\":47,\"children\":[{\"authorityId\":60,\"children\":[],\"createTime\":\"2020-04-27T11:41:07\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-user\",\"name\":\"用户管理\",\"sort\":1,\"type\":\"页面\",\"url\":\"sysUser\"},{\"authorityId\":59,\"children\":[],\"createTime\":\"2020-04-27T09:39:51\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-roles\",\"name\":\"角色管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"sysRoles\"},{\"authorityId\":48,\"children\":[{\"authorityId\":65,\"children\":[],\"createTime\":\"2020-05-08T13:28:16\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"获取树形权限列表接口\",\"sort\":1,\"type\":\"按钮\",\"url\":\"/user/back/authority/getTreeAuthorityList\"},{\"authorityId\":64,\"children\":[],\"createTime\":\"2020-05-08T13:27:09\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"添加权限接口\",\"sort\":2,\"type\":\"按钮\",\"url\":\"/user/back/authority/addAuthority\"},{\"authorityId\":66,\"children\":[],\"createTime\":\"2020-05-08T13:28:52\",\"display\":\"显示\",\"fAuthorityId\":48,\"name\":\"修改权限接口\",\"sort\":3,\"type\":\"按钮\",\"url\":\"/user/back/authority/updateAuthority\"}],\"createTime\":\"2020-04-24T21:00:41\",\"display\":\"显示\",\"fAuthorityId\":47,\"icon\":\"sys-authority\",\"name\":\"权限管理\",\"sort\":3,\"type\":\"页面\",\"url\":\"sysAuthority\"}],\"createTime\":\"2020-04-25T10:22:28\",\"display\":\"显示\",\"fAuthorityId\":0,\"icon\":\"system\",\"name\":\"系统管理\",\"sort\":2,\"type\":\"页面\",\"url\":\"/system\"}]},\"msg\":\"成功！\"}', '127.0.0.1', 7, 'admin', '2020-05-08 14:11:15', '2020-05-08 14:11:15', '正常');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `name` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `url` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限地址',
  `type` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型：页面，按钮',
  `f_authority_id` int(11) NULL DEFAULT NULL COMMENT '父权限ID',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `display` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否显示：显示，隐藏',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'icon',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态:正常，删除',
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (47, '系统管理', '/system', '页面', 0, 2, '显示', 'system', '2020-04-25 10:22:28', '2020-05-08 13:24:59', '正常');
INSERT INTO `authority` VALUES (48, '权限管理', 'sysAuthority', '页面', 47, 3, '显示', 'sys-authority', '2020-04-24 21:00:41', '2020-04-24 21:00:41', '正常');
INSERT INTO `authority` VALUES (51, '嵌套路由', '/nested', '页面', 0, 2, '显示', 'nested', '2020-04-25 16:12:07', '2020-04-26 18:33:21', '删除');
INSERT INTO `authority` VALUES (52, '菜单1', 'menu1', '页面', 51, 1, '显示', NULL, '2020-04-25 16:12:55', '2020-04-26 18:32:17', '删除');
INSERT INTO `authority` VALUES (59, '角色管理', 'sysRoles', '页面', 47, 2, '显示', 'sys-roles', '2020-04-27 09:39:51', '2020-04-27 09:39:51', '正常');
INSERT INTO `authority` VALUES (60, '用户管理', 'sysUser', '页面', 47, 1, '显示', 'sys-user', '2020-04-27 11:41:07', '2020-04-27 11:41:07', '正常');
INSERT INTO `authority` VALUES (61, '后台主页面', '/adminHome', '页面', 0, 1, '隐藏', NULL, '2020-05-08 13:24:54', '2020-05-08 13:53:13', '正常');
INSERT INTO `authority` VALUES (62, '获取用户信息接口', '/user/back/user/info', '按钮', 61, 1, '显示', NULL, '2020-05-08 13:25:53', '2020-05-08 13:26:01', '正常');
INSERT INTO `authority` VALUES (63, '获取用户菜单接口', '/user/back/authority/getMenuList', '按钮', 61, 2, '显示', NULL, '2020-05-08 13:26:33', '2020-05-08 13:26:33', '正常');
INSERT INTO `authority` VALUES (64, '添加权限接口', '/user/back/authority/addAuthority', '按钮', 48, 2, '显示', NULL, '2020-05-08 13:27:09', '2020-05-08 13:27:49', '正常');
INSERT INTO `authority` VALUES (65, '获取树形权限列表接口', '/user/back/authority/getTreeAuthorityList', '按钮', 48, 1, '显示', NULL, '2020-05-08 13:28:16', '2020-05-08 13:28:16', '正常');
INSERT INTO `authority` VALUES (66, '修改权限接口', '/user/back/authority/updateAuthority', '按钮', 48, 3, '显示', NULL, '2020-05-08 13:28:52', '2020-05-08 13:28:52', '正常');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `f_role_id` int(11) NULL DEFAULT NULL COMMENT '父角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态:正常,删除',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (31, '普通用户', 0, '2020-04-27 10:18:00', '2020-04-27 10:18:00', '正常');
INSERT INTO `role` VALUES (32, '测试用户', 31, '2020-04-26 18:52:59', '2020-04-26 18:52:59', '正常');
INSERT INTO `role` VALUES (33, '测试用户', 31, '2020-04-27 02:53:07', '2020-04-27 11:40:08', '删除');
INSERT INTO `role` VALUES (34, 'test', 31, '2020-04-27 08:23:42', '2020-04-27 16:43:21', '删除');

-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority`  (
  `role_authority_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `authority_id` int(11) NULL DEFAULT NULL COMMENT '权限ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态:正常，删除',
  PRIMARY KEY (`role_authority_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authority
-- ----------------------------
INSERT INTO `role_authority` VALUES (44, 31, 60, '2020-04-28 18:16:17', '2020-04-28 18:27:16', '删除');
INSERT INTO `role_authority` VALUES (45, 31, 48, '2020-04-28 18:16:17', '2020-04-28 18:27:22', '删除');
INSERT INTO `role_authority` VALUES (46, 31, 47, '2020-04-28 18:16:17', '2020-05-06 09:58:44', '正常');
INSERT INTO `role_authority` VALUES (47, 31, 59, '2020-04-28 18:27:28', '2020-04-28 18:27:28', '正常');
INSERT INTO `role_authority` VALUES (48, 32, 59, '2020-05-06 09:30:46', '2020-05-06 09:30:46', '正常');
INSERT INTO `role_authority` VALUES (49, 32, 48, '2020-05-06 09:30:46', '2020-05-06 09:30:46', '正常');
INSERT INTO `role_authority` VALUES (50, 32, 47, '2020-05-06 09:30:49', '2020-05-06 09:30:49', '正常');
INSERT INTO `role_authority` VALUES (51, 31, 60, '2020-05-06 09:57:07', '2020-05-06 09:57:07', '正常');
INSERT INTO `role_authority` VALUES (52, 31, 48, '2020-05-06 09:57:07', '2020-05-06 09:57:07', '正常');
INSERT INTO `role_authority` VALUES (53, 31, 65, '2020-05-08 13:29:41', '2020-05-08 13:29:41', '正常');
INSERT INTO `role_authority` VALUES (54, 31, 64, '2020-05-08 13:29:41', '2020-05-08 13:29:41', '正常');
INSERT INTO `role_authority` VALUES (55, 31, 66, '2020-05-08 13:29:41', '2020-05-08 13:29:41', '正常');
INSERT INTO `role_authority` VALUES (56, 31, 61, '2020-05-08 13:44:35', '2020-05-08 13:44:35', '正常');
INSERT INTO `role_authority` VALUES (57, 31, 62, '2020-05-08 13:44:35', '2020-05-08 13:44:35', '正常');
INSERT INTO `role_authority` VALUES (58, 31, 63, '2020-05-08 13:44:35', '2020-05-08 13:44:35', '正常');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '账号状态：正常，冻结',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态:正常,删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (7, 'admin', '0296ad1f8b74ea72b6cf7f619b221600', 'qAa_u.QgOK&3 &;~\\r3TvdXdi_', '正常', '2019-12-19 22:51:37', '2020-04-28 10:09:41', '正常');
INSERT INTO `user` VALUES (8, 'admin', '0296ad1f8b74ea72b6cf7f619b221600', 'qAa_u.QgOK&3 &;~\\r3TvdXdi_', '正常', '2019-11-12 06:51:37', '2020-04-27 17:14:30', '删除');
INSERT INTO `user` VALUES (9, 'test', 'af1b199924e93b5803ab315048810bda', '\\:=,?qq}p87,r-0k`oG~l%@1*\'a\'', '正常', '2020-04-27 17:14:46', '2020-04-28 10:52:22', '删除');
INSERT INTO `user` VALUES (10, 'test1', '4b2344de13cb597f54a77f951fa859f7', 'CDF-cDtA)]%=[%&?9.y{;Yr f', '正常', '2020-04-28 10:39:48', '2020-04-28 10:52:22', '删除');
INSERT INTO `user` VALUES (11, 'test', 'ffb369e9e2c9bae761debb4219cb3380', 'zoo|\"dTF!{L$B=]+-1${q=iKc>G', '正常', '2020-05-06 09:31:31', '2020-05-06 09:31:31', '正常');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `tb_status` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '正常' COMMENT '状态:正常，删除',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (16, 7, 32, '2020-04-28 15:47:01', '2020-04-28 15:47:18', '删除');
INSERT INTO `user_role` VALUES (17, 7, 31, '2020-04-28 15:47:07', '2020-04-28 15:47:53', '删除');
INSERT INTO `user_role` VALUES (18, 7, 32, '2020-04-28 15:48:08', '2020-04-28 15:48:34', '删除');
INSERT INTO `user_role` VALUES (19, 7, 31, '2020-04-28 15:48:26', '2020-04-28 15:48:26', '正常');
INSERT INTO `user_role` VALUES (20, 11, 32, '2020-05-06 09:31:51', '2020-05-06 09:31:51', '正常');

SET FOREIGN_KEY_CHECKS = 1;
