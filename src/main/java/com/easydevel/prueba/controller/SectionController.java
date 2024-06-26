package com.easydevel.prueba.controller;

import com.easydevel.prueba.model.Section;
import com.easydevel.prueba.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSection(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>("Id is required.", HttpStatus.BAD_REQUEST);
        }

        Optional<Section> section = sectionService.getSection(id);
        if (section.isEmpty()) {
            return new ResponseEntity<>("Section not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(section.get());
    }

    @PostMapping("")
    public ResponseEntity<?> addSection(@RequestBody Section section) {
        if (section.getNombre() == null || section.getNombre().isEmpty()) {
            return new ResponseEntity<>("Name is required.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(sectionService.addSection(section));
    }

    @GetMapping("")
    public ResponseEntity<List<Section>> getSections() {
        return ResponseEntity.ok(sectionService.getSections());
    }
}
