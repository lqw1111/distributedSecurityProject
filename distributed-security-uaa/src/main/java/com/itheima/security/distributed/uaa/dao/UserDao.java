package com.itheima.security.distributed.uaa.dao;

import com.itheima.security.distributed.uaa.model.UserDto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@Repository
public class UserDao {
    Map<String, UserDto> map = new HashMap<>();
    Map<String, String> permession = new HashMap<>();
    {
        map.put("zhangsan", new UserDto("1", "zhangsan", "$2a$10$Hi784duAYmDgrojuXDfr9O/7Zu/kb0ANoctVvsKXH.4AEZoLW4Hm6", "zhangsan", "12345"));
        permession.put("zhangsan", "user");
    }
    public UserDto getUserByUsername(String username) {
        UserDto userDto = map.get("zhangsan");
        if (userDto == null) {
            return null;
        }
        return userDto;
    }

    public List<String> findPermissionsByUserId(String userName) {
        String userDto = permession.get(userName);
        List<String> permission = new ArrayList<>();
        if (userDto == null) return permission;
        permission.add(userDto);
        return permission;
    }

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    //根据账号查询用户信息
//    public UserDto getUserByUsername(String username){
//        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
//        //连接数据库查询用户
//        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
//        if(list !=null && list.size()==1){
//            return list.get(0);
//        }
//        return null;
//    }
//
//    //根据用户id查询用户权限
//    public List<String> findPermissionsByUserId(String userId){
//        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
//                "\n" +
//                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
//                "  SELECT role_id FROM t_user_role WHERE user_id = ? \n" +
//                ")\n" +
//                ")\n";
//
//        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
//        List<String> permissions = new ArrayList<>();
//        list.forEach(c -> permissions.add(c.getCode()));
//        return permissions;
//    }
}
