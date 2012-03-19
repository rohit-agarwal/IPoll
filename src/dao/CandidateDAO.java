package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CandidateDAO extends BaseHibernate{
	
	public boolean updateCandidate(Candidate cand)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(cand);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CandidateDAO method:updateCandidate ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public boolean persistcandidate(Candidate cand)
	{
		Session session = null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(cand);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			trans.rollback();
			System.out.println("Error: file:CandidateDAO method:persistCandidate ");
			return false;
		}finally
		{
			if(session!=null)
				session.close();
		}
	}
	public Candidate getCandidateIdByUser(String id){
	
		Session session = null;
		Candidate candidateCard = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(Candidate.class);
			ctr.add(Restrictions.eq("user",id));
			if(ctr.list().size()>0)
					candidateCard = (Candidate) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: CnadidateDAO method: getCandidateByUserId()");
		}
		
		return candidateCard;
	}
	public boolean writeCandidatureIndependent(String voterId, ReqForCandidature reqCandidate1,
			ReqForCandidature reqCandidate2, ReqForCandidature reqCandidate3, ReqForCandidature reqCandidate4,
			ReqForCandidature reqCandidate5, ReqForCandidature reqCandidate6, ReqForCandidature reqCandidate7,
			ReqForCandidature reqCandidate8, ReqForCandidature reqCandidate9, ReqForCandidature reqCandidate10,
			Request reqWardUser, FileInfo info, FileContent content, Candidate candidate,
			WardID wardId, String user, ReqForCandidatureWard reqForCandidatureWard){
		
		VoterIdDAO vdao= new VoterIdDAO();
		Session session=null;
		Transaction trans=null;
		
		
		try{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(info);
			System.out.println("FileId: "+info.getFileId());
			content.setFileId(info.getFileId());
			session.save(content);
			
			WardIdDAO t = new WardIdDAO();
			t.setSessionFactory(getSessionFactory());
			wardId = t.getWardId(wardId.getState(), wardId.getCity(), wardId.getWardNo());
			candidate.setConstituency(wardId.getWardId());
			vdao.setSessionFactory(getSessionFactory());
			if(vdao.getVoterId(voterId)== null)
			{
				throw new Exception();
			}
			
			WardUserDAO wsd = new WardUserDAO();
			wsd.setSessionFactory(getSessionFactory());
			WardUser ws = wsd.getWardUser(wardId.getWardId());
			reqWardUser.setId(ws.getId());
			session.save(reqWardUser);
			reqForCandidatureWard.setReqId(reqWardUser.getReqid());
			reqForCandidatureWard.setCandidateId(candidate.getId());
			session.save(reqForCandidatureWard);
			
			Request tempReq;
			
		}
		catch(Exception e)
		{
			//HibernateException
		}
		
		return false;
	}
	public Candidate getCandidateId(String CandidateId)
	{
		Candidate cand = null;
		Session session=null;
		Criteria constraint=null;
		try
		{
			session = getSessionFactory().openSession();
			constraint=session.createCriteria(Candidate.class);
			constraint.add(Restrictions.idEq(CandidateId));
			if(constraint.list().size()>0)
			{
				cand = (Candidate) constraint.list().get(0);
			}
		}catch(HibernateException e)
		{
			System.out.println("Error:Hibernate Exception\nFileName: CandiateDAO Method:getCandidate()");
			e.printStackTrace();
		}catch(ClassCastException c)
		{
			System.out.println("Error:Hibernate Configuration in Candidate.hbm.xml");
		}finally
		{
			if(session!=null)
			session.close();
		}
		
	
		return cand;
	}

	

}