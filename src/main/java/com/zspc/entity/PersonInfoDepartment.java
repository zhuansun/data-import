package com.zspc.entity;


import java.io.Serializable;

public class PersonInfoDepartment implements Serializable {
    private Integer id;

    private String name;

    private String ctftp;

    private String ctfid;

    private String gender;

    private String birthday;

    private String address;

    private String zip;

    private String mobile;

    private String tel;

    private String fax;

    private String email;

    private String version;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtftp() {
        return ctftp;
    }

    public void setCtftp(String ctftp) {
        this.ctftp = ctftp;
    }

    public String getCtfid() {
        return ctfid;
    }

    public void setCtfid(String ctfid) {
        this.ctfid = ctfid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PersonInfoDepartment other = (PersonInfoDepartment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCtftp() == null ? other.getCtftp() == null : this.getCtftp().equals(other.getCtftp()))
                && (this.getCtfid() == null ? other.getCtfid() == null : this.getCtfid().equals(other.getCtfid()))
                && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
                && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getZip() == null ? other.getZip() == null : this.getZip().equals(other.getZip()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
                && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCtftp() == null) ? 0 : getCtftp().hashCode());
        result = prime * result + ((getCtfid() == null) ? 0 : getCtfid().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getZip() == null) ? 0 : getZip().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}