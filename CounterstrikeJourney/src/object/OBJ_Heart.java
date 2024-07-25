
package object;


import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Heart extends Entity {
    O_GamePanel ogp;
    
    public static final String objName = "Sinh lực";
    
    public OBJ_Heart(O_GamePanel ogp) {   
        super(ogp);
        this.ogp = ogp; 
        
        type = type_pickupOnly;
        name = objName;
        value = 2;
        S1 = setup("/res/objects/heart_full", ogp.tileSizeW,ogp.tileSizeH);
        image = setup("/res/objects/heart_full", ogp.tileSizeW,ogp.tileSizeH);
        image2 = setup("/res/objects/heart_half", ogp.tileSizeW,ogp.tileSizeH);
        image3 = setup("/res/objects/heart_blank", ogp.tileSizeW,ogp.tileSizeH);
    }
    public boolean use(Entity entity){
        
        ogp.playSE(2);
        ogp.ui.addMessage("Sinh lực +" + value);
        entity.life += value;
//        if(entity.life > entity.maxLife){
//            entity.life = entity.maxLife;
//        }
        return true;
    }
}
