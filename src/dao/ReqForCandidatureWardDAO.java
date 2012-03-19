package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

class ReqForCandidatureWardDAO extends BaseHibernate {

	public ReqForCandidatureWardDAO(){
		
	}
	public boolean writeReqForCandidatureWard(ReqForCandidatureWard reqForCandidature)
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
			System.err.println("Error: at ReqForCandidatureWardDAO function: writeReqForCandidatureWard()");
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
