package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Drone extends Mob{
	Sound attacksfx;
	boolean inRadius;
	int timer;
	boolean canExplode;
	int enabletimer;
	public Drone(float myx, float myy) throws SlickException 
	{
		hp = 30;
		maxhp=hp;
		image = new Image("res/Drone Down 1.png");
		direction = "Down";
		seePlayer=false;
		x=myx;
		y=myy;
		canMoveX=true;
		canMoveY=true;
		timer=0;
		enabletimer=0;
		canExplode=true;
		Image[] upA = {new Image("res/Drone Up 1.png")};
		Image[] downA = {new Image("res/Drone Down 1.png")};
		Image[] leftA = {new Image("res/Drone Left 1.png")};
		Image[] rightA = {new Image("res/Drone Right 1.png")};
		left = new Animation(leftA,250,true);
		right = new Animation(rightA,250,true);
		up = new Animation(upA,250,true);
		down = new Animation(downA,250,true);
		sprite = down;
		//attacksfx
	}

	public void ai(Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls, ArrayList<Mob> mobs)
	{
		float i = (player.x-x);
		float j = (player.y-y);
		if(canSeePlayer(player,300))
		{
			seePlayer=true;
			Circle range = new Circle(x+image.getWidth()/2,y+image.getHeight()/2,64);
			Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			if((range.intersects(theplayer))||(range.contains(player.x,player.y)))
			{
				inRadius=true;
			}
			else inRadius=false;
		}
		else seePlayer=false;
		if(seePlayer&&canExplode)
		{
			double magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
			canMoveX=true;
			canMoveY=true;
			for(Wall wally: walls)
			{
				Rectangle box = new Rectangle(wally.x,wally.y,wally.image.getWidth(),wally.image.getHeight());
				float xhalf = image.getWidth()/2;
				float yhalf = image.getHeight()/2;
				float xqrtr = xhalf/2;
				float yqrtr = xhalf/2;
				boolean a=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y));
				boolean b=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y));
				boolean c=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+image.getHeight()));
				boolean d=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+image.getHeight()));
				boolean e=(box.contains((float)(x+xhalf + ((i/magnitude)*1.5*2)),y));
				boolean f=(box.contains((float)(x+xhalf + ((i/magnitude)*1.5*2)),y+image.getHeight()));
				boolean g=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yhalf));
				boolean h=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yhalf));
				boolean k=(box.contains((float)(x +xqrtr+ ((i/magnitude)*1.5*2)),y));
				boolean l=(box.contains((float)(x +xqrtr*3+ ((i/magnitude)*1.5*2)),y));
				boolean m=(box.contains((float)(x +xqrtr+ ((i/magnitude)*1.5*2)),y+image.getHeight()));
				boolean n=(box.contains((float)(x +xqrtr*3+ ((i/magnitude)*1.5*2)),y+image.getHeight()));
				boolean o=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yqrtr));
				boolean p=(box.contains((float)(x + ((i/magnitude)*1.5*2)),y+yqrtr*3));
				boolean q=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yqrtr));
				boolean r=(box.contains((float)(x+image.getWidth() + ((i/magnitude)*1.5*2)),y+yqrtr*3));
				
				if (a||b||c||d||e||f||g||h||k||l||m||n||o||p||q||r)
				{
					canMoveX=false;
				}
				a=(box.contains(x,(float)(y + ((j/magnitude)*1.5*2))));
				b=(box.contains(x+image.getWidth(),(float)(y + ((j/magnitude)*1.5*2))));
				c=(box.contains(x,(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
				d=(box.contains(x+image.getWidth(),(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
				e=(box.contains(x+xhalf,(float)(y + ((j/magnitude)*1.5*2))));
				f=(box.contains(x+xhalf,(float)(y+image.getHeight() + ((j/magnitude)*1.5*2))));
				g=(box.contains(x,(float)(y +yhalf+ ((j/magnitude)*1.5*2))));
				h=(box.contains(x+image.getWidth(),(float)(y +yhalf+ ((j/magnitude)*1.5*2))));
				k=(box.contains(x+xqrtr,(float)(y + ((j/magnitude)*1.5*2))));
				l=(box.contains(x+xqrtr*3,(float)(y + ((j/magnitude)*1.5*2))));
				m=(box.contains(x+xqrtr,(float)(y +image.getHeight()+ ((j/magnitude)*1.5*2))));
				n=(box.contains(x+xqrtr*3,(float)(y +image.getHeight()+ ((j/magnitude)*1.5*2))));;
				o=(box.contains(x,(float)(y +yqrtr+ ((j/magnitude)*1.5*2))));
				p=(box.contains(x,(float)(y +yqrtr*3+ ((j/magnitude)*1.5*2))));
				q=(box.contains(x+image.getWidth(),(float)(y +yqrtr+ ((j/magnitude)*1.5*2))));
				r=(box.contains(x+image.getWidth(),(float)(y +yqrtr*3+ ((j/magnitude)*1.5*2))));
				if (a||b||c||d||e||f||g||h||k||l||m||n||o||p||q||r)
				{
					canMoveY=false;
					break;
				}
					
			}
			
			if(canMoveX&&!inRadius)
				x = (float) (x + ((i/magnitude)*1.25));
			else
				sprite.stop();
			if(canMoveY&&!inRadius)
				y = (float) (y + ((j/magnitude)*1.25));
			else
				sprite.stop();
		double angle = Math.atan(j/i);
		if (((angle < (Math.PI/2)) && (angle > (Math.PI/4))) || ((angle > (Math.PI/-2)) && (angle < (Math.PI/-4))))
		{ 
			if(player.y+32>y)
			{
				sprite = down;
				direction = "Down";
			}
			else
			{
				sprite = up;
				direction = "Up";
			}
		}
		if ((angle > (Math.PI/-4)) && (angle < (Math.PI/4)))
		{
			if(player.x+32>x)
			{
				sprite =right;
				direction="Right";
			}
			else
			{
				sprite = left;
				direction="Left";
			}
		}
		}
		if(inRadius&&canExplode)
		{
			if(timer>=60)
			{
				timer=0;
				hp=0;
				player.hurt();
				player.hurt();
				player.hurt();
				player.hurt();
				player.hurt();
			}
			else
			{
				timer++;
				System.out.println(timer);
				//TODO graphic of charging up explosion
			}
		}
		else
			timer=0;
		if(!canExplode)
		{
			if(enabletimer>=220)
			{
				canExplode=true;
				enabletimer=0;
			}
			else
			{
				//TODO set some sort of graphic, notifying disabledness
				enabletimer++;
				System.out.println(enabletimer);
			}
		}
		//System.out.println(sprite.getCurrentFrame());
	}
	
}
