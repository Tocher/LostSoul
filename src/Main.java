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
	
	public static int WIDTH = 1300;				//������ ����
	public static int HEIGHT = 650;				//������ ����
	public static String NAME = "LostSoul";		//�������
	
	Hero hero = new Hero("hero.png");
	public static long delta2=0;
	ArrayList<World> w = new ArrayList<World>();
	
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
		
		//long lastTime = System.currentTimeMillis(); 	//����� � ��
		//long delta;	
		addKeyListener(new MyKeyAdapter(hero));
		XML xml = new XML();
		xml.ReadBgXML(w);
		hero.x = 240;
		hero.y = 240;
		while(running)
		{			
			//delta = System.currentTimeMillis() - lastTime; 
			//lastTime = System.currentTimeMillis();	
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
		
		//int s1 = Math.round(hero.x/40);
		//int s2 = Math.round(hero.y/40);
		
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_bg(0,0,g);
		}		
		for(int i=0;i<w.size();i++)
		{
			w.get(i).draw_obj(0,0,g);
		}		
		
		/*
		g.setColor(Color.orange);
 		for(int i=0;i<getHeight();i+=40)
 		{
 			for(int j=0;j<getWidth();j+=40)
 			{
 				g.drawLine(j, i, j+40, i);
 	 			g.drawLine(j, i, j, i+40);
 			}
 		}
 		
		
				
		if(delta2+150<delta)
		{
			delta2=delta;
			hero.HeroMove(500, 4);
		}
		*/
		hero.HeroDraw(g);
		
		
		g.dispose();
		bs.show();
	}

}
