package oneplayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 5;
    float volumne;

    public Sound() {
        soundURL[0] = getClass().getResource("/res/music/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/res/music/coin.wav");
        soundURL[2] = getClass().getResource("/res/music/powerup.wav");
        soundURL[3] = getClass().getResource("/res/music/unlock.wav");
        soundURL[4] = getClass().getResource("/res/music/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/music/Adventure.wav");
        soundURL[6] = getClass().getResource("/res/music/hitmonster.wav");
        soundURL[7] = getClass().getResource("/res/music/receivedamage.wav");
        soundURL[8] = getClass().getResource("/res/music/cursor.wav");
        soundURL[9] = getClass().getResource("/res/music/levelup.wav");
        soundURL[10] = getClass().getResource("/res/music/burning.wav");
        soundURL[11] = getClass().getResource("/res/music/cuttree.wav");
        soundURL[12] = getClass().getResource("/res/music/gameover.wav");
        soundURL[13] = getClass().getResource("/res/music/stairs.wav");
        soundURL[14] = getClass().getResource("/res/music/sleep.wav");
        soundURL[15] = getClass().getResource("/res/music/blocked.wav");
        soundURL[16] = getClass().getResource("/res/music/parry.wav");
        soundURL[17] = getClass().getResource("/res/music/speak.wav");
        soundURL[18] = getClass().getResource("/res/music/Merchant.wav");
        soundURL[19] = getClass().getResource("/res/music/Dungeon.wav");
        soundURL[20] = getClass().getResource("/res/music/chipwall.wav");
        soundURL[21] = getClass().getResource("/res/music/dooropen.wav");
        soundURL[22] = getClass().getResource("/res/music/FinalBattle.wav");
    }

    public void setFile(final int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //Float control accept number -80f -> 6f
            checkVolumne();
        } catch (Exception ex) {
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolumne() {
        switch (volumeScale) {
            case 0:
                volumne = -80f;
                break;
            case 1:
                volumne = -20f;
                break;
            case 2:
                volumne = -12f;
                break;
            case 3:
                volumne = -5f;
                break;
            case 4:
                volumne = -1f;
                break;
            case 5:
                volumne = 6f;
                break;
        }
        fc.setValue(volumne);
    }
}
