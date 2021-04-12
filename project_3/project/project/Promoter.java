package project;


import java.util.ArrayList;
import java.util.Scanner;
/////
public class Promoter  implements Comparable<Promoter>{
	private String name;
	private double budget;
	private Ministry min;
	///
	private int id;
	private static int nextid =0;
	private ArrayList<Venue> venues;
	private ArrayList<Event> approvedEvents = new ArrayList<Event>();
	private ArrayList<Plan> plannedEvents = new ArrayList<Plan>();

	public Promoter (String name, double budget, Ministry min, ArrayList<Venue> venues) {

		this.name = name;
		this.budget = budget;
		this.min = min;    
		this.venues= venues;
		id = nextid;
		nextid++;
  	}
	//////
	
	public int compareTo(Promoter other)
	{
		return this.getName().compareTo(other.getName());
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBudget(double budget)
	{
		this.budget = budget;
	}
	
	public String toString()
	{
		return this.getId()+";"+this.name +";"+this.budget+";"+this.plannedEvents.size()+";"+this.approvedEvents.size();
	}
	
	public void addPlan (Plan p)
	{
		plannedEvents.add(p);
	}
	
	public static void resetId()
	{
		
		nextid=0;
	}
	
	/**
	 * Edits the data for a given promotor
	 * @param scan
	 */
	public void updateLocalData(Scanner scan)
	{
	    scan.nextLine();
		String currname = getName();
		double currBudget = getBudget();
		System.out.println("Hit enter to keep name as ["+currname+"], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = currname;
		System.out.println("Hit enter to keep budget at ["+currBudget +"] or enter new budget:");
		String budentry=scan.nextLine();
		double budget;
		if (budentry.equals(""))
			budget = currBudget;
		else
			budget = Double.parseDouble(budentry);
		setName(name);
		setBudget(budget);

	}
	
	public void payFor(Venue venue, Event event)
	{
		String type = event.getType();
		double cost = venue.getEstimate(type);
		budget -=cost;
	}
	
	public void submitPlans()
	{
		//System.out.println(name + " submitting "+ plannedEvents.size()+ " events.");
		for (Plan pl:plannedEvents)
		{
			int approvid =planEvent(pl.getNumPatrons(), pl.getEventType(),pl.getDate());
			//System.out.println(approvid +":"+ pl.getNumPatrons()+ pl.getEventType()+pl.getDate());
		}
			
	}

	public int planEvent(int numPatrons, String eventType, Date date)
	{
		double minCost =Double.MAX_VALUE;
		Venue selVen = null;
		int returnVal = -1;
		ArrayList<Venue> affordableVens= new ArrayList<Venue>();
		for (Venue ven:venues)
		{
			//System.out.println("checking "+ven.getName());
			if (this.budget >= ven.getEstimate(eventType))
				affordableVens.add(ven);
		}

		if (affordableVens.size()>0)
		{

			for (Venue ven:affordableVens)
			{
				//System.out.println("checking "+ven.getName());
				if (ven.available(date))
				{

					if (ven.canHold(numPatrons))
					{ 
						double eventCost = ven.getEstimate(eventType);
						//System.out.println("Evaluating "+ven.getName()+"@"+eventCost);
						if (eventCost<minCost)
						{
							minCost = eventCost;
							selVen = ven;
						}

					}
				//	else
				//		System.out.println(ven.getName()+" cannot hold "+numPatrons);
				}
				//else
				//	System.out.println(ven.getName()+" not available on "+date);

			}
		}
		if (selVen!=null)
		{
			Event e = new Event(eventType + " FOR " + this.name,eventType,numPatrons, date);
			returnVal = selVen.reserve(e,getBudget(),min);
			if (returnVal>=0)
			{
				System.out.println(this.getName() + " reserved "+e.getVenue().getName()+" for a party on day " +date.getDay());
				e.setVenue(selVen);
				payFor(selVen,e);
				approvedEvents.add(e);

			}
			else
				System.out.println("Unable to reserve  "+selVen.getName()+" for a party on day " +date.getDay()+"("+ this.getName() + ")");

		}
		else 
			returnVal= -1;
		//select venue
		return returnVal;
	}

	
	public double getBudget()
	{
		return budget;
	}

	public String getName()
	{
		return name;
	}

}
