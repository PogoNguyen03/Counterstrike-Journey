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

public class OBJ_ManaCrystal extends Entity{
    
    O_GamePanel ogp;
    
    public static final String objName = "Năng lượng";
    
    public OBJ_ManaCrystal(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        
        type = type_pickupOnly;
        name = objName;
        value = 1;
        S1 = setup("/res/objects/manacrystal_full", ogp.tileSizeW,ogp.tileSizeH);
        image = setup("/res/objects/manacrystal_full", ogp.tileSizeW,ogp.tileSizeH);
        image2 = setup("/res/objects/manacrystal_blank", ogp.tileSizeW,ogp.tileSizeH);
    }
    public boolean use(Entity entity){
        
        ogp.playSE(2);
        ogp.ui.addMessage("Năng lượng +" + value);
        entity.mana += value;
        return true;
    }
}
