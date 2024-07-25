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
public class OBJ_Axe extends Entity{
    
    public static final String objName = "Rìu cầm tay";
    
    public OBJ_Axe(O_GamePanel ogp) {
        super(ogp);
        
        type = type_axe;
        name = objName;
        S1 = setup("/res/objects/axe", ogp.tileSizeW,ogp.tileSizeH);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "Rìu gỗ \n Không quá tốt \n nhưng có thể đốn cây";
        price = 75;
        knockBackPower = 10;
        motion1_duration = 5;
        motion2_duration = 40;
    }
    
}
