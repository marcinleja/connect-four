package com.softniac.connectfour.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Column(nullable = true)
    private Date lastModified;

    public EntityBase() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getLastModified() {
	return lastModified;
    }

    public void setLastModified(Date lastModified) {
	this.lastModified = lastModified;
    }

    @PrePersist
    public void prePersist() {
	lastModified = new Date();
    }

    @PreUpdate
    public void preUpdate() {
	lastModified = new Date();
    }
}
