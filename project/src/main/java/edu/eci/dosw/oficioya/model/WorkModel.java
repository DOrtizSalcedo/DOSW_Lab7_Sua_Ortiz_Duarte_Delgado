package edu.eci.dosw.oficioya.model;

public class WorkModel {

    private Long id;
    private String name;
    private boolean isPrincipalWork;
    private String category;

    public WorkModel() {
    }

    public WorkModel(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrincipalWork() {
        return isPrincipalWork;
    }

    public void setPrincipalWork(boolean principalWork) {
        isPrincipalWork = principalWork;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
