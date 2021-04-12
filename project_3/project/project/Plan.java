package project;


public class Plan {

	private int numPatrons;
	private String eventType;
	private Date date;

	public Plan(int numPatrons, String eventType, Date date) {
		this.numPatrons = numPatrons;
		this.eventType = eventType;
		this.date = new Date(date.getDay());

	}

	public int getNumPatrons() {
		return numPatrons;
	}

	public String getEventType() {
		return eventType;
	}

	public Date getDate() {
		return date;
	}
}
