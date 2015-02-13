package NewExample;

public class Ref<A> {
	private A value;
	Ref(A x) { this.value = x; }
	void setValue(A x) { this.value = x; }
	A getValue() { return this.value; }
}
