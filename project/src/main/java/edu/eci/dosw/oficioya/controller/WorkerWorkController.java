package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.model.WorkerWorkModel;
import edu.eci.dosw.oficioya.service.WorkerWorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/worker-works")
@Tag(name = "Oficios del trabajador", description = "Asociacion entre un trabajador y los oficios que ejerce")
public class WorkerWorkController {

    private final WorkerWorkService workerWorkService;

    public WorkerWorkController(WorkerWorkService workerWorkService) {
        this.workerWorkService = workerWorkService;
    }

    @PostMapping
    @Operation(summary = "Asignar oficio", description = "Asocia un oficio existente a un trabajador existente")
    public ResponseEntity<WorkerWorkModel> create(@Valid @RequestBody WorkerWorkModel workerWork) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workerWorkService.save(workerWork));
    }

    @GetMapping
    @Operation(summary = "Listar oficios asignados",
            description = "Devuelve las asignaciones, con filtro opcional por oficio, categoria o nombre del trabajador")
    public ResponseEntity<List<WorkerWorkModel>> findAll(@RequestParam(required = false) String workName,
                                                         @RequestParam(required = false) String category,
                                                         @RequestParam(required = false) String workerName) {
        if (workName != null) {
            return ResponseEntity.ok(workerWorkService.findByWorkName(workName));
        }
        if (category != null) {
            return ResponseEntity.ok(workerWorkService.findByWorkCategory(category));
        }
        if (workerName != null) {
            return ResponseEntity.ok(workerWorkService.findByWorkerUserName(workerName));
        }
        return ResponseEntity.ok(workerWorkService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener asignacion por ID", description = "Devuelve una asignacion a partir de su identificador")
    public ResponseEntity<WorkerWorkModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workerWorkService.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Quitar oficio", description = "Elimina la asociacion entre un trabajador y un oficio")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workerWorkService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
