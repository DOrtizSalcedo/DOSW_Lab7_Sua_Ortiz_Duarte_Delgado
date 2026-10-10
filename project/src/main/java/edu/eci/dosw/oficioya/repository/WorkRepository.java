package edu.eci.dosw.oficioya.repository;

import edu.eci.dosw.oficioya.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByName(String name);
    List<Work> findByCategory(String category);
}
