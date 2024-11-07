package by.polikarpov;

import by.polikarpov.entity.Person;
import by.polikarpov.repository.PersonDaoIml;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        PersonDaoIml dao = new PersonDaoIml();
        List<Person> persons = dao.findAll();
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}
