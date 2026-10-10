package edu.eci.dosw.oficioya.model;

public class WorkerWorkModel {

    private Long id;

    /**
     * Se guarda el identificador del trabajador y no el modelo completo para evitar
     * el ciclo WorkerModel -> WorkerWorkModel -> WorkerModel al momento de mapear.
     */
    private Long workerId;

    private WorkModel work;
    private boolean isPrincipal;

    public WorkerWorkModel() {
    }

    public WorkerWorkModel(Long workerId, WorkModel work, boolean isPrincipal) {
        this.workerId = workerId;
        this.work = work;
        this.isPrincipal = isPrincipal;
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

    public WorkModel getWork() {
        return work;
    }

    public void setWork(WorkModel work) {
        this.work = work;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    public void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }
}
