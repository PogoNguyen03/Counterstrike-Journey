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
public class OBJ_Pickaxe extends Entity{
    
   public static final String objName = "Cuốc";
    
    public OBJ_Pickaxe(O_GamePanel ogp) {
        super(ogp);
        
        type = type_pickaxe;
        name = objName;
        S1 = setup("/res/objects/pickaxe", ogp.tileSizeW,ogp.tileSizeH);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "Cuốc mới\nDễ dàng phá đá mở đường!";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 10;
        motion2_duration = 40;
    }
    
}
