package manual;

public class Pair<A, B> {
	A a; B b;
	Pair(A a, B b) { this.a = a; this.b = b; }
	A a() { return this.a; }
	B b() { return this.b; }
}
