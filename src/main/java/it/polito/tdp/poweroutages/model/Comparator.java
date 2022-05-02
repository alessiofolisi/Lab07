package it.polito.tdp.poweroutages.model;

public class Comparator implements java.util.Comparator<PowerOutages> {

	@Override
	public int compare(PowerOutages o1, PowerOutages o2) {
		
		long durata1 = o1.outageDuration;
		long durata2 = o2.outageDuration;
		
		return (int) (durata2-durata1);
	} }
