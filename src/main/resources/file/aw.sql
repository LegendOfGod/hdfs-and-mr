/*
 Navicat Premium Data Transfer

 Source Server         : sobottest
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 10.150.1.103:3306
 Source Schema         : sobot_db

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 27/09/2021 17:25:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aw_addition_ticket
-- ----------------------------
DROP TABLE IF EXISTS `aw_addition_ticket`;
CREATE TABLE `aw_addition_ticket`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `serviceTicketCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务单单号',
  `additionTicketCode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结算补单单号',
  `additionTicketCodeStatus` int(4) NULL DEFAULT NULL COMMENT '0待补单  1已补单',
  `createTime` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
  `additionTime` bigint(11) NULL DEFAULT NULL COMMENT '补单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '补单信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aw_return_visit
-- ----------------------------
DROP TABLE IF EXISTS `aw_return_visit`;
CREATE TABLE `aw_return_visit`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `ticketId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单id',
  `ticketCode` bigint(11) NULL DEFAULT NULL COMMENT '工单编号',
  `createTime` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_ticketCode`(`ticketCode`) USING BTREE COMMENT '工单编号唯一索引',
  UNIQUE INDEX `index_ticketId`(`ticketId`) USING BTREE COMMENT '工单id唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '回访工单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aw_return_visit_service_ticket
-- ----------------------------
DROP TABLE IF EXISTS `aw_return_visit_service_ticket`;
CREATE TABLE `aw_return_visit_service_ticket`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `returnVisitId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回访id',
  `ticketCode` bigint(11) NULL DEFAULT NULL COMMENT '工单编号',
  `serviceTicketCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务单号',
  `satisfication` int(4) NULL DEFAULT NULL COMMENT '满意度0满意 1非常满意 2一般  3不满意 4非常不满意',
  `satisficationReason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '不满意原因',
  `appeal` int(4) NULL DEFAULT NULL COMMENT '申诉0否  1是',
  `appealReason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '申述说明',
  `edit` int(4) NULL DEFAULT NULL COMMENT '是否可编辑0可编辑 1不可编辑',
  `updateTime` bigint(11) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_serviceTicketCode`(`serviceTicketCode`) USING BTREE COMMENT '服务单单号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aw_service_ticket
-- ----------------------------
DROP TABLE IF EXISTS `aw_service_ticket`;
CREATE TABLE `aw_service_ticket`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键id',
  `ticketId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单id',
  `ticketCode` bigint(11) NULL DEFAULT NULL COMMENT '工单编号',
  `serviceTicketCode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务单号',
  `relationServiceTicketCode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联服务单号',
  `finishServiceTicket` int(4) NULL DEFAULT NULL COMMENT '安维是否完成0否 1是',
  `feeBelong` int(4) NULL DEFAULT NULL COMMENT '费用归属1余额单支付  2预发单支付  3我司承担  4客户现场支付',
  `saleOffice` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售办公室',
  `serviceId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客服id',
  `serviceName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客服姓名',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '客服备注说明',
  `customerId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户id',
  `customerName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `customerPhone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户电话',
  `customerGroup` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户组',
  `addressProvince` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属省份',
  `addressCity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属城市',
  `addressDistrict` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属区县',
  `addressDetail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '联系地址',
  `saleOrder` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售单号',
  `productCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `productName` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `saleTime` bigint(11) NULL DEFAULT NULL COMMENT '销售日期',
  `productNum` int(11) NULL DEFAULT NULL COMMENT '安维数量',
  `needTime` bigint(11) NULL DEFAULT NULL COMMENT '需求时间',
  `serviceTicketType` int(4) NULL DEFAULT NULL COMMENT '服务类型1产品安装 2产品维修',
  `serviceType` int(4) NULL DEFAULT NULL COMMENT '服务方式1上门服务 2送寄服务 3配件补发',
  `productExpressNum` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品快递单号',
  `purchaseOrder` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购单号',
  `serviceNetwork` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务网点',
  `appointmentTime` bigint(11) NULL DEFAULT NULL COMMENT '预约时间',
  `deliverType` int(4) NULL DEFAULT NULL COMMENT '发运方式0上门服务 1客户自提  2物流快递',
  `serviceExpressNum` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务单快递单号',
  `serviceTotalFee` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务单总费用',
  `accessoryInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '配件信息[\r\n     {\r\n     \"accessoryName\": \"配件名称\",\r\n     \"accessoryNum\": 100,\r\n     \"accessoryPrice\": 5.00,\r\n     \"accessoryTotalFee\": 500.00\r\n     },\r\n     {\r\n     \"accessoryName\": \"配件名称\",\r\n     \"accessoryNum\": 100,\r\n     \"accessoryPrice\": 5.00,\r\n     \"accessoryTotalFee\": 500.00\r\n     }\r\n]',
  `networkRemark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '网点备注说明',
  `customerCloseNum` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户结算单号',
  `customerCloseStatus` int(4) NULL DEFAULT NULL COMMENT '客户结算状态0待结算 1已结算  2无需结算',
  `networkCloseNum` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网点结算单号',
  `networkCloseStatus` int(4) NULL DEFAULT NULL COMMENT '网点结算状态0未结算 1已结算',
  `createServiceId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建客服id',
  `createTime` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updateId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `updateName` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人姓名',
  `updateTime` bigint(11) NULL DEFAULT NULL COMMENT '更新时间',
  `serviceTicketStatus` bigint(4) NULL DEFAULT NULL COMMENT '服务单状态1-已派单 2-已撤回 3-已作废 4-已接单 5-已派工 6-已响应 7-已完工 8-已审核 9-审核驳回 10-已回访 11-结算中 12-已结案',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_serviceTicketCode`(`serviceTicketCode`) USING BTREE COMMENT '服务单号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '安维服务单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aw_service_ticket_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `aw_service_ticket_operate_log`;
CREATE TABLE `aw_service_ticket_operate_log`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `serviceTicketCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务单号',
  `stage` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点说明1-已派单 2-已撤回 3-已作废 4-已接单 5-已派工 6-已响应 7-已完工 8-已审核 9-审核驳回 10-已回访 11-结算中 12-已结案',
  `operateTime` bigint(11) NULL DEFAULT NULL COMMENT '操作时间',
  `operateCompany` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人公司',
  `operateName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `operateRemark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作备注说明',
  `fileInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附件（文件名称和文件url）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '服务单操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aw_ticket_product
-- ----------------------------
DROP TABLE IF EXISTS `aw_ticket_product`;
CREATE TABLE `aw_ticket_product`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `ticketCode` bigint(11) NULL DEFAULT NULL COMMENT '工单编号',
  `ticketId` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单id',
  `productInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品信息[\r\n	{\r\n		\"productCode\":\"商品编号1\",\r\n		\"productName\":\"设备名称1\",\r\n		\"productNum\":1\r\n	},\r\n	{\r\n		\"productCode\":\"商品编号2\",\r\n		\"productName\":\"设备名称2\",\r\n		\"productNum\":2\r\n	}\r\n]',
  `finishServiceTicket` int(4) NULL DEFAULT NULL COMMENT '安维是否完成0否 1是',
  `needTime` bigint(11) NULL DEFAULT NULL COMMENT '需求时间',
  `createTime` bigint(11) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(11) NULL DEFAULT NULL COMMENT '更新时间',
  `deleteFlag` int(4) NULL DEFAULT NULL COMMENT '0未删除 1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '安维产品安装/产品维修工单携带的商品编号信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
