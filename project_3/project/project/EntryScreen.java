package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EntryScreen {

	public EntryScreen() {
	}

	public ArrayList<Promoter> managePromoters(Scanner scan, ArrayList<Promoter> proms, Ministry min,
			ArrayList<Venue> vens) throws NumberFormatException {
		ReportScreen r = new ReportScreen();
		char mchoice = 'c';
		String menu = "";
		while (mchoice != 'X') {
			String menuOptions = "[A]dd/Create promoter\n[E]dit/Update promoter\n";
			menuOptions += "[L]ist/Read promoters\n[D]elete promoter\nE[x]it\n";
			System.out.println(menuOptions);
			menu = scan.next().toUpperCase();
			mchoice = menu.charAt(0);
			switch (mchoice) {
			case 'A': {
				Promoter p = createPromoter(scan, min, vens);
				if (p != null)
					proms.add(p);
				break;
			}
			case 'L': {
				Collections.sort(proms);
				r.listPromoters(proms, System.out);
				break;
			}
			case 'E': {
				System.out.println("Please enter the ID of the promoter to be updated:");
				int pid = Integer.parseInt(scan.next());
				int pdx = findPromoter(proms, pid);
				if (pdx >= 0)
					proms.get(pdx).updateLocalData(scan);
				else
					System.out.println("Promoter with id " + pid + " not found.");
				break;
			}
			case 'D': {
				System.out.println("Please enter the ID of the promoter to be deleted:");
				int pid = Integer.parseInt(scan.next());
				int pdx = findPromoter(proms, pid);

				if (pdx >= 0)
					proms.remove(pdx);
				else
					System.out.println("Promoter with id " + pid + " not found.");
				break;
			}

			}

		}
		return proms;
	}

	public Promoter createPromoter(Scanner scan, Ministry min, ArrayList<Venue> vens) {
		Promoter p = null;
		try {
			System.out.println("Please enter Promoter Name:");
			String name = scan.next();
			System.out.println("Please enter Promoter Budget:");
			double budget = Double.parseDouble(scan.next());
			p = new Promoter(name, budget, min, vens);
		} catch (NumberFormatException nfe) {
		}
		return p;
	}

	public int findPromoter(ArrayList<Promoter> proms, int pid) {
		int pdx = -1;
		int currdx = 0;
		while ((currdx < proms.size()) && (pdx == -1)) {
			if (proms.get(currdx).getId() == pid)
				pdx = currdx;
			currdx++;

		}

		return pdx;

	}

	public ArrayList<Venue> manageVenues(Scanner scan, ArrayList<Venue> vens) {
		ReportScreen r = new ReportScreen();
		char mchoice = 'c';
		String menu = "";
		// code required here to implement a venue management screen
		while (mchoice != 'X') {
			String menuOptions = "[A]dd/Create venue\n[E]dit/Update venue\n";
			menuOptions += "[L]ist/Read Venues\n[D]elete venue\nE[x]it\n";
			System.out.println(menuOptions);
			menu = scan.next().toUpperCase();
			mchoice = menu.charAt(0);
			switch (mchoice) {
			case 'A': {
				Venue v = createVenue(scan);
				if (v != null)
					vens.add(v);
				break;
			}
			case 'L': {
				Collections.sort(vens);
				r.listVenues(vens, System.out);
				break;
			}
			case 'E': {
				System.out.println("Please enter the ID of the venue to be updated:");
				int vid = Integer.parseInt(scan.next());
				int vdx = findVenue(vens, vid);
				if (vdx >= 0)
					vens.get(vdx).updateLocalData(scan);
				else
					System.out.println("Venue with id " + vid + " not found.");
				break;
			}
			case 'D': {
				System.out.println("Please enter the ID of the venue to be deleted:");
				int vid = Integer.parseInt(scan.next());
				int pdx = findVenue(vens, vid);

				if (pdx >= 0)
					vens.remove(pdx);
				else
					System.out.println("Venue with id " + vid + " not found.");
				break;
			}
			}
		}
		return vens;
	}

	public Venue createVenue(Scanner scan) {
		Venue v = null;
		char mchoice = 'c';
		String menu = "";

		// code needed to obtain the information required to represent a venue and
		// create an appropriate venue object
		while (mchoice != 'X') {
			System.out.println(
					"General Purpose [V]enue\n[P]arty venue\n[S]ports venue\n[T]raining venue\nE[x]it to previous menu\n");

			menu = scan.next().toUpperCase();
			mchoice = menu.charAt(0);

			switch (mchoice) {
			case 'V': {
				try {
					System.out.println("Please enter Venue Name:");
					String name = scan.next();
					System.out.println("Please enter Venue Size:");
					double size = Double.parseDouble(scan.next());
					System.out.println("Please enter Venue Base Price:");
					double basePrice = Double.parseDouble(scan.next());
					System.out.println("Please enter Venue Level:");
					int lev = Integer.parseInt(scan.next());
					v = new Venue(name, size, basePrice, lev);
				} catch (NumberFormatException nfe) {
				}
				break;
			}
			case 'P': {
				try {
					System.out.println("Please enter Venue Name:");
					String name = scan.next();
					System.out.println("Please enter Stage Area Size:");
					double stageArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Bar Area Size:");
					double barArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Food Area Size:");
					double foodArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Number of Security:");
					int numSecurity = Integer.parseInt(scan.next());
					System.out.println("Please enter Venue Base Price:");
					double basePrice = Double.parseDouble(scan.next());
					System.out.println("Please enter Venue Level:");
					int lev = Integer.parseInt(scan.next());
					v = new PartyVenue(name, stageArea, barArea, foodArea, numSecurity, basePrice, lev);
				} catch (NumberFormatException nfe) {
				}
				break;
			}
			case 'S': {
				try {
					System.out.println("Please enter Venue Name:");
					String name = scan.next();
					System.out.println("Please enter Competitor Area Size:");
					double competitorArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Spectator Area Size:");
					double spectatorArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Number of Security:");
					int numSecurity = Integer.parseInt(scan.next());
					System.out.println("Please enter Venue Base Price:");
					double basePrice = Double.parseDouble(scan.next());
					System.out.println("Please enter Venue Level:");
					int lev = Integer.parseInt(scan.next());
					v = new SportsVenue(name, competitorArea, spectatorArea, numSecurity, basePrice, lev);
				} catch (NumberFormatException nfe) {
				}
				break;
			}
			case 'T': {
				try {
					System.out.println("Please enter Venue Name:");
					String name = scan.next();
					System.out.println("Please enter Instructor Area Size:");
					double instructorArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Other Area Size:");
					double otherArea = Double.parseDouble(scan.next());
					System.out.println("Please enter Number of Security:");
					int numSecurity = Integer.parseInt(scan.next());
					System.out.println("Please enter Venue Base Price:");
					double basePrice = Double.parseDouble(scan.next());
					System.out.println("Please enter Venue Level:");
					int lev = Integer.parseInt(scan.next());
					v = new TrainingVenue(name, instructorArea, otherArea, basePrice, lev);
				} catch (NumberFormatException nfe) {
				}
				break;
			}
			}
		}
		return v;
	}

	public int findVenue(ArrayList<Venue> vens, int vid) {
		int vdx = -1;
		//// code needed here to find venue with id VID in arraylist of venues
		for (Venue venue : vens) {
			if (venue.getId() == vid) {
				vdx = vens.indexOf(venue);
			}
		}

		return vdx;

	}

}
