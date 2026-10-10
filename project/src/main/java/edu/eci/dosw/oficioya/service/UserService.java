package edu.eci.dosw.oficioya.service;

import edu.eci.dosw.oficioya.entity.User;
import edu.eci.dosw.oficioya.mapper.UserMapper;
import edu.eci.dosw.oficioya.model.UserModel;
import edu.eci.dosw.oficioya.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserModel save(UserModel user) {
        UserModel saved = userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
        log.info("Usuario guardado con id: {}", saved.getId());
        return saved;
    }

    /**
     * Actualiza los datos personales del usuario. Corresponde a la parte del metodo
     * actualizar del laboratorio 6 que manejaba nombre, correo, telefono y contrasena.
     */
    @Transactional
    public UserModel update(Long id, UserModel user) {
        User current = findEntity(id);
        current.setName(user.getName());
        current.setPhone(user.getPhone());
        current.setEmail(user.getEmail());
        current.setPassword(user.getPassword());
        log.info("Usuario actualizado con id: {}", id);
        return userMapper.toModel(userRepository.save(current));
    }

    /**
     * Valida las credenciales comparando correo y contrasena, como en el laboratorio 6.
     * Solo se admite el ingreso de cuentas activas.
     */
    @Transactional(readOnly = true)
    public boolean login(String email, String password) {
        boolean valid = userRepository.findByEmail(email).stream()
                .anyMatch(user -> user.isActiveAccount() && user.getPassword().equals(password));
        if (valid) {
            log.info("Login exitoso para el correo: {}", email);
        } else {
            log.warn("Login fallido para el correo: {}", email);
        }
        return valid;
    }

    @Transactional(readOnly = true)
    public UserModel findById(Long id) {
        return userMapper.toModel(findEntity(id));
    }

    @Transactional(readOnly = true)
    public List<UserModel> findAll() {
        return userRepository.findAll().stream().map(userMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<UserModel> findByName(String name) {
        return userRepository.findByName(name).stream().map(userMapper::toModel).toList();
    }

    @Transactional(readOnly = true)
    public List<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email).stream().map(userMapper::toModel).toList();
    }

    @Transactional
    public void deleteById(Long id) {
        findEntity(id);
        userRepository.deleteById(id);
        log.info("Usuario eliminado con id: {}", id);
    }

    private User findEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario con id {} no encontrado", id);
                    return new RuntimeException("Usuario no encontrado");
                });
    }
}
