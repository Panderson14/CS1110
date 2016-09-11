//Patrick Anderson (psa5dg)
// use an ArrayList 
import java.util.Scanner;
public class LousListElective {
	public static void main(String[] args) throws Exception {
		//http://stardock.cs.virginia.edu/louslist/Courses/view/CS
		String baseAddress = "http://stardock.cs.virginia.edu/louslist/Courses/view/";
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a department: ");
		String dept = keyboard.next();
		dept = dept.toUpperCase();
		CachingURL webAddress = new CachingURL(baseAddress+dept);
		Scanner webReader = new Scanner(webAddress.openStream());
		int compare[] = {0,0,0,0,0};
		String section = "", title = "", teacher = "";
		int level = 0, enrollment = 0;
		String blah = "";
		while (webReader.hasNextLine()) {
			String line = webReader.nextLine();
			String[] cells = line.split(";");
			String[] stuff = {"","","","",""};
			int[] stuff1 = {0,0};
			level = Integer.parseInt(cells[1]);
			if (level >= 4000 && cells[5].equals("Lecture")) {
				enrollment = Integer.parseInt(cells[15]);
				compare[1] = enrollment;
				compare[3] = level;
				if (compare[1] > compare[0] || (compare[1] == compare[0] && compare[3] > compare[2])) {
					compare[0] = compare [1];
					compare[2] = level;
					section = cells[2];
					title = cells[3];
					teacher = cells[4];
					stuff[0]=dept;stuff[1]=dept;stuff[2]=section;stuff[3]=title;stuff[4]=teacher;
					stuff1[0]=level;stuff1[1]=enrollment;
					blah = "The most popular 4000+ level course in the "+stuff[0]+" department is "+stuff[1]+" "+stuff1[0]+"-"+stuff[2]+" ("+stuff[3]+") with "+stuff1[1]+" students, and is taught by "+stuff[4]+".";
				}
			}
		}
		System.out.println(blah);
		webReader.close();
		keyboard.close();
	}
}
