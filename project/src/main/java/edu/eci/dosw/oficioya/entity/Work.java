package edu.eci.dosw.oficioya.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String workName;

    @Column()
    private boolean isPrincipalWork;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String workCategory;

    public Work() {
    }

    public Work(Long workerId, String workName, String workCategory) {
        this.workerId = workerId;
        this.workName = workName;
        this.workCategory = workCategory;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(String workCategory) {
        this.workCategory = workCategory;
    }

    public boolean isPrincipalWork() {
        return isPrincipalWork;
    }

    public void setPrincipalWork(boolean principalWork) {
        isPrincipalWork = principalWork;
    }
}
