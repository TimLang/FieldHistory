package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.PersonDTO;

import java.util.List;

public interface IPersonService {

    PersonDTO.Info create(PersonDTO dto);

    PersonDTO.Info update(PersonDTO dto, Long id);

    PersonDTO.Info getById(Long id);

    List<PersonDTO.Info> getList();

    void delete(Long id);
}
