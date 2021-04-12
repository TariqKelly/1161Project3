package project;
import java.util.Scanner;

public class PartyVenue extends Venue {
	double stageArea;
	double barArea, foodArea;
	int numSecurity;

	/**
	 * PartyVenue constructor
	 * 
	 * @param name
	 * @param stageArea
	 * @param barArea
	 * @param foodArea
	 * @param numSecurity
	 * @param basePrice
	 * @param lev
	 */
	public PartyVenue(String name, double stageArea, double barArea, double foodArea, int numSecurity, double basePrice,
			int lev) {
		super(name, stageArea + barArea + foodArea, basePrice, lev);
		this.numSecurity = numSecurity;
		this.stageArea = stageArea;
		this.barArea = barArea;
		this.foodArea = foodArea;

	}

	public double getEstimate(String type) {
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
		if (type.equals("SPORT"))
			price += sportsPrep;
		if (type.equals("TRAINING"))
			price += trainPrep;

		// System.out.println(this.getName()+":estimate to hold a "+type +" is "+
		// price);
		return price;

	}

	public double getCurrStage() {
		return stageArea;
	}

	public double getBarArea() {
		return barArea;
	}

	public double getFoodArea() {
		return foodArea;
	}

	public int getNumSecurity() {
		return numSecurity;
	}

	public void setStageArea(double stageArea) {

		this.stageArea = stageArea;
	}

	public void setFoodArea(double foodArea) {

		this.foodArea = foodArea;
	}

	public void setBarArea(double barArea) {

		this.barArea = barArea;
	}

	public void setNumSecurity(int numSecurity) {

		this.numSecurity = numSecurity;
	}

	public String toString() {
		return "ID:" + this.getId() + ";" + this.getName() + ";#Events:" + this.getApprovedEvents().size()
				+ ";Stage Area:" + stageArea + ";Bar Area:" + barArea + ";Food Area:" + foodArea + ";#Sec"
				+ numSecurity;

	}

	public String toFile() {
		return "" + this.getId() + ";" + this.getName() + ";" + this.getApprovedEvents().size() + ";" + stageArea + ";"
				+ barArea + ";" + foodArea + ";" + numSecurity;

	}

	/**
	 * Edits the data for a party venue
	 * 
	 * @param scan
	 */
	public void updateLocalData(Scanner scan) {
		scan.nextLine();
		String currname = getName();
		double currstage = getCurrStage();
		double currbar = getBarArea();
		double currfood = getFoodArea();
		double currprice = getPrice();
		int currsecurity = getNumSecurity();
		int currlevel = getLevel();
		System.out.println("Hit enter to keep name as [" + currname + "], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = currname;

		System.out.println("Hit enter to keep stage area at [" + currstage + "] or enter new stage area:");
		String stageentry = scan.nextLine();
		double stageArea;
		if (stageentry.equals(""))
			stageArea = currstage;
		else
			stageArea = Double.parseDouble(stageentry);

		System.out.println("Hit enter to keep Bar area at [" + currbar + "] or enter new Bar area:");
		String barentry = scan.nextLine();
		double barArea;
		if (barentry.equals(""))
			barArea = currbar;
		else
			barArea = Double.parseDouble(barentry);

		System.out.println("Hit enter to keep Food area at [" + currfood + "] or enter new Food area:");
		String foodentry = scan.nextLine();
		double foodArea;
		if (foodentry.equals(""))
			foodArea = currfood;
		else
			foodArea = Double.parseDouble(foodentry);

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
		setStageArea(stageArea);
		setBarArea(barArea);
		setFoodArea(foodArea);
		setNumSecurity(numSecurity);
		setPrice(price);
		setLevel(level);
	}
}
