
public class OONaturals {
	interface Nat {
		Nat pred();
	}
	
	class Zero implements Nat {
		public Nat pred() {
			return new Zero();
		}
	}
	
	class Succ implements Nat {
		Nat n;
		
		public Succ(Nat n) {
			this.n = n;
		}
		
		public Nat pred() {
			return n;
		}
	}
}
