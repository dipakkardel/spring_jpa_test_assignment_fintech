package com.gjj.igden.dao.daoimpl;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Account;
import com.gjj.igden.model.Role;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDAO<Role> {

	@Override
	public Role read(Role obj) {
		// TODO Auto-generated method stub
		return (Role) em.createQuery("from Role where id = "+obj.getId()).getSingleResult();
	}

	@Override
	public List<Role> readAll() {
		return em.createQuery("from Role").getResultList();
	}
	
}