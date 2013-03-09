import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyMouseAdapter extends MouseAdapter
{
	Hero hero;

	public MyMouseAdapter(Hero hero)
	{
		this.hero = hero;
	}

	public void mouseClicked(MouseEvent e)
	{
		
		hero.MovementToCoords(e.getX() / 40, e.getY() / 40);
	}
	
}
