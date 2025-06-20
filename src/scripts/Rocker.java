package scripts;

import generics.BaseClass;

public class Rocker extends BaseClass{
public static void main(String[] args) {
	
	String s = "dkfsdl:sdfkjs.df,  sdk : sd";
	s = s.toLowerCase().replaceAll(" ","-").replaceAll(":", "").replaceAll("\\.", "").replaceAll(",","");
	System.out.println("::: "+s);
}
}
