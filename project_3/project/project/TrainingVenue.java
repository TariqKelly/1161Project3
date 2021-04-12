package project;
import java.util.Scanner;

public class TrainingVenue extends Venue {
	private double instructorArea;
	private double otherArea;

	/**
	 * TrainingVenue constructor
	 * 
	 * @param name
	 * @param instructorArea
	 * @param otherArea
	 * @param basePrice
	 * @param lev
	 */
	public TrainingVenue(String name, double instructorArea, double otherArea, double basePrice, int lev) {
		super(name, instructorArea + otherArea, basePrice, lev);
		this.instructorArea = instructorArea;
		this.otherArea = otherArea;

	}

	public double getEstimate(String type) {
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
		if (type.equals("SPORT"))
			price += sportsPrep;

		// System.out.println(this.getName()+":estimate to hold a "+type +" is "+
		// price);
		return price;

	}

	public double getInstructorArea() {
		return instructorArea;
	}

	public double getOtherArea() {
		return otherArea;

	}

	public void setInstructorArea(double instructorArea) {

		this.instructorArea = instructorArea;
	}

	public void setOtherArea(double otherArea) {

		this.otherArea = otherArea;
	}

	public String toString() {
		return "ID:" + this.getId() + ";" + this.getName() + ";#Events:" + this.getApprovedEvents().size()
				+ ";Inst.Area" + instructorArea + ";Oth.Area" + otherArea;

	}

	public String toFile() {
		return "" + this.getId() + ";" + this.getName() + ";" + this.getApprovedEvents().size() + ";" + instructorArea
				+ ";" + otherArea;

	}

	/**
	 * Edits the data for a given venue
	 * 
	 * @param scan
	 */
	public void updateLocalData(Scanner scan) {
		scan.nextLine();
		String currname = getName();
		double currinstruct = getInstructorArea();
		double currother = getOtherArea();
		double currprice = getPrice();
		int currlevel = getLevel();
		System.out.println("Hit enter to keep name as [" + currname + "], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = currname;

		System.out.println("Hit enter to keep instructor area at [" + currinstruct + "] or enter new instructor area:");
		String instructentry = scan.nextLine();
		double instructorArea;
		if (instructentry.equals(""))
			instructorArea = currinstruct;
		else
			instructorArea = Double.parseDouble(instructentry);

		System.out.println("Hit enter to keep Bar area at [" + currother + "] or enter new Bar area:");
		String otherentry = scan.nextLine();
		double otherArea;
		if (otherentry.equals(""))
			otherArea = currother;
		else
			otherArea = Double.parseDouble(otherentry);

		System.out.println("Hit enter to keep price at [" + currprice + "] or enter new price:");
		String priceentry = scan.nextLine();
		double price;
		if (priceentry.equals(""))
			price = currprice;
		else
			price = Double.parseDouble(priceentry);

		System.out.println("Hit enter to keep level at [" + currlevel + "] or enter new level:");
		String levelentry = scan.nextLine();
		int level;
		if (levelentry.equals(""))
			level = currlevel;
		else
			level = Integer.parseInt(levelentry);

		setName(name);
		setInstructorArea(instructorArea);
		setOtherArea(otherArea);
		setPrice(price);
		setLevel(level);
	}
}
