import java.awt.Graphics;


public class Hero extends Unit 
{
	private Animator hero;
	private int frame = 6;
	private boolean ch = false;
	
	public Hero(String name) 
	{
		Init init_png = new Init();
		hero = new Animator(init_png.getArrayList(name, 32, 32, 4, 3));
	}
	
	public void HeroDraw(Graphics g) 
	{
		g.drawImage(hero.sprite, x, y, 32, 32, null);
	}
	
	public void HeroMove(int x_new, int ms)
	{
		if (frame == 6)
			frame = 7;
		else if (frame == 7)
		{
			if (ch)
				frame = 8;
			else
				frame = 6;
		}
		else if (frame == 8)
			frame = 7;
		hero.chFrame(frame);
		x += ms;
	}
}
