import java.util.ArrayList;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import figures.rectangle.Rectangle;
import figures.DrawableRect;
import figures.ColoredRect;

public class Scribble1 extends Applet {
	int last_x,
		last_y,
		rectangleIndex;

	ArrayList<figures.rectangle.Rectangle> rectangles;

	private int getRandom(int leftBound, int rightBound) {
		return (leftBound + (int) (Math.random() * rightBound));
	}

	public void init() {
		rectangles = new ArrayList<figures.rectangle.Rectangle>();

		// Слушает события нажатия клавиш мыши
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					last_x = e.getX();
					last_y = e.getY();
					rectangleIndex = searchRectangle(last_x, last_y);
				}
			}
		});

		// Слушает собития перемещения мыши
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					if (rectangleIndex != -1) {
						int x = e.getX();
						int y = e.getY();
						figures.rectangle.Rectangle tempRect = rectangles.get(rectangleIndex);
						tempRect.move(x - last_x, y - last_y);
						rectangles.set(rectangleIndex, tempRect);
						last_x = x;
						last_y = y;
						redrawRectangles();
					}
				}
			}
		});

		// Создает кнопку Clear.
		Button clearButton = new Button("Clear");
		// Определяет, создает и регистрирует объект слушателя
		// для обработки события, связанного с нажатием кнопки.
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillBackground();
				rectangles.clear();
			}
		});
		// Добавляет кнопку в апплет.
		this.add(clearButton);

		Button addRectangleButton = new Button("Add Reactangle");
		addRectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRectangle(1);
			}
		});
		this.add(addRectangleButton);

		Button addDrawableRectangleButton = new Button("Add Drawable");
		addDrawableRectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRectangle(2);
			}
		});
		this.add(addDrawableRectangleButton);

		Button coloredDrawableRectangleButton = new Button("Add Colored");
		coloredDrawableRectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRectangle(3);
			}
		});
		this.add(coloredDrawableRectangleButton);
	}

	void addRectangle(int caseNumber) {
		Graphics g = getGraphics();
		java.awt.Rectangle bound = getBounds();
		int x1 = getRandom(0, bound.width);
		int y1 = getRandom(0, bound.height);
		int x2 = x1 + getRandom(25, 50);
		int y2 = y1 + getRandom(25, 50);
		figures.rectangle.Rectangle rectangle;
		switch (caseNumber) {
			case 1:
				rectangle = new figures.rectangle.Rectangle(x1, y1, x2, y2);
				break;
			case 2:
				rectangle = new DrawableRect(x1, y1, x2, y2);
				break;
			case 3:
				rectangle = new ColoredRect(x1, y1, x2, y2);
				break;
			default:
				rectangle = new figures.rectangle.Rectangle(x1, y1, x2, y2);
				break;
		}
		rectangle.draw(g);
		rectangles.add(rectangle);
	}

	int searchRectangle(int x, int y) {
		for (int i = 0; i < rectangles.size(); i++) {
			figures.rectangle.Rectangle tempRect = rectangles.get(i);
			int[] coords = tempRect.rect_print();
			if ((x > coords[0]) && (x < coords[2]) && (y > coords[1]) && (y < coords[3])) {
				return i;
			}
		}
		return -1;
	}

	void fillBackground() {
		Graphics g = getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getSize().width, getSize().height);
	}

	void redrawRectangles() {
		fillBackground();
		Graphics g = getGraphics();
		for (int i = 0; i < rectangles.size(); i++) {
			figures.rectangle.Rectangle tempRect = rectangles.get(i);
			tempRect.draw(g);
		}
	}
}