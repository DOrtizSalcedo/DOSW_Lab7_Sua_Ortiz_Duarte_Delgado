package edu.eci.dosw.oficioya.repository;

import edu.eci.dosw.oficioya.entity.WorkerWork;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkerWorkRepository extends JpaRepository<WorkerWork, Long> {
    List<WorkerWork> findByUser_Name(String name);
    List<WorkerWork> findByWork_Category(String category);
}
