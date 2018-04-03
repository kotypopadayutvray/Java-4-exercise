package figures.rectangle;

import java.awt.*;

public class Rectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Rectangle() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
	}

	public Rectangle(int width, int height) {
		x1 = 0;
		y1 = 0;
		x2 = width;
		y2 = height;
	}

	public Rectangle(int x1_, int y1_, int x2_, int y2_) {
		x1 = x1_;
		y1 = y1_;
		x2 = x2_;
		y2 = y2_;
	}

	public int[] rect_print() {
		int[] result = new int[4];
		result[0] = x1;
		result[1] = y1;
		result[2] = x2;
		result[3] = y2;
		return result;
	}

	public void move(int dx, int dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	public Rectangle union(Rectangle r) {
		int x1_ = x1 > r.x1 ? r.x1 : x1;
		int y1_ = y1 > r.y1 ? r.y1 : y1;
		int x2_ = x2 > r.x2 ? r.x2 : x2;
		int y2_ = y2 > r.y2 ? r.y2 : y2;
		return new Rectangle(x1_, y1_, x2_, y2_);
	}

	public Rectangle union(int[] coordinates) {
		int x1_ = x1 > coordinates[0] ? coordinates[0] : x1;
		int y1_ = y1 > coordinates[1] ? coordinates[1] : y1;
		int x2_ = x2 > coordinates[2] ? coordinates[2] : x2;
		int y2_ = y2 > coordinates[3] ? coordinates[3] : y2;
		return new Rectangle(x1_, y1_, x2_, y2_);
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));
	}
}