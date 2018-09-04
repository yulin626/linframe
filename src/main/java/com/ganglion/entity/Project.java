package com.ganglion.entity;

import javax.persistence.Table;

@Table(name = "ZM_GM_Project")
public class Project extends BaseEntity {

    /**
     * 简介
     */
    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
