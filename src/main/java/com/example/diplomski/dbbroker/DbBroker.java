package com.example.diplomski.dbbroker;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.example.diplomski.entities.FizickoLice;
import com.example.diplomski.entities.OpstiDomenskiObjekat;
import com.example.diplomski.entities.PoslovniPartner;
import com.example.diplomski.entities.PravnoLice;
import com.example.diplomski.entities.Proizvod;
import com.example.diplomski.entities.ProizvodUsluga;
import com.example.diplomski.entities.TipProizvoda;
import com.example.diplomski.entities.Usluga;
import com.example.diplomski.entities.Zaposleni;

@SuppressWarnings("deprecation")
public class DbBroker {
	
	private static DbBroker dbBroker;
	private Session session;
	private static SessionFactory sf;
	private Transaction transaction;
	
	
	private DbBroker() {
		 sf = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	public static DbBroker getInstance(){
		if(dbBroker != null){
			
			return dbBroker;
		}else
			return dbBroker = new DbBroker();
	}
	
	public Session getSession(){
		session = sf.openSession();
		return session;
	}
	
	public void closeSession(){
		if(session.isOpen()){
			session.close();
		}
	}
	
	public void beginTransaction(){
		transaction = session.beginTransaction();
	}
	
	public void rollbackTransaction(){
		if(transaction!= null){
			transaction.rollback();
		}
	}
	
	public Transaction getTransaction(){
		if(session.isOpen())
			return session.getTransaction();
		return null;
	}
	
	public void commitTransaction(){
		session.getTransaction().commit();
	}

	
	public Zaposleni getZaposleniById(Integer idZaposlenog){
		String hql = "from zaposleni where zaposleniId = "+idZaposlenog;
		Query query = session.createQuery(hql);
		List results = query.list();
		if(!results.isEmpty()){
			 
			return (Zaposleni) results.get(0);
		}
		 
		return null;
	}
	public Zaposleni getZaposleniByUserNameAndPass(String username, String password){
		
       
        Criteria crit = session.createCriteria(Zaposleni.class).add(Restrictions.eq("korisnickoIme", username)).add(Restrictions.eq("sifra", password));
   
        List<Zaposleni> listaZaposlenih = crit.list();
        
        if(listaZaposlenih.isEmpty()){
        	 
        	return null;
        }else{
        	 
        	return listaZaposlenih.get(0);
        }
	}

	public List<Zaposleni> getAllZaposleni() {
	        Criteria crit = session.createCriteria(Zaposleni.class).add(Restrictions.eq("tip", 2));
	         
		return crit.list();
	}


	public Integer countZaposleni(){
		String hql = "select  count(*) as brojZaposlenih from zaposleni";
		Query query = session.createQuery(hql);
		List results = query.list();
		Object resul = results.get(0);
		 
		return 1;
	}
	public List<Zaposleni> getZaposleniRange(int startIndex, int endIndex) {
		Criteria crit = session.createCriteria(Zaposleni.class).add(Restrictions.eq("tip", 2)).add(Restrictions.ge("idZaposlenog", startIndex)).add(Restrictions.le("idZaposlenog", endIndex));
		 
		return crit.list();
	}

	public List<PravnoLice> gettAllPravnoLiceByName(String searchPhrase){
		Criteria crit = session.createCriteria(PravnoLice.class).add(Restrictions.ilike("naziv", "%"+searchPhrase+"%"));
		 
		return crit.list();
	}
	
	public List<String> getAutocompetePravnoLiceNaziv(){
		Criteria crit = session.createCriteria(PravnoLice.class).setProjection(Projections.property("naziv"));
		 
		return crit.list();
	}
	
	public List<String> getAutocompleteFizickoLicePrezime(){
		Criteria crit = session.createCriteria(FizickoLice.class).setProjection(Projections.property("prezime"));
		 
		return crit.list();
	}
	
	public List<String> getAutocompleteFizickoLicePrezimeMesto(String mesto){
		Criteria crit = session.createCriteria(FizickoLice.class).add(Restrictions.eq("mesto", mesto)).setProjection(Projections.property("prezime"));
		 
		return crit.list();
	}
	
	public void saveEntity(Object obj){
		
		 session.persist(obj);
	      
	      session.refresh(obj);
	}

	public List<FizickoLice> gettAllFizickoLiceByName(String searchPhrase) {
		Criteria crit = session.createCriteria(FizickoLice.class).add(Restrictions.ilike("prezime", "%"+searchPhrase+"%"));
		 
		return crit.list();
	}
	
	public List<Object> getAllEntities(OpstiDomenskiObjekat odo){
		Criteria crit = session.createCriteria(odo.getClass());
		 
		return crit.list();
	}
	
	
	
	public List<String> getAllMestaFilickoLice(){
		Criteria crit = session.createCriteria(FizickoLice.class).setProjection(Projections.distinct(Projections.property("mesto")));
         return crit.list();
         
	}

	public ProizvodUsluga getProizvodUslugaById(Integer idProizvodUsluga) {
		Criteria crit = session.createCriteria(ProizvodUsluga.class).add(Restrictions.eq("idProizvodUsluga", idProizvodUsluga));
		if(crit.list().isEmpty()){
			 
			return null;
		}else{
			 
			return (ProizvodUsluga) crit.list().get(0);
		}
	}
	
	public OpstiDomenskiObjekat getEntityById(OpstiDomenskiObjekat odo, Integer id){
		Criteria crit = session.createCriteria(odo.getClass()).add(Restrictions.eq("idProizvodUsluga", id));
		if(crit.list().isEmpty()){
			 
			return null;
		}else{
			 
			return (ProizvodUsluga) crit.list().get(0);
		}
	}
	public List<TipProizvoda> getAllTipProizvoda(){
		Criteria crit = session.createCriteria(TipProizvoda.class);
		return  crit.list();
	}

	public void smaniKolicinuProizvoda(Proizvod proi) {
		proi.setKolicina(proi.getKolicina() - 1);
		this.saveEntity(proi);
	}

	public List<FizickoLice> gettAllFizickoLiceByNameAndMesto(String searchPhrase, String mesto) {
		if(mesto.equals("sva") || mesto == null || mesto.equals("")){
			return gettAllFizickoLiceByName(searchPhrase);
		}else{
			Criteria crit = session.createCriteria(FizickoLice.class).add(Restrictions.ilike("prezime", "%"+searchPhrase+"%"))
					.add(Restrictions.eq("mesto", mesto));
			 
			return crit.list();
		}
	}
}
