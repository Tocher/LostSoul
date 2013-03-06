
import java.awt.Graphics;

public class Hero extends Unit
{
	int x;
	int y;
	int hp;
	int ms=1;
	private Animator hero;
	private int frame = 6;
	private int dest_x = 0;
	private int dest_y = 0;
	long delta2 = 0;
	long delta3 = 0;
	
	
	public Hero(String name) 
	{
		Init init_png = new Init();
		hero = new Animator(init_png.getArrayList(name, 32, 32, 4, 4));
	}
	
	public boolean onCoords(int x,int y)
	{
		if(x == this.x/40&&y == this.y/40)
			return true;
		else		
			return false;
	}
	
	public void HeroDraw(Graphics g,long delta) 
	{
		if(delta2+10<delta)
		{			
			delta2=delta;
			if(dest_x!=0&&dest_y!=0)
			{
				if(dest_x!=x)
					if(dest_x>x)
					{
						x+=ms;
						if(delta3+50<delta)
						{
							delta3 = delta;
							GetFrame(2);
						}
					}
					else
					{
						x-=ms;
						if(delta3+50<delta)
						{
							delta3 = delta;
							GetFrame(4);
						}
					}				
				if(dest_y!=y)
					if(dest_y>y)
					{
						y+=ms;
						if(delta3+50<delta)
						{
							delta3 = delta;
							GetFrame(3);
						}
					}
					else
					{
						y-=ms;
						if(delta3+50<delta)
						{
							delta3 = delta;
							GetFrame(1);
						}
					}
				if(dest_x==x&&dest_y==y)
				{
					dest_x=0;
					dest_y=0;
				}					
			}
		}
		g.drawImage(hero.sprite, x * 40, y * 40, 32, 32, null);
		hero.chFrame(frame);
		
	}
	
	public void GetFrame(int frame_set)
	{
		frame++;
		switch(frame_set)
		{
		case 3:
			if(frame>2)
				frame=0;				
			break;
		case 2:
			if(frame>11||frame<8)
				frame=8;
			break;
		case 1:
			if(frame>15||frame<12)
				frame=12;
			break;
		case 4:
			if(frame>7||frame<4)
				frame=4;
			break;
		}
	}
	
	public void Move(int dest)
	{
		if(dest_x==0&&dest_y==0)
		{
			switch(dest)
			{
			case 1:
				dest_y = --y;
				dest_x = x;
				break;
			case 2:
				dest_y = y;
				dest_x = ++x;
				break;
			case 3:
				dest_y = ++y;
				dest_x = x;
				break;
			case 4:
				dest_y = y;
				dest_x = --x;
				break;
					
			}
		}		
	}
}
