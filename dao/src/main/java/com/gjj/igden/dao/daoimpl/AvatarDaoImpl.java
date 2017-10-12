package com.gjj.igden.dao.daoimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import com.gjj.igden.dao.AbstractDAO;
import com.gjj.igden.dao.daoUtil.DAOException;
import com.gjj.igden.model.Avatar;

@Repository
@Transactional
public class AvatarDaoImpl extends AbstractDAO<Avatar> {

	public void create(Avatar avatar) throws DAOException {
        super.create(avatar);
    }
	
	@Override
	public Avatar read(Avatar obj) {
		 return (Avatar) em.createQuery("from Avatar where id = "+obj.getId()).getSingleResult();
	}

	@Override
	public List<Avatar> readAll() {
		return null;
	}
	
	public byte[] getDefaultAvatar() throws DAOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("/img/default.jpg")) {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
