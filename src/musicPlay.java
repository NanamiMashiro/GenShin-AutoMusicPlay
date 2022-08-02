import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class musicPlay implements Runnable {

    String fileName; //文件名
    String path; //路径
    String lyrics; //
    Robot robot;

    /*Robot对象*/
    {
        try {
            robot = new Robot();
        } catch (AWTException e){
            e.printStackTrace();
        }
    }

    public musicPlay(String fileName) throws IOException {
        this.fileName = fileName;
        this.path = "songList/" + fileName;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        String lyrics = stringBuilder.toString();
        reader.close();//释放资源
        this.lyrics = lyrics;
        //System.out.println(lyrics);




    }

    @Override
    public void run() {
        robot.delay(2000); //延迟2s
        for (int i = 0; i < lyrics.length(); i++) {
            if(lyrics.charAt(i)==' '){
                robot.delay(400);
            } else if(lyrics.charAt(i)==','){
                robot.delay(200);
            } else if(lyrics.charAt(i)=='.'){
                robot.delay(350);
            } else if(lyrics.charAt(i)=='/'){
                robot.delay(150);
            }
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(lyrics.charAt(i)));
            robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(lyrics.charAt(i)));
        }
    }
}
