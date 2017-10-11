package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.model.Bar;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class BarDaoImpl extends AbstractDAO<Bar> {
  
	@Override
	public Bar read(Bar obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<Bar> readAll() {
		return em.createQuery("from Bar").getResultList();
	}
	
	public Bar getSingleBar(long id, String instId) {
		return (Bar) em.createQuery("FROM Bar WHERE id = "+id+" AND instId_fk = "+instId+" ").getSingleResult();
	}
  
	public List<Bar> getBarList(String instId) {
		return em.createQuery("FROM Bar WHERE instId_fk = "+instId+" ").getResultList();
	}
  
	public boolean createBar(Bar bar) throws DAOException {
		super.create(bar);
		return true;
	}
	
	public boolean updateBar(Bar bar) {
		super.update(bar);
		return true;
	}
	
	public boolean deleteBar(long mdId, String instId) {
		em.createQuery("DELETE FROM Bar WHERE id = "+mdId+" AND instId_fk = "+instId+" ").executeUpdate();
		return true;
	}
	
	public boolean deleteBar(Bar bar) {
		super.delete(bar);
		return true;
	}
	
	public List<String> searchTickersByChars(String tickerNamePart) {
		return em.createQuery("symbol FROM Bar WHERE symbol LIKE %"+tickerNamePart+"%").getResultList();
	}
}
