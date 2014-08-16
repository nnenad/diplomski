package com.example.diplomski.so;

import org.hibernate.HibernateException;

import com.example.diplomski.dbbroker.DbBroker;

public abstract class OpstaSO {
	public Object izvrsiSO(Object odo) throws Exception{
        try{
            otvoriTransakciju();
            System.out.println("Otvorena transakvija");
            proveriPreduslov(odo);
            System.out.println("Proveren preduslov");
            Object poruka = izvrsiOperaciju(odo);
            potvrdiTransakciju();
            return poruka;
        }catch(HibernateException cnfe){
        	 ponistiTransakciju();
             throw new Exception(cnfe.getMessage());
        }finally{
        	DbBroker.getInstance().closeSession();
        }

    }

    

    private void otvoriTransakciju() throws Exception {
        DbBroker.getInstance().getSession().beginTransaction();
    }

    private void ponistiTransakciju(){
    	DbBroker.getInstance().rollbackTransaction();
    }

    private void potvrdiTransakciju() throws Exception {
    	DbBroker.getInstance().commitTransaction();
    }

   abstract protected void proveriPreduslov(Object odo) throws Exception;

   abstract protected Object izvrsiOperaciju(Object odo) throws Exception;
}
