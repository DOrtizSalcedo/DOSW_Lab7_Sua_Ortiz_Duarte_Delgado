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
}
