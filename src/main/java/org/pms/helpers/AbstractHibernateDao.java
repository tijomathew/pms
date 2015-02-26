package org.pms.helpers;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: tijo.
 */
public abstract class AbstractHibernateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getHibernateSession() {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.ALWAYS);

        return session;
    }
}
