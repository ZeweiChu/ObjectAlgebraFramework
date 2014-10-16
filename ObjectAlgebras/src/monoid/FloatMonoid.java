package monoid;

import library.Monoid;

public class FloatMonoid implements Monoid<Float>{
	public Float join(Float x, Float y){
		return x + y;
	}
    public Float empty(){
		return 0f;
	}
}
