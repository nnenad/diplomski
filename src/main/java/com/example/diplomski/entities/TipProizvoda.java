package com.example.diplomski.entities;
// Generated Oct 24, 2013 2:39:14 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipProizvoda generated by hbm2java
 */
@Entity
@Table(name="tip_proizvoda"
    ,catalog="diplomski"
)
public class TipProizvoda  implements java.io.Serializable,OpstiDomenskiObjekat {


     private Integer idTipProizvoda;
     private String nazivTipa;
     //private Set<Proizvod> proizvods = new HashSet<Proizvod>(0);

    public TipProizvoda() {
    }

//    public TipProizvoda(String nazivTipa, Set<Proizvod> proizvods) {
//       this.nazivTipa = nazivTipa;
//       this.proizvods = proizvods;
//    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id_tip_proizvoda", unique=true, nullable=false)
    public Integer getIdTipProizvoda() {
        return this.idTipProizvoda;
    }
    
    public void setIdTipProizvoda(Integer idTipProizvoda) {
        this.idTipProizvoda = idTipProizvoda;
    }
    
    @Column(name="naziv_tipa", length=45)
    public String getNazivTipa() {
        return this.nazivTipa;
    }
    
    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }
//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tipProizvoda")
//    public Set<Proizvod> getProizvods() {
//        return this.proizvods;
//    }
//    
//    public void setProizvods(Set<Proizvod> proizvods) {
//        this.proizvods = proizvods;
//    }

	public String entityName() {
		// TODO Auto-generated method stub
		return "TipProizvoda";
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


