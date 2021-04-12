package project;


import java.util.Scanner;

public class SportsVenue extends Venue {
	private double competitorArea;
	private double spectatorArea;
	private int numSecurity;

	/**
	 * SportsVenue constructor
	 * 
	 * @param name
	 * @param competitorArea
	 * @param spectatorArea
	 * @param numSecurity
	 * @param basePrice
	 * @param lev
	 */
	public SportsVenue(String name, double competitorArea, double spectatorArea, int numSecurity, double basePrice,
			int lev) {
		super(name, competitorArea + spectatorArea, basePrice, lev);
		this.competitorArea = competitorArea;
		this.spectatorArea = spectatorArea;
		this.numSecurity = numSecurity;

	}

	public double getSize() {
		return competitorArea + spectatorArea;
	}

	public int countSecurity() {
		return numSecurity;
	}

	public double getCompArea() {
		return competitorArea;
	}

	public double getSpecArea() {
		return spectatorArea;

	}

	public int getNumSecurity() {
		return numSecurity;
	}

	public void setCompArea(double competitorArea) {

		this.competitorArea = competitorArea;
	}

	public void setSpecArea(double spectatorArea) {

		this.spectatorArea = spectatorArea;
	}

	public void setNumSecurity(int numSecurity) {

		this.numSecurity = numSecurity;
	}

	public double getEstimate(String type) {
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
		if (type.equals("TRAINING"))
			price += trainPrep;

		// System.out.println(this.getName()+":estimate to hold a "+type +" is "+
		// price);
		return price;

	}

	public String toString() {
		return "ID:" + this.getId() + ";" + this.getName() + ";#Events:" + this.getApprovedEvents().size()
				+ ";Compet Area:" + competitorArea + ";Spec Area:" + spectatorArea + ";#Sec:" + numSecurity;

	}

	public String toFile() {
		return "" + this.getId() + ";" + this.getName() + ";" + this.getApprovedEvents().size() + ";" + competitorArea
				+ ";" + spectatorArea + ";" + numSecurity;

	}

	/**
	 * Edits the data for a sports venue
	 * 
	 * @param scan
	 */
	public void updateLocalData(Scanner scan) {
		scan.nextLine();
		String currname = getName();
		double currcomp = getCompArea();
		double currspec = getSpecArea();
		double currprice = getPrice();
		int currsecurity = getNumSecurity();
		int currlevel = getLevel();
		System.out.println("Hit enter to keep name as [" + currname + "], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = currname;

		System.out.println("Hit enter to keep competitor area at [" + currcomp + "] or enter new competitor area:");
		String compentry = scan.nextLine();
		double competitorArea;
		if (compentry.equals(""))
			competitorArea = currcomp;
		else
			competitorArea = Double.parseDouble(compentry);

		System.out.println("Hit enter to keep spectator area at [" + currspec + "] or enter new spectator area:");
		String specentry = scan.nextLine();
		double spectatorArea;
		if (specentry.equals(""))
			spectatorArea = currspec;
		else
			spectatorArea = Double.parseDouble(specentry);

		System.out.println("Hit enter to keep price at [" + currprice + "] or enter new price:");
		String priceentry = scan.nextLine();
		double price;
		if (priceentry.equals(""))
			price = currprice;
		else
			price = Double.parseDouble(priceentry);

		System.out.println("Hit enter to keep number of security at [" + currsecurity + "] or enter new level:");
		String securityentry = scan.nextLine();
		int numSecurity;
		if (securityentry.equals(""))
			numSecurity = currsecurity;
		else
			numSecurity = Integer.parseInt(securityentry);

		System.out.println("Hit enter to keep level at [" + currlevel + "] or enter new level:");
		String levelentry = scan.nextLine();
		int level;
		if (levelentry.equals(""))
			level = currlevel;
		else
			level = Integer.parseInt(levelentry);

		setName(name);
		setCompArea(competitorArea);
		setSpecArea(spectatorArea);
		setNumSecurity(numSecurity);
		setPrice(price);
		setLevel(level);
	}
}
