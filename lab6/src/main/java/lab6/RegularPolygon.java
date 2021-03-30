package lab6;

import java.awt.Polygon;

public class RegularPolygon extends Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4012396669922659888L;

	public RegularPolygon(int x0, int y0, int radius, int sides) {
		double alpha = 2 * Math.PI / sides;
		for (int i = 0; i < sides; i++) {
			double x = x0 + radius * Math.cos(alpha * i);
			double y = y0 + radius * Math.sin(alpha * i);
			this.addPoint((int) x, (int) y);
		}
	}
}
