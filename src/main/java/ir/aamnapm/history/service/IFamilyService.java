package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.FamilyDTO;

import java.util.List;

public interface IFamilyService {

    FamilyDTO.Info create(FamilyDTO dto);

    FamilyDTO.Info update(FamilyDTO dto, Long id);

    FamilyDTO.Info getById(Long id);

    List<FamilyDTO.Info> getList();

    void delete(Long id);
}
