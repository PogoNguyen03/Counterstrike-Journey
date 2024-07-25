
package entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import object.OBJ_Door_Iron;
import oneplayer.O_GamePanel;
import tiles_interactive.IT_MetalPlate;
import tiles_interactive.InteractiveTiles;

/**
 *
 * @author LENOVO
 */
public class NPC_BigRock extends Entity{
    
    public  static final String npcName = "Big Rock";
    
    public NPC_BigRock(O_GamePanel ogp) {
        super(ogp);
        
        
        name = npcName;
        direction = "S";
        speed = 4;
        
        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidArea.width = 44;
        solidArea.height= 40;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
        
        
        getNPCImage();
        setDialogue();

    }

    public void getNPCImage() {

            this.A1 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);
            this.A2 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);

            this.D1 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);
            this.D2 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);

            this.S1 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);
            this.S2 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);

            this.W1 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);
            this.W2 = setup("/res/NPC/bigrock", ogp.tileSizeW,ogp.tileSizeH);

    }

    public void setDialogue() {
        dialogues[0][0] = "Đây là một tảng đả khổng lồ";
        
    }

    public void update(){
        
    }
    @Override
    public void setAction() {}

    @Override
    public void speak() {
//        super.speak();
        
        facePlayer();
        startDialogue(this,dialogueSet);
        
        dialogueSet++;
        
        if(dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }
//        onPath = true;
    }
    
    public void move(String d){
        
        this.direction = d;
        
        checkCollision();
        
        if (collisionOn == false) {
                switch (direction) {
                case "W":
                    worldY -= speed;
                    break;
                case "S":
                    worldY += speed;
                    break;
                case "A":
                    worldX -= speed;
                    break;
                case "D":
                    worldX += speed;
                    break;

                }
            }
    }
    
    public void delectPlate(){
        
        ArrayList<InteractiveTiles> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();
        
        //Create a plate list
        for(int i = 0; i < ogp.iTile[1].length; i++){
            if(ogp.iTile[ogp.currentMap][i] != null &&
                    ogp.iTile[ogp.currentMap][i].name != null &&
                    ogp.iTile[ogp.currentMap][i].name.equals(IT_MetalPlate.itName)){
                plateList.add(ogp.iTile[ogp.currentMap][i]);
            }
        }
        //Create a rock list
        for(int i = 0; i < ogp.npc[1].length; i++){
            if(ogp.npc[ogp.currentMap][i] != null &&
                    ogp.npc[ogp.currentMap][i].name.equals(NPC_BigRock.npcName)){
                rockList.add(ogp.npc[ogp.currentMap][i]);
            }
        }
        
        int count = 0;
        // Scan the plate list
        for(int i = 0; i < plateList.size(); i++){
            
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);
            
            if(distance < 8){
                
                if(linkedEntity ==  null){
                    linkedEntity = plateList.get(i);
                    ogp.playSE(3);
                }
            }
            else{
                if(linkedEntity == plateList.get(i)){
                        
                    linkedEntity = null;
                }
            }
        }
        //Scan the rock list
        for(int i = 0; i < rockList.size(); i++){
                
            //Count the rock on the plate
            if(rockList.get(i).linkedEntity != null){
                count++;
            }
        }
        //if all the rocks are on the plates, the iron door opens
        if(count == rockList.size()){
            for(int i = 0; i < ogp.obj[1].length; i++){
                
                if(ogp.obj[ogp.currentMap][i] != null && ogp.obj[ogp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                    
                    ogp.obj[ogp.currentMap][i] = null;
                    ogp.playSE(21);
                }
            }
        }
    }
}
