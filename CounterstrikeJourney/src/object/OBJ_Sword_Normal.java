
package object;

import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Sword_Normal extends Entity {
    
    public static final String objName = "Kiếm thường";
    
    public OBJ_Sword_Normal(O_GamePanel  ogp){
        super(ogp);
        
        type = type_sword;
        name = objName;
        S1 = setup("/res/objects/sword_normal",ogp.tileSizeH,ogp.tileSizeW);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nMột thanh kiếm cũ.";
        price = 20;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
}
