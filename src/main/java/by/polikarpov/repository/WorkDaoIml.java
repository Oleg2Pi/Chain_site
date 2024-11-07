package by.polikarpov.repository;

import by.polikarpov.entity.Executor;
import by.polikarpov.entity.Work;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class WorkDaoIml implements Dao<Work, Integer>{
    private Session session = HibernateUtils.getSession();

    @Override
    public void save(Work work) {
        try {
            session.beginTransaction();
            session.save(work);
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
    public Work find(Integer id) {
        Work work = null;

        try {
            session.beginTransaction();
            work = session.get(Work.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return work;
    }

    @Override
    public List<Work> findAll() {
        return List.of();
    }

    public List<Work> findAllByExecutor(Integer id) {
        List<Work> works = null;

        try {
            session.beginTransaction();
            works = (List<Work>) session
                    .createQuery("from Work WHERE executor.id = :id", Work.class)
                    .setParameter(1, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

        return works;
    }

    @Override
    public void update(Work work) {
        try {
            session.beginTransaction();
            session.update(work);
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
    public void delete(Work work) {
        try {
            session.beginTransaction();
            session.delete(work);
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
