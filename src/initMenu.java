import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class initMenu  {


    /*JMenu*/
    JMenuBar jMenuBar = new JMenuBar();
    JMenu functionJMenu = new JMenu("功能");

    JMenuItem replay = new JMenuItem("没有功能哦😘");

    public String testName;
    public String[] list;
    public JComboBox<String> songList = new JComboBox<>(); //comboBox


    public initMenu()  {
        JFrame jFrame = new JFrame("Version2.0");
        jFrame.setSize(500, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        //jFrame.setAlwaysOnTop(true); 设置界面置顶(不需要)
        /*图片*/
        ImageIcon bg = new ImageIcon("img/ava1.jpg");
        JLabel label = new JLabel(bg);
        label.setBounds(200,0,bg.getIconWidth(),bg.getIconHeight());
        jFrame.add(label);


        /*ComboBox*/

        String path = "songList";
        File f = new File(path);
        if(!f.exists()){
            System.out.println(path+"不存在!");
            return;
        }
        File[] fa = f.listFiles();
        assert fa !=null; //断言
        list = new String[fa.length];
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            list[i] = fs.getName();
        }
        songList = new JComboBox<>(list);
        songList.setBounds(0,0,100,40);





        /*JButton*/
        JButton jButton = new JButton("开始演奏");
        jButton.setBounds(200, 385, 100, 30);

        /*JButton的监听器*/
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击");
                testName = Objects.requireNonNull(songList.getSelectedItem()).toString();
                System.out.println(testName);
                try {
                    musicPlay mp = new musicPlay(testName);
                    Thread t = new Thread(mp,"线程1");
                    t.start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });


        /*组件拼装*/

        jFrame.add(jButton);
        jFrame.add(songList);
        jFrame.setVisible(true);
        functionJMenu.add(replay);
        jMenuBar.add(functionJMenu);
        jFrame.setJMenuBar(jMenuBar);
    }

}
