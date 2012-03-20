package dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class VoterIdDAO extends BaseHibernate {
	VoterId vid;
	
	public boolean writeVoterId(VoterId voterid,WardID wardid,FileContent content,FileInfo file,Request request,RequestForVoterId req4voterid,WardUser warduser)
	{
		Session session = null;
		Transaction trans = null;
		try{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(file);
			System.out.println("FileId: "+file.getFileId());
			content.setFileId(file.getFileId());
			session.save(content);
			
			WardIdDAO t = new WardIdDAO();
			t.setSessionFactory(getSessionFactory());
			wardid = t.getWardId(wardid.getState(), wardid.getCity(),wardid.getWardNo());
			
			voterid.setWardId(wardid.getWardId());
			voterid.setImageFileId(file.getFileId());
			session.save(voterid);
			
			WardUserDAO wsd = new WardUserDAO();
			wsd.setSessionFactory(getSessionFactory());
			WardUser ws = wsd.getWardUser(wardid.getWardId());
			
			request.setId(ws.getId());
			session.save(request);
			
			req4voterid.setReqid(request.getReqid());
			req4voterid.setVoterid(voterid.getVoterID());
			session.save(req4voterid);
			trans.commit();
			return true;
		}catch(NullPointerException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating voterId.");
			if(trans!=null)
				trans.rollback();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			System.err.println("Error: creating voterId.");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public String getWardId(String state,String city,String wardNo)
	{
		WardID wardid;
		WardIdDAO t = new WardIdDAO();
		t.setSessionFactory(getSessionFactory());
		wardid = t.getWardId(state, city,wardNo);
		if(wardid!=null)
			return String.valueOf(wardid.wardId);
		return null;
	}
	
	public VoterId getVoterId(String voterid)
	{
		Session session = null;
		VoterId votercard = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(VoterId.class);
			ctr.add(Restrictions.idEq(voterid));
			if(ctr.list().size()>0)
					votercard = (VoterId) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoterIdDAO method: getVoterId()");
		}
		return votercard;
	}
	public VoterId getVoterIdByUser(String id)
	{
		Session session = null;
		VoterId votercard = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(VoterId.class);
			ctr.add(Restrictions.eq("id",id));
			if(ctr.list().size()>0)
					votercard = (VoterId) ctr.list().get(0); 
		}catch(HibernateException e)
		{
			System.err.println("Error: file: VoterIdDAO method: getVoterId()");
		}
		
		return votercard;
	}
	public boolean updateVoterIdPhoto(VoterId voterid,FileInfo file,FileContent content)
	{
		Session session=null;
		Transaction trans = null;
		FileInfo f = null;
		FileContent fc = null;
		try
		{
			if(voterid==null)
				return false;
			FileDAO fd = new FileDAO();
			fd.setSessionFactory(getSessionFactory());
			f = fd.getFileInfo(voterid.imageFileId);
			fc = fd.getFileContent(voterid.imageFileId);
			if(f.getUploadedBy().compareToIgnoreCase(voterid.getId())!=0)
				return false;
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.delete(fc);
			session.delete(f);
			session.save(file);
			content.setFileId(file.getFileId());
			session.save(content);
			voterid.setImageFileId(file.getFileId());
			session.update(voterid);
			trans.commit();
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: updateVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean updateVoterId(VoterId voterid)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.update(voterid);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: updateVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean createVoterId(VoterId voterid)
	{
		Session session=null;
		Transaction trans = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(voterid);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: createVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	
	public boolean approveVoterId(String voterid,Long reqid,String warduser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		VoterId votecard = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			RequestDAO r = new RequestDAO();
			r.setSessionFactory(getSessionFactory());
			req = r.getRequest(reqid);
			if(req==null)
				throw new Exception("Cannot find the desired request");
			if(req.getReqstatus().compareToIgnoreCase(Request.UNVERIFIED)!=0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.ACCEPTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			votecard = getVoterId(voterid);
			if(votecard==null)
				throw new Exception("The requested voterid doesnt exist.");
			votecard.setValidity(VoterId.ACCEPTED);
			votecard.setValidatedBy(warduser);
			votecard.setEffectiveFrom(new Timestamp(new Date().getTime()));
			session.update(votecard);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			e.printStackTrace();
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: approveVoterId()");
			if(trans!=null)
				trans.rollback();
		}finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean rejectVoterId(String voterid,Long reqid,String warduser)
	{
		Session session=null;
		Transaction trans = null;
		Request req = null;
		VoterId votecard = null;
		try
		{
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			RequestDAO r = new RequestDAO();
			r.setSessionFactory(getSessionFactory());
			req = r.getRequest(reqid);
			if(req==null)
				throw new Exception("Cannot find the desired request");
			if(req.getReqstatus().compareToIgnoreCase(Request.UNVERIFIED)!=0)
				throw new Exception("The desired request is served.");
			req.setReqstatus(Request.REJECTED);
			req.setReqServ(new Timestamp(new Date().getTime()));
			session.update(req);
			votecard = getVoterId(voterid);
			if(votecard==null)
				throw new Exception("The requested voterid doesnt exist.");
			votecard.setValidity(VoterId.REJECTED);
			votecard.setValidatedBy(warduser);
			votecard.setEffectiveFrom(new Timestamp(new Date().getTime()));
			session.update(votecard);
			trans.commit();
			return true;
		}catch(HibernateException e)
		{
			System.err.println("Error: at VoterIdDAO function: rejectVoterId()");
			if(trans!=null)
				trans.rollback();
		}catch(Exception e)
		{
			System.err.println("Error: at VoterIdDAO function: rejectVoterId()");
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
