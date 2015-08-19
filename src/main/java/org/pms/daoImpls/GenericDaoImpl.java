package org.pms.daoImpls;

import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.pms.daos.GenericDao;

import java.util.List;

/**
 * User: tijo.
 */
public abstract class GenericDaoImpl<T> extends HibernateSessionImpl implements GenericDao<T> {

    private Class<T> type;

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Override
    public void createAndSave(T newInstanceToSave) {
        this.getDb(false).save(newInstanceToSave);
    }

    @Override
    public void createAndSaveBatchesInstances(List<T> newInstancesListToSave) {
        Session session = this.getDb(false);
        newInstancesListToSave.forEach((newInstance) -> session.saveOrUpdate(newInstance));
    }

    @Override
    public T readInstance() {
        throw new UnsupportedOperationException("This method should not called!!...");
    }

    @Override
    public void updateInstance(T updateInstance) {
        this.getDb(false).update(updateInstance);
    }

    @Override
    public void deleteInstance(T deleteInstance) {
        throw new UnsupportedOperationException("This method should not called!!...");
    }

    @Override
    public List<T> readAllInstances() {
        return getDb(false).createCriteria(getType()).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void updateBatchesInstances(List<T> updateInstancesList) {
        throw new UnsupportedOperationException("This method should not called!!...");
    }

    @Override
    public void deleteBatchesInstances(List<T> deleteInstancesList) {
        throw new UnsupportedOperationException("This method should not called!!...");
    }
}
