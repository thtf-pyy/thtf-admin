package com.thtf.common.log.listener;

import com.thtf.base.api.feign.RemoteSysLogService;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.common.log.event.SysLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * 注解形式的监听 异步监听日志事件
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 11:55
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
public class SysLogListener {

    @Autowired
    private RemoteSysLogService remoteSysLogService;

    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogSaveOrUpdateVO sysLog = (SysLogSaveOrUpdateVO) event.getSource();
        // 保存日志
        remoteSysLogService.add(sysLog);
        log.info("远程日志记录成功：{}", sysLog);
    }
}
