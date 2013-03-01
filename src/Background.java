import java.awt.Graphics;


public class Background 
{
	private Animator grass;
	
	public Background()
	{
		Init init_png = new Init();			
		grass = new Animator(init_png.getArrayList("grass_tile.png", 40, 40, 1, 5));
	}
	
	public void DrawBg(int x,int y,Graphics g,XML xml)
	{
		
		int width_of_screen=40+x;
		int height_of_screen=20+y;
		String buf;
		for(int i=x;i<width_of_screen;i++)
		{
			for(int j=y; j<height_of_screen; j++)
			{
				buf = xml.ReadBgXML(i, j);
				switch(buf)
				{
				case "1":
					grass.chFrame(0);
					g.drawImage(grass.sprite, i*40, j*40, null);
					break;
				case "2":
					grass.chFrame(1);
					g.drawImage(grass.sprite, i*40, j*40, null);
					break;
				case "3":
					grass.chFrame(2);
					g.drawImage(grass.sprite, i*40, j*40, null);
					break;
				case "4":
					grass.chFrame(3);
					g.drawImage(grass.sprite, i*40, j*40, null);
					break;
				case "5":
					grass.chFrame(4);
					g.drawImage(grass.sprite, i*40, j*40, null);
					break;
				}
			}
		}
		
	}
}
