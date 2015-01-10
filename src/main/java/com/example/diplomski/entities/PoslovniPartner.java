package com.example.diplomski.entities;
// Generated Oct 24, 2013 2:39:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PoslovniPartner generated by hbm2java
 */
@Entity
@Table(name="poslovni_partner"
    ,catalog="diplomski"
)
@Inheritance(strategy=InheritanceType.JOINED)
public class PoslovniPartner  implements java.io.Serializable, OpstiDomenskiObjekat {

     private Integer idPoslovnogPartnera;
     private String adresa;
     private String mesto;
     private Set<KontaktOsobe> kontaktOsobes = new HashSet<KontaktOsobe>(0);
     private Set<Racun> racuns = new HashSet<Racun>(0);

    public PoslovniPartner() {
    }

    public PoslovniPartner(String adresa, String mesto, Set<KontaktOsobe> kontaktOsobes, Set<Racun> racuns) {
    	this.mesto = mesto;
       this.adresa = adresa;
       this.kontaktOsobes = kontaktOsobes;
       this.racuns = racuns;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id_poslovnog_partnera", unique=true, nullable=false)
    public Integer getIdPoslovnogPartnera() {
        return this.idPoslovnogPartnera;
    }
    
    public void setIdPoslovnogPartnera(Integer idPoslovnogPartnera) {
        this.idPoslovnogPartnera = idPoslovnogPartnera;
    }
    
    @Column(name="adresa", length=64)
    public String getAdresa() {
        return this.adresa;
    }
    
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    
    @Column(name="mesto", length=64)
    public String getMesto() {
        return this.mesto;
    }
    
    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="poslovniPartner")
    public Set<KontaktOsobe> getKontaktOsobes() {
        return this.kontaktOsobes;
    }
    
    public void setKontaktOsobes(Set<KontaktOsobe> kontaktOsobes) {
        this.kontaktOsobes = kontaktOsobes;
    }

@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="poslovniPartner")
    public Set<Racun> getRacuns() {
        return this.racuns;
    }
    
    public void setRacuns(Set<Racun> racuns) {
        this.racuns = racuns;
    }

	public String entityName() {
		return "PoslovniPartner";
	}

	public String idColumName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer idColumnValue() {
		// TODO Auto-generated method stub
		return null;
	}

   


}


