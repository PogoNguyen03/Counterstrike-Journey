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
public class OBJ_Shield_Blue extends Entity{
    
    public static final String objName = "Khiên mới";
    
    public OBJ_Shield_Blue(O_GamePanel ogp) {
        super(ogp);
        
        type = type_shield;
        name = objName;
        S1 = setup("/res/objects/shield_blue",ogp.tileSizeW,ogp.tileSizeH);
        defenseValue = 2;
        description = "[" + name + "]\nKhiên tốt, phiêu lưu tốt";
        price = 100;
    }
    
}
