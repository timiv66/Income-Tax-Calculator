package tax;

public class Filer {
	
	private String status;
	private double annualIncome;
	private double taxCredits;
	private double atldeductions;
	private double itmdeductions;
	private String name;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public double getTaxCredits() {
		return taxCredits;
	}
	public void setTaxCredits(double taxCredits) {
		this.taxCredits = taxCredits;
	}
	public double getAtldeductions() {
		return atldeductions;
	}
	public void setAtldeductions(double atldeductions) {
		this.atldeductions = atldeductions;
	}
	public double getItmdeductions() {
		return itmdeductions;
	}
	public void setItmdeductions(double itmdeductions) {
		this.itmdeductions = itmdeductions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double calcAGI() {
		double AGI;
		AGI = annualIncome-atldeductions;
		return AGI;
	}
	
	public double calcTaxIncome() {
		double taxableInc = 0;
		if (status.matches("Single") || status.matches("Married File Separately")) {
			if (itmdeductions == 0) {
				taxableInc = calcAGI() - 13850;
			}else {
				taxableInc = calcAGI() - itmdeductions;
			}	
		}
		else if (status.matches("Married File Together")) {
			if(itmdeductions == 0) {
				taxableInc=calcAGI()-27700;
			}
			else {
				taxableInc=calcAGI()-itmdeductions;	
			}
		}	
		else if (status.matches("Head of Household")) {
			if(itmdeductions == 0) {
				taxableInc=calcAGI()-20800;
			}
			else {
				taxableInc=calcAGI()-itmdeductions;
			}
		}
		return taxableInc;
	}
	
	public double calcGrossTax() {
		double grossTaxLib = 0;
		switch (status) {
			case "Signle"://gross tax liability for single
				if(calcTaxIncome()>0 && calcTaxIncome()<=11000) {
					grossTaxLib=calcTaxIncome()*.10;
				}
				else if(calcTaxIncome()>11000 && calcTaxIncome()<=44725) {
					grossTaxLib=calcTaxIncome()*.12;
				}
				else if(calcTaxIncome()>44725 && calcTaxIncome()<=95375) {
					grossTaxLib=calcTaxIncome()*.22;
				}
				else if(calcTaxIncome()>95375 && calcTaxIncome()<=182100) {
					grossTaxLib=calcTaxIncome()*.24;
				}
				else if(calcTaxIncome()>182100 && calcTaxIncome()<=231250) {
					grossTaxLib=calcTaxIncome()*.32;
				}
				else if(calcTaxIncome()>231250 && calcTaxIncome()<=578125) {
					grossTaxLib=calcTaxIncome()*.35;
				}
				else if(calcTaxIncome()>578125) {
					grossTaxLib=calcTaxIncome()*.37;		
				}
				
				break;
			
			case"Married File Separately"://gross tax liability for Married File Separately
				if(calcTaxIncome()>0 && calcTaxIncome()<=11000) {
					grossTaxLib=calcTaxIncome()*.10;
				}
				else if(calcTaxIncome()>11000 && calcTaxIncome()<=44725) {
					grossTaxLib=calcTaxIncome()*.12;
				}
				else if(calcTaxIncome()>44725 && calcTaxIncome()<=95375) {
					grossTaxLib=calcTaxIncome()*.22;
				}
				else if(calcTaxIncome()>95375 && calcTaxIncome()<=182100) {
					grossTaxLib=calcTaxIncome()*.24;
				}
				else if(calcTaxIncome()>182100 && calcTaxIncome()<=231250) {
					grossTaxLib=calcTaxIncome()*.32;
				}
				else if(calcTaxIncome()>231250 && calcTaxIncome()<=346875) {
					grossTaxLib=calcTaxIncome()*.35;
				}
				else if(calcTaxIncome()>346875) {
					grossTaxLib=calcTaxIncome()*.37;
				}
				break;
				
			case"Married File Together"://gross tax liability for Married File Separately Together
				if(calcTaxIncome()>0 && calcTaxIncome()<=22000) {
					grossTaxLib=calcTaxIncome()*.10;
				}
				else if(calcTaxIncome()>22000 && calcTaxIncome()<=89450) {
					grossTaxLib=calcTaxIncome()*.12;
				}
				else if(calcTaxIncome()>89450 && calcTaxIncome()<=190750) {
					grossTaxLib=calcTaxIncome()*.22;
				}
				else if(calcTaxIncome()>190750 && calcTaxIncome()<=364200) {
					grossTaxLib=calcTaxIncome()*.24;
				}
				else if(calcTaxIncome()>364200 && calcTaxIncome()<=462500) {
					grossTaxLib=calcTaxIncome()*.32;
				}
				else if(calcTaxIncome()>462500 && calcTaxIncome()<=693750) {
					grossTaxLib=calcTaxIncome()*.35;
				}
				else if(calcTaxIncome()>693750) {
					grossTaxLib=calcTaxIncome()*.37;
				}
				break;
				
			case"Head of Household"://gross tax liability for Head of Household
				if(grossTaxLib>0 && grossTaxLib<=15700) {
					grossTaxLib=grossTaxLib*.10;
				}
				else if(calcTaxIncome()>15700 && calcTaxIncome()<=59850) {
					grossTaxLib=calcTaxIncome()*.12;
				}
				else if(calcTaxIncome()>59850 && calcTaxIncome()<=95350) {
					grossTaxLib=calcTaxIncome()*.22;
				}
				else if(calcTaxIncome()>95350 && calcTaxIncome()<=182100) {
					grossTaxLib=calcTaxIncome()*.24;
				}
				else if(calcTaxIncome()>182100 && calcTaxIncome()<=231250) {
					grossTaxLib=calcTaxIncome()*.32;
				}
				else if(calcTaxIncome()>231250 && calcTaxIncome()<=578100) {
					grossTaxLib=calcTaxIncome()*.35;
				}
				else if(calcTaxIncome()>578100) {
					grossTaxLib=calcTaxIncome()*.37;
				}
				break;	
		}
		return grossTaxLib;
	}
	
	public double calctaxDueorRef() {
		double taxDueorRef;
		taxDueorRef = calcGrossTax() - getTaxCredits();
		return taxDueorRef;
	}
	
	Filer(){
		
	}
	
	Filer(String status, double annualIncome, double taxCredits, double deductions, String name){
		
	}
	
	
	public static void main(String[] args) {
		
		Filer filer1 = new Filer();
		filer1.setAnnualIncome(65000);
		filer1.setAtldeductions(4000);
		filer1.setItmdeductions(0);
		filer1.setStatus("Single");
		System.out.println(filer1.calcTaxIncome());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
