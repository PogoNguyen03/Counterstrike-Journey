
package object;


import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Door extends Entity
{
    O_GamePanel ogp;
    
    public static final String objName = "Door";
    
    public OBJ_Door(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        
        type = type_obstacle;
        name = objName;
        S1 = setup("/res/objects/door", ogp.tileSizeW,ogp.tileSizeH);
        this.collision = true;
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width  = 48;
        solidArea.height  = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Bạn cần chìa khóa để mở cửa";
    }
    
    public void interact(){
        
        startDialogue(this,0);
//        ogp.gameState = ogp.dialogueState;
//        ogp.ui.currentDialogue = "Bạn cần chìa khóa để mở cửa";
    }
}
