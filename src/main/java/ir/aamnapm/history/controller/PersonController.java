package ir.aamnapm.history.controller;

import ir.aamnapm.history.dto.PersonDTO;
import ir.aamnapm.history.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    private final IPersonService iPersonService;


    @PostMapping
    public ResponseEntity<PersonDTO.Info> create(@RequestBody PersonDTO dto) {
        return new ResponseEntity<>(iPersonService.create(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PersonDTO.Info> update(@RequestBody PersonDTO dto) {
        return new ResponseEntity<>(iPersonService.create(dto), HttpStatus.OK);
    }

}
