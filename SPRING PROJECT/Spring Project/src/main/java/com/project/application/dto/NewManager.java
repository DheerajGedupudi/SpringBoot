package com.project.application.dto;

public class NewManager
{
    private String managerUsername;

    public NewManager() {
    }

    public NewManager(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    @Override
    public String toString() {
        return "NewManager{" +
                "managerUsername='" + managerUsername + '\'' +
                '}';
    }
}
