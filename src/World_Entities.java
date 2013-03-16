
public class World_Entities
{
	public Animator grass;
	public Animator objects;
	public Animator objects2;
	public Animator house;
	
	public World_Entities()
	{
		Init init_png = new Init();			
		grass = new Animator(init_png.getArrayList("grass_tile.png", 40, 40, 3, 6));	
		objects = new Animator(init_png.getArrayList("obj_tile.png", 50, 70, 1, 3));
		objects2 = new Animator(init_png.getArrayList("obj2_tile.png", 40, 40, 1, 3));		
		house = new Animator(init_png.getArrayList("house_tile.png", 150, 70, 1, 1));		
	}
}
