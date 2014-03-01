package rpg;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends Entity{
	String type;
	public Wall(float myx, float myy, String myType) throws SlickException
	{
		super();
		x=myx;
		y=myy;
		type = myType;
		//TODO depending on type: set image and isSolid
		//testing use only (below):
		image = new Image("res/Video Game Tiles - Pixel by Pixel/block.png");
		isSolid=true;
		//if (myType.equals("water")) image = new Image("res/")
	}
	public Wall(float myx, float myy) throws SlickException
	{
		super();
		x=myx;
		y=myy;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/block.png");
		isSolid=true;
	}
    public String toString()
    {
        String mysolid = "Not Solid";
        if (isSolid) mysolid = "Solid";
        return mysolid + " " + type + " block at "+ x +","+ y ;
    }
}
