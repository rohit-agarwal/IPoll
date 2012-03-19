package dao;
import java.util.Date;

public class Candidate {
	private String id;
	private String user;
	private String voterId;
	private Boolean validity;
	private Date dateOfEffect;
	private String representingParty;
	private Integer partyType;
	private String symbol;
	private Long constituency;
	private String candidate1;
	private String candidate2;
	private String candidate3;
	private String candidate4;
	private String candidate5;
	private String candidate6;
	private String candidate7;
	private String candidate8;
	private String candidate9;
	private String candidate10;
	
	Candidate(){

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniqueId() {
		return user;
	}

	public void setUniqueId(String uniqueId) {
		this.user = uniqueId;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public Boolean getValidity() {
		return validity;
	}

	public void setValidity(Boolean validity) {
		this.validity = validity;
	}

	public Date getDateOfEffect() {
		return dateOfEffect;
	}

	public void setDateOfEffect(Date dateOfEffect) {
		this.dateOfEffect = dateOfEffect;
	}

	public String getRepresentingParty() {
		return representingParty;
	}

	public void setRepresentingParty(String representingParty) {
		this.representingParty = representingParty;
	}

	public Integer getPartyType() {
		return partyType;
	}

	public void setPartyType(Integer partyType) {
		this.partyType = partyType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCandidate1() {
		return candidate1;
	}

	public void setCandidate1(String candidate1) {
		this.candidate1 = candidate1;
	}

	public String getCandidate2() {
		return candidate2;
	}

	public void setCandidate2(String candidate2) {
		this.candidate2 = candidate2;
	}

	public String getCandidate3() {
		return candidate3;
	}

	public void setCandidate3(String candidate3) {
		this.candidate3 = candidate3;
	}

	public String getCandidate4() {
		return candidate4;
	}

	public void setCandidate4(String candidate4) {
		this.candidate4 = candidate4;
	}

	public String getCandidate5() {
		return candidate5;
	}

	public void setCandidate5(String candidate5) {
		this.candidate5 = candidate5;
	}

	public String getCandidate6() {
		return candidate6;
	}

	public void setCandidate6(String candidate6) {
		this.candidate6 = candidate6;
	}

	public String getCandidate7() {
		return candidate7;
	}

	public void setCandidate7(String candidate7) {
		this.candidate7 = candidate7;
	}

	public String getCandidate8() {
		return candidate8;
	}

	public void setCandidate8(String candidate8) {
		this.candidate8 = candidate8;
	}

	public String getCandidate9() {
		return candidate9;
	}

	public void setCandidate9(String candidate9) {
		this.candidate9 = candidate9;
	}

	public String getCandidate10() {
		return candidate10;
	}

	public void setCandidate10(String candidate10) {
		this.candidate10 = candidate10;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getConstituency() {
		return constituency;
	}

	public void setConstituency(Long constituency) {
		this.constituency = constituency;
	}
	

}