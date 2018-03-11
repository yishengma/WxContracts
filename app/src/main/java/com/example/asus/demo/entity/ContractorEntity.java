package com.example.asus.demo.entity;

/**
 * Created by asus on 18-3-8.
 */

public class ContractorEntity {

    private int ContractorId;
    private int mProfilePicture;
    private String mName;
    private int mType;

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
