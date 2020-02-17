package com.thtf.common.cas.client.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpSessionEvent;

/**
 * cas校验流程：
 *
 *         1.用户访问业务系统（client）
 *         2.无权限时跳转到cas（服务端），并且携带参数为service=${客户端url}，所以我们看到登录页时浏览器路径为 http://localhost:8443/cas/login?service=http%3A%2F%2Flocalhost%3A8123%2Flogin%2cas
 *         3.当用户在cas处登录完后，会颁发一个ticket带回client端，如http://localhost:8123/login/cas?ticket=xxxxx
 *         4.业务系统获取到ticket后，拿这个ticket请求cas获取对应的登录用户
 *         5.cas返回对应的用户信息，业务系统再对该用户进行身份映射再次认证，则完成本次登录

 */

@Configuration
public class CasSecurityConfig {
    //cas服务器地址
    @Value("${cas.server.url:http://localhost:8443/cas}")
    private String casServerUrl;

    //cas客户端服务器地址
    @Value("${app.server.url:http://localhost:8001}")
    private String casClientUrl;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        //本机服务，访问 /login/cas 时进行校验登录（根据这个值在 CAS Server 上生成ticket，然后用来到WEB服务器上验证ticket）
        serviceProperties.setService(casClientUrl + "/login/cas");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    //CAS入口点
    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(
            ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint entryPoint
                = new CasAuthenticationEntryPoint();
        //cas服务期登录URL
        entryPoint.setLoginUrl(casServerUrl + "/login");
        entryPoint.setServiceProperties(serviceProperties);
        return entryPoint;
    }

    //cas 票据校验器
    @Bean
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator(casServerUrl);
    }

    //cas认证
    // 注意casAuthenticationProvider()方法创建的bean，该bean可以设置用户映射，正常来说，
    // 例如通过第三方github登录，这里返回的openid，
    // 是github的用户id，则需要对用户进行映射或做用户绑定，则在这里做手脚
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        //认证类（由于用户名认证交给CAS Server，这里只是负责给用户赋予角色权限信息）
        provider.setUserDetailsService(userDetailsService);
        provider.setKey("CAS_PROVIDER_LOCALHOST_8001");
        return provider;
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    //请求单点登录过滤器（SpringSecurity提供）
    @Bean
    public LogoutFilter logoutFilter() {
        //退出后转发路径
        LogoutFilter logoutFilter = new LogoutFilter(
                casServerUrl + "logout",
                securityContextLogoutHandler());
        //cas退出（通过这个地址就可以访问 /cas/logout ）
        logoutFilter.setFilterProcessesUrl("/logout/cas");
        return logoutFilter;
    }

    //单点退出过滤器（CAS提供），销毁票据
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerUrl);
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    //设置退出监听
    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(
            HttpSessionEvent event) {
        return new SingleSignOutHttpSessionListener();
    }
}
