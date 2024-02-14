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
		AGI = getAnnualIncome()-getAtldeductions();
		return AGI;
	}
	
	public double calcTaxIncome() {
		double taxableInc = 0;
		if (getStatus().matches("Single") || getStatus().matches("Married File Separately")) {
			if (getItmdeductions() == 0) {
				taxableInc = calcAGI() - 13850;
			}else {
				taxableInc = calcAGI() - getItmdeductions();
			}	
		}
		else if (getStatus().matches("Married File Together")) {
			if(getItmdeductions() == 0) {
				taxableInc=calcAGI()-27700;
			}
			else {
				taxableInc=calcAGI()-getItmdeductions();	
			}
		}	
		else if (getStatus().matches("Head of Household")) {
			if(getItmdeductions() == 0) {
				taxableInc=calcAGI()-20800;
			}
			else {
				taxableInc=calcAGI()-getItmdeductions();
			}
		}
		return taxableInc;
	}
	
	public double calcGrossTax() {
		double grossTax = 0;
		//Single filers
		if (getStatus().matches("Single")) {
			if(calcTaxIncome() >= 0 && calcTaxIncome() <= 11000) {
				grossTax = calcTaxIncome() * .10;
				return grossTax;
			}else if(calcTaxIncome() > 11000 && calcTaxIncome() <= 44725) {
				grossTax = calcTaxIncome() * .12;
				return grossTax;
			}else if(calcTaxIncome() > 44725 && calcTaxIncome() <= 95375) {
				grossTax = calcTaxIncome() * .22;
				return grossTax;
			}else if(calcTaxIncome() > 95375 && calcTaxIncome() <= 182100) {
				grossTax = calcTaxIncome() * .24;
				return grossTax;
			}else if(calcTaxIncome() > 182100 && calcTaxIncome() <= 231250) {
				grossTax = calcTaxIncome() * .32;
				return grossTax;
			}else if(calcTaxIncome() > 231250 && calcTaxIncome() <= 578125) {
				grossTax = calcTaxIncome() * .35;
				return grossTax;
			}else if(calcTaxIncome() > 578125) {
				grossTax = calcTaxIncome() * .37;
				return grossTax;
			}
		}
		
		//Married Filed Together
		else if (getStatus().matches("Married File Together")) {
			if(calcTaxIncome() >= 0 && calcTaxIncome() <= 22000) {
				grossTax = calcTaxIncome() * .10;
				return grossTax;
			}else if(calcTaxIncome() > 22000 && calcTaxIncome() <= 89450) {
				grossTax = calcTaxIncome() * .12;
				return grossTax;
			}else if(calcTaxIncome() > 89450 && calcTaxIncome() <= 190750) {
				grossTax = calcTaxIncome() * .22;
				return grossTax;
			}else if(calcTaxIncome() > 190750 && calcTaxIncome() <= 364200) {
				grossTax = calcTaxIncome() * .24;
				return grossTax;
			}else if(calcTaxIncome() > 364200 && calcTaxIncome() <= 462500) {
				grossTax = calcTaxIncome() * .32;
				return grossTax;
			}else if(calcTaxIncome() > 462500 && calcTaxIncome() <= 693750) {
				grossTax = calcTaxIncome() * .35;
				return grossTax;
			}else if(calcTaxIncome() > 693751) {
				grossTax = calcTaxIncome() * .37;
				return grossTax;
			}
		}
		
		//Married File Separately
		else if (getStatus().matches("Married File Separately")) {
			if(calcTaxIncome() >= 0 && calcTaxIncome() <= 11000) {
				grossTax = calcTaxIncome() * .10;
				return grossTax;
			}else if(calcTaxIncome() > 11000 && calcTaxIncome() <= 44725) {
				grossTax = calcTaxIncome() * .12;
				return grossTax;
			}else if(calcTaxIncome() > 44725 && calcTaxIncome() <= 95375) {
				grossTax = calcTaxIncome() * .22;
				return grossTax;
			}else if(calcTaxIncome() > 95375 && calcTaxIncome() <= 182100) {
				grossTax = calcTaxIncome() * .24;
				return grossTax;
			}else if(calcTaxIncome() > 182100 && calcTaxIncome() <= 231250) {
				grossTax = calcTaxIncome() * .32;
				return grossTax;
			}else if(calcTaxIncome() > 231250 && calcTaxIncome() <= 346875) {
				grossTax = calcTaxIncome() * .35;
				return grossTax;
			}else if(calcTaxIncome() > 346875) {
				grossTax = calcTaxIncome() * .37;
				return grossTax;
			}
		}
		
		//Head of Household
		else if (getStatus().matches("Head of Household")) {
			if(calcTaxIncome() >= 0 && calcTaxIncome() <= 15700) {
				grossTax = calcTaxIncome() * .10;
				return grossTax;
			}else if(calcTaxIncome() > 15700 && calcTaxIncome() <= 59850) {
				grossTax = calcTaxIncome() * .12;
				return grossTax;
			}else if(calcTaxIncome() > 59850 && calcTaxIncome() <= 95350) {
				grossTax = calcTaxIncome() * .22;
				return grossTax;
			}else if(calcTaxIncome() > 95350 && calcTaxIncome() <= 182100) {
				grossTax = calcTaxIncome() * .24;
				return grossTax;
			}else if(calcTaxIncome() > 182100 && calcTaxIncome() <= 231250) {
				grossTax = calcTaxIncome() * .32;
				return grossTax;
			}else if(calcTaxIncome() > 231250 && calcTaxIncome() <= 578100) {
				grossTax = calcTaxIncome() * .35;
				return grossTax;
			}else if(calcTaxIncome() > 578100) {
				grossTax = calcTaxIncome() * .37;
				return grossTax;
			}
		}
		
		return grossTax;
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
