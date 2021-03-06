package br.com.cbgomes.ws.ws_auth.jwt.filter;


import br.com.cbgomes.ws.ws_auth.jwt.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         var token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
         if(token != null && jwtTokenProvider.validateToken(token)){
             var auth = jwtTokenProvider.getAutentication(token);
             SecurityContextHolder.getContext().setAuthentication(auth);
         }

         filterChain.doFilter(servletRequest,servletResponse) ;
    }
}
