package edu.eci.dosw.oficioya.service;

import edu.eci.dosw.oficioya.entity.Work;
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.entity.WorkerWork;
import edu.eci.dosw.oficioya.mapper.WorkerWorkMapper;
import edu.eci.dosw.oficioya.model.WorkerWorkModel;
import edu.eci.dosw.oficioya.repository.WorkRepository;
import edu.eci.dosw.oficioya.repository.WorkerRepository;
import edu.eci.dosw.oficioya.repository.WorkerWorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerWorkService {

    private final WorkerWorkRepository workerWorkRepository;
    private final WorkerRepository workerRepository;
    private final WorkRepository workRepository;
    private final WorkerWorkMapper workerWorkMapper;

    public WorkerWorkService(WorkerWorkRepository workerWorkRepository, WorkerRepository workerRepository,
                             WorkRepository workRepository, WorkerWorkMapper workerWorkMapper) {
        this.workerWorkRepository = workerWorkRepository;
        this.workerRepository = workerRepository;
        this.workRepository = workRepository;
        this.workerWorkMapper = workerWorkMapper;
    }

    @Transactional
    public WorkerWorkModel save(WorkerWorkModel workerWork) {
        WorkerWork entity = workerWorkMapper.toEntity(workerWork);
        entity.setWorker(resolveWorker(workerWork));
        entity.setWork(resolveWork(workerWork));
        return workerWorkMapper.toModel(workerWorkRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public WorkerWorkModel findById(Long id) {
        return workerWorkRepository.findById(id)
                .map(workerWorkMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Oficio del trabajador no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<WorkerWorkModel> findAll() {
        return workerWorkRepository.findAll().stream().map(workerWorkMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkerWorkModel> findByWorkName(String name) {
        return workerWorkRepository.findByWork_Name(name).stream().map(workerWorkMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkerWorkModel> findByWorkCategory(String category) {
        return workerWorkRepository.findByWork_Category(category).stream()
                .map(workerWorkMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkerWorkModel> findByWorkerUserName(String name) {
        return workerWorkRepository.findByWorker_User_Name(name).stream()
                .map(workerWorkMapper::toModel).toList();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!workerWorkRepository.existsById(id)) {
            throw new RuntimeException("Oficio del trabajador no encontrado");
        }
        workerWorkRepository.deleteById(id);
    }

    /**
     * El mapper ignora la referencia al trabajador porque no se puede reconstruir desde
     * el identificador, por lo que el servicio la resuelve contra la base de datos.
     */
    private Worker resolveWorker(WorkerWorkModel workerWork) {
        if (workerWork.getWorkerId() == null) {
            throw new RuntimeException("Se debe indicar el trabajador");
        }
        return workerRepository.findById(workerWork.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
    }

    private Work resolveWork(WorkerWorkModel workerWork) {
        if (workerWork.getWork() == null || workerWork.getWork().getId() == null) {
            throw new RuntimeException("Se debe indicar el oficio");
        }
        return workRepository.findById(workerWork.getWork().getId())
                .orElseThrow(() -> new RuntimeException("Oficio no encontrado"));
    }
}
