package tilemap;

import oneplayer.O_GamePanel;
import TwoPlayer.T_GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import jdk.jshell.execution.Util;
import oneplayer.UtililyTool;

public class TileManager {

    T_GamePanel gp;
    O_GamePanel ogp;
    public Tile[] tile;
    public int mapTileNum[][][];
    boolean drawPath = true;

    //2Player
    public TileManager(final T_GamePanel gp) {
        this.gp = gp;
        this.tile = new Tile[50];
        ogp.getClass();
        ogp.getClass();
          this.mapTileNum = new int[ogp.maxMap][ogp.maxScreenCol][ogp.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/world02.txt",0);
        loadMap("/res/maps/interior01.txt",1);
    }

    //1Player
    public TileManager(O_GamePanel ogp) {
        this.ogp = ogp;
        this.tile = new Tile[50];
        this.mapTileNum = new int[ogp.maxMap][50][50];
        
        getTileImage();
        loadMap1P("/res/maps/world02.txt",0);
        loadMap1P("/res/maps/interior01.txt",1);
        loadMap1P("/res/maps/dungeon01.txt",2);
        loadMap1P("/res/maps/dungeon02.txt",3);
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass00.png"));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/grass01.png"));
           
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water00.png"));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water01.png"));
            tile[13].collision = true;
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water02.png"));
            tile[14].collision = true;
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water03.png"));
            tile[15].collision = true;
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water04.png"));
            tile[16].collision = true;
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water05.png"));
            tile[17].collision = true;
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water06.png"));
            tile[18].collision = true;
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water07.png"));
            tile[19].collision = true;
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water08.png"));
            tile[20].collision = true;
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water09.png"));
            tile[21].collision = true;
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water10.png"));
            tile[22].collision = true;
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water11.png"));
            tile[23].collision = true;
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water12.png"));
            tile[24].collision = true;
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/water13.png"));
            tile[25].collision = true;
            
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road00.png"));
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road01.png"));
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road02.png"));
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road03.png"));
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road04.png"));
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road05.png"));
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road06.png"));
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road07.png"));
            tile[34] = new Tile();
            tile[34].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road08.png"));
            tile[35] = new Tile();
            tile[35].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road09.png"));
            tile[36] = new Tile();
            tile[36].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road10.png"));
            tile[37] = new Tile();
            tile[37].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road11.png"));
            tile[38] = new Tile();
            tile[38].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/road12.png"));
            
            //này dùng làm hầm ngục 
            tile[39] = new Tile();
            tile[39].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/earth.png"));
            tile[47] = new Tile();
            tile[47].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/wall_1.png"));
            tile[47].collision = true;

            
            
            
            
            tile[40] = new Tile();
            tile[40].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/wall.png"));
            tile[40].collision = true;

            

            
            tile[41] = new Tile();
            tile[41].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/tree.png"));
            tile[41].collision = true;

            
            
            tile[42] = new Tile();
            tile[42].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/hut.png"));
            tile[42].collision = false;
            tile[43] = new Tile();
            tile[43].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/floor01.png"));
            tile[43].collision = false;
            tile[44] = new Tile();
            tile[44].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/table01.png"));
            tile[44].collision = true;

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/036.png"));
            tile[45].collision = false;
            tile[46] = new Tile();
            tile[46].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/037.png"));
            tile[46].collision = false;
            
            
            //Hố bẫy pit
            tile[48] = new Tile();
            tile[48].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tileset/pit.png"));

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(this.getClass().getResourceAsStream("/res/tiles_interactive/trunk.png"));
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

//        setUp(0, "grass", false);
//        setUp(1, "wall", true);
//        setUp(2, "water", true);
//        setUp(3, "earth", false);
//        setUp(4, "tree", true);
//        setUp(5, "sand", false);
    }

    
    public void setUp(int index, String imageName, boolean collision) {
        UtililyTool uTool = new UtililyTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tileset/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSizeW, gp.tileSizeH);
            tile[index].collision = collision;
        } catch (IOException e) {
        }
        

    }

    //2Player
    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[ogp.currentMap][col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSizeW, gp.tileSizeH, null);
            col++;
            x += gp.tileSizeW;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSizeH;
            }
        }

    }

    //1Player
    public void loadMap1P(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < ogp.maxWorldCol && row < ogp.maxWorldRow) {
                String line = br.readLine();
                while (col < ogp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == ogp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException | NumberFormatException e) {

        }
    }

    public void draw1P(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < ogp.maxWorldCol && worldRow < ogp.maxWorldRow) {
            int tileNum = mapTileNum[ogp.currentMap][worldCol][worldRow];
            int worldX = worldCol * ogp.tileSizeW;
            int worldY = worldRow * ogp.tileSizeH;
            int screenX = worldX - ogp.player.worldX + ogp.player.screenX;
            int screenY = worldY - ogp.player.worldY + ogp.player.screenY;
            if (worldX + ogp.tileSizeW > ogp.player.worldX - ogp.player.screenX
                    && worldX - ogp.tileSizeW < ogp.player.worldX + ogp.player.screenX
                    && worldY + ogp.tileSizeH > ogp.player.worldY - ogp.player.screenY
                    && worldY - ogp.tileSizeH < ogp.player.worldY + ogp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY,ogp.tileSizeW,ogp.tileSizeH,null);
            }

            worldCol++;

            if (worldCol == ogp.maxWorldCol) {
                worldCol = 0;
                worldRow++;

            }
        }

//        if(drawPath == true){
//            g2.setColor(new Color(255,0,0,70));
//            
//            for (int i = 0; i < ogp.pFinder.pathList.size(); i++){
//                int worldX = ogp.pFinder.pathList.get(i).col * ogp.tileSizeH;
//                int worldY = ogp.pFinder.pathList.get(i).row * ogp.tileSizeW;
//                int screenX = worldX - ogp.player.worldX + ogp.player.screenX;
//                int screenY = worldY - ogp.player.worldY + ogp.player.screenY;
//                
//                g2.fillRect(screenX, screenY, ogp.tileSizeW, ogp.tileSizeH);
//            }
//        }
    }

}
