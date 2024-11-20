package by.polikarpov.service;

import by.polikarpov.entity.Person;
import by.polikarpov.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonByChatId(long id) {
        return Optional.ofNullable(personRepository.getPersonByChatId(id));
    }

    public List<Person> getAllPersonsWithoutPersonChatId(Long chatId) {
        return personRepository.getPersonsWithoutPersonChatId(chatId);
    }
}
