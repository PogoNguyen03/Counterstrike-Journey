/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oneplayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class Config {

    O_GamePanel ogp;
    public Config(O_GamePanel ogp) {
        this.ogp = ogp;
    }
    
    public void saveConfig(){
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/res/Config/config.txt"));
        
        if (ogp.fullScreenOn == true) {
            bw.write("On");
        }
            
        if (ogp.fullScreenOn == false) {
            bw.write("Off");
        }
        bw.newLine();
        
        // Music volume
        bw.write(String.valueOf(ogp.music.volumeScale));
        bw.newLine();
            
        // SE volume
        bw.write(String.valueOf(ogp.se.volumeScale));
        bw.newLine();
            
        bw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
    public void loadConfig(){
        try {
        BufferedReader br = new BufferedReader(new FileReader("src/res/Config/config.txt"));
        String s = br.readLine();
            
            //Full screen
            if(s.equals("Mở")){
                ogp.fullScreenOn = true;
            }
            if(s.equals("Tắt")){
                ogp.fullScreenOn = false;
            }
            
            //Music volume
            s = br.readLine();
            ogp.music.volumeScale = Integer.valueOf(s);
            
            //Music volume
            s = br.readLine();
            ogp.se.volumeScale = Integer.valueOf(s);
            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
