package com.shtm.po;

import javax.validation.constraints.NotNull;

import com.shtm.manage.groups.ClazzsGroups.DeleteClazzsGroups;
import com.shtm.manage.groups.ClazzsGroups.InsertClazzsGroups;

public class Clazzs {
	
	@NotNull(message="{clazzs.id.notnull.error}",groups={InsertClazzsGroups.class,DeleteClazzsGroups.class})
    private String id;
	
	@NotNull(message="{clazzs.text.notnull.error}",groups={InsertClazzsGroups.class})
    private String text;

    private Double num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }
}