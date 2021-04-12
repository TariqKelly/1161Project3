package project;
import java.util.ArrayList;
import java.util.Scanner;

public class Ministry {
	private String name;
	private final int LOWRISK_DAILYMAX =5000;
	private final int MEDIUMRISK_DAILYMAX =500;
	private final int HIGHRISK_DAILYMAX =0;
	
	public static final int LOWRISK_SEPARATION = 3;
	public static final int MEDIUMRISK_SEPARATION = 6;
	public static final int HIGHRISK_SEPARATION  =50;
	
	private enum Level{low, medium, high};
	
	private Level alertLevel;
	
	private ArrayList<ApprovalRequest> approvedRequests = new ArrayList<ApprovalRequest>();
	//private ArrayList<ApprovalRequest> deniedRequests = new ArrayList<ApprovalRequest>();
   	public Ministry(String name, int level)
   	{
   		this.name=name;
   		switch(level)
   		{
   		case 1:{
   			alertLevel = Level.low;
   			break;
   		}
   		case 2:{
   			alertLevel = Level.medium;
   			break;
   		}
   		case 3:{
   			alertLevel = Level.high;
   			break;
   		}
   		}
   		}
   	

	public int checkApproval(ApprovalRequest a)
	{
	    int maxForDay=0;
  		switch(alertLevel)
   		{
   		case low:{
   			maxForDay= LOWRISK_DAILYMAX;
   			break;
   		}
   		case medium:{
   			maxForDay= MEDIUMRISK_DAILYMAX;
   			break;
   		}
   		case high:{
   			maxForDay= HIGHRISK_DAILYMAX;
   			break;
   		}
   		}

		//getday from event
		int  day = a.getEvent().getDate().getDay();
		//get all approved events for day by looking through approvedrequests
		int sumForDay =0;
		ArrayList<ApprovalRequest> approvedForDay = new ArrayList<ApprovalRequest>();
		for (ApprovalRequest ar:approvedRequests)
		{
			if (ar.getEvent().getDate().getDay()==day)
			{
				approvedForDay.add(ar);
				sumForDay+=ar.getEvent().getNumPeople();
			}
		}
		//get sum of people for day
		int returnVal=-1;
		if (sumForDay+a.getEvent().getNumPeople()>maxForDay)
		{
			returnVal = -1;
			a.setComment("Maximum daily activity exceeded");
			System.out.println("Maximum daily activity exceeded");
		}
		else
		{
			if (canHold(a.getVenue(),a.getEvent().getNumPeople(),alertLevel))
			{
				returnVal = a.getId();
				approvedRequests.add(a);
			}
			else 
			{
				returnVal = -1;
			    a.setComment("Venue does not meet specifications");
				System.out.println("Venue too small for event");
			}
			
		}
		
		return returnVal;
		
	}
	
	private  boolean canHold(Venue venue, int nump, Level alertLevel)
	{
		//System.out.println("Checking if venue"+ venue.getId() + " can hold "+nump + " persons");
		boolean returnVal = false;
	    int separation=0;
	  		switch(alertLevel)
	   		{
	   		case low:{
	   			separation= LOWRISK_SEPARATION;
	   			break;
	   		}
	   		case medium:{
	   			separation= MEDIUMRISK_SEPARATION;
	   			break;
	   		}
	   		case high:{
	   			separation= HIGHRISK_SEPARATION;
	   			break;
	   		}
	   		}
        if (nump <(int)(venue.getSize()/separation))
           returnVal = true;
        
        else
        	 returnVal= false;
		return returnVal;
	}
	
public void showPublicInfo(Scanner scan, WorkArea work)
{
	System.out.println("-------------MINISTRY OF "+name+"-------------------------");
	System.out.println("High risk separation:"+HIGHRISK_SEPARATION);
	System.out.println("Medium risk separation:"+MEDIUMRISK_SEPARATION);
	System.out.println("Low risk separation:"+LOWRISK_SEPARATION);
	System.out.println("-----------------------------------------------------");
	System.out.println("Process planned events?[y/n]");
	String response  = scan.next();
    if (response.toUpperCase().charAt(0)=='Y')
    {
    	System.out.println("Processing data for "+work.promoters.size() + " promoters");
    	for(Promoter p:work.promoters)
    		p.submitPlans();
     
    }
	System.out.println("-----------------------------------------------------");
	
	
}
	

   
}
