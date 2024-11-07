package by.polikarpov.repository;

import by.polikarpov.entity.ActivityArea;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

public class ActivityAreaDaoImpl implements DaoStaticClass<ActivityArea, String>{

    @Override
    public ActivityArea find(String text) {
        Session session = HibernateUtils.getSession();
        ActivityArea result = null;

        try {
            session.beginTransaction();

            result = session
                    .createQuery("FROM ActivityArea WHERE type = :text", ActivityArea.class)
                    .setParameter("text", text).uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return result;
    }
}
