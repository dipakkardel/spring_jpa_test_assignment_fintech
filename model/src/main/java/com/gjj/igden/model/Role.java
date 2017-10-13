package com.gjj.igden.model;

import org.springframework.security.core.GrantedAuthority;

import com.gjj.igden.utils.EntityId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "app_role")
public class Role implements GrantedAuthority, EntityId {

    private Long id;
    private String name;
    
    public Role(Long id) {
		super();
		this.id = id;
	}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    public Long getId() {
        return id;
    }

    @Override
    @Transient
    public String getAuthority() {
    	return "ROLE_" + getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
