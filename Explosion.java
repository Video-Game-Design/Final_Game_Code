package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Explosion extends Entity {

	public Explosion(float myx, float myy) throws SlickException
	{
		x=myx;
		y=myy;
		Image[] boom={new Image("res/Explosion 1.png"),new Image("res/Explosion 2.png"),new Image("res/Explosion 3.png"),new Image("res/Explosion 4.png"),new Image("res/Explosion 5.png"),new Image("res/Explosion 6.png"),new Image("res/Explosion 7.png"),new Image("res/Explosion 8.png")};
		sprite = new Animation(boom,10,true);
	}
	public void update(ArrayList<Explosion> explosions, int i)
	{
		if(sprite.getFrame()==7)
		{
			explosions.remove(i);
		}
	}

}
