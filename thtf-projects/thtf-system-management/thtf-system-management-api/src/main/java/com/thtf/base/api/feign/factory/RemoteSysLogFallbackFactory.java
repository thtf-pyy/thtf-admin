package com.thtf.base.api.feign.factory;

import com.thtf.base.api.feign.RemoteSysLogService;
import com.thtf.base.api.feign.fallback.RemoteSysLogFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * 日志服务Fallback工厂
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:31
 * 版本：  v1.0
 * ---------------------------
 */
@Component
public class RemoteSysLogFallbackFactory implements FallbackFactory<RemoteSysLogService> {
    @Override
    public RemoteSysLogService create(Throwable throwable) {
        return new RemoteSysLogFallbackImpl(throwable);
    }
}
