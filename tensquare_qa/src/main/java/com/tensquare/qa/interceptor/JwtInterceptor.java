package com.tensquare.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hftang
 * @date 2019-05-14 15:30
 * @desc
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    /***
     * 只是把token放到 request域中
     * 不管是哪种情况 都会放行的
     * 具体操作会在具体的业务中去处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");
        if (handler != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                //取出token
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null && roles.equals("admin")) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && roles.equals("user")) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new Exception("令牌不正确");
                }
            }
        }
        System.out.println("经过了拦截 QA");
        return true;//是否拦截 true标示的是放行  false 表示的是拦截
    }

}
