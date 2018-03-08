package com.example.asus.demo.entity;

/**
 * Created by asus on 18-3-8.
 */

public class ContractorEntity {
    private int ContractorId;
    private int mProfilePicture;
    private String mName;

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
}
