package edu.eci.dosw.oficioya.service;

import edu.eci.dosw.oficioya.mapper.WorkMapper;
import edu.eci.dosw.oficioya.model.WorkModel;
import edu.eci.dosw.oficioya.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    public WorkService(WorkRepository workRepository, WorkMapper workMapper) {
        this.workRepository = workRepository;
        this.workMapper = workMapper;
    }

    @Transactional
    public WorkModel save(WorkModel work) {
        return workMapper.toModel(workRepository.save(workMapper.toEntity(work)));
    }

    @Transactional(readOnly = true)
    public WorkModel findById(Long id) {
        return workRepository.findById(id)
                .map(workMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Oficio no encontrado"));
    }

    @Transactional(readOnly = true)
    public List<WorkModel> findAll() {
        return workRepository.findAll().stream().map(workMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkModel> findByName(String name) {
        return workRepository.findByName(name).stream().map(workMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<WorkModel> findByCategory(String category) {
        return workRepository.findByCategory(category).stream().map(workMapper::toModel).toList();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!workRepository.existsById(id)) {
            throw new RuntimeException("Oficio no encontrado");
        }
        workRepository.deleteById(id);
    }
}
