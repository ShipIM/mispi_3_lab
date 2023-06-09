package DataBase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class DataBaseHitDAO implements Serializable, DAO<DataBaseHit, Integer> {
    public DataBaseHitDAO() {

    }

    @Override
    public List<DataBaseHit> getAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DataBaseHit> criteria = builder.createQuery(DataBaseHit.class);
            criteria.from(DataBaseHit.class);

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void create(DataBaseHit entity) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(DataBaseHit entity) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.remove(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
