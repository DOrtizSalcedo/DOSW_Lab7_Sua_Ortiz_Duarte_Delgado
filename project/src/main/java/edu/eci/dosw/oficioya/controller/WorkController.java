package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.model.WorkModel;
import edu.eci.dosw.oficioya.service.WorkService;
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
@RequestMapping("/api/works")
@Tag(name = "Oficios", description = "Operaciones relacionadas con los oficios")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping
    @Operation(summary = "Crear oficio", description = "Registra un nuevo oficio")
    public ResponseEntity<WorkModel> create(@Valid @RequestBody WorkModel work) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workService.save(work));
    }

    @GetMapping
    @Operation(summary = "Listar oficios", description = "Devuelve los oficios, con filtro opcional por nombre o categoria")
    public ResponseEntity<List<WorkModel>> findAll(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String category) {
        if (name != null) {
            return ResponseEntity.ok(workService.findByName(name));
        }
        if (category != null) {
            return ResponseEntity.ok(workService.findByCategory(category));
        }
        return ResponseEntity.ok(workService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener oficio por ID", description = "Devuelve un oficio a partir de su identificador")
    public ResponseEntity<WorkModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(workService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar oficio", description = "Modifica los datos de un oficio existente")
    public ResponseEntity<WorkModel> update(@PathVariable Long id, @Valid @RequestBody WorkModel work) {
        work.setId(id);
        return ResponseEntity.ok(workService.save(work));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar oficio", description = "Elimina un oficio a partir de su identificador")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
