import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter
{
 Hero hero;
 
 public MyKeyAdapter(Hero hero)
 {
  this.hero = hero;
 }
 
 public void keyPressed(KeyEvent e)
 {
	 switch (e.getKeyCode())
	  {
	   case 87:
	    hero.Move(1);
	    break;
	   case 68:
	    hero.Move(2);
	    break;
	   case 83:
	    hero.Move(3);
	    break;
	   case 65:
	    hero.Move(4);
	    break;
	  }
 }

}