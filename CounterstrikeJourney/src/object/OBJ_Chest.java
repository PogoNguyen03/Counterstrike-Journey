// 
// Decompiled by Procyon v0.5.36
// 

package object;


import entity.Entity;
import oneplayer.O_GamePanel;

public class OBJ_Chest extends Entity{
   
    public static final String objName = "Rương bí ẩn";
    
    O_GamePanel ogp;
//    Entity loot;
//    boolean opened = false;
    public OBJ_Chest(O_GamePanel ogp/*, Entity loot*/) {
        super(ogp);
        this.ogp = ogp;
        //this.loot = loot;
        
        type = type_obstacle;
        name = objName;
        image = setup("/res/objects/chest", ogp.tileSizeW,ogp.tileSizeH);
        image2 = setup("/res/objects/chest_opened", ogp.tileSizeW,ogp.tileSizeH);
        S1 = image;
        collision = true;
        
        solidArea.x = 4;
        solidArea.y = 25;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
//        try {
//            image = ImageIO.read(this.getClass().getResourceAsStream("/res/objects/chest.png"));
//            image = uTool.scaleImage(image,ogp.tileSizeW,ogp.tileSizeH);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public void setLoot(Entity loot){
        this.loot = loot;
        
        setDialogue();
    }
    
    public void setDialogue(){
        dialogues[0][0] = "Bạn hãy mở kho báu và tìm thấy " + loot.name +"!\n...Nhưng bạn không thể chứa thêm đồ nữa!";
        dialogues[1][0] = "Bạn hãy mở kho báu và tìm thấy " + loot.name +"!\nBạn nhận được " + loot.name + "!";
        dialogues[2][0] = "Rương trống";
    }
    
    public void interact(){
//        ogp.gameState = ogp.dialogueState;
        if(opened == false){
            ogp.playSE(3);
            
//            StringBuilder sb = new StringBuilder();
//            sb.append("Bạn hãy mở kho báu và tìm thấy " + loot.name +"!");
            
            if(ogp.player.canObtainItem(loot) == false){
                startDialogue(this,0);
//                sb.append("\n...Những bạn không thể chứa thêm đồ nữa!");
            }
            else {
                startDialogue(this,1);
//                sb.append("\nBạn nhận được " + loot.name + "!");
//                ogp.player.inventory.add(loot);
                S1 = image2;
                opened = true;
            }
//            dialogues[0][0] = sb.toString();
//            startDialogue(this,0);
        }
        else{
//            dialogues[1][0] = "Rương trống";
            startDialogue(this,2);
        }
    }
}
