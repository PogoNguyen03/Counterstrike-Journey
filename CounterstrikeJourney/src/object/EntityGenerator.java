/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import oneplayer.O_GamePanel;

/**
 *
 * @author LENOVO
 */
public class EntityGenerator {
    O_GamePanel ogp;

    public EntityGenerator(O_GamePanel ogp) {
        this.ogp = ogp;
    }
    
        public Entity getObject(String itemName){
        
        Entity obj = null;
        
        switch(itemName){
            case OBJ_Axe.objName: obj = new OBJ_Axe(ogp); break;
            case OBJ_Boots.objName: obj = new OBJ_Boots(ogp); break;
            case OBJ_Key.objName: obj = new OBJ_Key(ogp); break;
            case OBJ_Door.objName: obj = new OBJ_Door(ogp); break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(ogp); break;
            case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(ogp); break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(ogp); break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(ogp); break;
            case OBJ_Tent.objName: obj = new OBJ_Tent(ogp); break;
            case OBJ_lantern.objName: obj = new OBJ_lantern(ogp); break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(ogp); break;
            case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(ogp); break;
            case OBJ_Fireball.objName: obj = new OBJ_Fireball(ogp); break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(ogp); break;
            case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(ogp); break;
            case OBJ_Rock.objName: obj = new OBJ_Rock(ogp); break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(ogp); break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(ogp); break;
        }
        return obj;
    }
    
}
