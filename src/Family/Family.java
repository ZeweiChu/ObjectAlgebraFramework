package Family;

interface NodeAlg<N,E> {
	N node(E e);
}

interface Touches<N> {
	boolean touches(N n1, N n2); 
}

class GEdge<N> {
	public N n1, n2;
}

//interface Node extends Touches<Edge> {}

/*
class TouchesBase<N,E> implements NodeAlg<N,E> {
	Touches<N> touches;
	GEdge<N> gedge;
	
	
	public N node(E e) {
		return touches.
	}
}*/

/*
class TouchesBase<E extends Edge> implements NodeAlg<Touches<E>,E> {
	public Touches<E> node(E e) {
		return e.;
	}
} */
