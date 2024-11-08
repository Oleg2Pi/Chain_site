package by.polikarpov.service;

import by.polikarpov.entity.Person;
import by.polikarpov.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPersonById(int id) {
        return personRepository.findById(id);
    }
}
