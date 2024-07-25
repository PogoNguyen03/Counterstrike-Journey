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
public class OBJ_Coin_Bronze extends Entity{
    O_GamePanel ogp;
    
    public static final String objName = "Tiền đồng";
    
    public OBJ_Coin_Bronze(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        
        type = type_pickupOnly;
        name = objName;
        value = 1;
        S1 = setup("/res/objects/coin_bronze", ogp.tileSizeW,ogp.tileSizeH);
    }
    public boolean use(Entity entity){
        
        ogp.playSE(1);
        ogp.ui.addMessage("Đồng +" + value);
        ogp.player.coin += value;
        return true;
    }
}
