package fr.vilth.sprintplanner.api.controllers;

public class MemberViewDto {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;

	protected MemberViewDto() {
		// private empty no-arg constructor
	}

	@Override
	public String toString() {
		return "{id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "}";
	}

}
