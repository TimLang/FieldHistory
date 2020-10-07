package ir.aamnapm.history.service;

import ir.aamnapm.history.dto.PersonDTO;
import ir.aamnapm.history.model.FieldHistoryPerson;
import ir.aamnapm.history.model.Person;
import ir.aamnapm.history.repository.PersonDAO;
import ir.aamnapm.history.utils.HistoryFieldUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonService implements IPersonService {

    private final PersonDAO personDAO;
    private final IFieldHistoryService<FieldHistoryPerson> iFieldHistoryService;

    @Override
    public PersonDTO.Info create(PersonDTO dto) {

        Person person = new Person();
        person.setVersion(0);
        person.setAge(dto.getAge());
        person.setComment(dto.getComment());
        person.setLastName(dto.getLastName());
        person.setFirstName(dto.getFirstName());

        Person save = personDAO.save(person);

        PersonDTO.Info personDTO = new PersonDTO.Info();
        personDTO.setId(save.getId());
        personDTO.setAge(save.getAge());
        personDTO.setComment(save.getComment());
        personDTO.setVersion(save.getVersion());
        personDTO.setLastName(save.getLastName());
        personDTO.setFirstName(save.getFirstName());

        return personDTO;
    }

    @Transactional
    @Override
    public PersonDTO.Info update(PersonDTO dto, Long id) {
        Optional<Person> byId = personDAO.findById(id);

        if (byId.isPresent()) {

            new HistoryFieldUtil(dto, PersonDTO.class, byId.get(), Person.class, iFieldHistoryService, new FieldHistoryPerson()).checkAndSave();

            Person person = byId.get();
            person.setId(id);
            person.setAge(dto.getAge());
            person.setComment(dto.getComment());
            person.setLastName(dto.getLastName());
            person.setFirstName(dto.getFirstName());

            Person save = personDAO.save(person);

            PersonDTO.Info personDTO = new PersonDTO.Info();
            personDTO.setId(save.getId());
            personDTO.setAge(save.getAge());
            personDTO.setComment(save.getComment());
            personDTO.setVersion(save.getVersion());
            personDTO.setLastName(save.getLastName());
            personDTO.setFirstName(save.getFirstName());


            return personDTO;

        } else {
            throw null;
        }
    }

    @Override
    public PersonDTO.Info getById(Long id) {
        Optional<Person> byId = personDAO.findById(id);
        if (byId.isPresent()) {

            Person person = byId.get();
            PersonDTO.Info personDTO = new PersonDTO.Info();
            personDTO.setId(person.getId());
            personDTO.setAge(person.getAge());
            personDTO.setComment(person.getComment());
            personDTO.setVersion(person.getVersion());
            personDTO.setLastName(person.getLastName());
            personDTO.setFirstName(person.getFirstName());
            return personDTO;
        } else {
            throw null;
        }
    }

    @Override
    public List<PersonDTO.Info> getList() {
        List<Person> all = personDAO.findAll();

        return all.stream()
                .map(person -> {
                    PersonDTO.Info personDTO = new PersonDTO.Info();
                    personDTO.setId(person.getId());
                    personDTO.setAge(person.getAge());
                    personDTO.setComment(person.getComment());
                    personDTO.setVersion(person.getVersion());
                    personDTO.setLastName(person.getLastName());
                    personDTO.setFirstName(person.getFirstName());
                    return personDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Person> byId = personDAO.findById(id);
        if (byId.isPresent()) {
            personDAO.delete(byId.get());
        } else {
            throw null;
        }
    }
}
