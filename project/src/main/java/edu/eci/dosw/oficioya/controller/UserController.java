package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.model.UserModel;
import edu.eci.dosw.oficioya.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios de la plataforma")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Registra un nuevo usuario en la plataforma")
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Devuelve los usuarios, con filtro opcional por nombre o correo")
    public ResponseEntity<List<UserModel>> findAll(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String email) {
        if (name != null) {
            return ResponseEntity.ok(userService.findByName(name));
        }
        if (email != null) {
            return ResponseEntity.ok(userService.findByEmail(email));
        }
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario a partir de su identificador")
    public ResponseEntity<UserModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Modifica los datos personales del usuario")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @Valid @RequestBody UserModel user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario a partir de su identificador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
