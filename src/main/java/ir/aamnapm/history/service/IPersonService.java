package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.PersonDTO;

public interface IPersonService {

    PersonDTO.Info create(PersonDTO dto);

    PersonDTO.Info update(PersonDTO dto);

}
