package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages> best;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	public List<PowerOutages> getOutages(Nerc nerc_id){	
		List<PowerOutages> outages = new ArrayList(podao.getOutages(nerc_id));
		
		return outages;
	}
	
	public List<PowerOutages> trovaSoluzione(Nerc nerc , int anni , int ore){
		
		List<PowerOutages> parziale = new ArrayList<PowerOutages>();
		
		cerca(nerc , anni , ore , 0 , parziale);
		
		return parziale;
	}

	public void cerca(Nerc nerc , int anni , int ore , int L , List<PowerOutages> parziale) {
		
		if(best==null || this.calcolaPersone(best)<this.calcolaPersone(parziale)) best = new ArrayList<>(parziale);
		
		for(PowerOutages po : this.getOutages(nerc)) {
			if(this.isValidOre(parziale, ore, po) && this.isValidAnni(parziale, anni, po)) {
				parziale.add(po);
				cerca(nerc , anni , ore , L+1 , parziale);
				parziale.remove(parziale.size()-1);
			}
		}
		
		
	}
	
	public boolean isValidOre(List<PowerOutages> parziale , int ore , PowerOutages p) {
		
		long conta=0;
		
		if(parziale.size()==0) return true;
		for(PowerOutages po : parziale) {
			conta+=po.outageDuration;
		}
		conta+=p.outageDuration;
		
		if(conta<=ore) return true;
		return false;
	}
	
	public boolean isValidAnni(List<PowerOutages> parziale , int anni , PowerOutages p) {
		PowerOutages vecchio = null;
		
		if(parziale.size()==0) return true;
		if(vecchio==null) vecchio = parziale.get(0);
		
		for(PowerOutages po : parziale) {
			if(po.getDate_event_began().getYear() < vecchio.getDate_event_began().getYear()) {
				vecchio = po;
			}	
		}
		
		if(vecchio!=null) {
		if(Math.abs(p.getDate_event_began().getYear() - vecchio.getDate_event_began().getYear()) <= 4) {
			return true;
		}}
		
		return false;
	}
	
	public int calcolaPersone(List<PowerOutages> parziale) {
		
		int nPersone=0;
		for(PowerOutages p : parziale) {
			nPersone+=p.getCustomers_affected();
		}
		return nPersone;
	}
	
	
}
