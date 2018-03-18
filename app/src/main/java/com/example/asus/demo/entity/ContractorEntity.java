package com.example.asus.demo.entity;


public class ContractorEntity {

    private int ContractorId;
    private int mProfilePicture;//联系人的头像
    private String mName;//联系人名字
    private int mType;//类型由于区分联系人和字母分隔

    public int getContractorId() {
        return ContractorId;
    }

    public void setContractorId(int contractorId) {
        ContractorId = contractorId;
    }

    public int getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        mProfilePicture = profilePicture;
    }

    public String getName() {
        return mName == null ? "" : mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
