package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticacion", description = "Operaciones de inicio de sesion")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesion", description = "Valida las credenciales comparando correo y contrasena")
    public ResponseEntity<String> login(@RequestBody Map<String, String> datos) {
        if (userService.login(datos.get("correo"), datos.get("contrasena"))) {
            return ResponseEntity.ok("Autenticacion exitosa");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
    }
}
