package generics;

public class Data {
	
	public String getData(int a, int b)
	{
		String[][] s = new String[2][16];
		s[0][1]="62360"; //local host
		s[0][2]="https://assist.org/"; //URL
		s[0][3]=""; 
		s[0][4]=""; 	
		s[0][5]=""; 
		s[0][6]=""; 
		s[0][7]="";
		
		return s[a][b];
	}
}
