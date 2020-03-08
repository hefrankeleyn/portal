package com.hef.qhjiaoyiyuan.bean;

/**
 * @Date 2020/3/8
 * @Author lifei
 */
public class QHRole implements Cloneable{

    private Integer rId;
    private String roleType;
    private String roleName;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    protected QHRole clone()  {
        try {
            return (QHRole) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return "QHRole{" +
                "rId=" + rId +
                ", roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
