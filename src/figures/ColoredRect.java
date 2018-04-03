package figures;

import java.awt.*;
import figures.rectangle.Rectangle;

public class ColoredRect extends DrawableRect {
	private Color inColor;

	public ColoredRect() {
		super();
		inColor = new Color(0, 255, 0);
	}

	public ColoredRect(int width, int height) {
		super(width, height);
		inColor = new Color(getRandom(0, 255), getRandom(0, 255), getRandom(0, 255));
	}

	public ColoredRect(int x1_, int y1_, int x2_, int y2_) {
		super(x1_, y1_, x2_, y2_);
		inColor = new Color(getRandom(0, 255), getRandom(0, 255), getRandom(0, 255));
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(inColor);
		int coords[] = super.rect_print();
		g.fillRect(coords[0], coords[1], Math.abs(coords[2] - coords[0]), Math.abs(coords[3] - coords[1]));
	}
}