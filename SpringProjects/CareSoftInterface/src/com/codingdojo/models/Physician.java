package com.codingdojo.models;

import java.util.ArrayList;
import java.util.Date;

public class Physician extends User implements HIPAACompliantUser{
//... imports class definition...
    
    // Inside class:    
    private ArrayList<String> patientNotes;
	
    // TO DO: Constructor that takes an ID
    public Physician(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
    
    // TO DO: Implement HIPAACompliantUser!
    public void newPatientNotes(String notes, String patientName, Date date) {
        String report = String.format(
            "Datetime Submitted: %s \n", date);
        report += String.format("Reported By ID: %s\n", this.id);
        report += String.format("Patient Name: %s\n", patientName);
        report += String.format("Notes: %s \n", notes);
        this.patientNotes.add(report);
    }

    // TO DO: Setters & Getters
	public ArrayList<String> getPatientNotes() {
		return patientNotes;
	}

	public void setPatientNotes(ArrayList<String> patientNotes) {
		this.patientNotes = patientNotes;
	}

	
	//Pin must be exactly 4 digits, returns false if not.
	//Expected to assign the pin to the user (as a member variable)
	@Override
	public boolean assignPin(int pin) {
		int pinLength = String.valueOf(pin).length();
		if (pinLength == 4 ) {
			this.pin = pin;
			return true;
		} else {	
			return false;
		}
	}
	
	//Checks the physician's id against the given id;
	//returns true if they are a match, otherwise false.
	public boolean accessAuthorized(Integer confirmedAuthID) {
		if (this.id == confirmedAuthID) {
			return true;
		} else {
			return false;
		}
	}
}