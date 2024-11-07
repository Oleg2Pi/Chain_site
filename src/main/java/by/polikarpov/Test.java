package by.polikarpov;

import by.polikarpov.entity.Person;
import by.polikarpov.repository.PersonDaoIml;

public class Test {
    public static void main(String[] args) {
        PersonDaoIml dao = new PersonDaoIml();
        Person person = dao.find(2);
        System.out.println(person);
        person.setLastName("Hirakava");
        dao.update(person);
    }
}
