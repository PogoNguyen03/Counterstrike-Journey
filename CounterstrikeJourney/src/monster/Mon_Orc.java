/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class Mon_Orc extends Entity{
    O_GamePanel ogp;
    public Mon_Orc(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        name = "Orc";
        direction = "S";
        type = type_monster;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 10;
        defense = 10;
        exp = 10;
        knockBackPower = 5;
        
 
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height= 40;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;
//        
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
        getMonsImage();
        getAttackImage();  
        }
    
    public void getMonsImage(){
        this.W1 = setup("/res/monster/orc_up_1", ogp.tileSizeW,ogp.tileSizeH);
        this.W2 = setup("/res/monster/orc_up_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.S1 = setup("/res/monster/orc_down_1", ogp.tileSizeW,ogp.tileSizeH);
        this.S2 = setup("/res/monster/orc_down_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.D1 = setup("/res/monster/orc_right_1", ogp.tileSizeW,ogp.tileSizeH);
        this.D2 = setup("/res/monster/orc_right_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.A1 = setup("/res/monster/orc_left_1", ogp.tileSizeW,ogp.tileSizeH);
        this.A2 = setup("/res/monster/orc_left_2", ogp.tileSizeW,ogp.tileSizeH);
        
    }
    
    public void getAttackImage(){
        
        this.D1_AT = setup("/res/monster/orc_attack_right_1", ogp.tileSizeW*2,ogp.tileSizeH);
        this.D2_AT = setup("/res/monster/orc_attack_right_2", ogp.tileSizeW*2,ogp.tileSizeH);
        this.A1_AT = setup("/res/monster/orc_attack_left_1", ogp.tileSizeW*2,ogp.tileSizeH);
        this.A2_AT = setup("/res/monster/orc_attack_left_2", ogp.tileSizeW*2,ogp.tileSizeH);
        this.S1_AT = setup("/res/monster/orc_attack_down_1", ogp.tileSizeW,ogp.tileSizeH*2);
        this.S2_AT = setup("/res/monster/orc_attack_down_2", ogp.tileSizeW,ogp.tileSizeH*2);
        this.W1_AT = setup("/res/monster/orc_attack_up_1", ogp.tileSizeW,ogp.tileSizeH*2);
        this.W2_AT = setup("/res/monster/orc_attack_up_2", ogp.tileSizeW,ogp.tileSizeH*2);
    }
    
//    public void update(){
//        
//        super.update();
//        
//        int xDistance = Math.abs(worldX - ogp.player.worldX);
//        int yDistance = Math.abs(worldY - ogp.player.worldY);
//        int tileDistance = (xDistance + yDistance)/ogp.tileSizeW;
//        
//        if(onPath == false && tileDistance < 5){
//            
//            int i = new Random().nextInt(100) + 1;
//            if( i > 50){
//                onPath = true;
//            }
//        }
//        if(onPath == true && tileDistance > 20){
//            onPath = false;
//        }
//    }
    
    public void setAction(){
//        
//        int xDistance = Math.abs(worldX - ogp.player.worldX);
//        int yDistance = Math.abs(worldY - ogp.player.worldY);
//        int tileDistance = (xDistance + yDistance)/ogp.tileSizeW;
//        
        if(onPath == true){
            
            //Kiểm tra nếu nó dừng
            checkStopChasingOrNot(ogp.player,15,100);
//            if(tileDistance > 20){
//                onPath = false;
//            }   
            //tìm kiếm hướng để đi
//            int goalCol = (ogp.player.worldX + ogp.player.solidArea.x)/ogp.tileSizeW;
//            int goalRow = (ogp.player.worldY + ogp.player.solidArea.y)/ogp.tileSizeH;
            
            searchPath(getGoalCol(ogp.player), getGoalRow(ogp.player));
            

        }
        else{
            //Kiểm tra bắt đầu có thay đổi không
//            if(tileDistance < 5){
//            
//                int i = new Random().nextInt(100) + 1;
//                if( i > 50){
//                    onPath = true;
//                }
//            }
            checkStartChasingOrNot(ogp.player, 5 ,100);
            //tạo random hướng
//            actionLockCounter++;
//            if (actionLockCounter == 120) {
//                Random random = new Random();
//                int i = random.nextInt(100) + 1;//from 1 to 100
//                if (i <= 25) {
//                    direction = "W";
//                } else if (i <= 50) {
//                    direction = "S";
//                } else if (i <= 75) {
//                    direction = "A";
//                } else if (i <= 100) {
//                    direction = "D";
//                }
//                actionLockCounter = 0;
//            }
            getRandomDirection(120);
        }  
        
        //kiểm tra tấn công
        if(attacking == false){
            checkAttackOrNot(30, ogp.tileSizeW*4, ogp.tileSizeH);
        }
    }
    public void damageReaction(){
        //đặt bộ đếm
        actionLockCounter = 0;
         // Tạo đối tượng Random
        Random random = new Random();

        // Mảng chứa các chữ cái WASD
        char[] letters = {'W', 'A', 'S', 'D'};

        // Sinh số ngẫu nhiên trong khoảng từ 0 đến 3
        int randomIndex = random.nextInt(4);

        // Lấy chữ cái tương ứng từ mảng
        String randomLetter = Character.toString(letters[randomIndex]);
        
        direction = randomLetter;
        onPath = true;
    }
    public void checkDrop(){
        
        // CAST A DIE
        int i = new Random().nextInt(100)+1;
        
        // SET THE MONSTER DROP
        //tỉ lệ rơi đồ 
        if(i < 50){
            dropItem(new OBJ_Coin_Bronze(ogp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(ogp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_ManaCrystal(ogp));
        }
    }
}
