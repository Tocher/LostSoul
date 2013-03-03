import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;

public class XML {
	
	public ArrayList<World> ReadBgXML(ArrayList<World> w)
	{
		try 
        {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("assets/xml/test.xml");
            Document rDoc = parser.build(fr);            
            List<Element> temp = rDoc.getRootElement().getChildren();
            for (int i = 0; i < temp.size(); ++i) 
            {             	
            	w.add(new World(Integer.valueOf(temp.get(i).getAttributeValue("x")),Integer.valueOf(temp.get(i).getAttributeValue("y")),Integer.valueOf(temp.get(i).getAttributeValue("bg")),Integer.valueOf(temp.get(i).getAttributeValue("obj"))));   
            }
            return w;
        }
        catch (Exception ex) 
        {
            //System.out.println(ex.getMessage());
        }	
		return w;
	}
	 
    public void ReadXML() 
    {
        try 
        {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("assets/xml/test.xml");
            Document rDoc = parser.build(fr);
            System.out.println(rDoc.getRootElement().getName());
            List<Element> temp = rDoc.getRootElement().getChildren();
            for (int i = 0; i < temp.size(); ++i) 
            {                
                System.out.print(temp.get(i).getAttributeValue("x"));
                System.out.print(temp.get(i).getAttributeValue("y"));
                System.out.print(temp.get(i).getAttributeValue("bg"));
                System.out.println(temp.get(i).getAttributeValue("obj"));
            }
        }
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void OutXML() 
    {
    	Element rootElement = new Element("world");
    	Document doc = new Document(rootElement);
    	rootElement.setAttribute("color", "black");
    	rootElement.setAttribute(new Attribute("size", "13.3"));
    	rootElement.addContent(new Element("acer"));
    	rootElement.addContent(new Element("acer").setAttribute("price", "cheap"));
    	rootElement.addContent(new Element("acer").
    	        setAttribute("price", "cheap").
    	        setAttribute("quality", "bad"));
    	Element elAsus = new Element("asus");
    	elAsus.setAttribute("price", "normal");
    	elAsus.setAttribute("quality", "normal");
    	rootElement.addContent(elAsus);
    	Element elSony = new Element("sony");
    	elSony.setAttribute("price", "expensive");
    	elSony.setAttribute("quality", "good");
    	rootElement.addContent(elSony);
    	Element elAcer = rootElement.getChild("acer");
    	elAcer.getAttribute("quality").setValue("normal");
    	elAsus.addContent("Asus Eee PC");
    	rootElement.addContent(new Comment("This is just comment!"));
    	try 
    	{
    	    XMLOutputter outputter = new XMLOutputter();
    	    outputter.setFormat(Format.getPrettyFormat());
    	    FileWriter fw = new FileWriter("output.xml");
    	    outputter.output(doc, fw);
    	    fw.close();
    	}
    	catch (Exception ex) 
    	{
    	    System.out.println(ex.getMessage());
    	}    	
    }
}

