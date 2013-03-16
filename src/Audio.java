import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Audio implements Runnable
{
	public void run() 
	{
		try
		{
			Player player = new Player(new FileInputStream("assets/music/song.mp3"));
			player.play();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (JavaLayerException e)
		{
			e.printStackTrace();
		}
	}
	
}