package com.shtm.po;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.RolesGroups.UpdateRolePermission;

public class Roles {
	@NotBlank(message="{roles.id.notblank.error}",groups={UpdateRolePermission.class})
    private String id;

    private String name;

    private Short status;

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