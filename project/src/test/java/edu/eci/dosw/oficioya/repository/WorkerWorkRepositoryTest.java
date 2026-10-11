package edu.eci.dosw.oficioya.repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import edu.eci.dosw.oficioya.entity.User;
import edu.eci.dosw.oficioya.entity.Work;
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.entity.WorkerWork;
import edu.eci.dosw.oficioya.model.EstadoTrabajador;

@DataJpaTest
@ActiveProfiles("test")
class WorkerWorkRepositoryTest {

    @Autowired
    private WorkerWorkRepository workerWorkRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Debe guardar la relación trabajador-oficio")
    void shouldSaveWorkerWork() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));
        Work work = workRepository.save(new Work("Plomería", "Hogar"));

        WorkerWork guardado = workerWorkRepository.save(new WorkerWork(worker, work, true));

        assertNotNull(guardado.getId());
        assertTrue(guardado.isPrincipal());
    }

    @Test
    @DisplayName("Debe buscar por categoría del oficio")
    void shouldFindByWorkCategory() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));
        Work work = workRepository.save(new Work("Plomería", "Hogar"));
        workerWorkRepository.save(new WorkerWork(worker, work, true));

        List<WorkerWork> resultados = workerWorkRepository.findByWork_Category("Hogar");

        assertEquals(1, resultados.size());
    }

    @Test
    @DisplayName("Debe relacionar trabajador y oficio")
    void shouldRelateWorkerAndWork() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));
        Work work = workRepository.save(new Work("Plomería", "Hogar"));
        WorkerWork guardado = workerWorkRepository.save(new WorkerWork(worker, work, true));

        WorkerWork encontrado = workerWorkRepository.findById(guardado.getId()).get();

        assertEquals(worker.getId(), encontrado.getWorker().getId());
        assertEquals(work.getId(), encontrado.getWork().getId());
    }

    @Test
    @DisplayName("Debe eliminar la relación trabajador-oficio")
    void shouldDeleteWorkerWork() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));
        Work work = workRepository.save(new Work("Plomería", "Hogar"));
        WorkerWork guardado = workerWorkRepository.save(new WorkerWork(worker, work, true));

        workerWorkRepository.deleteById(guardado.getId());

        assertTrue(workerWorkRepository.findById(guardado.getId()).isEmpty());
    }
}