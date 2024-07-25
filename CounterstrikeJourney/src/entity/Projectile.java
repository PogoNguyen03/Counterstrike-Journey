package entity;

import oneplayer.O_GamePanel;

public class Projectile extends Entity{
    Entity user;
    
    public Projectile(O_GamePanel ogp) {
        super(ogp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive,Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    public void update(){
        
        if(user == ogp.player){
            int monterIndex = ogp.cChecker.checkEntity(this, ogp.monster);
            if(monterIndex != 999){
                ogp.player.damageMonster(monterIndex,this ,attack, knockBackPower);
                // bị trống dữ liệu những không biết nằm đâu tạm thời chạy đỡ 
                generateParticle(user.projectile, ogp.monster[ogp.currentMap][monterIndex]);
                alive = false;
            }
        }
        
        if(user != ogp.player){
            boolean contactPlayer = ogp.cChecker.checkPlayer(this);
            if(ogp.player.invincible == false && contactPlayer == true){
                damagePlayer1(attack);
                generateParticle(user.projectile,user.projectile);
                alive = false;
            }
        }
        
        switch(direction){
            case "W" -> worldY -= speed;
            case "S" -> worldY += speed;
            case "A" -> worldX -= speed;
            case "D" -> worldX += speed;
        }
        
        life--;
        if(life <=0){
            alive = false;
        }
        spiteCounter++;
        if(spiteCounter > 12){
            if(spiteNum == 1){
                spiteNum = 2;
            }
            else if(spiteNum == 2){
                spiteNum = 1;
            }
            spiteCounter = 0;
        }
    }
    public boolean haveResource(Entity user){
        
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user){
    }
}
