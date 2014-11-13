package monoid;

import library.Monoid;

public class StringMonoid implements Monoid<String>{
	public String join(String x, String y){
		return x + " " + y;
	}
    public String empty(){
		return "";
	}
}
