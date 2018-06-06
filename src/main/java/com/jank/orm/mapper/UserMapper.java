package com.jank.orm.mapper;

import com.jank.orm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Chenyafeng on 2018/6/5.
 */
@Mapper
public interface UserMapper {

    /**
     * 查询多个用户
     * @author chenyafeng
     * @date 2018/6/5
     * @param
     */
    List<User> selectUserList(@Param("user")User user,
                              @Param("start") Integer start,
                              @Param("end") Integer end);

    /**
     * 查询单个用户
     * @author chenyafeng
     * @date 2018/6/5
     */
    User selectById(@Param("id") int id);

    /**
     * 根据用户名查询
     * @author chenyafeng
     * @date 2018/6/6
     */
    User selectByUsername(String username);

    /**
     * 新增用户
     * @author chenyafeng
     * @date 2018/6/5
     */
    void insert(@Param("user") User user);

    /**
     * 修改用户
     * @author chenyafeng
     * @date 2018/6/5
     */
    void update(@Param("user") User user);

    /**
     * 删除用户
     * @author chenyafeng
     * @date 2018/6/5
     */
    void delete(@Param("id") int id);

}
