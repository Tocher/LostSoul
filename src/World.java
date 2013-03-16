import java.awt.Graphics;


public class World {
	private int x;
	private int y;
	private int bg;
	private int obj;
	public World up;
	public World down;
	public World right;
	public World left;
	
	public World(int x,int y,int bg,int obj)
	{
		this.x = x;
		this.y = y;
		this.bg = bg;
		this.obj = obj;
	}
	
	public void SetFriends(World up, World right, World down, World left)
	{
		this.up = up;
	}
	
	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
	public void draw_bg(int x,int y,Graphics g,World_Entities WE)
	{
		WE.grass.chFrame(this.bg-1);
		g.drawImage(WE.grass.sprite, (int)(this.x*40*Main.size), (int)(this.y*40*Main.size), (int)(40*Main.size), (int)(40*Main.size), null);				
			
	}
	
	public void draw_obj(int x,int y,Graphics g, Hero hero, long delta,World_Entities WE)
	{
		if(this.obj!=0)
		{
			if(this.obj<10)
			{
				WE.objects.chFrame(obj-1);
				g.drawImage(WE.objects.sprite, (int)(this.x*40*Main.size-5*Main.size), (int)(this.y*40*Main.size-32*Main.size), (int)(50*Main.size), (int)(70*Main.size), null);
			}
			else if(this.obj<20)
			{
				WE.objects2.chFrame(obj-10);
				g.drawImage(WE.objects2.sprite, (int)(this.x*40*Main.size), (int)(this.y*40*Main.size), (int)(40*Main.size), (int)(40*Main.size), null);
			}
			else
			{				
				WE.house.chFrame(obj-20);
				g.drawImage(WE.house.sprite, this.x*40, this.y*40, null);
			}
		}
		if(hero.onCoords(this.x,this.y))
		{			
			hero.HeroDraw(g,delta);
		}
	}
	
	public boolean IsObj()
	{
		if (obj != 0)
			return true;
		else
			return false;
	}
}
