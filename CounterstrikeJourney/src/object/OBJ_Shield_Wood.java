
package object;

import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Shield_Wood extends Entity {
    
    public static final String objName = "Khiên gỗ cũ";
    
    public OBJ_Shield_Wood(O_GamePanel ogp){
        super(ogp);
    
        type = type_shield;
        name = objName;
        S1 = setup("/res/objects/shield_wood",ogp.tileSizeW,ogp.tileSizeH);
        defenseValue = 1;
        description = "[" + name + "]\nCó còn hơn không";
        price = 35;
    }
   
}
