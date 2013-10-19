package com.example.diplomski.entities;

// default package
// Generated Oct 9, 2012 10:16:24 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

/**
 * Zaposleni generated by hbm2java
 */
@Entity
@Table(name = "zaposleni", catalog = "diplomski")
public class Zaposleni implements java.io.Serializable {

	@NonVisual
	private Integer idZaposlenog;
	private String ime;
	private String prezime;

	public Zaposleni() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_zaposlenog", unique = true, nullable = false)
	public Integer getIdZaposlenog() {
		return this.idZaposlenog;
	}

	public void setIdZaposlenog(Integer idZaposlenog) {
		this.idZaposlenog = idZaposlenog;
	}

	@Column(name = "ime", length = 64)
	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	@Column(name = "prezime", length = 64)
	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}