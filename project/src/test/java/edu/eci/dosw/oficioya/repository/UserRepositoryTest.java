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
import edu.eci.dosw.oficioya.entity.Worker;
import edu.eci.dosw.oficioya.model.EstadoTrabajador;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Test
    @DisplayName("Debe guardar un usuario correctamente")
    void shouldSaveUser() {
        User user = new User("Carlos", "3001234567", "carlos@mail.com", "123456", LocalDateTime.now(), true);
        User guardado = userRepository.save(user);

        assertNotNull(guardado.getId());
        assertEquals("Carlos", guardado.getName());
    }

    @Test
    @DisplayName("Debe buscar usuarios por email")
    void shouldFindByEmail() {
        userRepository.save(new User("Ana", "3001111111", "ana@mail.com", "123456", LocalDateTime.now(), true));
        userRepository.save(new User("Luis", "3002222222", "luis@mail.com", "123456", LocalDateTime.now(), true));

        List<User> resultados = userRepository.findByEmail("ana@mail.com");

        assertEquals(1, resultados.size());
        assertEquals("Ana", resultados.get(0).getName());
    }

    @Test
    @DisplayName("Debe relacionar un usuario con un trabajador")
    void shouldRelateUserWithWorker() {
        User user = userRepository.save(new User("Marta", "3003333333", "marta@mail.com", "123456", LocalDateTime.now(), true));
        workerRepository.save(new Worker(user, EstadoTrabajador.ACTIVO));

        List<Worker> resultados = workerRepository.findByUser(user);

        assertEquals(1, resultados.size());
        assertEquals("Marta", resultados.get(0).getUser().getName());
    }

    @Test
    @DisplayName("Debe eliminar un usuario")
    void shouldDeleteUser() {
        User user = userRepository.save(new User("Sofia", "3004444444", "sofia@mail.com", "123456", LocalDateTime.now(), true));

        userRepository.deleteById(user.getId());

        assertTrue(userRepository.findById(user.getId()).isEmpty());
    }
}