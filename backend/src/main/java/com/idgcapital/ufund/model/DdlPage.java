package com.idgcapital.ufund.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.idgcapital.ufund.common.controller.AppView;

public class DdlPage {
    @JsonView({AppView.MenuList.class})
    private Integer pageid;

    @JsonView({AppView.MenuList.class})
    private String pagename;

    @JsonView({AppView.MenuList.class})
    private String description;

    @JsonView({AppView.MenuList.class})
    private String menu;

    @JsonView({AppView.MenuList.class})
    private String submenu;

    private String linkpath;

    @JsonView({AppView.MenuList.class})
    private String thirdmenu;

    private String menuchi;

    private String submenuchi;

    private String thirdmenuchi;

    private Boolean isenabled;

    @JsonView({AppView.MenuList.class})
    private String gridStr;

    @JsonView({AppView.MenuList.class})
    private String gridNameStr;

    @JsonView({AppView.MenuList.class})
    private String staffId;

    @JsonView({AppView.MenuList.class})
    private String staffName;



    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename == null ? null : pagename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu == null ? null : submenu.trim();
    }

    public String getLinkpath() {
        return linkpath;
    }

    public void setLinkpath(String linkpath) {
        this.linkpath = linkpath == null ? null : linkpath.trim();
    }

    public String getThirdmenu() {
        return thirdmenu;
    }

    public void setThirdmenu(String thirdmenu) {
        this.thirdmenu = thirdmenu == null ? null : thirdmenu.trim();
    }

    public String getMenuchi() {
        return menuchi;
    }

    public void setMenuchi(String menuchi) {
        this.menuchi = menuchi == null ? null : menuchi.trim();
    }

    public String getSubmenuchi() {
        return submenuchi;
    }

    public void setSubmenuchi(String submenuchi) {
        this.submenuchi = submenuchi == null ? null : submenuchi.trim();
    }

    public String getThirdmenuchi() {
        return thirdmenuchi;
    }

    public void setThirdmenuchi(String thirdmenuchi) {
        this.thirdmenuchi = thirdmenuchi == null ? null : thirdmenuchi.trim();
    }

    public Boolean getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public String getGridStr() {
        return gridStr;
    }

    public void setGridStr(String gridStr) {
        this.gridStr = gridStr;
    }

    public String getGridNameStr() {
        return gridNameStr;
    }

    public void setGridNameStr(String gridNameStr) {
        this.gridNameStr = gridNameStr;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}