package com.onlinetutorialspoint.java8;

public class Department {
    private int deptId;
    private String deptName;
    public Department(int deptId, String deptName) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
    }
    public int getDeptId() {
        return deptId;
    }
    public String getDeptName() {
        return deptName;
    }
}
