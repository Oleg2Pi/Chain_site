package by.polikarpov.repository;

import by.polikarpov.entity.ImagePerson;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.awt.*;
import java.util.List;

public class ImagePersonDaoIml implements Dao<ImagePerson, Integer> {
    @Override
    public void save(ImagePerson imagePerson) {
    }

    @Override
    public ImagePerson find(Integer id) {
        Session session = HibernateUtils.getSession();
        ImagePerson imagePerson = null;

        try {
            session.beginTransaction();

            imagePerson = (ImagePerson) session.get(ImagePerson.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return imagePerson;
    }

    @Override
    public List<ImagePerson> findAll() {
        return List.of();
    }

    @Override
    public void update(ImagePerson imagePerson) {
    }

    @Override
    public void delete(ImagePerson imagePerson) {
    }
}
