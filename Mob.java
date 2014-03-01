package rpg;



import java.util.ArrayList;
import java.util.Timer;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public abstract class Mob extends Entity {
	public double hp;
	public int dmg;
    public int money;
    public Image image;
    public Image[] leftA;
    public Image[] rightA;
	public Image[] upA;
	public Image[] downA;
	public Image projectile;
    public boolean seePlayer;
    public int moveX;
	public int moveY;
	public boolean canShoot;
	public int directionrnd;
	public static boolean moveagain;
	public Timer timer = new Timer();
	public Timer subtimer = new Timer();
	public int knockbackX;
	public int knockbackY;
	public int knockbackTimer;
	public boolean knockedBack;
	public Mob()
	{
		
	}
	
	public void hurt(double damage)
	{
		hp -= damage;
	}
	public void dropHP(double i,ArrayList<Medkit> meds) throws SlickException
	{
		if(Math.random()<=i)
		{
			meds.add(new Medkit(x+24,y+48));
		}
	}
	
	public void ai(Player player, ArrayList<Projectile> projectiles) throws SlickException
	{
			
	}
	public boolean canSeePlayer(Player player, float range)
	{
		Circle radius = new Circle(x,y,range);
		Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
		if (radius.intersects(theplayer)||radius.contains(theplayer.getX(),theplayer.getY()))
		{
			return true;
		}
		else return false;
	}

}
