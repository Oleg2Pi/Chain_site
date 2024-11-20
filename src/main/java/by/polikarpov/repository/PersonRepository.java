package by.polikarpov.repository;

import by.polikarpov.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Person getPersonByChatId(Long chatId);

    @Query("SELECT p FROM Person p WHERE p.chatId <> :chatId")
    public List<Person> getPersonsWithoutPersonChatId(Long chatId);
}
