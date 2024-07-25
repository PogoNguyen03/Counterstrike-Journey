package oneplayer;

import entity.Entity;
import java.awt.Rectangle;
import oneplayer.O_GamePanel;

public class CollisionChecker {

    O_GamePanel ogp;

    public CollisionChecker(O_GamePanel ogp) {
        this.ogp = ogp;
    }

    //kiem tra nhan vat co va cham vs 1 block khac khong(dia hin&quai&NPC)
    public void checkTile(Entity entity) {
        //xac dinh x y la toa do vung va cham cua nhan vat
        //xac dinh 4 canh cua vung va cham
        final int entityLeftWorldX = entity.worldX + entity.solidArea.x;//leftX
        final int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;//rightX
        final int entityTopWorldY = entity.worldY + entity.solidArea.y;//topY
        final int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;//bottomY

        int entityLeftCol = entityLeftWorldX / ogp.tileSizeW;
        int entityRightCol = entityRightWorldX / ogp.tileSizeW;
        int entityTopRow = entityTopWorldY / ogp.tileSizeH;
        int entityBottomRow = entityBottomWorldY / ogp.tileSizeH;

        int tileNum1, tileNum2;
        
        //Sử dụng tamporal hướng khi bạn bị đẩy lùi
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
        
        switch (entity.direction) {
            case "W" -> {//neu di len thi check 2 huong top trai va top phai
                entityTopRow = (entityTopWorldY - entity.speed) / ogp.tileSizeH;
                tileNum1 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityTopRow];//sửa
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityTopRow];//sửa
                //neu block tiep theo la block va cham thi bat vung va cham cua nhan vat
                //neu 1 trong 2 (nv va block)la true ->dang cham 1 block va cham
                //va khong cho di theo huong nay
                if (ogp.tileM.tile[tileNum1].collision == true || ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "S" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / ogp.tileSizeH;
                tileNum1 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityBottomRow];//sửa
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityBottomRow];//sửa
                if (ogp.tileM.tile[tileNum1].collision == true || ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "A" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / ogp.tileSizeW;
                tileNum1 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityTopRow];//sửa
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityBottomRow];//sửa
                if (ogp.tileM.tile[tileNum1].collision == true || ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "D" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / ogp.tileSizeW;
                tileNum1 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityTopRow];//sửa
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityBottomRow];//sửa
                if (ogp.tileM.tile[tileNum1].collision == true || ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "AW" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / ogp.tileSizeH;
                entityLeftCol = (entityLeftWorldX - entity.speed) / ogp.tileSizeW;
                tileNum1 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityTopRow];//sửa
                // tileNum2=ogp.tileM.mapTileNum[]
                if (ogp.tileM.tile[tileNum1].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "WD" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / ogp.tileSizeH;
                entityRightCol = (entityRightWorldX + entity.speed) / ogp.tileSizeW;
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityTopRow];//sửa
                if (ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "SA" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / ogp.tileSizeW;
                entityBottomRow = (entityBottomWorldY + entity.speed) / ogp.tileSizeH;
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityLeftCol][entityBottomRow];//sửa
                if (ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            case "SD" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / ogp.tileSizeW;
                entityBottomRow = (entityBottomWorldY + entity.speed) / ogp.tileSizeH;
                tileNum2 = ogp.tileM.mapTileNum[ogp.currentMap][entityRightCol][entityBottomRow];//sửa
                if (ogp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
            }
            default -> {
            }
        }

    }

    public int checkObject(final Entity entity, final boolean player) {
        int index = 999;
        
        //Sử dụng tamporal hướng khi bạn bị đẩy lùi
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
        
        for (int i = 0; i < ogp.obj[1].length; i++) {//sửa
            if (ogp.obj[ogp.currentMap][i] != null) {//sửa
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                //Get obj's solid area position
                ogp.obj[ogp.currentMap][i].solidArea.x += ogp.obj[ogp.currentMap][i].worldX;//sửa
                ogp.obj[ogp.currentMap][i].solidArea.y += ogp.obj[ogp.currentMap][i].worldY;//sửa
                
                switch (direction) {

                    case "W" -> {//neu di len thi check 2 huong top trai va top phai
                        entity.solidArea.y -= entity.speed;

                        //Use Intersects method of Rectangle class: automatic checks if these two rectangle are colliding or not 
                        //To use this method: needed to find out these solidArea's current position
                        //(2 rectangle: entity and obj)
                        //Sự khác biệt với method checkTile: dùng intersects thì code sẽ tiện và nhanh hơn
                        //Nhưng không dùng ở checkTile vì đối với objs chỉ cần scan một số lượng obj nhất định (10)
                        //Nó không quá nhiều để chạy vòng lặp, còn với tile background thì phải lặp qua toàn bộ map cho mỗi lần di chuyển
                        //so với cách code checkTile ở trên là chỉ càn check 2 tile cho mỗi hướng di chyển thì cách trên tốt hơn dùng intersects 
                        break;
                    }
                    case "S" -> {
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                    case "A" -> {
                        entity.solidArea.x -= entity.speed;
                        break;
                    }
                    case "D" -> {
                        entity.solidArea.x += entity.speed;
                        break;
                    }
                    case "AW" -> {
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    }
                    case "WD" -> {
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    }
                    case "SA" -> {
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                    case "SD" -> {
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                }
                if (entity.solidArea.intersects(ogp.obj[ogp.currentMap][i].solidArea)) {//sửa
                    if (ogp.obj[ogp.currentMap][i].collision == true) {//sửa
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                ogp.obj[ogp.currentMap][i].solidArea.x = ogp.obj[ogp.currentMap][i].solidAreaDefaultX;//sửa
                ogp.obj[ogp.currentMap][i].solidArea.y = ogp.obj[ogp.currentMap][i].solidAreaDefaultY;//sửa
            }
        }
        return index;
    }

    //Check NPC or MONSTER
    public int checkEntity(Entity entity, Entity[][] target) {//sửa
        int index = 999;
        
        //Sử dụng tamporal hướng khi bạn bị đẩy lùi
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }
        
        for (int i = 0; i < target[1].length; i++) {//sửa
            if (target[ogp.currentMap][i] != null) {//sửa
                //Get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                //Get obj's solid area position
                target[ogp.currentMap][i].solidArea.x += target[ogp.currentMap][i].worldX;//sửa
                target[ogp.currentMap][i].solidArea.y += target[ogp.currentMap][i].worldY;//sửa
                switch (direction) {
                    case "W" -> {
                        entity.solidArea.y -= entity.speed;
                        break;
                    }
                    case "S" -> {
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                    case "A" -> {
                        entity.solidArea.x -= entity.speed;
                        break;
                    }
                    case "D" -> {
                        entity.solidArea.x += entity.speed;
                        break;
                    }
                    case "AW" -> {
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    }
                    case "WD" -> {
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y -= entity.speed;
                        break;
                    }
                    case "SA" -> {
                        entity.solidArea.x -= entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                    case "SD" -> {
                        entity.solidArea.x += entity.speed;
                        entity.solidArea.y += entity.speed;
                        break;
                    }
                }
                if (entity.solidArea.intersects(target[ogp.currentMap][i].solidArea)) {
                    if(target[ogp.currentMap][i] != entity){
                         entity.collisionOn = true;
                    index = i;
                    }
                   
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[ogp.currentMap][i].solidArea.x = target[ogp.currentMap][i].solidAreaDefaultX;
                target[ogp.currentMap][i].solidArea.y = target[ogp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    //Không cho NPC đi qua player
    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;
        //Get entity's solid area position
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;

        //Get obj's solid area position
        ogp.player.solidArea.x += ogp.player.worldX;
        ogp.player.solidArea.y += ogp.player.worldY;
        switch (entity.direction) {
            case "W" -> {
                entity.solidArea.y -= entity.speed;
                break;
            }
            case "S" -> {
                entity.solidArea.y += entity.speed;
                break;
            }
            case "A" -> {
                entity.solidArea.x -= entity.speed;
                break;
            }
            case "D" -> {
                entity.solidArea.x += entity.speed;
                break;
            }
            case "AW" -> {
                entity.solidArea.x -= entity.speed;
                entity.solidArea.y -= entity.speed;
                break;
            }
            case "WD" -> {
                entity.solidArea.x += entity.speed;
                entity.solidArea.y -= entity.speed;
                break;
            }
            case "SA" -> {
                entity.solidArea.x -= entity.speed;
                entity.solidArea.y += entity.speed;
                break;
            }
            case "SD" -> {
                entity.solidArea.x += entity.speed;
                entity.solidArea.y += entity.speed;
                break;
            }
        }
        if (entity.solidArea.intersects(ogp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        ogp.player.solidArea.x = ogp.player.solidAreaDefaultX;
        ogp.player.solidArea.y = ogp.player.solidAreaDefaultY;
        return contactPlayer;
    }

    public void checkObject(Entity aThis, Entity[][] npc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
