/*
 * Copyright (C) 2018 bsyonline
 */
package com.rolex.alphax.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rolex
 * @since 2018
 */
@Component
@Slf4j
public class URIRewriteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public boolean shouldFilter() {
        return "/test".equals(RequestContext.getCurrentContext().get(FilterConstants.REQUEST_URI_KEY));
    }

    @Override
    public Object run() throws ZuulException {
        log.info(String.format("filter name %s ordered %s", this.getClass().getName(), this.filterOrder()));
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        log.info("uri: {}", uri);
        log.info("uri: {}", ctx.get(FilterConstants.REQUEST_URI_KEY));
        uri = uri.replace("test", "haha");
        log.info("rewrite uri: {}", uri);
        ctx.put(FilterConstants.REQUEST_URI_KEY, uri);
        request.setAttribute("rewrite", true);
        log.info("rewrite uri: {}", ctx.get(FilterConstants.REQUEST_URI_KEY));
        return null;
    }
}
