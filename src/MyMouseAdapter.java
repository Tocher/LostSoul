import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyMouseAdapter extends MouseAdapter
{
	int x, y;
	Hero hero;
	Movement move;
	Graphics g;
	
	public MyMouseAdapter(Hero hero, Graphics g)
	{
		this.hero = hero;
		move = new Movement(hero);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		x = e.getX() / 40;
		y = e.getY() / 40;
		move.HeroMove(x, y, g);
		//JOptionPane.showMessageDialog(null, x + " " + y);
		//hero.x = x;
		//hero.y = y;
	}
	
}
