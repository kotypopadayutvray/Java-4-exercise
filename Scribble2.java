import java.applet.*;
import java.awt.*;
import java.awt.event.*;
//сам апплет реализует интерфейсы,т.е. является слушателем
public class Scribble2 extends Applet implements MouseListener, MouseMotionListener {
		private int last_x, last_y;
		public void init() {
			// Сообщает данному апплету о том, какие объекты
			// классов MouseListener и MouseMotionListener он должен оповещать
			// о событиях, связанных с мышью и ее перемещением.
			// Поскольку интерфейс реализуется в самом апплете,
			// при этом будут вызываться методы апплета.
			this.addMouseListener(this) ;
			this.addMouseMotionListener(this);
		}
		// Метод интерфейса MouseListener. Вызывается при нажатии
		// пользователем кнопки мыши.
		public void mousePressed(MouseEvent e) {
			last_x = e.getX();
			last_y = e.getY();
		}
		// Метод интерфейса MouseMotionListener. Вызывается при
		// перемещении мыши с нажатой кнопкой.
		public void mouseDragged(MouseEvent e) {
			Graphics g = this.getGraphics();
			int x = e.getX(), y = e.getY();
			g.drawLine(last_x, last_y, x, y);
			last_x = x; last_y = y;
		}
		// Другие, не используемые методы интерфейса MouseListener.
		public void mouseReleased(MouseEvent e) {;}
		public void mouseClicked(MouseEvent e) {;}
		public void mouseEntered(MouseEvent e) {;}
		public void mouseExited(MouseEvent e) {;}
		// Другой метод интерфейса MouseMotionListener.
		public void mouseMoved(MouseEvent e) {;}
}