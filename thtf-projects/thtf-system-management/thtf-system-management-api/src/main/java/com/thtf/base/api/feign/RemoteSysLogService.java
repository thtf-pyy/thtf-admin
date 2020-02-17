package com.thtf.base.api.feign;

import com.thtf.base.api.SysLogControllerApi;
import com.thtf.base.api.feign.factory.RemoteSysLogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * ---------------------------
 * 日志服务Feign客户端
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:31
 * 版本：  v1.0
 * ---------------------------
 */
@FeignClient(name = "thtf-system-management", path = "/api/v1", fallbackFactory = RemoteSysLogFallbackFactory.class)
public interface RemoteSysLogService extends SysLogControllerApi {

}
