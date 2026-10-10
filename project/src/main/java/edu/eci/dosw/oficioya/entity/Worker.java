package edu.eci.dosw.oficioya.entity;

import edu.eci.dosw.oficioya.model.EstadoTrabajador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkerWork> works;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EstadoTrabajador status;

    public Worker() {
    }

    public Worker(User user, EstadoTrabajador status) {
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoTrabajador getStatus() {
        return status;
    }

    public void setStatus(EstadoTrabajador status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WorkerWork> getWorks() {
        return works;
    }

    public void setWorks(List<WorkerWork> works) {
        this.works = works;
    }
}
