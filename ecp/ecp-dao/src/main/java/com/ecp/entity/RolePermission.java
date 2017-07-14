package com.ecp.entity;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    /**
     * 角色权限关系表自增ID
     */
    @Id
    @Column(name = "role_permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePermissionId;

    /**
     * 引用角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 引用权限ID
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 获取角色权限关系表自增ID
     *
     * @return role_permission_id - 角色权限关系表自增ID
     */
    public Long getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * 设置角色权限关系表自增ID
     *
     * @param rolePermissionId 角色权限关系表自增ID
     */
    public void setRolePermissionId(Long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * 获取引用角色ID
     *
     * @return role_id - 引用角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置引用角色ID
     *
     * @param roleId 引用角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取引用权限ID
     *
     * @return permission_id - 引用权限ID
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置引用权限ID
     *
     * @param permissionId 引用权限ID
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rolePermissionId=").append(rolePermissionId);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append("]");
        return sb.toString();
    }
}