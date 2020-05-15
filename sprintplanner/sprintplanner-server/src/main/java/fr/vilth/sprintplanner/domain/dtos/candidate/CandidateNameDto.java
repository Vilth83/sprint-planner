package fr.vilth.sprintplanner.domain.dtos.candidate;

public class CandidateNameDto {

    String firstname;

    String lastname;

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public CandidateNameDto() {
    }

    public CandidateNameDto(String firstname, String lastname) {
	this.firstname = firstname;
	this.lastname = lastname;
    }
}
