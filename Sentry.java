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
		money = 5;
		dmg = 2;
		image = new Image("res/Video Game Tiles - Pixel by Pixel/Sentry Down.png");
		seePlayer = false;
		direction = "Down";
		x=myx;
		y=myy;
		moveX=0;
		moveY=0;
		canShoot=true;
		directionrnd = 3;
		//directionrnd isnt acutally random here. i just needed a direction variable as a int type
		//Call stuff at diff frames in the animation class
		knockbackX=0;
		knockbackY=0;
		knockbackTimer=0;
		knockedBack=false;
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
	
	public void ai( Player player, ArrayList<Projectile> projectiles) throws SlickException
	{
		if (canSeePlayer(player, 384))
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
			if (!((stayaway.intersects(mobbox))||(stayaway.contains(x,y))))
			{
				x = (float) (x + ((i/magnitude)*1.5*2));
				y = (float) (y + ((j/magnitude)*1.5*2));
			}
			x = (float) (x + ((i/magnitude)*-1.5));
			y = (float) (y + ((j/magnitude)*-1.5));
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
