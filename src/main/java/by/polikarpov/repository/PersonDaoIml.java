package by.polikarpov.repository;

import by.polikarpov.entity.Person;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class PersonDaoIml implements Dao<Person, Integer> {

    @Override
    public void save(Person person) {
    }

    @Override
    public Person find(Integer id) {
        Session session = HibernateUtils.getSession();
        Person person = null;

        try {
            session.beginTransaction();

            person = session.get(Person.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return person;
    }

    @Override
    public List<Person> findAll() {
        Session session = HibernateUtils.getSession();
        List<Person> persons = null;

        try {
            session.beginTransaction();

            persons = session.createQuery("from Person").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return persons;
    }

    @Override
    public void update(Person person) {
    }

    @Override
    public void delete(Person person) {
    }
}
