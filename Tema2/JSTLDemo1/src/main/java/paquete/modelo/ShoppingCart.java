package paquete.modelo;

public class ShoppingCart {
	private int numTickets;
	private int numAdult, numYounger, numSenior;
	private double totalAdult, totalYounger, totalSenior;
	
	public ShoppingCart (int numTicketAdults, int numTicketYounger, int numTicketSenior) {
		numTickets = numTicketAdults + numTicketYounger + numTicketSenior;
		
		numAdult = numTicketAdults;
		numYounger = numTicketYounger;
		numSenior = numTicketSenior;
		
		totalAdult = numTicketAdults * TicketPrice.ADULT;
		totalYounger = numTicketYounger * TicketPrice.YOUNGER;
		totalSenior = numTicketSenior * TicketPrice.SENIOR;
	}

	public int getNumTickets() {
		return numTickets;
	}

	public int getNumAdult() {
		return numAdult;
	}

	public int getNumYounger() {
		return numYounger;
	}

	public int getNumSenior() {
		return numSenior;
	}

	public double getTotalAdult() {
		return totalAdult;
	}

	public double getTotalYounger() {
		return totalYounger;
	}

	public double getTotalSenior() {
		return totalSenior;
	}
	
	
}
