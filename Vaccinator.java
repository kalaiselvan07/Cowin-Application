import java.io.IOException;
import java.util.Scanner;

public class Vaccinator extends Government{
	static Scanner sc = new Scanner(System.in);
	private static String aadhaar,name,age,gender,mobile,vaccine;
	
	Vaccinator() throws IOException {
		super();
	}
	
	public static void showUpload(){
		cls();
		System.out.print("\n\n\t\t");
		for(int i=20;i>0;i--) {
			System.out.print("**");
			try {
				Thread.sleep(200); // 1000 ms = 1s
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		cls();
		System.out.println("\n\tRegistered Succesfully!");
		try {
				Thread.sleep(2000); // 1000 ms = 1s
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}


	static void callVaccinator() throws IOException {
		int ch=0;
		do {
			System.out.println("\n\t\tVACCINATOR PORTAL\n");
			System.out.println(" (1)New Registration\t(2)Vaccinated people\t(3)Edit details\t(4)Log out");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				cls();
				System.out.print("Aadhaar Number : ");
				aadhaar = sc.next();
				sc.nextLine();
				if (checkAadhaar(aadhaar)) {
					if (checkDose("0")) {
						showInfo();
						System.out.println(" (1)Update vaccination status for 1st Dose\t(2)Cancel Registration");
						switch (sc.nextInt()) {
						case 1:
							updateInfo(7, "1");
							//getCertified();
							//deleteOldCertificate();
							//generateCertificate();
							break;
						case 2:
							deleteInfo();
							break;
						}
					} 
					else if(checkDose("1")) {
						showInfo();
						System.out.println(" (1)Update vaccination status for 2nd Dose\t(2)Go back");
						switch (sc.nextInt()) {
						case 1:
							updateInfo(7, "2");
							//getCertified();
							//deleteOldCertificate();
							//generateCertificate();
							break;
						case 2:
							break;
						}
					} 
					else {
						System.out.println("Candidate is Fully Vaccinated");
						showInfo();
					}
				} 
				else 
				{
					vaccine = selectVaccine();
					if (!vaccine.equals("aaa")) 
					{
						getCandidateInfo();
						String[] ar = { " ", aadhaar, name, age, gender, mobile, vaccine, "1" };
						addDetails(ar);
						showUpload();
						//getCertified();
					}
				}
				cls();
				break;
			case 2:
				cls();
				displayList(1);
				System.out.println("\tPress any number to go back");
				sc.next();
				cls();
				break;
			case 3:
				cls();
				System.out.print("Enter Aadhaar number :");
				String str = sc.next();
				sc.nextLine();
				if(checkAadhaar(str)) {
					showInfo();
					System.out.println(" What do you want to change ?\n (1)Name (2)Age (3)Gender (4)Mobile Number");
					//int ch3 = sc.nextInt();
					switch(sc.nextInt()) {
					case 1: 
						System.out.println(" Name(new) : ");
						updateInfo(2,sc.next());
						break;
					case 2: 
						System.out.println(" Age(new) : ");
						updateInfo(3,sc.next());
						break;
					case 3: 
						System.out.println(" Gender(new) : ");
						updateInfo(4,sc.next());
						break;
					case 4: 
						System.out.println(" Mobile(new) : ");
						updateInfo(5,sc.next());
						break;
					}
				}
				else {
					System.out.println("Candidate is not registered for vaccination!");
				}
				try {
					Thread.sleep(2000); // 1000 ms = 1s
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				cls();
				//getCertified();
				break;
			case 4:
				ch=4;
				break;
			}
		} while (ch!=4);
		cls();
		updateList();
	}
	
	static void getCandidateInfo() {
		System.out.print("Name\t: ");
		name = sc.nextLine();
		for(int i =0;i<12-name.length();i++) {
			name+=" ";
		}
		System.out.print("Age\t: ");
		age = sc.nextLine();
		System.out.print("Gender\t: ");
		gender = sc.nextLine();
		System.out.print("Mobile\t: ");
		mobile = sc.nextLine();
	}
}