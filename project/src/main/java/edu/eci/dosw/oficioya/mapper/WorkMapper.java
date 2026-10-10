package edu.eci.dosw.oficioya.mapper;

import edu.eci.dosw.oficioya.entity.Work;
import edu.eci.dosw.oficioya.model.WorkModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    WorkModel toModel(Work entity);

    Work toEntity(WorkModel model);
}
