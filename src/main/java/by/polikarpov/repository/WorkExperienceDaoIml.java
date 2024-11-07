package by.polikarpov.repository;

import by.polikarpov.entity.WorkExperience;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

public class WorkExperienceDaoIml implements DaoStaticClass<WorkExperience, String> {

    @Override
    public WorkExperience find(String text) {
        Session session = HibernateUtils.getSession();
        WorkExperience result = null;

        try {
            session.beginTransaction();

            result = session
                    .createQuery("FROM WorkExperience WHERE category = :text", WorkExperience.class)
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
