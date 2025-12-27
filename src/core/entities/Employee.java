package core.entities;

import core.enums.EmployeeRole;

public class Employee {
    private String id;
    private String name;
    private EmployeeRole role;

    public Employee(String id, String name, EmployeeRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public EmployeeRole getRole() { return role; }

    public String getInfo() {
        return name + " - " + role.getName() + " (ID: " + id + ")";
    }
}