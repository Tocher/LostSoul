import java.awt.Graphics;

import javax.swing.JOptionPane;


public class Movement
{
	Unit unit;
	
	public Movement(Unit unit)
	{
		this.unit = unit;
	}
	
	public void HeroMove(int x, int y, Graphics g)
	{
		Hero hero = (Hero) unit;
		
		JOptionPane.showMessageDialog(null, x + " " + hero.x);
		
		for (int i = 0; i < 10; i++)
		{
			hero.x++;
			
			hero.HeroDraw(g, 0);
		}
	}
}
