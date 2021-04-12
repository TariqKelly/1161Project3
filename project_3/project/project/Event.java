package project;
public class Event {
	String name;
	Venue venue;
	Date date;
	String type;
	int numPeople;

	public Event(String name, String type, int numPeople, Date date) {
		this.name = name;
		this.type = type;
		this.date = date;
		this.numPeople = numPeople;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public Date getDate() {

		return date;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue v) {
		venue = v;
	}

	public String toString() {
		return "Date:" + this.getDate() + ";Name:" + this.getName() + ";Type:" + this.getType() + ";Venue:"
				+ this.getVenue() + ":numPeople:" + this.getNumPeople();
	}

}
