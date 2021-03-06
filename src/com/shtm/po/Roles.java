package com.shtm.po;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.RolesGroups.DeleteRoleGroups;
import com.shtm.manage.groups.RolesGroups.InsertRoleGroups;
import com.shtm.manage.groups.RolesGroups.SelectRolePermissionsById;
import com.shtm.manage.groups.RolesGroups.UpdateRoleGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;
import com.shtm.util.Static;

public class Roles {
	@NotBlank(message="{roles.id.notblank.error}",groups={UpdateRolePermission.class,DeleteRoleGroups.class,UpdateRoleGroups.class,SelectRolePermissionsById.class})
    private String id;
    
    @NotBlank(message="{roles.name.notblank.error}",groups={InsertRoleGroups.class})
    @Pattern(regexp=Static.REG.ROLES_NAME,message="{roles.name.reg.error}",groups={InsertRoleGroups.class,UpdateRoleGroups.class})
    private String name;
    
    @NotNull(message="{roles.status.notnull.error}",groups={UpdateRoleGroups.class})
    private Short status;
    
    @NotBlank(message="{roles.description.notblank.error}",groups={InsertRoleGroups.class,UpdateRoleGroups.class})
    private String description;

    private Short staticc;

    private Timestamp createtime;

    private String creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getStaticc() {
        return staticc;
    }

    public void setStaticc(Short staticc) {
        this.staticc = staticc;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}