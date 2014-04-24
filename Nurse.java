package rpg;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Nurse extends Mob {
		ArrayList<Mob> healTargets=new ArrayList<Mob>();
		Mob target;
		float i;
		float j;
		double magnitude;
		int healtimer;
	public Nurse(float myx, float myy) throws SlickException 
	{
		hp=40;
		maxhp=hp;
		healtimer = 0;
		image=new Image ("res/Nurse Down 1.png");
		target = null;
		direction="Down";
		seePlayer=false;
		x=myx;
		y=myy;
		canMoveX=true;
		canMoveY=true;
		Image[] upA= {new Image("res/Nurse Up 1.png")};
		Image[] downA= {new Image("res/Nurse Down 1.png")};
		Image[] leftA= {new Image("res/Nurse Left 1.png")};
		Image[] rightA= {new Image("res/Nurse Right 1.png")};
		left = new Animation(leftA,100,true);
		right = new Animation(rightA,100,true);
		up = new Animation(upA,100,true);
		down = new Animation(downA,100,true);
		sprite = down;
		
	}
	public void ai(Player player, ArrayList<Projectile> projectiles, ArrayList<Wall> walls, ArrayList<Mob> mobs)
	{	//healing propority to running away from player.
		canMoveX=true;
		canMoveY=true;
		healTargets.clear();
		for (Mob moby : mobs)
		{
			if((!(moby instanceof Nurse))&&(Math.sqrt((Math.pow(moby.x-x,2))+(Math.pow(moby.y-y,2))) < 275))
				healTargets.add(moby);
		}
		if(healTargets.isEmpty())
		{
			if(Math.sqrt((Math.pow(player.x-x,2))+(Math.pow(player.y-y,2))) < 275)
			{
				
				i = -1*(player.x-x);
				j = -1*(player.y-y);
				magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
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
				if(magnitude==0)
					magnitude++;
				if(canMoveX)
					x = (float) (x + ((i/magnitude)*3));
				if(canMoveY)
					y = (float) (y + ((j/magnitude)*3));
			}
			
		}
		else
		{
			Mob lowesthp = healTargets.get(0);
			for(int i = 1; i <healTargets.size()-1;i++)
			{
				if (healTargets.get(i).hp<lowesthp.hp)
					lowesthp=healTargets.get(i);
			}
			i = (lowesthp.x-x);
			j = (lowesthp.y-y);
			magnitude = Math.sqrt(Math.pow(i,2) + Math.pow(j,2));
			if(Math.sqrt((Math.pow(lowesthp.x-x,2))+(Math.pow(lowesthp.y-y,2))) < 135)
			{
				canMoveX=false;
				canMoveY=false;
			}
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
			if(canMoveX)
				x = (float) (x + ((i/magnitude)*3));
			if(canMoveY)
				y = (float) (y + ((j/magnitude)*3));
			//Graphical Heal effect
			if(healtimer >= 5)
			{
				healtimer = 0;
				if(lowesthp.hp<lowesthp.maxhp)
					lowesthp.hp++;
			}
			else
				healtimer++;		
		}
		if(Math.abs(i)>Math.abs(j))
		{
			if(i<0)
				sprite = left;
			else
				sprite = right;
		}
		else
		{
			if(j>0)
				sprite = down;
			else
				sprite = up;
		}
	}
	
}


