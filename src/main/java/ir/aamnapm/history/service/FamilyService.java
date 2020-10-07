package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.FamilyDTO;
import ir.aamnapm.history.model.Family;
import ir.aamnapm.history.model.FieldHistoryFamily;
import ir.aamnapm.history.repository.FamilyDAO;
import ir.aamnapm.history.utils.HistoryFieldUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FamilyService implements IFamilyService {

    private final FamilyDAO familyDAO;
    private final IFieldHistoryService iFieldHistoryService;

    @Override
    public FamilyDTO.Info create(FamilyDTO dto) {

        Family family = new Family();
        family.setVersion(0);
        family.setFirstName(dto.getFirstName());
        family.setRelationship(dto.getRelationship());

        Family save = familyDAO.save(family);

        FamilyDTO.Info familyDTO = new FamilyDTO.Info();
        familyDTO.setId(save.getId());
        familyDTO.setVersion(save.getVersion());
        familyDTO.setFirstName(save.getFirstName());
        familyDTO.setRelationship(save.getRelationship());

        return familyDTO;
    }

    @Transactional
    @Override
    public FamilyDTO.Info update(FamilyDTO dto, Long id) {
        Optional<Family> byId = familyDAO.findById(id);

        if (byId.isPresent()) {

            new HistoryFieldUtil(dto, FamilyDTO.class, byId.get(), Family.class, iFieldHistoryService, new FieldHistoryFamily()).checkAndSave();

            Family person = byId.get();
            person.setId(id);
            person.setFirstName(dto.getFirstName());
            person.setRelationship(dto.getRelationship());

            Family save = familyDAO.save(person);

            FamilyDTO.Info FamilyDTO = new FamilyDTO.Info();
            FamilyDTO.setId(save.getId());
            FamilyDTO.setVersion(save.getVersion());
            FamilyDTO.setFirstName(save.getFirstName());
            person.setRelationship(save.getRelationship());

            return FamilyDTO;

        } else {
            throw null;
        }
    }

    @Override
    public FamilyDTO.Info getById(Long id) {
        Optional<Family> byId = familyDAO.findById(id);
        if (byId.isPresent()) {

            Family family = byId.get();

            FamilyDTO.Info familyDTO = new FamilyDTO.Info();
            familyDTO.setId(family.getId());
            familyDTO.setVersion(family.getVersion());
            familyDTO.setRelationship(family.getRelationship());
            familyDTO.setFirstName(family.getFirstName());

            return familyDTO;
        } else {
            throw null;
        }
    }

    @Override
    public List<FamilyDTO.Info> getList() {
        List<Family> all = familyDAO.findAll();

        return all.stream()
                .map(person -> {
                    FamilyDTO.Info familyDTO = new FamilyDTO.Info();
                    familyDTO.setId(person.getId());
                    familyDTO.setVersion(person.getVersion());
                    familyDTO.setRelationship(person.getRelationship());
                    familyDTO.setFirstName(person.getFirstName());
                    return familyDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Family> byId = familyDAO.findById(id);
        if (byId.isPresent()) {
            familyDAO.delete(byId.get());
        } else {
            throw null;
        }
    }
}
