package com.gjj.igden.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gjj.igden.utils.EntityId;

@Entity
@Table(name="operational_parameters")
public class OperationParameters implements EntityId {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "operational_name")
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	WatchListDesc watchListDesc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OperationParameters [id=" + id + ", name=" + name + ", watchListDesc=" + watchListDesc + "]";
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public WatchListDesc getWatchListDesc() {
		return watchListDesc;
	}

	public void setWatchListDesc(WatchListDesc watchListDesc) {
		this.watchListDesc = watchListDesc;
	}

}