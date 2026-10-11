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
import edu.eci.dosw.oficioya.entity.Work;
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.entity.WorkerWork;
import edu.eci.dosw.oficioya.model.EstadoTrabajador;

@DataJpaTest
@ActiveProfiles("test")
class WorkRepositoryTest {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private WorkerWorkRepository workerWorkRepository;

    @Test
    @DisplayName("Debe guardar un oficio correctamente")
    void shouldSaveWork() {
        Work guardado = workRepository.save(new Work("Electricista", "Hogar"));

        assertNotNull(guardado.getId());
        assertEquals("Electricista", guardado.getName());
    }

    @Test
    @DisplayName("Debe buscar oficios por categoría")
    void shouldFindByCategory() {
        workRepository.save(new Work("Electricista", "Hogar"));
        workRepository.save(new Work("Plomero", "Hogar"));
        workRepository.save(new Work("Mecánico", "Vehículos"));

        List<Work> resultados = workRepository.findByCategory("Hogar");

        assertEquals(2, resultados.size());
    }

    @Test
    @DisplayName("Debe relacionar un oficio con un trabajador")
    void shouldRelateWorkWithWorker() {
        User user = userRepository.save(new User("Juan", "3005555555", "juan@mail.com", "123456", LocalDateTime.now(), true));
        Worker worker = workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));
        Work work = workRepository.save(new Work("Plomería", "Hogar"));
        workerWorkRepository.save(new WorkerWork(worker, work, true));

        List<WorkerWork> resultados = workerWorkRepository.findByWork_Name("Plomería");

        assertEquals(1, resultados.size());
        assertEquals("Juan", resultados.get(0).getWorker().getUser().getName());
    }

    @Test
    @DisplayName("Debe actualizar un oficio")
    void shouldUpdateWork() {
        Work work = workRepository.save(new Work("Pintor", "Hogar"));

        work.setCategory("Construcción");
        workRepository.save(work);

        Work actualizado = workRepository.findById(work.getId()).get();
        assertEquals("Construcción", actualizado.getCategory());
    }
}