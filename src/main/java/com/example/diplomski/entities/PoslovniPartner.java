package com.example.diplomski.entities;

// default package
// Generated Oct 9, 2012 10:16:24 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * PoslovniPartner generated by hbm2java
 */
@Entity
@Table(name = "poslovni_partner", catalog = "diplomski")
public class PoslovniPartner implements java.io.Serializable {

	private Integer idPoslovnogPartnera;
	private String adresa;
	private FizickoLice fizickoLice;

	public PoslovniPartner() {
		
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_poslovnog_partnera", unique = true, nullable = false)
	public Integer getIdPoslovnogPartnera() {
		return this.idPoslovnogPartnera;
	}

	public void setIdPoslovnogPartnera(Integer idPoslovnogPartnera) {
		this.idPoslovnogPartnera = idPoslovnogPartnera;
	}

	@Column(name = "adresa", length = 64)
	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}