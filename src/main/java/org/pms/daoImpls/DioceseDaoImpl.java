package org.pms.daoImpls;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pms.daos.DioceseDao;
import org.pms.models.Diocese;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tijo on 19/03/17.
 */
@Repository
public class DioceseDaoImpl extends GenericDaoImpl<Diocese> implements DioceseDao {

    public DioceseDaoImpl() {
        setType(Diocese.class);
    }

    @Override
    public boolean addDiocese(Diocese diocese) {
        createAndSave(diocese);
        return true;
    }

    @Override
    public Long getDioceseCountInSystem() {
        return (Long) getDb(false).createCriteria(Diocese.class, "diocese").setProjection(Projections.max("diocese.dioceseNo")).uniqueResult();
    }

    @Override
    public List<Diocese> getAllDiocese() {
        return readAllInstances();
    }

    //Fetching respective Diocese based on the diocese ID assigned to a user.
    @Override
    public List<Diocese> getDioceseForDioceseIDDM(Long dioceseID) {
        return getDb(false).createCriteria(Diocese.class, "diocese").add(Restrictions.eq("diocese.id", dioceseID)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }
}
