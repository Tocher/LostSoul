import java.awt.Component;
import java.awt.Graphics;

public class Hero extends Component
{
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	int hp;
	int ms=1;
	private Animator hero;
	private int frame = 6;
	private boolean ch = false;	
	private int dest_x = 0;
	private int dest_y = 0;
	
	
	public Hero(String name) 
	{
		Init init_png = new Init();
		hero = new Animator(init_png.getArrayList(name, 32, 32, 4, 3));
	}
	
	public void HeroDraw(Graphics g) 
	{
		if(dest_x!=0&&dest_y!=0)
		{
			if(dest_x!=x)
				if(dest_x>x)
				{
					x+=ms;
					GetFrame(2);
				}
				else
				{
					x-=ms;
					GetFrame(4);
				}				
			if(dest_y!=y)
				if(dest_y>y)
				{
					y+=ms;
					GetFrame(3);
				}
				else
				{
					y-=ms;
					GetFrame(1);
				}
			if(dest_x==x&&dest_y==y)
			{
				dest_x=0;
				dest_y=0;
			}
					
		}
		g.drawImage(hero.sprite, x, y, 32, 32, null);
		hero.chFrame(frame);
		
	}
	
	public void GetFrame(int frame_set)
	{
		frame++;
		switch(frame_set)
		{
		case 3:
			if(frame>1)
				frame=0;				
			break;
		case 2:
			if(frame>8||frame<6)
				frame=6;
			break;
		case 1:
			if(frame>11||frame<9)
				frame=9;
			break;
		case 4:
			if(frame>5||frame<3)
				frame=3;
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
				dest_y = y-40;
				dest_x = x;
				break;
			case 2:
				dest_y = y;
				dest_x = x+40;
				break;
			case 3:
				dest_y = y+40;
				dest_x = x;
				break;
			case 4:
				dest_y = y;
				dest_x = x-40;
				break;
					
			}
		}		
	}
}
