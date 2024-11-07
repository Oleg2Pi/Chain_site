package by.polikarpov.repository;

import by.polikarpov.entity.UserStatus;
import by.polikarpov.entity.UserStatus;
import by.polikarpov.utils.HibernateUtils;
import org.hibernate.Session;

public class UserStatusDaoIml implements DaoStaticClass<UserStatus, String>{
    @Override
    public UserStatus find(String text) {
        Session session = HibernateUtils.getSession();
        UserStatus result = null;

        try {
            session.beginTransaction();

            result = session
                    .createQuery("FROM UserStatus WHERE category = :text", UserStatus.class)
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
