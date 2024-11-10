package by.polikarpov.repository;

import by.polikarpov.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person getPersonByChatId(Long chatId);
}
