package by.polikarpov.repository;

import by.polikarpov.entity.Executor;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class ExecutorDaoIml implements Dao<Executor, Integer> {
    @Override
    public void save(Executor executor) {
    }

    @Override
    public Executor find(Integer id) {
        Session session = HibernateUtils.getSession();
        Executor executor = null;

        try {
            session.beginTransaction();

            executor = (Executor) session.get(Executor.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return executor;
    }

    @Override
    public List<Executor> findAll() {
        Session session = HibernateUtils.getSession();
        List<Executor> executors = null;

        try {
            session.beginTransaction();

            executors = session.createQuery("from Executor").list();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
        return executors;
    }

    @Override
    public void update(Executor executor) {
    }

    @Override
    public void delete(Executor executor) {
    }
}
