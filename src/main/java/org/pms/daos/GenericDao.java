package org.pms.daos;

import org.hibernate.Criteria;

import java.util.List;

/**
 * User: tijo.
 */
public interface GenericDao<T> {

    //Single Instance Operations.

    /**
     * Save the instance to the underline DB.
     *
     * @param newInstanceToSave
     */
    void createAndSave(T newInstanceToSave);

    /**
     * Read the instance from the underline DB.
     *
     * @return T instance
     */
    T readInstance();

    /**
     * Update the instance to the underline DB.
     *
     * @param updateInstance
     */
    void updateInstance(T updateInstance);

    /**
     * Delete the instance from the underline DB.
     *
     * @param deleteInstance
     */
    void deleteInstance(T deleteInstance);

    //Multiple Instances operations.

    /**
     * Save the list of instance to the underline DB.
     *
     * @param newInstancesListToSave
     */
    void createAndSaveBatchesInstances(List<T> newInstancesListToSave);

    /**
     * Read the list of instance from the underline DB.
     *
     * @return list of T instance
     */
    List<T> readAllInstances();

    /**
     * Update the list of  instance to the underline DB.
     *
     * @param updateInstancesList
     */
    void updateBatchesInstances(List<T> updateInstancesList);

    /**
     * Delete the list of instance from the underline DB.
     *
     * @param deleteInstancesList
     */
    void deleteBatchesInstances(List<T> deleteInstancesList);

}
