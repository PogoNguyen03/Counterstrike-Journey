
package monster;

import entity.Entity;
import java.util.Random;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import oneplayer.O_GamePanel;

public class Mon_Greenslime extends Entity {
    O_GamePanel ogp;
    public Mon_Greenslime(O_GamePanel ogp) {
        super(ogp);
        this.ogp = ogp;
        name = "Green Slime";
        direction = "S";
        type = type_monster;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 2;
        
        projectile = new OBJ_Rock(ogp);
 
        solidArea.x = 3;
        solidArea.y = 3;
        solidArea.width = 45;
        solidArea.height= 45;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
        
        getMonsImage();
          
        }
    
    public void getMonsImage(){
        this.W1 = setup("/res/monster/greenslime_down_1", ogp.tileSizeW,ogp.tileSizeH);
        this.W2 = setup("/res/monster/greenslime_down_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.S1 = setup("/res/monster/greenslime_down_1", ogp.tileSizeW,ogp.tileSizeH);
        this.S2 = setup("/res/monster/greenslime_down_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.D1 = setup("/res/monster/greenslime_down_1", ogp.tileSizeW,ogp.tileSizeH);
        this.D2 = setup("/res/monster/greenslime_down_2", ogp.tileSizeW,ogp.tileSizeH);
        
        this.A1 = setup("/res/monster/greenslime_down_1", ogp.tileSizeW,ogp.tileSizeH);
        this.A2 = setup("/res/monster/greenslime_down_2", ogp.tileSizeW,ogp.tileSizeH);
        
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
            
            //Kiểm tsa có bắn không
//            int i = new Random().nextInt(100)+1;
//            if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
//                projectile.set(worldX, worldY, direction, true, this);
////                ogp.projectileList.add(projectile);
//                //CHECK VACANCY
//                for(int ii = 0; ii < ogp.projectile[1].length; ii++){
//                    if(ogp.projectile[ogp.currentMap][ii] == null){
//                        ogp.projectile[ogp.currentMap][ii] = projectile;
//                        break;
//                    }
//                }
//                shotAvailableCounter = 0;
//            }
            checkShootOrNot(200, 30);
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
