package edu.nju.onlineStock.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;// 将请求和响应强制转换成Http形式
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setContentType("text/html;charset=utf-8");

        StrongRequest strongRequest = new StrongRequest(request);
        filterChain.doFilter(strongRequest, response);

    }

    public void destroy() {

    }
}
