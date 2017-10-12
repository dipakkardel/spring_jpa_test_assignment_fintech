package com.gjj.igden.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gjj.igden.utils.EntityId;

@Entity
@Table(name = "account_avatar")
public class Avatar implements EntityId {

	private Long id;
	private byte[] image;

	public Avatar() {
		super();
	}

	public Avatar(byte[] image) {
		super();
		this.image = image;
	}

	@Override
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="avatar_id")
	public Long getId() {
		return id;
	}
	

	@Column(name="image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
		
	}

}
