package by.polikarpov.repository;

import by.polikarpov.entity.Resume;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class ResumeDaoIml implements Dao<Resume, Integer>{
    private Session session = HibernateUtils.getSession();

    @Override
    public void save(Resume resume) {
        try {
            session.beginTransaction();
            session.save(resume);
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
    public Resume find(Integer id) {
        Resume resume = null;

        try {
            session.beginTransaction();
            resume = (Resume) session.get(Resume.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return resume;
    }

    @Override
    public List<Resume> findAll() {
        List<Resume> resumes = null;

        try {
            session.beginTransaction();
            resumes = (List<Resume>) session.createQuery("from Resume").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return resumes;
    }

    @Override
    public void update(Resume resume) {
        try {
            session.beginTransaction();
            session.update(resume);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public void delete(Resume resume) {
    }
}
