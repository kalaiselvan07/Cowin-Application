import java.util.Scanner;

public class MainPage extends Thread{

	public void show() {
		try {
				Thread.sleep(1500); 
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		cls();
		for(int i=10;i>0;i--) {
			System.out.print("\n\n\t\tTry again after "+ i + " seconds");
			try {
				Thread.sleep(1000); // 1000 ms = 1s
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			cls();
		}
		System.out.println("");
	}

	public static void cls() {
    	try {
    		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    	}catch(Exception E) {
    		System.out.println(E);
    	}
    }
	
	public static void main(String[] args) throws Exception {
		
		int z=0;
		new Vaccinator();
		MainPage obj = new MainPage();

		cls();
		while (z==0) {
			int j = 3,k=3;
			Scanner sc = new Scanner(System.in);
			System.out.println("\n\t\t COWIN APP \n");
			System.out.println(" Sign in for : (1) Admin portal  (2) Vaccinator portal (3) Exit\n");
			int ch0 = sc.nextInt();
			
			switch (ch0) {
				
				case 1:
					
					while (j > 0) {
						System.out.print("\n Admin Login Password : ");
						int pwd = sc.nextInt();
						if (pwd == 12345) {
							cls();
							Government.callAdmin();
							break;
						} 
						else if(j!=1){
							cls();
							System.out.println("\n\n\t\tEnter valid password!"  + "(Attempts left : "+ (j-1) +")");
							j--;
						}
						else {
							cls();
							System.out.println("\n\n\t\t Too many Wrong attempts !\n");
							obj.show();
							j--;
						}
					}
					break;
				
				case 2:
					while (k>0) {
						System.out.print("\n Vaccinator Password " + "(" + k + " Attempts left) : ");
						int pwd = sc.nextInt();
						//if(checkPassword(sc.next()))
						if (pwd == 54321) {
							Vaccinator.callVaccinator();
							break;
						} 
						else if(k!=1){
							cls();
							System.out.println("\n\n\t\tEnter valid password!"  + "(Attempts left : "+ (j-1) +")");
							k--;
						}
						else {
							cls();
							System.out.println("\n\n\t\t Too many Wrong attempts !\n");
							obj.show();
							k--;
						}
					}

					break;
				
				case 3:
					z=1;
					cls();
					break;
				default:
					System.out.println("Invalid input");
					break;		
		
			}
		}
		System.out.println("\n\n\n\t\t\t\t\t Thank you !\n\n\n\n");
	}
}
