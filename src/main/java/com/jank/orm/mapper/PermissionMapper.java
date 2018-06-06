package com.jank.orm.mapper;

import com.jank.orm.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 权限
 */
@Mapper
public interface PermissionMapper {
    /**
     *
     */
    List<Permission> selectAll();
    
    /**
     *
     */
    int insert(Permission permission);
    
    /**
     *
     */
    int insertList(List<Permission> permissionList);
    
    /**
     *
     */
    int delete(Permission permission);
}
