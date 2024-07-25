package oneplayer;

import data.Progress;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.Mon_Bat;
import monster.Mon_Greenslime;
import monster.Mon_Orc;
import monster.Mon_SkeletonLord;
import object.OBJ_Axe;
import object.OBJ_BlueHeart;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Tent;
import object.OBJ_lantern;
import tiles_interactive.IT_DestructibleWall;
import tiles_interactive.IT_DryTree;


public class AssetSetter {

    O_GamePanel ogp;

    public AssetSetter(final O_GamePanel ogp) {
        this.ogp = ogp;
    }

    //Instantiate (khởi tạo) some default objs and place on the map
    public void setObject() {
        int mapNum = 0;
        //Set vị trí cho key
        int i = 0;
        ogp.obj[mapNum][i] = new OBJ_Key(ogp);
        ogp.obj[mapNum][i].worldX = 23 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 7 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Coin_Bronze(ogp);
        ogp.obj[mapNum][i].worldX = 23 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 40 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Key(ogp);
        ogp.obj[mapNum][i].worldX = 37 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 7 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Axe(ogp);
        ogp.obj[mapNum][i].worldX = 26 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 11 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Shield_Blue(ogp);
        ogp.obj[mapNum][i].worldX = 37 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 28 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Potion_Red(ogp);
        ogp.obj[mapNum][i].worldX = 37 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 43 * ogp.tileSizeH;
        i++;
         ogp.obj[mapNum][i] = new OBJ_Potion_Red(ogp);
        ogp.obj[mapNum][i].worldX = 10 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 29* ogp.tileSizeH;
        i++;
         ogp.obj[mapNum][i] = new OBJ_Potion_Red(ogp);
        ogp.obj[mapNum][i].worldX = 38 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 10 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Heart(ogp);
        ogp.obj[mapNum][i].worldX = 33 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 20 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_ManaCrystal(ogp);
        ogp.obj[mapNum][i].worldX = 9 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 11 * ogp.tileSizeH;
        i++;
//              //Set vị trí cho door
        ogp.obj[mapNum][i] = new OBJ_Door(ogp);
        ogp.obj[mapNum][i].worldX =  10 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY =  12 * ogp.tileSizeH;
        i++;

        ogp.obj[mapNum][i] = new OBJ_Door(ogp);
        ogp.obj[mapNum][i].worldX = 12 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 22 * ogp.tileSizeH;
        i++;




//        ogp.obj[mapNum][i] = new OBJ_Door(ogp);
//        ogp.obj[mapNum][i].worldX = 8 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 28 * ogp.tileSizeH;
//        i++;

//        ogp.obj[mapNum][i] = new OBJ_Chest(ogp);
//        ogp.obj[mapNum][i].worldX = 10 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 8 * ogp.tileSizeH;
//        i++;
//        ogp.obj[mapNum][i] = new OBJ_Boots(ogp);
//        ogp.obj[mapNum][i].worldX = 37 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 42* ogp.tileSizeH;
//        i++;
//        ogp.obj[3] = new OBJ_Door(ogp);
//        ogp.obj[3].worldX = 10 * ogp.tileSizeW;
//        ogp.obj[3].worldY = 12 * ogp.tileSizeH;
//
//        ogp.obj[4] = new OBJ_Door(ogp);
//        ogp.obj[4].worldX = 8 * ogp.tileSizeW;
//        ogp.obj[4].worldY = 28 * ogp.tileSizeH;
//
//        ogp.obj[5] = new OBJ_Door(ogp);
//        ogp.obj[5].worldX = 12 * ogp.tileSizeW;
//        ogp.obj[5].worldY = 22 * ogp.tileSizeH;

    //rương kho báu
        ogp.obj[mapNum][i] = new OBJ_Chest(ogp/*, new OBJ_Key(ogp)*/);
        ogp.obj[mapNum][i].setLoot(new OBJ_Key(ogp));
        ogp.obj[mapNum][i].worldX = 30 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 28 * ogp.tileSizeH;
        i++;
        //đèn
        ogp.obj[mapNum][i] = new OBJ_lantern(ogp);
        ogp.obj[mapNum][i].worldX = 22 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 22 * ogp.tileSizeH;
        i++;
        //Lều đi ngủ
        ogp.obj[mapNum][i] = new OBJ_Tent(ogp);
        ogp.obj[mapNum][i].worldX = 23 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 22 * ogp.tileSizeH;
        i++;
        
        //Map 2
        mapNum = 2;
        i = 0;
        ogp.obj[mapNum][i] = new OBJ_Chest(ogp);
        ogp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(ogp));
        ogp.obj[mapNum][i].worldX = 40 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 41 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Chest(ogp);
        ogp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(ogp));
        ogp.obj[mapNum][i].worldX = 13 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 16 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Chest(ogp);
        ogp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(ogp));
        ogp.obj[mapNum][i].worldX = 26 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 34 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_Chest(ogp);
        ogp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(ogp));
        ogp.obj[mapNum][i].worldX = 27 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 15 * ogp.tileSizeH;
        i++;
        
        //cửa săt
        ogp.obj[mapNum][i] = new OBJ_Door_Iron(ogp);
        ogp.obj[mapNum][i].worldX = 28 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 23 * ogp.tileSizeH;
        i++;
        
        //map 3
        mapNum = 3;
        i = 0;
        ogp.obj[mapNum][i] = new OBJ_Door_Iron(ogp);
        ogp.obj[mapNum][i].worldX = 25 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 15 * ogp.tileSizeH;
        i++;
        ogp.obj[mapNum][i] = new OBJ_BlueHeart(ogp);
        ogp.obj[mapNum][i].worldX = 25 * ogp.tileSizeW;
        ogp.obj[mapNum][i].worldY = 8 * ogp.tileSizeH;
        i++;
    }
    public void setNPC(){
        //map 0
        int mapNum = 0;
        int i = 0;
        ogp.npc[mapNum][i]= new NPC_OldMan(ogp);
        ogp.npc[mapNum][i].worldX= 21 * ogp.tileSizeW;
        ogp.npc[mapNum][i].worldY= 21 * ogp.tileSizeH;
        i++;
        
        //map 1
        mapNum = 1;
        i = 0;
        ogp.npc[mapNum][i]= new NPC_Merchant(ogp);
        ogp.npc[mapNum][i].worldX= 12 * ogp.tileSizeW;
        ogp.npc[mapNum][i].worldY= 7 * ogp.tileSizeH;
        i++;
    
        //map 2
        //CỤc đá
//        mapNum = 2;
//        ogp.obj[mapNum][i] = new NPC_BigRock(ogp);
//        ogp.obj[mapNum][i].worldX = 20 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 25 * ogp.tileSizeH;
//        i++;
//        ogp.obj[mapNum][i] = new NPC_BigRock(ogp);
//        ogp.obj[mapNum][i].worldX = 11 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 18 * ogp.tileSizeH;
//        i++;
//        ogp.obj[mapNum][i] = new NPC_BigRock(ogp);
//        ogp.obj[mapNum][i].worldX = 23 * ogp.tileSizeW;
//        ogp.obj[mapNum][i].worldY = 14 * ogp.tileSizeH;
//        i++;
    }
    
    public void setMonster(){
        int mapNum = 0;
        int i = 0;
//        ogp.monster[mapNum][i] = new Mon_Merchant(ogp);
//        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 23;
//        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 12;
//        i++;
        ogp.monster[mapNum][i] = new Mon_Greenslime(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 23;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 37;
        i++;
        ogp.monster[mapNum][i] = new Mon_Greenslime(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 30;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 39;
        i++;
        ogp.monster[mapNum][i] = new Mon_Greenslime(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 25;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 39;
        i++;
        ogp.monster[mapNum][i] = new Mon_Greenslime(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 12;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 32;
        i++;
        ogp.monster[mapNum][i] = new Mon_Greenslime(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 36;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 23;
        i++;
        ogp.monster[mapNum][i] = new Mon_Orc(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 12;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 33;
        i++;
        
        //map 2
        mapNum = 2;
        i++;
        ogp.monster[mapNum][i] = new Mon_Bat(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 34;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 39;
        i++;
        ogp.monster[mapNum][i] = new Mon_Bat(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 36;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 25;
        i++;
        ogp.monster[mapNum][i] = new Mon_Bat(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 39;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 26;
        i++;
        ogp.monster[mapNum][i] = new Mon_Bat(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 28;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 11;
        i++;
        ogp.monster[mapNum][i] = new Mon_Bat(ogp);
        ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 10;
        ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 19;
        i++;
        
        //map 3
        mapNum = 3;
        i++;
        if(Progress.skeletonLordDefeated == false){
            ogp.monster[mapNum][i] = new Mon_SkeletonLord(ogp);
            ogp.monster[mapNum][i].worldX = ogp.tileSizeW * 23;
            ogp.monster[mapNum][i].worldY = ogp.tileSizeH * 16;
            i++;
        }
        
    }
    //tạo cây chặt và đập đá ở côn lôn =))) được ở đây
    public void setInteractiveTiles(){
        //Chặt cây
        int mapNum = 0;
        int i = 0;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,27,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,28,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,29,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,30,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,31,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,32,11);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,33,11);i++;     
        
        
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,31,20);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,31,21);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,31,22);i++;
        
//        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,13,20);i++;
//        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,12,21);i++;
//        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,13,21);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,25,29);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,29);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,30);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,31);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,32);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,33);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,26,34);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,27,34);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,28,34);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,29,34);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,30,34);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,30,33);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,30,32);i++;
        ogp.iTile[mapNum][i] = new IT_DryTree(ogp,30,31);i++;
       
        
        //Đập đá 
        mapNum = 2;
        i = 0;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,18,30);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,17,31);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,17,32);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,17,34);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,18,34);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,18,33);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,10,22);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,10,24);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,38,18);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,38,19);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,38,20);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,38,21);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,18,13);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,18,14);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,22,28);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,30,28);i++;
        ogp.iTile[mapNum][i] = new IT_DestructibleWall(ogp,32,28);i++;
        
        //Nút khởi động cửa sắt
       
//         ogp.iTile[mapNum][i] = new IT_MetalPlate(ogp,20,22);i++;
//         ogp.iTile[mapNum][i] = new IT_MetalPlate(ogp,8,17);i++;
//         ogp.iTile[mapNum][i] = new IT_MetalPlate(ogp,39,31);i++;
    }
}
