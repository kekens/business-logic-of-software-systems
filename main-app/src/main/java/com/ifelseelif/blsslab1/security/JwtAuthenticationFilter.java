package com.ifelseelif.blsslab1.security;

import com.ifelseelif.blsslab1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;
import static org.springframework.util.StringUtils.substringMatch;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (validateToken(token)) {
            String userLogin = jwtProvider.getLoginFromToken(token);
            System.out.println("token " + token);
            CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            servletRequest.setAttribute("username", jwtProvider.getLoginFromToken(token));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        if (token.isEmpty()) {
            return false;
        }

        return jwtProvider.validateToken(token);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }


}
