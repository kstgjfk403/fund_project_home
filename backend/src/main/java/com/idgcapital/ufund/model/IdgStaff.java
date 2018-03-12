package com.idgcapital.ufund.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class IdgStaff implements Serializable{
    private String staffid;

    private Integer branchid;

    private Integer deptid;

    private Integer groupid;

    private String firstnameeng;

    private String lastnameeng;

    private String firstnamechi;

    private String lastnamechi;

    private String nickname;

    private String title;

    private String email;

    private String officeno;

    private String mobileno;

    private String faxno;

    private Date joindate;

    private Date leavedate;

    private String username;

    private String password;

    private Date lastlogindate;

    private String stafftype;

    private String staffstatus;

    private String createdby;

    private Date createddate;

    private String modifiedby;

    private Date modifieddate;

    private String pin;

    private String userstatus;

    private Boolean pm;

    private Boolean boardrepresentative;

    private Boolean inhousecounsel;

    private Boolean approved;

    private String comment;

    private Boolean observer;

    private String address;

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid == null ? null : staffid.trim();
    }

    public Integer getBranchid() {
        return branchid;
    }

    public void setBranchid(Integer branchid) {
        this.branchid = branchid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getFirstnameeng() {
        return firstnameeng;
    }

    public void setFirstnameeng(String firstnameeng) {
        this.firstnameeng = firstnameeng == null ? null : firstnameeng.trim();
    }

    public String getLastnameeng() {
        return lastnameeng;
    }

    public void setLastnameeng(String lastnameeng) {
        this.lastnameeng = lastnameeng == null ? null : lastnameeng.trim();
    }

    public String getFirstnamechi() {
        return firstnamechi;
    }

    public void setFirstnamechi(String firstnamechi) {
        this.firstnamechi = firstnamechi == null ? null : firstnamechi.trim();
    }

    public String getLastnamechi() {
        return lastnamechi;
    }

    public void setLastnamechi(String lastnamechi) {
        this.lastnamechi = lastnamechi == null ? null : lastnamechi.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOfficeno() {
        return officeno;
    }

    public void setOfficeno(String officeno) {
        this.officeno = officeno == null ? null : officeno.trim();
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno == null ? null : faxno.trim();
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
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

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public String getStafftype() {
        return stafftype;
    }

    public void setStafftype(String stafftype) {
        this.stafftype = stafftype == null ? null : stafftype.trim();
    }

    public String getStaffstatus() {
        return staffstatus;
    }

    public void setStaffstatus(String staffstatus) {
        this.staffstatus = staffstatus == null ? null : staffstatus.trim();
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby == null ? null : modifiedby.trim();
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin == null ? null : pin.trim();
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus == null ? null : userstatus.trim();
    }

    public Boolean getPm() {
        return pm;
    }

    public void setPm(Boolean pm) {
        this.pm = pm;
    }

    public Boolean getBoardrepresentative() {
        return boardrepresentative;
    }

    public void setBoardrepresentative(Boolean boardrepresentative) {
        this.boardrepresentative = boardrepresentative;
    }

    public Boolean getInhousecounsel() {
        return inhousecounsel;
    }

    public void setInhousecounsel(Boolean inhousecounsel) {
        this.inhousecounsel = inhousecounsel;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Boolean getObserver() {
        return observer;
    }

    public void setObserver(Boolean observer) {
        this.observer = observer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}