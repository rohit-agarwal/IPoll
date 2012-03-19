package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ReqForCandidatureDAO extends BaseHibernate {
	
	public ReqForCandidatureDAO(){
		
	}
	public boolean writeReqForCandidature(ReqForCandidatureWard reqForCandidature)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(reqForCandidature);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at ReqForCandidatureDAO function: writeReqForCandidature()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}

}
