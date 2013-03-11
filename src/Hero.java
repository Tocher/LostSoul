

public class Hero extends Unit
{	
	
	public Hero(String name) 
	{
		Init init_png = new Init();
		unit = new Animator(init_png.getArrayList(name, 32, 32, 4, 4));
	}
	
	public boolean onCoords(int x,int y)
	{
		if(x == this.x/40&&y == this.y/40)
			return true;
		else		
			return false;
	}
	
	public void MoveScreen()
	{
		
	}
}
