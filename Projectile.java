package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Projectile extends Entity {
		float i;
		float j;
		float x1;
		float y1;
		double magnitude;
		boolean fromPlayer;
		boolean canDamage;
	public Projectile(float myx, float myy, float myx1, float myy1, boolean playerowner) throws SlickException 
	{
		x = myx;
		y = myy;
		startingX=myx;
		startingY=myy;
		x1=myx1;
		y1=myy1;
		i = startingX-x1;
		j = startingY-y1;
		magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
		cannotMoveLeft=false;
		cannotMoveRight=false;
		cannotMoveUp= false;
		cannotMoveDown=false;
		fromPlayer = playerowner;
		canDamage=true;
		if (fromPlayer)
			image = new Image("res/Video Game Tiles - Pixel by Pixel/Energy Ball Blue.png");
		else
			image = new Image("res/Video Game Tiles - Pixel by Pixel/Energy Ball Red.png");
		
	}
	
	public void update(int multiple,Player player, ArrayList<Mob> mobs, ArrayList<Projectile> projectiles) throws SlickException
	{
		double angle = Math.atan(j/i);
		if (((angle < (Math.PI/2)) && (angle > (Math.PI/4))) || ((angle > (Math.PI/-2)) && (angle < (Math.PI/-4))))
		{ 
			if(y1>startingY)
			{
				y+=multiple;
			}
			else
			{
				y+=-multiple;
			}
		}
		if ((angle > (Math.PI/-4)) && (angle < (Math.PI/4)))
		{
			if(x1>startingX)
			{
				x+=multiple;
			}
			else
			{
				x+=-multiple;
			}
		}
		if(canDamage)
		{
			if(!fromPlayer)
			{
				Rectangle ball = new Rectangle(x,y,image.getWidth(),image.getHeight());
				Rectangle playerbox = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
				if((playerbox.intersects(ball))||(playerbox.contains(x,y)))
				{
					player.hurt();
					canDamage=false;
					image = new Image("res/Video Game Tiles - Pixel by Pixel/Blank.png");
				}
			}
			else
			{
				Rectangle ball = new Rectangle(x,y,image.getWidth(),image.getHeight());
				for (Mob moby:mobs)
				{
					Rectangle mobbox = new Rectangle(moby.x,moby.y,moby.image.getWidth(),moby.image.getHeight());
					if((mobbox.intersects(ball))||(mobbox.contains(x,y)))
					{
						moby.hurt(10);
						canDamage=false;
						image = new Image("res/Video Game Tiles - Pixel by Pixel/Blank.png");
					}
				}
			}
		}
	}
}
