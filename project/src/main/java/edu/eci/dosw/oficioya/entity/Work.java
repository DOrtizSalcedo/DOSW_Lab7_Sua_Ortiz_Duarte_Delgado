package edu.eci.dosw.oficioya.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column()
    private boolean isPrincipalWork;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String category;

    public Work() {
    }

    public Work(String name, String category) {
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

    public void setName(String workName) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String workCategory) {
        this.category = workCategory;
    }

    public boolean isPrincipalWork() {
        return isPrincipalWork;
    }

    public void setPrincipalWork(boolean principalWork) {
        isPrincipalWork = principalWork;
    }
}
