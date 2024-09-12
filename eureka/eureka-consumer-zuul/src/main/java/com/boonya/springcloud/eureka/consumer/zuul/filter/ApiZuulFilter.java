package com.boonya.springcloud.eureka.consumer.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Copyright: 2019-2021
 * @FileName: ApiZuulFilter.java
 * @Author: PJL
 * @Date: 2020/10/16 17:20
 * @Description: Zuul过滤器
 */
@Slf4j
@Component
public class ApiZuulFilter extends ZuulFilter {
    /**
     * 指定路由过滤的时机
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 指定过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤为true走过滤
     * @return
     */

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        log.info(String.format("Method[%s]  URL:[%s]", request.getMethod(), request.getRequestURL().toString()));
        if(request.getParameter("token") == null){
            try {
                response.getWriter().write("token is null");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
