package rpg;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Sentry extends Mob {
	boolean subSeePlayer=false;
	Animation see;
	Sound attacksfx;
	public Sentry(float myx, float myy) throws SlickException 
	{
		hp = 60;
		maxhp=hp;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down.png");
		seePlayer = false;
		direction = "Down";
		x=myx;
		y=myy;
		canShoot=true;
		directionrnd = 3;
		//directionrnd isnt acutally random here. i just needed a direction variable as a int type
		//Call stuff at diff frames in the animation class
		canMoveX=true;
		canMoveY=true;
		Image[] upA = {new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Up Shoot 1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Up Shoot 2.png")};
		Image[] downA= {new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down Shoot 1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down Shoot 2.png")};
		Image[] leftA={new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Left Shoot 1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Left Shoot 2.png")};
		Image[] rightA={new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Right Shoot 1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Right Shoot 2.png")};
		Image[] idleA={new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Left.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Right.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Up.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down.png"),};
		Image[] seeA={new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Left Alert.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Right Alert.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Up.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down Alert.png")};
		left = new Animation(leftA,500,true);
		right = new Animation(rightA,500,true);
		up = new Animation(upA,500,true);
		down = new Animation(downA,500,true);
		idle = new Animation(idleA,1000,false);
		see = new Animation(seeA,1000,false);
		sprite = idle;
		sprite.setCurrentFrame(3);
		attacksfx = new Sound("res/sound/Laser.wav");
	}
	
	public void ai( Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls, ArrayList<Mob> mobs) throws SlickException
	{
		if (canSeePlayer(player, 400))
		{
			seePlayer=true;
			Rectangle[] ranges = {new Rectangle(x-224,y+20,224,40),new Rectangle(x+20,y-224,40,224),new Rectangle(x+80,y+20,224,40),new Rectangle(x+20,y+80,40,224)};
			Rectangle theplayer = new Rectangle(player.x,player.y,player.image.getWidth(),player.image.getHeight());
			for (Rectangle recky:ranges)
			{
				if (recky.intersects(theplayer)||recky.contains(theplayer.getX(),theplayer.getY()))
				{
					subSeePlayer=true;
					break;
				}
				else
					subSeePlayer=false;
			}
		}
		else seePlayer=false;
		if (seePlayer)
		{
			Rectangle mobbox = new Rectangle(x,y,image.getWidth(),image.getHeight());
			Circle stayaway = new Circle(player.x,player.y,64); //make 64 right outside melee range
			float i = (player.x-x);
			float j = (player.y-y);
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
			if (!((stayaway.intersects(mobbox))||(stayaway.contains(x,y))))
			{
				if(canMoveX)
				x = (float) (x + ((i/magnitude)*2.25));
				if(canMoveY)
				y = (float) (y + ((j/magnitude)*2.25));
			}
			//if(canMoveX)
			//x = (float) (x + ((i/magnitude)*-1.5));
			//if(canMoveY)
			//y = (float) (y + ((j/magnitude)*-1.5));
			//java trig is in radians
			double angle = Math.atan(j/i);
			//System.out.println((angle*180)/Math.PI);
			if (((angle < (Math.PI/2)) && (angle > (Math.PI/4))) || ((angle > (Math.PI/-2)) && (angle < (Math.PI/-4))))
			{ 
				if(player.y+32>y)
				{
					sprite = down;
					sprite.stopAt(1);
					directionrnd = 3;
					up.restart();
					left.restart();
					right.restart();
				}
				else
				{
					sprite = up;
					sprite.stopAt(1);
					directionrnd = 2;
					down.restart();
					left.restart();
					right.restart();
				}
			}
			if ((angle > (Math.PI/-4)) && (angle < (Math.PI/4)))
			{
				if(player.x+32>x)
				{
					sprite =right;
					sprite.stopAt(1);
					directionrnd = 1;
					up.restart();
					down.restart();
					left.restart();
				}
				else
				{
					sprite = left;
					sprite.stopAt(1);
					directionrnd = 0;
					up.restart();
					down.restart();
					right.restart();
				}
			}
		}
		else
		{
			sprite = idle;
			sprite.setCurrentFrame(directionrnd);
			up.restart();
			down.restart();
			right.restart();
			left.restart();
		}
		if (subSeePlayer&&canShoot)
		{
			seePlayer=true;
			canShoot=false;
			projectiles.add(new Projectile(x+32,y+24,player.x+32,player.y+32,false));
			attacksfx.play();
			Timer waittime = new Timer();
			waittime.schedule(new TimerTask()
			{
				public void run()
				{
					canShoot = true;
				}
			},2000);
		}
	}

}
