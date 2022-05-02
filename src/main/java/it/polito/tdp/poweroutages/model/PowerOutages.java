package it.polito.tdp.poweroutages.model;

import java.sql.Date;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class PowerOutages {

	int nerc_id;
	int customers_affected;
	LocalDateTime date_event_began;
	LocalDateTime date_event_finished;
	long outageDuration;
	
	

	public PowerOutages(int nerc_id, int customers_affected, LocalDateTime date_event_began,
			LocalDateTime date_event_finished) {
		super();
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.date_event_began = date_event_began;
		this.date_event_finished = date_event_finished;
		
		outageDuration = date_event_began.until(date_event_finished , ChronoUnit.HOURS);
		
	}
	
	
	
	public int getNerc_id() {
		return nerc_id;
	}


	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}


	public int getCustomers_affected() {
		return customers_affected;
	}


	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}


	public LocalDateTime getDate_event_began() {
		return date_event_began;
	}


	public void setDate_event_began(LocalDateTime date_event_began) {
		this.date_event_began = date_event_began;
	}


	public LocalDateTime getDate_event_finished() {
		return date_event_finished;
	}


	public void setDate_event_finished(LocalDateTime date_event_finished) {
		this.date_event_finished = date_event_finished;
	}



	@Override
	public String toString() {
		return date_event_began.getYear() + " " + date_event_began + " " + date_event_finished + " " + outageDuration + " " + customers_affected + "\n";
	}



	public long getOutageDuration() {
		return outageDuration;
	}



	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}	
	
	
	
}
