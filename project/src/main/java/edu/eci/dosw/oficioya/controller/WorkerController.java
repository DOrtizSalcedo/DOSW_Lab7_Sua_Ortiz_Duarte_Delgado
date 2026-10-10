package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.model.WorkerModel;
import edu.eci.dosw.oficioya.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@Tag(name = "Trabajadores", description = "Operaciones relacionadas con trabajadores")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    @Operation(summary = "Crear trabajador", description = "Registra un nuevo trabajador con estado ACTIVO")
    public ResponseEntity<WorkerModel> create(@Valid @RequestBody WorkerModel worker) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workerService.create(worker));
    }

    @GetMapping
    @Operation(summary = "Listar trabajadores", description = "Devuelve todos los trabajadores registrados")
    public ResponseEntity<List<WorkerModel>> findAll(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(workerService.findByUserName(name));
        }
        return ResponseEntity.ok(workerService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener trabajador por ID", description = "Devuelve un trabajador a partir de su identificador")
    public ResponseEntity<WorkerModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar trabajador", description = "Modifica un trabajador que no este INACTIVO")
    public ResponseEntity<WorkerModel> update(@PathVariable Long id, @Valid @RequestBody WorkerModel worker) {
        return ResponseEntity.ok(workerService.update(id, worker));
    }

    @PatchMapping("/{id}/inactivar")
    @Operation(summary = "Inactivar trabajador", description = "Cambia el estado del trabajador a INACTIVO sin eliminarlo")
    public ResponseEntity<WorkerModel> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(workerService.deactivate(id));
    }
}
