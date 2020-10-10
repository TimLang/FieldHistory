package ir.aamnapm.history.controller;

import ir.aamnapm.history.dto.FamilyDTO;
import ir.aamnapm.history.service.IFamilyService;
import ir.aamnapm.history.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/family")
public class FamilyController {

    private final IFamilyService iFamilyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FamilyDTO.Info> getById(@PathVariable Long id) {
        return new ResponseEntity<>(iFamilyService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FamilyDTO.Info>> getList() {
        return new ResponseEntity<>(iFamilyService.getList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FamilyDTO.Info> create(@RequestBody FamilyDTO.Create dto) {
        return new ResponseEntity<>(iFamilyService.create(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FamilyDTO.Info> update(@RequestBody FamilyDTO.Update dto, @PathVariable Long id) {
        return new ResponseEntity<>(iFamilyService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iFamilyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
