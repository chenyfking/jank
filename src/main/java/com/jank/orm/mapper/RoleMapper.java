package com.jank.orm.mapper;

import com.jank.orm.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 角色
 */
@Mapper
public interface RoleMapper {
    /**
     *
     */
    List<Role> selectAll();

    /**
     *
     */
    int insert(Role role);

    /**
     * 更新权限
     */
    int updatePermissions(Role role);
    
    /**
     *
     */
    int update(Role role);

    /**
     *
     */
    int delete(int id);

    /**
     *
     */
    List<Role> selectByUser(Integer userId);

    /**
     *
     */
    Role selectByCode(String code);

    /**
     * 添加权限
     */
    int insertPermissions(Role role);
}
