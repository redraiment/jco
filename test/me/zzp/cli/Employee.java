package me.zzp.cli;

import java.util.Date;

public class Employee {
    @Option(shortName = "n")
    @Required
    private String name;

    @Option
    @Optional(defaultValue = "25")
    private Integer age;

    @Option(shortName = "f", name = "full-time-employee")
    @Optional(defaultValue = "false")
    private Boolean fullTime;
    
    @Option(shortName = "p", name = "project")
    private String[] projects;
    
    @Option(shortName = "a", name="amount")
    private Integer[] amounts;

    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String[] getProjects() {
        return projects;
    }

    public Integer[] getAmounts() {
        return amounts;
    }
}
