package edu.eci.dosw.oficioya.repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import edu.eci.dosw.oficioya.entity.User;
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.model.EstadoTrabajador;

@DataJpaTest
@ActiveProfiles("test")
class WorkerRepositoryTest {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Debe guardar un trabajador correctamente")
    void shouldSaveWorker() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker guardado = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));

        assertNotNull(guardado.getId());
        assertEquals(EstadoTrabajador.ACTIVO, guardado.getStatus());
    }

    @Test
    @DisplayName("Debe buscar trabajadores por nombre de usuario")
    void shouldFindByUserName() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));

        List<Worker> resultados = workerRepository.findByUser_Name("Juan");

        assertEquals(1, resultados.size());
    }

    @Test
    @DisplayName("Debe relacionar un trabajador con su usuario")
    void shouldRelateWorkerWithUser() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));

        Worker encontrado = workerRepository.findById(worker.getId()).get();

        assertEquals(user.getId(), encontrado.getUser().getId());
    }

    @Test
    @DisplayName("Debe actualizar el estado de un trabajador")
    void shouldUpdateWorkerStatus() {
        User user = userRepository.save(new User("Juan", "3001112233", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));

        worker.setStatus(EstadoTrabajador.PAUSADO);
        workerRepository.save(worker);

        Worker actualizado = workerRepository.findById(worker.getId()).get();
        assertEquals(EstadoTrabajador.PAUSADO, actualizado.getStatus());
    }
}