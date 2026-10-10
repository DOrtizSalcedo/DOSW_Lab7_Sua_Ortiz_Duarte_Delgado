package edu.eci.dosw.oficioya.mapper;

import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.entity.WorkerWork;
import edu.eci.dosw.oficioya.model.WorkerModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, WorkerWorkMapper.class})
public interface WorkerMapper {

    WorkerModel toModel(Worker entity);

    Worker toEntity(WorkerModel model);

    /**
     * Restablece la referencia inversa de cada oficio hacia el trabajador para que la
     * cascada definida en Worker persista correctamente la tabla puente.
     */
    @AfterMapping
    default void linkWorks(@MappingTarget Worker worker) {
        if (worker.getWorks() != null) {
            for (WorkerWork workerWork : worker.getWorks()) {
                workerWork.setWorker(worker);
            }
        }
    }
}
