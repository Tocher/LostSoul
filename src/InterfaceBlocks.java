import java.awt.Color;
import java.awt.Graphics;


public class InterfaceBlocks {
	
	public void drawHpBar(Graphics g,int hp) {
		g.setColor(Color.black);
		g.drawRect(50, 50, 200, 20);
		g.setColor(Color.red);
		g.fillRect(51, 51, 199*hp/100, 19);
	}
	
}
