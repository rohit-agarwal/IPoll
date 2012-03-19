<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %>
<script>
var count=2;
function addCandidate()
{
	if(count<=10)
	{
		var newCandidate, parent, newLine;
		newCandidate= document.createElement("input");
		newLine= document.createElement("br");
		parent= document.getElementById("CandidateList");
		newCandidate.setAttribute('id', "candidate"+count);
		newCandidate.setAttribute('name', "candidate"+count++);
		newCandidate.setAttribute('type', "text");
		parent.appendChild(newCandidate);
		parent.appendChild(newLine);
	}
	else
	{
		alert("Cannot add more Candidates.");
	}
	return true;
}
</script>
<div class="main">
	<h2> Candidature Registration </h2>
	
	<s:form action="regCandidate.action" enctype="multipart/form-data" method="post" onsubmit="verifyForm()">
		
		<tr>
			<td colspan="2"> <div class="error" id="error">${requestScope.error} </div></td>
		</tr>
			<s:textfield label="Name " id="name" name="name"></s:textfield>
			<s:textfield label="Surname " id="surName" name="surName"></s:textfield>
		<tr>
			<s:textfield label="Voter Id" id="voterId" name="voterId"></s:textfield>
			<td>Do not have Voter Id? Choose "Voter Id" from "Register".</td>
		</tr>
		<tr>
			<td colspan="3">
				<div id="error" class="error"></div>
			</td>
		</tr>
		<s:textfield label="Constituency  " name="constituency" id="constituency" ></s:textfield>
		<s:file label="Photo " name="userPhoto" id="userPhoto" />
		<s:file label="Symbol " name="CandidateLogo" id="CandidateLogo" />	
		<tr>
			<td >
				<label>List of Candidates : </label>
			</td>			
		
			<td>
				<div id="CandidateList">
					<input type="text" name="candidate1" id="candidate1"/>
					<br/>
				</div>
			</td>
		</tr>
		<tr>
			<td><input value="Add More Candidate(s)" type="button" onclick="return addCandidate()" />
		</tr>
		<s:submit align="center" value="Submit" />
	</s:form>
</div>	