package com.thtf.common.core.constant;

/**
 * ---------------------------
 * pyy公用常量
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 17:24
 * 版本：  v1.0
 * ---------------------------
 */
public interface CommonConstant {

    /** 删除 */
    int DELETED_FLAG = 1;
    /** 未删除 */
    Integer UN_DELETED_FLAG = 0;

    /** 锁定 */
    String USER_STATUS_DISABLED = "1";
    /** 正常 */
    String USER_STATUS_ENABLED = "0";//


    /** 图形验证码前缀 */
    String IMAGE_KEY = "IMAGE_CODE_";

    /** 短信验证码前缀 */
    String SMS_KEY = "SMS_CODE_";

    /** 操作日志类型： 查询/获取 */
    int OPERATE_TYPE_QUERY = 1;

    /** 操作日志类型： 添加 */
    int OPERATE_TYPE_ADD = 2;

    /** 操作日志类型： 更新 */
    int OPERATE_TYPE_UPDATE = 3;

    /** 操作日志类型： 删除 */
    int OPERATE_TYPE_DELETE = 4;

    /** 操作记录标记 */
    int LOG_TYPE_OPERATION = 1;

    /** 异常记录标记 */
    int LOG_TYPE_EXCEPTION = 2;
}
