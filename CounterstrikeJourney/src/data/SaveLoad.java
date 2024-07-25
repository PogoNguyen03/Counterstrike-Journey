/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_lantern;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class SaveLoad {
    
    O_GamePanel ogp;

    public SaveLoad(O_GamePanel ogp) {
        
        this.ogp = ogp;
        
    }
    

    
    public void save(){
        
        try{
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            //PLAYER STATS
            ds.level = ogp.player.level;
            ds.maxLife = ogp.player.maxLife;
            ds.life = ogp.player.life;
            ds.maxMana = ogp.player.maxMana;
            ds.mana = ogp.player.mana;
            ds.strength = ogp.player.strength;
            ds.dexterity = ogp.player.dexterity;
            ds.exp = ogp.player.exp;
            ds.nextLevelExp = ogp.player.nextLevelExp;
            ds.coin = ogp.player.coin;
            
            //PLAYER INVENTORY
            for(int i = 0; i < ogp.player.inventory.size(); i++){
                ds.itemNames.add(ogp.player.inventory.get(i).name);
                ds.itemAmounts.add(ogp.player.inventory.get(i).amount);
            }
            
            //PLAYER EQUIPMENT
            ds.currentWeaponSlot = ogp.player.getCurrentWeaponSlot();
            ds.currentShildSlot = ogp.player.getCurrentShieldSlot();
            
            //OBJ ON MAP
            ds.mapObjectNames = new String[ogp.maxMap][ogp.obj[1].length];
            ds.mapObjectWorldX = new int[ogp.maxMap][ogp.obj[1].length];
            ds.mapObjectWorldY = new int[ogp.maxMap][ogp.obj[1].length];
            ds.mapObjectLootNames = new String[ogp.maxMap][ogp.obj[1].length];
            ds.mapObjectOpened = new boolean [ogp.maxMap][ogp.obj[1].length];
            
            for(int mapNum = 0; mapNum < ogp.maxMap; mapNum++){
                
                for(int i = 0; i < ogp.obj[1].length; i++){
                    
                    if(ogp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else{
                        ds.mapObjectNames[mapNum][i] = ogp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = ogp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = ogp.obj[mapNum][i].worldY;
                        if(ogp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootNames[mapNum][i] = ogp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = ogp.obj[mapNum][i].opened;
                    }
                }
            }
            
            //viết DataStorage object
            oos.writeObject(ds);
        }
        catch(Exception e){
            System.out.println("Save Exception!");
        }
    }
    
    public void load(){
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            //đọc DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            
            //PLAYER STATS
            ogp.player.level = ds.level;
            ogp.player.maxLife = ds.maxLife;
            ogp.player.life = ds.life;
            ogp.player.maxMana = ds.maxMana;
            ogp.player.mana = ds.mana;
            ogp.player.strength = ds.strength;
            ogp.player.dexterity = ds.dexterity;
            ogp.player.exp = ds.exp;
            ogp.player.nextLevelExp = ds.nextLevelExp;
            ogp.player.coin = ds.coin;
            
            //PLAYER INVENTORY
            ogp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                ogp.player.inventory.add(ogp.eGenerator.getObject(ds.itemNames.get(i)));
                ogp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }
            
            //PLAYER EQUIPMENT
            ogp.player.currentWeapon =ogp.player.inventory.get(ds.currentWeaponSlot);
            ogp.player.currentShield =ogp.player.inventory.get(ds.currentShildSlot);
            ogp.player.getAttackP1();
            ogp.player.getDefense();
            ogp.player.getImageP1();
            
            //OBJ ON MAP
            for(int mapNum = 0; mapNum < ogp.maxMap; mapNum++){
                
                for(int i = 0; i < ogp.obj[1].length; i++){
                    
                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        ogp.obj[mapNum][i] = null;
                    }
                    else{
                        ogp.obj[mapNum][i] = ogp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        ogp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        ogp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if(ds.mapObjectLootNames[mapNum][i] != null){
                            ogp.obj[mapNum][i].setLoot(ogp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
                        }
                        ogp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(ogp.obj[mapNum][i].opened == true){
                            ogp.obj[mapNum][i].S1 = ogp.obj[mapNum][i].image2;
                        }
                    }
                }
            }
            
        }
        catch(Exception e){
            System.out.println("Load Exception!");
        }
    }
}
