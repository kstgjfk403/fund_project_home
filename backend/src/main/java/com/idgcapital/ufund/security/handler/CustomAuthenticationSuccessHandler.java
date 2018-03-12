package com.idgcapital.ufund.security.handler;


import com.idgcapital.ufund.security.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yi_zhang on 2018/2/21.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        HttpSession session = httpServletRequest.getSession();
        LoginUser authUser = (LoginUser) authentication.getPrincipal();
        session.setAttribute("auth", authUser);

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");

    }
}
