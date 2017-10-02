package com.shtm.po;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.RolesGroups.DeleteRoleGroups;
import com.shtm.manage.groups.RolesGroups.InsertRoleGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRoleGroups;
import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;

public class Roles {
	@NotBlank(message="{roles.id.notblank.error}",groups={UpdateRolePermission.class,DeleteRoleGroups.class,UpdateRoleGroups.class})
    private String id;
	
	@NotBlank(message="{roles.name.notblank.error}",groups={InsertRoleGroups.class})
    private String name;
    
	@NotBlank(message="{roles.status.notblank.error}",groups={UpdateRoleGroups.class})
    private Short status;
    
    @NotBlank(message="{roles.description.notblank.error}",groups={InsertRoleGroups.class,DeleteRoleGroups.class,UpdateRoleGroups.class})
    private String description;

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
}