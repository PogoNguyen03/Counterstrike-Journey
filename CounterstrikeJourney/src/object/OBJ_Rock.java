/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import entity.Projectile;
import java.awt.Color;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class OBJ_Rock extends Projectile{
     O_GamePanel ogp;

     public static final String objName = "Rock";
     
    public OBJ_Rock(O_GamePanel ogp) {
        super(ogp);
        this.ogp =ogp;
        
        name = objName;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        
        getImage();
    }

    
    public void getImage(){
        W1 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        W2 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        S1 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        S2 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        A1 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        A2 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        D1 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        D2 = setup("/res/projectile/rock_down_1", ogp.tileSizeW,ogp.tileSizeH);
        
    }
    public boolean haveResource(Entity user){
        
        boolean haveResource = false;
        if(user.ammo >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.ammo -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(40,50,0);
        return color;
    }
    
    public int getParticleSize(){
        int size = 10; 
        return size;
    }
    
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
