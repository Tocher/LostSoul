import java.awt.Graphics;


public class World {
	private int x;
	private int y;
	private int bg;
	private int obj;
	
	public World(int x,int y,int bg,int obj)
	{
		this.x = x;
		this.y = y;
		this.bg = bg;
		this.obj = obj;
	}
	
	public void draw_bg(int x,int y,Graphics g,World_Entities WE)
	{
		if((this.x>=x)&&(this.x<=x+40))
			if((this.y>=y)&&(this.y<=y+20))
			{
				WE.grass.chFrame(this.bg-1);
				g.drawImage(WE.grass.sprite, this.x*40, this.y*40, null);				
			}
	}
	
	public void draw_obj(int x,int y,Graphics g, Hero hero, long delta,World_Entities WE)
	{
		if(this.obj!=0)
		{
			if(this.obj<10)
			{
				WE.objects.chFrame(obj-1);
				g.drawImage(WE.objects.sprite, this.x*40-5, this.y*40-32, null);
			}
			else if(this.obj<20)
			{
				WE.objects2.chFrame(obj-10);
				g.drawImage(WE.objects2.sprite, this.x*40, this.y*40, null);
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
}
