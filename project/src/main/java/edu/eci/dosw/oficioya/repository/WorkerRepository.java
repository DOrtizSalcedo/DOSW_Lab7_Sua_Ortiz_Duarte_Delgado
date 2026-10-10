package edu.eci.dosw.oficioya.repository;

import edu.eci.dosw.oficioya.entity.User;
import edu.eci.dosw.oficioya.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findByUser_Name(String name);
    List<Worker> findByUser(User user);
}
