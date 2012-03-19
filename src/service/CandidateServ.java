package service;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import dao.FileContent;
import dao.FileDAO;
import dao.FileInfo;
import dao.RequestDAO;
import dao.Request;
import dao.WardID;
import dao.WardIdDAO;
import dao.WardUser;
import dao.Candidate;
import dao.CandidateDAO;

public class CandidateServ {

	CandidateDAO dao;
	Candidate candidateId;
		//send request to field officer
		//send request to listed candidates
		//send request to party
	
	
	public Candidate getCandidate(){
		return this.candidateId;
	}
	public boolean getCandidateId(String candidateId){

		if(dao!=null)
		{
			this.candidateId = dao.getCandidateId(candidateId);
			return true;
		}
		return false;
	}
	public boolean getCandidateIdByUser(String candidateId){ 
		this.candidateId = dao.getCandidateIdByUser(candidateId);
		if(candidateId!=null)
			return true;
		return false;
	}
	public boolean writeCandidature(){
		
		return false;
	}
}
