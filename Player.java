package rpg;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Entity {
    public int hp;
    Rectangle hitbox;
    Circle reach;
    public boolean isDead=false;
    public int shoot1=0;
    public int shoot2 = 0;
    public String weapon1 = "screw driver";
    public String weapon2 = "energy gun";
    public boolean canAttack = true;
    public boolean swinging;
    public Image[] leftA = {new Image("res/Video Game Tiles - Pixel by Pixel/Left A1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/Left A2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left A3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left A4.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Left B4.png")};
    public Image[] rightA = {new Image("res/Video Game Tiles - Pixel by Pixel/Right A1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/Right A2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right A3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right A4.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B2.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B3.png"),new Image("res/Video Game Tiles - Pixel by Pixel/Right B4.png")};
	public Image[] upA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerbk1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk2.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk3.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk4.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk5.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerbk6.png")};
	public Image[] downA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft2.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft3.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft4.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft5.png"), new Image("res/Video Game Tiles - Pixel by Pixel/playerft6.png")};
    public Image[] idleA = {new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/playerbk1.png")};
    public Image[] stabUpScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/B1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2.png")};
    public Image[] stabDownScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/F1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2.png")};
    public Image[] stabLeftScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/L1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/L2.png")};
    public Image[] stabRightScrew = {new Image("res/Video Game Tiles - Pixel by Pixel/R1.png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2.png")};
    public Image[] stabUpSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/B1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2 (Energy Saber).png"), new Image ("res/Video Game Tiles - Pixel by Pixel/B3 (Energy Saber).png")};
    public Image[] stabDownSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/F1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2 (Energy Saber).png"), new Image("res/Video Game Tiles - Pixel by Pixel/F3 (Energy Saber).png")};
    public Image[] stabLeftSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/S1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/S2 (Energy Saber).png"), new Image ("res/Video Game Tiles - Pixel by Pixel/S3 (Energy Saber).png")};
    public Image[] stabRightSaber = {new Image("res/Video Game Tiles - Pixel by Pixel/R1 (Energy Saber).png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2 (Energy Saber).png"), new Image("res/Video Game Tiles - Pixel by Pixel/R3 (Energy Saber).png")};
    public Image[] shootUp = {new Image("res/Video Game Tiles - Pixel by Pixel/B1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/B2 (Laser Shot).png")};
    public Image[] shootDown = {new Image("res/Video Game Tiles - Pixel by Pixel/F1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/F2 (Laser Shot).png")};
    public Image[] shootLeft = {new Image("res/Video Game Tiles - Pixel by Pixel/L1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/L2 (Laser Shot).png")};
    public Image[] shootRight = {new Image("res/Video Game Tiles - Pixel by Pixel/R1 (Laser Shot).png"),new Image("res/Video Game Tiles - Pixel by Pixel/R2 (Laser Shot).png")};
    public Sound hurtsfx;
	public String direction;
    public Animation sprite, up, down, left, right, idle,stabL,stabR,stabU,stabD;
    
    public Player() throws SlickException
    {
    	image = new Image("res/Video Game Tiles - Pixel by Pixel/playerft1.png");
    	hurtsfx=new Sound("res/sound/Hit.wav");
		
		//int[] time1= {300,300,300,300,300,300};
		int time1=100;
		int time2=300;
		left = new Animation(leftA,time1,false);
		right = new Animation(rightA,time1,false);
		up = new Animation(upA,time1,false);
		down = new Animation(downA,time1,false);
		idle = new Animation(idleA,1,false);
	
		stabL = new Animation(stabLeftScrew,time2,true);
		stabR = new Animation(stabRightScrew,time2, true);
		stabU = new Animation(stabUpScrew,time1,false);
		stabD = new Animation(stabDownScrew,time1,false);
			
		sprite = up;
		direction="down";
    	cannotMoveLeft =false;
    	cannotMoveRight=false;
    	cannotMoveUp=false;
    	cannotMoveDown=false;
    	isSolid = true;
    	x = 444;
    	y = 288;
    	hp = 20;
    }

    public boolean meleeRange(Entity object, float range) 
    {
    	reach = new Circle(x + 32, y + 32, range);
    	hitbox = new Rectangle(object.x, object.y, object.sprite
    			.getCurrentFrame().getWidth(), object.sprite.getCurrentFrame()
    			.getHeight());
    	boolean canHit = ((reach.intersects(hitbox)) || (reach.contains(
    			object.x, object.y)));
    	if (((direction.equals("up")) && (object.y < y)) && canHit) {
    		return true;
    	}
    	if (((direction.equals("down")) && (object.y > y)) && canHit) {
    		return true;
    	}
    	if ((((direction.equals("right")) && (object.x > x))) && canHit) {
    		return true;
    	}
    	if (((direction.equals("left")) && (object.x < x)) && canHit) {
    		return true;
    	}
    	return false;
    }
    public void setWeapon1(String weapons)
    {
    	this.weapon1  =  weapons;
    }
    public void setWeapon2(String weapons)
    {
    	this.weapon2  =  weapons;
    }
    public String getWeapon1()
    {
    	return this.weapon1;
    }
    public String getWeapon2()
    {
    	return this.weapon2;
    }
    public void Swing(String direction) throws SlickException
    { 
    	if (direction.equals("up"))
    	{
    		sprite=stabU;
    	}
    	else if(direction.equals("left"))
    	{
    		sprite=stabL;
    	}
    	else if(direction.equals("down"))
    	{
    		sprite=stabD;
    	}
    	else if(direction.equals("right"))
    	{
    		sprite=stabR;
    	}
    }
    public void hurt()
    {
    	hp--;
    	hurtsfx.play();
    	if (hp<0)
    	{
    		isDead=true;
    	}
    }
    public String toString()
    {
    	return (hp+" health");
    }
    
}
