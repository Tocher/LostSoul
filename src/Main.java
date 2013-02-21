import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean running;
	
	public static int WIDTH = 1000;				//Ширина окна
	public static int HEIGHT = 700;				//Высота окна
	public static String NAME = "RPG";		//Надпись
	
	Hero hero = new Hero("hero.png");
	public static long delta2=0;
	
	public static void main(String[] args) {		//Главная функция
		Main game = new Main();				
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));		//создание окна, по ширине высоте имени бла бла
		JFrame frame = new JFrame(Main.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		//Выравнивение окна по центру монитора
		int w = 1050;
		int h = 750;
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
		//MySQL sql = new MySQL();
		//sql.connect();
		long lastTime = System.currentTimeMillis(); 	//Время в мс
		long delta;	

		hero.x = 240;
		hero.y = 240;
		while(running)
		{			
			delta = System.currentTimeMillis() - lastTime; 
			lastTime = System.currentTimeMillis();	
			render(System.currentTimeMillis());			
		}
		
	}
	
	public void render(long delta) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		Init init_png = new Init();			
		Animator bg = new Animator(init_png.getArrayList("tpl1.png", 40, 40, 1, 1));
		bg.chFrame(0);		
		g.setColor(Color.BLACK);
		for(int i=0;i<getHeight();i+=40)
		{
			for(int j=0;j< getWidth();j+=40)
			{				
				g.drawImage(bg.sprite, j, i, 40, 40, null);
			}
		}
		
		for(int i=0;i<getHeight();i+=40)
		{
			for(int j=0;j< getWidth();j+=40)
			{
				g.drawLine(j, i, j+40, i);
				g.drawLine(j, i, j, i+40);
			}			
		}
				
		if(delta2+150<delta)
		{
			delta2=delta;
			hero.HeroMove(500, 2, g);
		}
		hero.HeroDraw(g);
		
		
		g.dispose();
		bs.show();
	}

}
