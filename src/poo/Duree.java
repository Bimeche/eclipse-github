package poo;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Duree {
	private int heures;
	private int minutes;
	private int secondes;
	private Calendar cal;
	public Duree(Time t){
		Date date = new Date();
		date = t;
		cal = Calendar.getInstance();
		cal.setTime(date);
		heures = cal.get(Calendar.HOUR_OF_DAY);
		minutes = cal.get(Calendar.MINUTE);
		secondes = cal.get(Calendar.SECOND);
	}
	
	public int getHeures(){return heures;}
	public int getMinutes(){return minutes;}
	public int getSecondes(){return secondes;}
	
	public Duree difference(Duree t){
		/* Pour les secondes */
		Duree result = this;
		if(heures<t.heures || (heures==t.heures && minutes<t.minutes) || (heures==t.heures && minutes==t.minutes && secondes<t.secondes)){
			return t.difference(this);
		}
		if(secondes<t.secondes){
			if(t.minutes!=0) t.minutes++;
			else minutes--;
			result.secondes = 60+secondes-t.secondes;
		}
		else result.secondes = secondes-t.secondes;
		
		if(minutes<t.minutes){
			if(t.heures!=0) t.heures++;
			else heures--;
			result.minutes = 60+minutes-t.minutes;
		}
		else result.minutes = minutes-t.minutes;
		
		result.heures = Math.abs((heures+ t.heures)%24);
		return result;
			
	}
	
	public String toString(){
		String result = "";
		if(heures<10)	result+="0"+heures+":";
		else result+=heures+":";
		if(minutes<10) result+="0"+minutes+":";
		else result+=minutes+":";
		if(secondes<10)	result+="0"+secondes;
		else result+=secondes;
		return(result);
	}
}
