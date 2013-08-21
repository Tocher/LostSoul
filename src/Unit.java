import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class Unit
{
	protected int hp;
	protected int x;
	protected int y;
	protected int ms=1;
	protected Animator unit;
	protected int frame = 6;
	protected int current_dest_x = 0;
	protected int current_dest_y = 0;
	protected int end_dest_x = 0;
	protected int end_dest_y = 0;
	protected long delta2 = 0;
	protected long delta3 = 0;	
	
	private long time_for_coords = 10;
	private long time_for_frame = 100;
	private int one_cell = 40;
	public ArrayList<World> w = new ArrayList<World>();
	
	public Unit(ArrayList<World> w)
	{
		this.w = w;
	}
	
	public void MovementToCoords(int dest_x,int dest_y)
	{		
		end_dest_x=dest_x;
		end_dest_y=dest_y;
		
		if(current_dest_x==0&&current_dest_y==0)
		{
			int way = WhereIGo(dest_x,dest_y);
			if(way!=0)
			{
				Move(way);
					if(delta3+time_for_frame<System.currentTimeMillis())
					{
						delta3 = System.currentTimeMillis();
						GetFrame(way);
					}
			}
		}
		
		if(end_dest_x*one_cell==x&&end_dest_y*one_cell==y)
		{
			end_dest_x=0;
			end_dest_y=0;
		}
		
	}
	
	public void CheckForMovement()
	{
		if(end_dest_x!=0&&end_dest_y!=0)
		{
			MovementToCoords(end_dest_x,end_dest_y);
		}
	}
	
	public int WhereIGo(int dest_x,int dest_y)
	{
		World w1 = w.get((y/40)*63 + x/40);			//поясняю: 63 - это кол-во клеток в одной строке
		World w2 = w.get((y/40)*63 + x/40 + 1);
		World w3 = w.get((y/40)*63 + x/40 + 63);
		World w4 = w.get((y/40)*63 + x/40);
		
		if(x>dest_x*one_cell)
		{
			if (w4.IsObj())
				return 3;
			else
				return 4;
		}
		if(x<dest_x*one_cell)
		{
			if (w2.IsObj())
				return 3;
			else
				return 2;
		}
		if(y>dest_y*one_cell)
		{
			if (w1.IsObj())
				return 4;
			else
				return 1;
		}
		if(y<dest_y*one_cell)
		{
			if(w3.IsObj())
				return 4;
			else
				return 3;
		}
		return 0;
	}

	public void GetFrame(int frame_set)
	{
		this.frame++;
		switch(frame_set)
		{
		case 1:
			if(this.frame>15||this.frame<12)
				this.frame=12;
			break;
		case 2:
			if(this.frame>11||this.frame<8)
				this.frame=8;
			break;
		case 3:
			if(this.frame>2)
				this.frame=0;				
			break;
		case 4:
			if(this.frame>7||this.frame<4)
				this.frame=4;
			break;
		}
	}
	
	public void HeroDraw(Graphics g,long delta) 
	{
		if(delta2+time_for_coords<delta)
		{			
			delta2=delta;
			if(current_dest_x!=0&&current_dest_y!=0)
			{
				if(current_dest_x!=x)
					if(current_dest_x>x)
					{
						x+=ms;
					}
					else
					{
						x-=ms;
					}				
				if(current_dest_y!=y)
					if(current_dest_y>y)
					{
						y+=ms;
					}
					else
					{
						y-=ms;
					}
				if(current_dest_x==x&&current_dest_y==y)
				{
					current_dest_x=0;
					current_dest_y=0;
				}	
				CheckForMovement();
			}
		}
		g.drawImage(unit.sprite, x, y, (int)(32*Main.size), (int)(32*Main.size), null);
		unit.chFrame(this.frame);
		
		//Debug information
		/*
		g.setColor(Color.white);
		World world = w.get((y/40)*63 + x/40);		
		
		g.drawString("X = " + String.valueOf(world.GetX()), 700, 20);
		g.drawString("Y = " + String.valueOf(world.GetY()), 900, 20);
		g.drawString("IsObj = " + String.valueOf(world.IsObj()), 1000, 20);
		
		g.drawString(String.valueOf(this.frame), 170, 20);
		g.drawString("X = " + String.valueOf(this.x), 200, 20);
		g.drawString("Y = " + String.valueOf(this.y), 250, 20);
		g.drawString("X_new = " + String.valueOf(this.end_dest_x), 400, 20);
		g.drawString("Y_new = " + String.valueOf(this.end_dest_y), 500, 20);
		*/
	}	
	
	public void Move(int dest)
	{
		if(current_dest_x==0&&current_dest_y==0)
		{
			switch(dest)
			{
			case 1:
				current_dest_y = --y;
				current_dest_x = x;
				break;
			case 2:
				current_dest_y = y;
				current_dest_x = ++x;
				break;
			case 3:
				current_dest_y = ++y;
				current_dest_x = x;
				break;
			case 4:
				current_dest_y = y;
				current_dest_x = --x;
				break;
					
			}
		}		
	}
}
