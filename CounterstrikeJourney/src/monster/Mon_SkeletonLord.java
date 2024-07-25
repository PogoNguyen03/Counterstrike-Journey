/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import data.Progress;
import entity.Entity;
import java.util.Random;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class Mon_SkeletonLord extends Entity{
    O_GamePanel ogp;
    
    public static final String monName = "Skeleton Lord";
    public Mon_SkeletonLord(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        boss = true;
        name = monName;
        direction = "S";
        type = type_monster;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 500;
        life = maxLife;
        attack = 20;
        defense = 20;
        exp = 50;
        knockBackPower = 5;
        sleep = true;
        
 
        int size = ogp.tileSizeW*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height= size - 48;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
        attackArea.width = 170;
        attackArea.height = 170;
        motion1_duration = 25;
        motion2_duration = 50;
//        
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
        getMonsImage();
        getAttackImage();  
        setDialgue();
        }
    
    public void getMonsImage(){
        
        int i = 5;
        if(inRage == false){
            
            this.W1 = setup("/res/monster/skeletonlord_up_1", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.W2 = setup("/res/monster/skeletonlord_up_2", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.S1 = setup("/res/monster/skeletonlord_down_1", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.S2 = setup("/res/monster/skeletonlord_down_2", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.D1 = setup("/res/monster/skeletonlord_right_1", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.D2 = setup("/res/monster/skeletonlord_right_2", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.A1 = setup("/res/monster/skeletonlord_left_1", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.A2 = setup("/res/monster/skeletonlord_left_2", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
        }
        if(inRage == true){
            this.W1 = setup("/res/monster/skeletonlord_phase2_up_1", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.W2 = setup("/res/monster/skeletonlord_phase2_up_2", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.S1 = setup("/res/monster/skeletonlord_phase2_down_1", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.S2 = setup("/res/monster/skeletonlord_phase2_down_2", ogp.tileSizeW*i*2,ogp.tileSizeH*i);
            this.D1 = setup("/res/monster/skeletonlord_phase2_right_1", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.D2 = setup("/res/monster/skeletonlord_phase2_right_2", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.A1 = setup("/res/monster/skeletonlord_phase2_left_1", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
            this.A2 = setup("/res/monster/skeletonlord_phase2_left_2", ogp.tileSizeW*i,ogp.tileSizeH*i*2);
        }
    }
    
    public  void setDialgue(){
        
        dialogues[0][0] = "KẺ NÀO ĐÁNH CẮP KHO BÁU CỦA TA!";
        dialogues[0][1] = "NGƯƠI SẼ CHẾT Ở ĐÂY!";
        dialogues[0][2] = "CHÀO MỪNG NGƯỜI ĐẾN VỚI ĐỊA NGỤC!!!";
    }
    
    public void getAttackImage(){
        
        int i = 5;
        if(inRage == false){
            
            this.W1 = setup("/res/monster/skeletonlord_up_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.W2 = setup("/res/monster/skeletonlord_up_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.S1 = setup("/res/monster/skeletonlord_down_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.S2 = setup("/res/monster/skeletonlord_down_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.D1 = setup("/res/monster/skeletonlord_right_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.D2 = setup("/res/monster/skeletonlord_right_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.A1 = setup("/res/monster/skeletonlord_left_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.A2 = setup("/res/monster/skeletonlord_left_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
        }
        if(inRage == true){
            this.W1 = setup("/res/monster/skeletonlord_phase2_attack_up_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.W2 = setup("/res/monster/skeletonlord_phase2_attack_up_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.S1 = setup("/res/monster/skeletonlord_phase2_attack_down_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.S2 = setup("/res/monster/skeletonlord_phase2_attack_down_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.D1 = setup("/res/monster/skeletonlord_phase2_attack_right_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.D2 = setup("/res/monster/skeletonlord_phase2_attack_right_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.A1 = setup("/res/monster/skeletonlord_phase2_attack_left_1", ogp.tileSizeW*i,ogp.tileSizeH*i);
            this.A2 = setup("/res/monster/skeletonlord_phase2_attack_left_2", ogp.tileSizeW*i,ogp.tileSizeH*i);
        }
        
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
        if(inRage == false && life < maxLife/2){
            inRage = true;
            getMonsImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *=2;
        }
//        int xDistance = Math.abs(worldX - ogp.player.worldX);
//        int yDistance = Math.abs(worldY - ogp.player.worldY);
//        int tileDistance = (xDistance + yDistance)/ogp.tileSizeW;
//        
        if(getTileDistance(ogp.player) < 10){
            
            moveTowardPlayer(60);
        }
        else{
            
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
            checkAttackOrNot(60, ogp.tileSizeW*7, ogp.tileSizeH*5);
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
        
    }
    public void checkDrop(){
        
        ogp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;
        
        //Restore the prvious music
        ogp.stopMusicSound();
        ogp.playMusic(5);
        
        //remove the iron doors
        for(int i = 0; i< ogp.obj[1].length; i++){
            if(ogp.obj[ogp.currentMap][i] != null && ogp.obj[ogp.currentMap][i].name.equals(OBJ_Door_Iron.objName)){
                ogp.playSE(21);
                ogp.obj[ogp.currentMap][i]= null;
            }
        }
        
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
