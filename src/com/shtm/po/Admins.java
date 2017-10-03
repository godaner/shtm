package com.shtm.po;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.shtm.manage.groups.AdminsGroups.DeleteAdminGroups;
import com.shtm.manage.groups.AdminsGroups.InsertAdminGroups;
import com.shtm.manage.groups.AdminsGroups.LoginGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminGroups;
import com.shtm.manage.groups.AdminsGroups.SelectAdminRolesById;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminGroups;
import com.shtm.manage.groups.AdminsGroups.UpdateAdminRolesGroups;
import com.shtm.util.Static;

public class Admins {
	@NotBlank(message="{admins.id.notblank.error}",groups={UpdateAdminGroups.class,DeleteAdminGroups.class,SelectAdminGroups.class,SelectAdminRolesById.class,UpdateAdminRolesGroups.class})
    private String id;
    
    @Pattern(regexp=Static.REG.USERNAME,message="{admins.username.reg.error}",groups={InsertAdminGroups.class,UpdateAdminGroups.class,LoginGroups.class})
    private String username;
    
    
    @Pattern(regexp=Static.REG.PASSWORD,message="{admins.password.reg.error}",groups={InsertAdminGroups.class,LoginGroups.class})
    private String password;
    
    private String salt;

    @NotNull(message="{admins.status.notnull.error}",groups={InsertAdminGroups.class,UpdateAdminGroups.class})
    private Short status;

    private Timestamp createtime;

    private String creator;

    @NotBlank(message="{admins.theme.notnull.error}",groups={InsertAdminGroups.class,UpdateAdminGroups.class})
    private String theme;

    @Pattern(regexp=Static.REG.EMAIL,message="{admins.email.reg.error}",groups={InsertAdminGroups.class,UpdateAdminGroups.class})
    private String email;

    private Short staticc;
    
    @NotBlank(message="{admins.description.notblank.error}",groups={InsertAdminGroups.class,UpdateAdminGroups.class})
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Short getStaticc() {
        return staticc;
    }

    public void setStaticc(Short staticc) {
        this.staticc = staticc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}