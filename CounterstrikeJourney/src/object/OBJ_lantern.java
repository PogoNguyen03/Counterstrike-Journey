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
public class OBJ_lantern extends Entity{
    
    public static final String objName = "Đèn lồng";
    
    public OBJ_lantern(O_GamePanel ogp) {
        super(ogp);
        
        type = type_light;
        name = objName;
        S1 = setup("/res/objects/lantern", ogp.tileSizeW,ogp.tileSizeH);
        description = "[" + name + "]\nBạn sẽ cần chúng ban đêm.";
        price = 200;
        lightRadius = 250;
    }
    
    
}
