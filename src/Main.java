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
	
	public static int WIDTH = 1300;				//������ ����
	public static int HEIGHT = 650;				//������ ����
	public static String NAME = "LostSoul";		//�������
	
	Hero hero = new Hero("hero.png");
	public static long delta2=0;
	
	public static void main(String[] args) {		//������� �������
		Main game = new Main();				
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));		//�������� ����, �� ������ ������ ����� ��� ���
		JFrame frame = new JFrame(Main.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();		//������������ ���� �� ������ ��������
		int w = 1300;
		int h = 730;
		int x1 = (dim.width - w) / 2;
		int y2 = (dim.height - h) / 2;
		frame.setLocation(x1, y2);
		
		frame.pack();						//�����-�� ���
		frame.setResizable(false);
		frame.setVisible(true);
		game.start();						//���������
	}
	
	public void start() {		//������� ������� �����
		running = true;
		new Thread(this).start();
	}
	
	public void run() {
		
		long lastTime = System.currentTimeMillis(); 	//����� � ��
		long delta;	
		
		XML xml = new XML();
		
		hero.x = 240;
		hero.y = 240;
		while(running)
		{			
			delta = System.currentTimeMillis() - lastTime; 
			lastTime = System.currentTimeMillis();	
			render(System.currentTimeMillis(),xml);			
		}
		
	}
	
	public void render(long delta,XML xml) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		Background bg = new Background();
		bg.DrawBg(0, 0, g, xml);
			
		
				
		if(delta2+150<delta)
		{
			delta2=delta;
			//hero.HeroMove(500, 4);
		}
		hero.HeroDraw(g);
		
		
		g.dispose();
		bs.show();
	}

}
