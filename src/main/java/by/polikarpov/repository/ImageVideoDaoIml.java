package by.polikarpov.repository;

import by.polikarpov.entity.ImageVideo;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class ImageVideoDaoIml implements Dao<ImageVideo, Integer> {
    private Session session;

    @Override
    public void save(ImageVideo imageVideo) {
        session = HibernateUtils.getSession();

        try {
            session.beginTransaction();
            session.save(imageVideo);
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

    @Override
    public ImageVideo find(Integer id) {
        session = HibernateUtils.getSession();
        ImageVideo imageVideo = null;

        try {
            session.beginTransaction();

            imageVideo = (ImageVideo) session.get(ImageVideo.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return imageVideo;
    }

    @Override
    public List<ImageVideo> findAll() {
        return List.of();
    }

    @Override
    public void update(ImageVideo imageVideo) {
        session = HibernateUtils.getSession();

        try {
            session.beginTransaction();

            session.update(imageVideo);

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

    @Override
    public void delete(ImageVideo imageVideo) {
    }
}
