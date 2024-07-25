/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class OBJ_Tent extends Entity{
    
    O_GamePanel ogp;
    
    public static final String objName = "Lều";
    
    public OBJ_Tent(O_GamePanel ogp) {
        super(ogp);
        
        this.ogp = ogp;
        
        type = type_consumable;
        name = objName;
        S1 = setup("/res/objects/tent", ogp.tileSizeW,ogp.tileSizeH);
        description = "[" + name + "]\nCó thể ngủ qua đêm với chúng.";
        price = 100;
        stackable = true;
    }

    
    public  boolean use(Entity entity){
        
        ogp.gameState = ogp.sleepState;
        ogp.playSE(14);
        ogp.player.life = ogp.player.maxLife;
        ogp.player.mana = ogp.player.maxMana;
        ogp.player.getSleepingImage(S1);
        return true;
    }
    
}
