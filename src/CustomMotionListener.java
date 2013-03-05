import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CustomMotionListener implements MouseMotionListener {
		public static int X;
		public static int Y;
		
		public void mouseMoved(MouseEvent e) {
			X = e.getX();
			Y = e.getY();								
		}

		public void mouseDragged(MouseEvent e) {
			
		}
	}