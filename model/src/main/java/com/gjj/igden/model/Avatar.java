package com.gjj.igden.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_avatar")
public class Avatar {

	private long id;
	private byte[] image;

	public Avatar() {
		super();
	}

	public Avatar(long id, byte[] image) {
		super();
		this.id = id;
		this.image = image;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="avatar_id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
