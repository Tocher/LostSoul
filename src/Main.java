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
	
	public static int WIDTH = 1300;				//Ширина окна
	public static int HEIGHT = 650;				//Высота окна
	public static String NAME = "LostSoul";		//Надпись
	
	public static long fps = 0;
	public static long xml_load = 0;
	
	Hero hero = new Hero("hero.png");
	public static long delta2=0;
	ArrayList<World> w = new ArrayList<World>();
	public int hovern = 0;
	Graphics g;
	
	public static void main(String[] args) {		//Главная функция
		Main game = new Main();				
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));		//создание окна, по ширине высоте имени бла бла
		JFrame frame = new JFrame(Main.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		//Выравнивение окна по центру монитора
		int w = 1300;
		int h = 730;
		int x1 = (dim.width - w) / 2;
		int y2 = (dim.height - h) / 2;
		frame.setLocation(x1, y2);
		
		frame.pack();						//какая-то бня
		frame.setResizable(false);
		frame.setVisible(true);
		game.start();						//ПОНЕСЛАСЬ
	}
	
	public void start() {		//Создает игровой поток
		running = true;
		new Thread(this).start();
	}
	
	public void run() {
		
		long lastTime = System.currentTimeMillis(); 	//Время в мс
		long delta = 0;	
		addKeyListener(new MyKeyAdapter(hero));
		addMouseListener(new MyMouseAdapter(hero));
		addMouseMotionListener(new CustomMotionListener());
		
		World_Entities WE = new World_Entities();
		
		xml_load = System.currentTimeMillis();
		XML xml = new XML();
		xml.ReadBgXML(w);
		xml_load = System.currentTimeMillis() - xml_load;
		hero.x = 80;
		hero.y = 80;
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
		
		//int s1 = Math.round(hero.x/40);
		//int s2 = Math.round(hero.y/40);
		
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_bg(0,0,g,WE);
		}		
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_obj(0,0,g,hero,delta,WE);
		}		
		

		
		g.setColor(Color.white);
 		for(int i=0;i<getHeight();i+=40)
 		{
 			for(int j=0;j<getWidth();j+=40)
 			{
 				g.drawLine(j, i, j+40, i);
 	 			g.drawLine(j, i, j, i+40);
 			}
 		}
 		g.setColor(Color.red);
 		g.drawString(String.valueOf(xml_load), 50, 20);
				
//		if(delta2+150<delta)
//		{
//			delta2=delta;
//			hero.HeroMove(500, 4);
//		}
		
 		
		
		
		
		g.dispose();
		bs.show();
	}

}
