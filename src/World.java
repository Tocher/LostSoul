import java.awt.Graphics;


public class World {
	private int x;
	private int y;
	private int bg;
	private int obj;
	private Animator grass;
	private Animator objects;
	private Animator objects2;
	private Animator house;
	
	public World(int x,int y,int bg,int obj)
	{
		Init init_png = new Init();			
		grass = new Animator(init_png.getArrayList("grass_tile.png", 40, 40, 3, 6));	
		objects = new Animator(init_png.getArrayList("obj_tile.png", 50, 70, 1, 3));
		objects2 = new Animator(init_png.getArrayList("obj2_tile.png", 40, 40, 1, 3));		
		house = new Animator(init_png.getArrayList("house_tile.png", 150, 70, 1, 1));		
		//hero = new Animator(init_png.getArrayList("hero.png", 32, 32, 4, 4));
		this.x = x;
		this.y = y;
		this.bg = bg;
		this.obj = obj;
	}
	
	public void draw_bg(int x,int y,Graphics g)
	{
		if((this.x>=x)&&(this.x<=x+40))
			if((this.y>=y)&&(this.y<=y+20))
			{
				grass.chFrame(this.bg-1);
				g.drawImage(grass.sprite, this.x*40, this.y*40, null);				
			}
	}
	
	public void draw_obj(int x,int y,Graphics g, Hero hero, long delta)
	{
		if(this.obj!=0)
		{
			if(this.obj<10)
			{
				objects.chFrame(obj-1);
				g.drawImage(objects.sprite, this.x*40-5, this.y*40-32, null);
			}
			else if(this.obj<20)
			{
				objects2.chFrame(obj-10);
				g.drawImage(objects2.sprite, this.x*40, this.y*40, null);
			}
			else
			{				
				house.chFrame(obj-20);
				g.drawImage(house.sprite, this.x*40, this.y*40, null);
			}
		}
		if(hero.onCoords(this.x,this.y))
			hero.HeroDraw(g,delta);
	}
}
