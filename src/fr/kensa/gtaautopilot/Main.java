package fr.kensa.gtaautopilot;

import fr.theshark34.swinger.util.WindowMover;
import jdk.jshell.execution.Util;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends JFrame {



    Robot r = null;
    static Main instance;
    public boolean enabled = false;
    public boolean paused = false;
    public boolean render = false;

    Thread t = new Thread(){
        @Override
        public void run() {
            while(!this.isInterrupted()) {
                BufferedImage map = Utils.processImg(instance.getRobot().createScreenCapture(Fields.map));
                if(render){
                    instance.getGraphics().drawImage(map,0,0,null);
                }
                if(enabled && !paused) {
                    Color arrowTop = new Color(map.getRGB(Fields.player1.x, Fields.player1.y));
                    Color arrowRight = new Color(map.getRGB(Fields.player1.x+10, Fields.player1.y));
                    Color arrowLeft = new Color(map.getRGB(Fields.player1.x-10, Fields.player1.y));
                    Color arrowVeryRight = new Color(map.getRGB(Fields.player1.x+20, Fields.player1.y));
                    Color arrowVeryLeft = new Color(map.getRGB(Fields.player1.x-20, Fields.player1.y));


                    // System.out.println("red : "+arrowTop.getRed() + " green : "+ arrowTop.getGreen()+ " blue : "+arrowTop.getBlue());
                    if (arrowTop.getRed() == Fields.gpsColor.getRed() && arrowTop.getGreen() == Fields.gpsColor.getGreen() && arrowTop.getBlue() == Fields.gpsColor.getBlue()) {
                        System.out.println("forward");
                        forward();
                    }else{

                        if(arrowVeryRight.getRed() == Fields.gpsColor.getRed() && arrowVeryRight.getGreen() == Fields.gpsColor.getGreen() && arrowVeryRight.getBlue() == Fields.gpsColor.getBlue()){
                            System.out.println("big right");
                            turnVeryRight();
                        }else{
                            if(arrowRight.getRed() == Fields.gpsColor.getRed() && arrowRight.getGreen() == Fields.gpsColor.getGreen() && arrowRight.getBlue() == Fields.gpsColor.getBlue()){
                                System.out.println("right");
                                turnRight();
                            }
                        }
                        if(arrowVeryLeft.getRed() == Fields.gpsColor.getRed() && arrowVeryLeft.getGreen() == Fields.gpsColor.getGreen() && arrowVeryLeft.getBlue() == Fields.gpsColor.getBlue()) {
                            System.out.println("big left");
                            turnVeryLeft();
                        }else{
                            if(arrowLeft.getRed() == Fields.gpsColor.getRed() && arrowLeft.getGreen() == Fields.gpsColor.getGreen() && arrowLeft.getBlue() == Fields.gpsColor.getBlue()) {
                                System.out.println("left");
                                turnLeft();
                            }
                        }




                        }
                    }
                }
            }

    };

    public static void main(String[] args){
        instance = new Main();
    }
    public Main(){
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        initHook();
        initJFrame();
        t.start();
    }

    public Robot getRobot() {
        return r;
    }
    public void startScreen(){
        File f = new File("output.png");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ImageIO.write(r.createScreenCapture(new Rectangle(0,0,1920,1080)),"png",f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initHook(){
        LogManager.getLogManager().reset();

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener(this));
    }
    public void initJFrame(){
        WindowMover mover = new WindowMover(this);
        this.addMouseListener(mover);
        this.addMouseMotionListener(mover);
        this.setName("gta autopilot");
        this.setSize(Fields.map.width,Fields.map.height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(new AutopilotPanel(this));
        if(render){
            this.setVisible(true);

        }
    }

    public void turnRight(){


        Thread turn = new Thread(){
            @Override
            public void run() {
                getRobot().keyPress(KeyEvent.VK_D);
                getRobot().keyPress(KeyEvent.VK_Z);
                try {
                    Thread.sleep(Fields.turnTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getRobot().keyRelease(KeyEvent.VK_D);
                getRobot().keyRelease(KeyEvent.VK_Z);


            }
        };
        turn.start();
    }
    public void turnLeft(){


        Thread turn = new Thread(){
            @Override
            public void run() {
                getRobot().keyPress(KeyEvent.VK_Q);
                getRobot().keyPress(KeyEvent.VK_Z);

                try {
                    Thread.sleep(Fields.turnTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getRobot().keyRelease(KeyEvent.VK_Q);
                getRobot().keyRelease(KeyEvent.VK_Z);



            }
        };
        turn.start();
    }
    public void turnVeryLeft(){
        Thread turn = new Thread(){
            @Override
            public void run() {
                getRobot().keyPress(KeyEvent.VK_Q);
                getRobot().keyPress(KeyEvent.VK_Z);

                try {
                    Thread.sleep(Fields.bigTurnTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getRobot().keyRelease(KeyEvent.VK_Q);
                getRobot().keyRelease(KeyEvent.VK_Z);



            }
        };
        turn.start();
    }
    public void turnVeryRight(){
        Thread turn = new Thread(){
            @Override
            public void run() {
                getRobot().keyPress(KeyEvent.VK_D);
                getRobot().keyPress(KeyEvent.VK_Z);

                try {
                    Thread.sleep(Fields.bigTurnTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getRobot().keyRelease(KeyEvent.VK_D);
                getRobot().keyRelease(KeyEvent.VK_Z);



            }
        };
        turn.start();
    }
    public void forward(){


        Thread turn = new Thread(){
            @Override
            public void run() {
                getRobot().keyPress(KeyEvent.VK_Z);

                try {
                    Thread.sleep(Fields.forwardTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getRobot().keyRelease(KeyEvent.VK_Z);



            }
        };
        turn.start();
    }
}
