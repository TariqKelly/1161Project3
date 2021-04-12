package project;

import java.io.PrintStream;
import java.util.ArrayList;

public class ReportScreen {

	public ReportScreen() {
	}

	public void listPromoters(ArrayList<Promoter> plist, PrintStream outStream) {
		if (outStream == System.out) {
			outStream.println("---------------Promoter List---------------");
			outStream.println("ID\tName\tBudget\t#Plans\t#Events");
			outStream.println("--------------------------------------------");
		}

		for (Promoter p : plist) {
			String outline = p.toString();
			if (outStream == System.out)
				outline = outline.replace(';', '\t');
			outStream.println(outline);
		}
	}

	public void listVenues(ArrayList<Venue> vlist, PrintStream outStream) {
		if (outStream == System.out)
			outStream.println("------Venue List--------");

		for (Venue v : vlist)
			if (outStream == System.out)
				outStream.println(v);
			else
				outStream.println(v.toFile());
	}

}
