package by.polikarpov;

import by.polikarpov.entity.Person;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtils.getSession();

        try {
            session.beginTransaction();

            List<Person> persons = session.createQuery("from Person").list();
            for (Person person : persons) {
                System.out.println(person.toString());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
