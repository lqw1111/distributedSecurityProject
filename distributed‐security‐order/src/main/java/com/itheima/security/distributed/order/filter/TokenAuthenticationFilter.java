package com.itheima.security.distributed.order.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.security.distributed.order.common.EncryptUtil;
import com.itheima.security.distributed.order.model.UserDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("json-token");
        if (token != null) {
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            //转成json
            JSONObject jsonObject = JSON.parseObject(json);

            UserDTO userDTO = new UserDTO();

            //用户身份信息
            String principal = jsonObject.getString("principal");
            userDTO.setUsername(principal);

            //用户权限
            JSONArray authoritieArray = jsonObject.getJSONArray("authorities");
            String[] authorities = authoritieArray.toArray(new String[authoritieArray.size()]);

            //将用户信息和权限填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO, null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //将authenticationToken填充到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
