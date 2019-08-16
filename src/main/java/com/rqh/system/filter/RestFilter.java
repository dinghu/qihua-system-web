package com.rqh.system.filter;

import com.rqh.system.exception.GlobalException;
import groovy.util.logging.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class RestFilter implements Filter {

    //日志
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest)req;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "access-token,X-Requested-With,Content-Type,Origin,Authorization, X-File-Name,Cookie,Accept,Connection,User-agent");//
        response.setHeader("Access-Control-Allow-Credentials", "true"); //设置允许携带cookie信
        String url=request.getRequestURI();
        //log.info("请求的地址"+url+"---"+"请求ip"+request.getRemoteAddr());
//        logger.info("请求的地址"+url+"---"+"请求ip"+request.getRemoteAddr());
        if (request.getMethod().equals( "OPTIONS" )) { //这里很重要，options请求，直接返回200，表示成功
            response.setStatus(200);
            return;
        }
        chain.doFilter(req, res);
    }


    @Override
    public void destroy() {
    }
}
