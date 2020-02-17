package com.thtf.common.data.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ---------------------------
 * 自动填充配置 （自动补全 创建人 修改人 ）
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-02-17 11:11
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private String operationId;
    private String operationName;

    public MybatisPlusMetaObjectHandler(String operationId, String operationName) {
        this.operationId = operationId;
        this.operationName = operationName;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("### 当前操作用户： operationId={}, operationName={} ###", operationId, operationName);
        // 创建人ID
        if (metaObject.hasSetter("createId")) {
            this.setFieldValByName("createId", operationId , metaObject);
        }
        // 创建人名称
        if (metaObject.hasSetter("createName")) {
            this.setFieldValByName("createName", operationName , metaObject);
        }
        // 创建时间
        if (metaObject.hasSetter("createTime")) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("### 当前操作用户： operationId={}, operationName={} ###", operationId, operationName);
        // 修改人ID
        if (metaObject.hasSetter("updateId")) {
            this.setFieldValByName("updateId", operationId, metaObject);
        }
        // 修改人名称
        if (metaObject.hasSetter("updateName")) {
            this.setFieldValByName("updateName", operationName, metaObject);
        }
        // 修改人时间
        if (metaObject.hasSetter("updateTime")) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
