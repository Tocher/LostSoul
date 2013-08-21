import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;


public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean running;
	
	public static int WIDTH = 1300;				
	public static int HEIGHT = 650;				
	public static String NAME = "LostSoul";	
	
	public static float size = (float) 1;
	
	public static long fps = 0;
	public static long xml_load = 0;
	
	Hero hero;
	static Thread audio = new Thread(new Audio());
	
	public static long delta2=0;
	public static ArrayList<World> w = new ArrayList<World>();
	
	public int hovern = 0;
	Graphics g;
	InterfaceBlocks blocks;
	
	public static void main(String[] args) {		
		Main game = new Main();				
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));		
		JFrame window = new JFrame(Main.NAME);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(game, BorderLayout.CENTER);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		
		int w = 1300;
		int h = 730;
		int x1 = (dim.width - w) / 2;
		int y2 = (dim.height - h) / 2;
		window.setLocation(x1, y2);
		
		window.pack();						
		window.setResizable(true);
		window.setSize(dim);
		window.setResizable(false);
		window.setLocation(0, 0);
		window.setVisible(true);
		
		audio.start();
		game.start();						
	}
	
	public void start() {		
		running = true;
		new Thread(this).start();
	}
	
	public void run() {
		
		long lastTime = System.currentTimeMillis(); 	
		long delta = 0;	
		
		World_Entities WE = new World_Entities();
		
		xml_load = System.currentTimeMillis();
		XML xml = new XML();
		xml.ReadBgXML(w);
		
		for(int i=0;i<w.size();i++)
		{
			for(int j=0;j<w.size();j++)
			{
				if((w.get(i).GetX()==w.get(j).GetX()+1)&&(w.get(i).GetY()==w.get(j).GetY()))
					w.get(i).left = w.get(j);
				if((w.get(i).GetX()==w.get(j).GetX()-1)&&(w.get(i).GetY()==w.get(j).GetY()))
					w.get(i).right = w.get(j);
				if((w.get(i).GetY()==w.get(j).GetY()+1)&&(w.get(i).GetX()==w.get(j).GetX()))
					w.get(i).up = w.get(j);
				if((w.get(i).GetY()==w.get(j).GetY()-1)&&(w.get(i).GetX()==w.get(j).GetX()))
					w.get(i).down = w.get(j);
			}
		}	
		
		blocks = new InterfaceBlocks();
		hero = new Hero("hero.png", w);
		addMouseListener(new MyMouseAdapter(hero));
		addMouseMotionListener(new CustomMotionListener());
		xml_load = System.currentTimeMillis() - xml_load;
		hero.x = 160;
		hero.y = 160;
		while(running)
		{			
			lastTime = System.currentTimeMillis();
			if(delta+10<lastTime)
			{
				delta = System.currentTimeMillis();	
				render(System.currentTimeMillis(),WE);
			}
		}
		
	}
	
	public void render(long delta, World_Entities WE) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		
		// Draw Background
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_bg(0,0,g,WE);
		}		
		// Draw objects and units
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_obj(0,0,g,hero,delta,WE);
		}		
		
		
		// Time for xml load
 		g.setColor(Color.red);
 		g.drawString(String.valueOf(xml_load), 50, 20); 		
		
 		blocks.drawHpBar(g, 50);
		
		
		g.dispose();
		bs.show();
	}

}
