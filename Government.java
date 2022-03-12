import java.io.*;
import java.util.*;
public class Government {
	
	static Scanner sc = new Scanner(System.in);
	private static File f1 = new File("C:\\Users\\kalai\\OneDrive\\Desktop\\CowinApp\\Govt Resources\\List of Vaccinated citizens and Vaccines\\List.csv");// Specify your preferred location
	private static File f2 = new File("C:\\Users\\kalai\\OneDrive\\Desktop\\CowinApp\\Govt Resources\\List of Vaccinated citizens and Vaccines\\Vaccines.csv");// specify your preferred location
	//private static File f3 = new File("C:\\Users\\kalai\\OneDrive\\Desktop\\CowinApp\\Govt Resources\\Certificates of Vaccinated people");
	private static List<String> vaccines = new ArrayList<String>();
	private static List<String[]> values = new ArrayList<String[]>();
	static int index = 0;
	
	public static void cls() {
    	try {
    		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    	}catch(Exception E) {
    		System.out.println(E);
    	}
    }

	Government() throws IOException{
		if(!f1.exists() && !f2.exists())
			newFile();
		else
			retrieveFile();
	}
	
	static void callAdmin() throws IOException {
		int ch=0;
		do {
			System.out.println("\n\t\t\tADMIN PORTAL\n");
			System.out.println(" (1)List of Vaccinated people  (2)Insights  (3)Add/Remove Vaccines  (4)Pre-registered Candidates (5)View certificate  (6)Log out");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				cls();
				displayList(1);
				System.out.println(" (1)Display certificate (2)Go back");
				switch(sc.nextInt()) {
					case 1:
						System.out.print("Aadhaar Number : ");
						String str = sc.next();
						sc.nextLine();
						if (checkAadhaar(str)) {
							showCertificate(values.get(index));
						}
						break;
					case 2:
						cls(); 
						break;
				}
				break;
			case 2:
				cls();
				getInsights();
				System.out.println("\tPress any number to go back");
				sc.next();
				cls();
				break;
			case 3:
				cls();
				updateVaccines();
				cls();
				break;
			case 4:
				cls();
				displayList(0);
				System.out.println("\tPress any number to go back");
				sc.next();
				cls();
				break;
			case 5:
				cls();
				do {
					System.out.print("Aadhaar Number : ");
					String str = sc.next();
					sc.nextLine();
					if (checkAadhaar(str)) {
						showCertificate(values.get(index));
					}
					else {
						System.out.println("Candidate is not Vaccinated!");
					}
					System.out.println(" (1)Search again (2)Go back");
				} while (sc.nextInt()!=2);
				cls();
			default:
				break;
			}
		} while (ch!=6);
		cls();
		updateList();
	}
	
	static void getInsights() {
		int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0;
		for(String[] st : values) {
			j=Integer.valueOf(st[3]);
			if(st[7].equals("2")) {
				a++;
				if(Integer.valueOf(st[3]) < 18) {
					b++;
				}
				else if(Integer.valueOf(st[3]) >= 18 && Integer.valueOf(st[3]) <=60 ) {
					c++;
				}
				else if(Integer.valueOf(st[3]) > 60) {
					d++;
				}
			}
			else if(st[7].equals("1")) {
				e++;
				if(Integer.valueOf(st[3]) < 18) {
					f++;
				}
				else if(Integer.valueOf(st[3]) >= 18 && Integer.valueOf(st[3]) <=60 ) {
					g++;
				}
				else if(Integer.valueOf(st[3]) > 60) {
					h++;
				}
			}
			else if(st[7].equals("0")) {
				i++;
			}
		}
		System.out.println("\n\t(1) Fully vaccinated\t: " + a);
		System.out.println("\t\t--> Below 18 years old\t\t: "+ b);
		System.out.println("\t\t--> Between 18 and 60 years old\t: "+ c);
		System.out.println("\t\t--> Above 60 years old\t\t: " + d);
		System.out.println("\n\t(2) Partially vaccinated: " + e);
		System.out.println("\t\t--> Below 18 years old\t\t: "+ f);
		System.out.println("\t\t--> Between 18 and 60 years old\t: "+ g);
		System.out.println("\t\t--> Above 60 years old\t\t: " + h);
		System.out.println("\n\t(3) Yet to Vaccinate\t: " + i);
		System.out.println("\n\t(4) Total\t\t: " + (a+e+i));
	}

	static void updateList() throws IOException {
		f1.delete();
		f2.delete();
		newFile();
	}
	
	static void showVaccines() {
		int i=0;
		if (vaccines.size()>0) {
			System.out.println("Available Vaccines : ");
			for (String str : vaccines) {
				System.out.println("\t(" + (i+1) + ")" + str);
				i++;
			} 
		}
		else {
			System.out.println("Vaccine Unavailable!");
		}
	}
	
	static String selectVaccine() {
		int ch;
		String strr="";
		showVaccines();
		if (vaccines.size()>0) {
			ch = sc.nextInt();
			strr = vaccines.get(ch-1);
		}
		else {
			strr= "aaa"; 
		}
		return strr;
	}
	
	static void newFile() throws IOException {
		f1.createNewFile();
		f2.createNewFile();
		FileWriter fw1= new FileWriter(f1);
		FileWriter fw2= new FileWriter(f2);
		fw2.append("Vaccines" +",");
		if (vaccines.size()>0) {
			for (String st : vaccines) {
				fw2.append(st + ",");
			}
		}
		fw2.flush();
		fw1.write("S.no , Aadhaar , Name , Age , Gender , Phone , Vaccine , Dose\n");
		fw1.flush();
		int i=1;
		for(String[] a : values) {
			fw1.write(i +"," + a[1] + "," + a[2] + "," + a[3] + "," + a[4] + "," + a[5] + ","+ a[6] + "," + a[7] + "\n");
			fw1.flush();
			i++;
		}
		fw2.close();
		fw1.close();
	}
	
	static void addDetails(String[] a) {
		values.add(a);
	}
	
	static void updateVaccines() {
		int ch = 0,ch1=0,ch2=0;
		showVaccines();
		do {
			System.out.println(" (1)Add vaccine (2)Remove vaccine (3)Go back");
			ch = sc.nextInt();
			switch (ch) {
				case 1:
					do {
						System.out.print("Company name : ");
						String vac = sc.next();
						vaccines.add(vac);
						System.out.println("(1)Add again (2)Go back");
						ch1 = sc.nextInt();
					} while (ch1!=2);
					break;
				case 2:
					do {
						if (vaccines.size() > 0) {
							System.out.print("Select Vaccine to remove :");
							int rm = sc.nextInt();
							vaccines.remove(rm-1);
							System.out.println("(1)Remove again (2)Go back");
							ch2 = sc.nextInt();
						} else {
							System.out.println("Vaccines Unavailable!");
							ch2 = 2;
						}
					} while (ch2 != 2);
					break;
				default:
					break;
			}
		} while (ch != 3);	
	}
	
	static void retrieveFile() throws IOException {
		String str;
		int i =0;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		FileReader fr2 = new FileReader(f2);
		BufferedReader br2 = new BufferedReader(fr2);
		try {
			while((str = br.readLine()) != null) {
				if(i>0)
					values.add(str.split(","));
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		fr.close();
		br.close();
		str=br2.readLine();
		List<String> vac = Arrays.asList(str.split(","));
		vaccines.addAll(vac);
		vaccines.remove(0);
		br2.close();
		//adding space for alignment
		for(String[] a: values) {
			for(int j=0;j<12-a[2].length();j++) {
				a[2]+=" ";
			}
			for(int j=0;j<6-a[2].length();j++) {
				a[4]+=" ";
			}
		}
	}
	
	static void updateInfo(int i,String str) {
		values.get(index)[i] = str ;
	}
	
	static void deleteInfo() {
		values.remove(index);
	}

	
	static void showInfo() {
		System.out.println("  _______________________________________________________________________________________________________________");
		System.out.println("  |S.no\t| " + "Aadhaar\t\t|  " + "Name\t\t\t|  " + "Age\t|" + "Gender\t|  "+ "Mobile\t|  "+ "Vaccine\t|  " + "Dose\t|");
		System.out.println("  _______________________________________________________________________________________________________________");
		System.out.println("  |" + (index+1) + "\t| " + values.get(index)[1] +"\t\t| "+ values.get(index)[2] +"\t\t| "
				+values.get(index)[3] +"\t|"+ values.get(index)[4] +"\t| "+ values.get(index)[5]+ "\t| " + values.get(index)[6] +"\t|  " + values.get(index)[7] +"\t|");
		System.out.println("  _______________________________________________________________________________________________________________");
	}
	
	static void displayList(int pre) {
		System.out.println("  _______________________________________________________________________________________________________________");
		System.out.println("  |S.no\t| " + "Aadhaar\t\t|  " + "Name\t\t\t|  " + "Age\t|" + "Gender\t|  "+ "Mobile\t|  "+ "Vaccine\t|  " + "Dose\t|");
    	int i=0,j=0;
    	while(i<values.size()) {
			if (Integer.valueOf(values.get(i)[7])>0 && pre == 1) {
				System.out.println(
						"  _______________________________________________________________________________________________________________");
				System.out.println("  |" + (j+1) + "\t| " + values.get(i)[1] + "\t\t| " + values.get(i)[2] + "\t\t| "
						+ values.get(i)[3] + "\t|" + values.get(i)[4] + "\t| " + values.get(i)[5] + "\t| "
						+ values.get(i)[6] + "\t|  " + values.get(i)[7] + "\t|");
				j++;
			}
			else if(values.get(i)[7].equals("0") && pre == 0){
				System.out.println(
						"  _______________________________________________________________________________________________________________");
				System.out.println("  |" + (j+1) + "\t| " + values.get(i)[1] + "\t\t| " + values.get(i)[2] + "\t\t| "
						+ values.get(i)[3] + "\t|" + values.get(i)[4] + "\t| " + values.get(i)[5] + "\t| "
						+ values.get(i)[6] + "\t|  " + values.get(i)[7] + "\t|");
				j++;
			}
			i++;
		}
		System.out.println("  _______________________________________________________________________________________________________________");
	}
	
	static void showCertificate(String[] s) {
		System.out.println("\n\n\t#########################################################");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t|\t  Certificate for COVID-19 Vaccination\t\t|");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t|   Beneficiary Details :\t\t\t\t|");
    	System.out.println("\t|\t Name\t\t: "+ s[2]  + "\t\t\t|");
    	System.out.println("\t|\t Age\t\t: "+ s[3] + "\t\t\t\t|");
    	System.out.println("\t|\t Gender\t\t: "+ s[4] + "   \t\t\t|");
    	System.out.println("\t|\t ID verified\t: "+ s[1] + "\t\t\t|");
    	System.out.println("\t|\t Status\t\t: "+ (Integer.valueOf(s[7]) == 1 ? "Partially Vaccinated" : "Fully Vaccinated") + "\t\t|");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t|   Vaccination Details :\t\t\t\t|");
    	System.out.println("\t|\t Vaccine Type\t: "+ s[6] + "\t\t\t|");
    	System.out.println("\t|\t 1st dose\t: "+ "Completed" + "\t\t\t|");
    	System.out.println("\t|\t 2nd dose\t: "+ (Integer.valueOf(s[7]) == 1 ? "Yet to Vaccinate" : "Completed    ") + "\t\t\t|");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t|\t\t\t   COWIN APP\t\t\t|");
    	System.out.println("\t|\t\t\t        -Winning over Covid\t|");
    	System.out.println("\t|\t\t\t\t\t\t\t|");
    	System.out.println("\t#########################################################");
	}
	
	static boolean checkDose(String str) {
		if(str.equals(values.get(index)[7])) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static boolean checkAadhaar(String str) {
		boolean a=false;
		if(values.size()>0) {
			for(int i=0;i<values.size();i++) {
				if (str.equals(values.get(i)[1])) {
					index = i;
					a=true;
					break;
				}
				else {
					a=false;
				}
			}
		}
		else {
			a = false;
		}
		return a;
	}
}
