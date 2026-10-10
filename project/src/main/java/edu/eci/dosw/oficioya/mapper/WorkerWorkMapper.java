package edu.eci.dosw.oficioya.mapper;

import edu.eci.dosw.oficioya.entity.WorkerWork;
import edu.eci.dosw.oficioya.model.WorkerWorkModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WorkMapper.class})
public interface WorkerWorkMapper {

    @Mapping(source = "worker.id", target = "workerId")
    WorkerWorkModel toModel(WorkerWork entity);

    /**
     * El trabajador no se puede reconstruir a partir del identificador, por lo que la
     * referencia inversa la establece WorkerMapper al mapear el trabajador completo.
     */
    @Mapping(target = "worker", ignore = true)
    WorkerWork toEntity(WorkerWorkModel model);
}
