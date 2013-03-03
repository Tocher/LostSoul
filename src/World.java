import java.awt.Graphics;


public class World {
	private int x;
	private int y;
	private int bg;
	private int obj;
	private Animator grass;
	private Animator objects;
	private Animator objects2;
	
	public World(int x,int y,int bg,int obj)
	{
		Init init_png = new Init();			
		grass = new Animator(init_png.getArrayList("grass_tile.png", 40, 40, 3, 6));	
		objects = new Animator(init_png.getArrayList("obj_tile.png", 50, 70, 1, 1));
		objects2 = new Animator(init_png.getArrayList("obj2_tile.png", 40, 40, 1, 2));
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
	
	public void draw_obj(int x,int y,Graphics g)
	{
		if(this.obj!=0)
		{
			if(this.obj==1)
			{
				objects.chFrame(obj-1);
				g.drawImage(objects.sprite, this.x*40-5, this.y*40-32, null);
			}
			else
			{
				objects2.chFrame(obj-2);
				g.drawImage(objects2.sprite, this.x*40, this.y*40, null);
			}
		}
	}
}
