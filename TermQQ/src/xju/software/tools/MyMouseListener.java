package xju.software.tools;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MyMouseListener implements MouseMotionListener ,MouseListener{
	private Point point = null;
	private Window frame;
	
	public MyMouseListener(Window frame)
	{
		this.frame = frame;
	}
	

	public void mouseDragged(MouseEvent e) {
		Point newPoint = e.getPoint();
		this.frame.setLocation(this.frame.getX()+(newPoint.x - point.x),
				this.frame.getY()+(newPoint.y - point.y));	
	}

	public void mouseMoved(MouseEvent e) {
		
		
	}

	public void mouseClicked(MouseEvent e) {
		
		
	}

	public void mouseEntered(MouseEvent e) {
		
		
	}

	public void mouseExited(MouseEvent e) {
		
		
	}

	public void mousePressed(MouseEvent e) {
		this.point = e.getPoint();
	}

	public void mouseReleased(MouseEvent e) {
		
		
	}

}
