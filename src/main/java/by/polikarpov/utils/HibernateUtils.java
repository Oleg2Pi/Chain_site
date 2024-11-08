package by.polikarpov.utils;

import by.polikarpov.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;

public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(ActivityArea.class);
            config.addAnnotatedClass(Executor.class);
            config.addAnnotatedClass(ImagePerson.class);
            config.addAnnotatedClass(Person.class);
            config.addAnnotatedClass(Resume.class);
            config.addAnnotatedClass(UserStatus.class);
            config.addAnnotatedClass(Work.class);
            config.addAnnotatedClass(WorkExperience.class);

            sessionFactory = config.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new RuntimeException(e);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        if (sessionFactory != null) {
            session.close();
        }
    }

}
