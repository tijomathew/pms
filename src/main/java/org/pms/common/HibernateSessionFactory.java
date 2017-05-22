package org.pms.common;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * User: tijo.
 */
public abstract class HibernateSessionFactory {

    private volatile SessionFactory sessionFactory;

    protected synchronized final Session getDb(boolean readOnly) throws HibernateException {
        Session session = sessionFactory.getCurrentSession();
        initSessionDefaults(session, readOnly);
        return session;
    }

    private static void initSessionDefaults(Session session, boolean readOnly) {
        if (readOnly) {
            session.setCacheMode(CacheMode.GET);
            session.setFlushMode(FlushMode.MANUAL);
        } else {
            session.setCacheMode(CacheMode.NORMAL);
            session.setFlushMode(FlushMode.AUTO);
            session.clear();
            session.flush();
        }
    }

    @Autowired(required = true)
    public void setSessionFactory(@Qualifier("sessionFactory") SessionFactory factory) {
        this.sessionFactory = factory;
    }
}
