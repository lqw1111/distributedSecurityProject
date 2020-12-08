package com.itheima.security.distributed.order.controller;

import com.itheima.security.distributed.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/r1")
    @PreAuthorize("hasAnyAuthority('user')") //标记拥有p1权限就可以访问
    public String r1() {
        UserDTO userDTO = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getUsername() + " 资源1";
    }
}
