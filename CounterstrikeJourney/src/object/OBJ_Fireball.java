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
public final class OBJ_Fireball extends Projectile{
    
    public static final String objName = "Fireball";
    
    O_GamePanel ogp;
    
    public OBJ_Fireball(O_GamePanel ogp) {
        super(ogp);
        this.ogp =ogp;
        
        name = objName;
        speed = 10;
        maxLife = 80;
        life = maxLife;
        attack = 10;
        knockBackPower = 5;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        W1 = setup("/res/projectile/fireball_up_1", ogp.tileSizeW,ogp.tileSizeH);
        W2 = setup("/res/projectile/fireball_up_2", ogp.tileSizeW,ogp.tileSizeH);
        S1 = setup("/res/projectile/fireball_down_1", ogp.tileSizeW,ogp.tileSizeH);
        S2 = setup("/res/projectile/fireball_down_2", ogp.tileSizeW,ogp.tileSizeH);
        A1 = setup("/res/projectile/fireball_left_1", ogp.tileSizeW,ogp.tileSizeH);
        A2 = setup("/res/projectile/fireball_left_2", ogp.tileSizeW,ogp.tileSizeH);
        D1 = setup("/res/projectile/fireball_right_1", ogp.tileSizeW,ogp.tileSizeH);
        D2 = setup("/res/projectile/fireball_right_2", ogp.tileSizeW,ogp.tileSizeH);
    }
    public boolean haveResource(Entity user){
        
        boolean haveResource = false;
        if(user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user){
        user.mana -= useCost;
    }
    public Color getParticleColor(){
        Color color = new Color(240,50,0);
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
