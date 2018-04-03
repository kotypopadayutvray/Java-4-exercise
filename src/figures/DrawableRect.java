package figures;

import java.awt.*;
import figures.rectangle.Rectangle;

public class DrawableRect extends Rectangle {
	private Color outColor;

	protected int getRandom(int leftBound, int rightBound) {
		return (leftBound + (int) (Math.random() * rightBound));
	}

	public DrawableRect() {
		super();
		outColor = new Color(getRandom(0, 255), getRandom(0, 255), getRandom(0, 255));
	}

	public DrawableRect(int width, int height) {
		super(width, height);
		outColor = new Color(getRandom(0, 255), getRandom(0, 255), getRandom(0, 255));
	}

	public DrawableRect(int x1_, int y1_, int x2_, int y2_) {
		super(x1_, y1_, x2_, y2_);
		outColor = new Color(getRandom(0, 255), getRandom(0, 255), getRandom(0, 255));
	}

	public void draw(Graphics g) {
		g.setColor(outColor);
		int coords[] = super.rect_print();
		g.drawRect(coords[0], coords[1], Math.abs(coords[2] - coords[0]), Math.abs(coords[3] - coords[1]));
	}

	public void draw(Graphics g, Color color) {
		g.setColor(color);
		int coords[] = super.rect_print();
		g.drawRect(coords[0], coords[1], Math.abs(coords[2] - coords[0]), Math.abs(coords[3] - coords[1]));
	}
}