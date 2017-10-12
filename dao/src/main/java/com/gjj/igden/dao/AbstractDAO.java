package com.gjj.igden.dao;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.utils.EntityId;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractDAO<E extends EntityId> {

    @PersistenceContext
    protected EntityManager em;

    public abstract E read(E obj);

    public abstract List<E> readAll();

   /* public abstract List<E> readAll(String query, String... searchParameter);

    public abstract List<E> readAll(String query, int quantity, int first, String... searchParameter);*/

    public void save(E obj) throws DAOException {
        if (obj.getId() != null) {
            update(obj);
        } else {
            create(obj);
        }
    }

    public void update(E obj) {
        em.merge(obj);
    }

    public void create(E obj) throws DAOException{
        em.persist(obj);
    }

    public void delete(E obj) throws DAOException {
    	System.out.println("AbstractDAO.delete()");
		try {
			em.remove(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

//  Additional

    protected Number countRows(String tableName) {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM " + tableName);
        return (Number) query.getSingleResult();
    }

    protected Number countRows(String tableName, String searchQuery, String... parameters) {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM " + tableName);
        return (Number) query.getSingleResult();
    }

//  Utility

    protected byte[] getBytesFromDB(Query query) throws DAOException {
        Object obj = query.getSingleResult();
        if (obj == null) {
            return null;
        }
        if (obj instanceof Blob) {
            try {
                return IOUtils.toByteArray(((Blob) obj).getBinaryStream());
            } catch (IOException | SQLException e) {
                throw new DAOException(e.getMessage());
            }
        } else if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        throw new DAOException("unknown class. expected Blob or byte[].");
    }
}
