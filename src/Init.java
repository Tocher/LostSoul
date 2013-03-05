import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Init
{		
	public ArrayList<BufferedImage> getArrayList(String name, int width, int height, int row, int col)
	{
		ArrayList<BufferedImage> image = new ArrayList<BufferedImage>();
		BufferedImageLoader loader = new BufferedImageLoader();
	    BufferedImage image_buff = null;   
	    
	    try
	    {
	    	image_buff = loader.loadImage(name);         
	    }
	    catch (IOException ex)
	    {            
	    }
	    
	    SpriteSheet image_sheet = new SpriteSheet(image_buff);
	    
	    int margin_x, margin_y;        
	    for(int t = 0; t < row; t++)
	    {
	    	margin_y = height * t;
	    	for(int k = 0; k < col; k++)
	    	{
	    		margin_x = width * k;
	    		image.add(image_sheet.grabSprite(margin_x, margin_y, width, height));
	    	}
	    }
	    
	    return image;
	}
}
