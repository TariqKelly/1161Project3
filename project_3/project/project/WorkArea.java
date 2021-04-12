package project;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkArea {

	public WorkArea() {
	}

	ArrayList<Promoter> promoters = new ArrayList<Promoter>();
	ArrayList<Venue> venues = new ArrayList<Venue>();
	Ministry mny = new Ministry("HEALTH", 2);
	ReportScreen r = new ReportScreen();

	public void clearData() {
		promoters.clear();
		venues.clear();
		Promoter.resetId();
		Venue.resetId();
	}

	public ArrayList<Promoter> loadPromoters(String pfile, Ministry mny, ArrayList<Venue> vens) {
		Scanner pscan = null;
		ArrayList<Promoter> plist = new ArrayList<Promoter>();
		try {
			pscan = new Scanner(new File(pfile));
			while (pscan.hasNext()) {
				String[] nextLine = pscan.nextLine().split(" ");
				String name = nextLine[0];
				double budget = Integer.parseInt(nextLine[1]);
				Promoter p = new Promoter(name, budget, mny, vens);
				plist.add(p);
			}

			pscan.close();
		} catch (IOException e) {
		} catch (NumberFormatException nfe) {
		}

		return plist;

	}

	public ArrayList<Promoter> loadEventsToPromoters(ArrayList<Promoter> plist, String efile) {
		Scanner escan = null;
		try {
			escan = new Scanner(new File(efile));
			while (escan.hasNext()) {
				String[] nextLine = escan.nextLine().split(" ");
				String name = nextLine[0];
				int numPatrons = Integer.parseInt(nextLine[1]);
				String etype = nextLine[2];
				Date day = new Date(Integer.parseInt(nextLine[3]));
				int pdx = findPromoterByName(plist, name);
				if (pdx >= 0)
					plist.get(pdx).addPlan(new Plan(numPatrons, etype, day));
			}

			escan.close();
		} catch (IOException e) {
		} catch (NumberFormatException nfe) {
		}

		return plist;

	}

	public ArrayList<Venue> loadVenues(String vfile) {
		Scanner vscan = null;
		ArrayList<Venue> vlist = new ArrayList<Venue>();
		try {
			vscan = new Scanner(new File(vfile));
			while (vscan.hasNext()) {
				String[] nextLine = vscan.nextLine().split(" ");
				switch (nextLine.length) {
				case 4: // regular venue
				{
					Venue v = new Venue(nextLine[0], Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[2]),
							Integer.parseInt(nextLine[3]));
					vlist.add(v);
					break;
				}
				case 5: // training venue
				{
					Venue v = new TrainingVenue(nextLine[0], Double.parseDouble(nextLine[1]),
							Double.parseDouble(nextLine[2]), Double.parseDouble(nextLine[3]),
							Integer.parseInt(nextLine[4]));
					vlist.add(v);
					break;
				}
				case 6: // sport venue
				{
					Venue v = new SportsVenue(nextLine[0], Double.parseDouble(nextLine[1]),
							Double.parseDouble(nextLine[2]), Integer.parseInt(nextLine[3]),
							Double.parseDouble(nextLine[4]), Integer.parseInt(nextLine[5]));
					vlist.add(v);
					break;
				}
				case 7: // party venue
				{
					Venue v = new PartyVenue(nextLine[0], Double.parseDouble(nextLine[1]),
							Double.parseDouble(nextLine[2]), Double.parseDouble(nextLine[3]),
							Integer.parseInt(nextLine[4]), Double.parseDouble(nextLine[5]),
							Integer.parseInt(nextLine[6]));
					vlist.add(v);
					break;
				}
				default: {
					// default case
				}

				}
			}
			vscan.close();
		}

		catch (IOException e) {
		}

		return vlist;

	}

	public void loadTestCase(int caseNo, Scanner scan) {
		loadData(caseNo);
		System.out.print("Test case " + caseNo + " loaded: Show data?[y/n]");
		String response = scan.next();
		if (response.toUpperCase().charAt(0) == 'Y') {
			ReportScreen r = new ReportScreen();
			r.listVenues(venues, System.out);
			r.listPromoters(promoters, System.out);

		}

	}

	public void loadData(int caseNo) {
		clearData();
		venues = loadVenues(getVenueFile(caseNo));
		promoters = loadPromoters(getProFile(caseNo), mny, venues);
		promoters = loadEventsToPromoters(promoters, getEventFile(caseNo));

	}

	private String getProFile(int caseNo) {
		return "cases/Promoter." + caseNo + ".txt";
	}

	private String getVenueFile(int caseNo) {
		return "cases/Venue." + caseNo + ".txt";
	}

	private String getEventFile(int caseNo) {
		return "cases/Event." + caseNo + ".txt";
	}

	public int findPromoterByName(ArrayList<Promoter> proms, String name) {
		int pdx = -1;
		int currdx = 0;
		while ((currdx < proms.size()) && (pdx == -1)) {
			if (proms.get(currdx).getName().equals(name))
				pdx = currdx;
			currdx++;

		}

		return pdx;

	}
}
