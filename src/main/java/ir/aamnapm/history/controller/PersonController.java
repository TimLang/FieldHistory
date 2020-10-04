package ir.aamnapm.history.controller;

import ir.aamnapm.history.dto.PersonDTO;
import ir.aamnapm.history.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    private final IPersonService iPersonService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO.Info> getById(@PathVariable Long id) {
        return new ResponseEntity<>(iPersonService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO.Info>> getList() {
        return new ResponseEntity<>(iPersonService.getList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonDTO.Info> create(@RequestBody PersonDTO dto) {
        return new ResponseEntity<>(iPersonService.create(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDTO.Info> update(@RequestBody PersonDTO dto, @PathVariable Long id) {
        return new ResponseEntity<>(iPersonService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iPersonService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
