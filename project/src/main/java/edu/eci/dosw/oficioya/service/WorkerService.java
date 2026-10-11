package edu.eci.dosw.oficioya.service;

import edu.eci.dosw.oficioya.entity.User;
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.mapper.WorkerMapper;
import edu.eci.dosw.oficioya.model.EstadoTrabajador;
import edu.eci.dosw.oficioya.model.WorkerModel;
import edu.eci.dosw.oficioya.repository.UserRepository;
import edu.eci.dosw.oficioya.repository.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerService {

    private static final Logger log = LoggerFactory.getLogger(WorkerService.class);

    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private final WorkerMapper workerMapper;

    public WorkerService(WorkerRepository workerRepository, UserRepository userRepository,
                         WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.workerMapper = workerMapper;
    }

    /**
     * Todo trabajador nuevo queda en estado ACTIVO, sin importar lo que traiga el modelo.
     */
    @Transactional
    public WorkerModel create(WorkerModel worker) {
        Worker entity = workerMapper.toEntity(worker);
        entity.setId(null);
        entity.setUser(resolveUser(worker));
        entity.setStatus(EstadoTrabajador.ACTIVO);
        WorkerModel created = workerMapper.toModel(workerRepository.save(entity));
        log.info("Trabajador creado con id: {}", created.getId());
        return created;
    }

    /**
     * Un trabajador inactivo no se puede modificar
     */
    @Transactional
    public WorkerModel update(Long id, WorkerModel worker) {
        Worker current = findEntity(id);
        if (current.getStatus() == EstadoTrabajador.INACTIVO) {
            log.warn("No se puede actualizar el trabajador inactivo con id {}", id);
            throw new RuntimeException("No se puede actualizar un trabajador inactivo");
        }
        if (worker.getStatus() != null) {
            current.setStatus(worker.getStatus());
        }
        log.info("Trabajador actualizado con id: {}", id);
        return workerMapper.toModel(workerRepository.save(current));
    }

    /**
     * el registro se conserva y solo cambia de estado
     */
    @Transactional
    public WorkerModel deactivate(Long id) {
        Worker worker = findEntity(id);
        worker.setStatus(EstadoTrabajador.INACTIVO);
        log.info("Trabajador inactivado con id: {}", id);
        return workerMapper.toModel(workerRepository.save(worker));
    }

    @Transactional(readOnly = true)
    public WorkerModel findById(Long id) {
        return workerMapper.toModel(findEntity(id));
    }

    @Transactional(readOnly = true)
    public List<WorkerModel> findAll() {
        return workerRepository.findAll().stream().map(workerMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkerModel> findByUserName(String name) {
        return workerRepository.findByUser_Name(name).stream().map(workerMapper::toModel).toList();
    }

    private Worker findEntity(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Trabajador con id {} no encontrado", id);
                    return new RuntimeException("Trabajador no encontrado");
                });
    }

    private User resolveUser(WorkerModel worker) {
        if (worker.getUser() == null || worker.getUser().getId() == null) {
            throw new RuntimeException("El trabajador debe estar asociado a un usuario existente");
        }
        return userRepository.findById(worker.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
