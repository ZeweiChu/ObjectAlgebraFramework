package manual;

public class Line<T extends Object> extends Object {
	Point<T> p1;
	Point<T> p2;
	Line(Point<T> p1, Point<T> p2) { this.p1 = p1; this.p2 = p2; }
	<W> W getP1X(W w0, W w1) { return new Point<W>(w0, w1).getX(); }
	
}

class Point<T extends Object> extends Object {
	T x;
	T y;
	Point(T x, T y) { this.x = x; this.y = y; }
	T getX() { return x; }
	T getY() { return y; }
}
