package edu.eci.dosw.oficioya.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "worker_work")

public class WorkerWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "work_id",  nullable = false)
    private Work work;

    @Column(nullable = false)
    private boolean isPrincipal;

    public WorkerWork() {
    }

    public WorkerWork(Worker worker, Work work, boolean isPrincipal) {
        this.worker = worker;
        this.work = work;
        this.isPrincipal = isPrincipal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }
}
