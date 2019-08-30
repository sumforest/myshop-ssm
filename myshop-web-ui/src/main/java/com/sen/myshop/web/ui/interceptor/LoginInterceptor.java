package com.sen.myshop.web.ui.interceptor;

import com.sen.myshop.web.ui.constant.SystemConstant;
import com.sen.myshop.web.ui.dto.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Sen
 * @Date: 2019/8/12 18:24
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(SystemConstant.LOGIN_USER);
        //未登录
        if (user == null) {
            return true;
        }
        //已登陆
        else {
            httpServletResponse.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
