package edu.eci.dosw.oficioya.model;

import java.util.List;

public class WorkerModel {

    private Long id;
    private UserModel user;
    private List<WorkerWorkModel> works;
    private String status;

    public WorkerModel() {
    }

    public WorkerModel(UserModel user, String status) {
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<WorkerWorkModel> getWorks() {
        return works;
    }

    public void setWorks(List<WorkerWorkModel> works) {
        this.works = works;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
