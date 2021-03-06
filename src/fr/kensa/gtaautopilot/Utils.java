package fr.kensa.gtaautopilot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.nio.Buffer;

public class Utils {
    public static BufferedImage processImg(BufferedImage img){
        BufferedImage newImg = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int y = 0;y < img.getHeight();y++){
            for(int x = 0;x < img.getWidth();x++){



                Color c = new Color(img.getRGB(x,y));
                if(c.getRed() < 110 && c.getGreen() < 100 && c.getBlue() < 130){
                    //road
                    newImg.setRGB(x,y,Color.BLACK.getRGB());
                }else{
                    if((c.getRed() >= 160 && c.getRed() <= 175) && (c.getGreen() >= 80 && c.getGreen() <=95) && (c.getBlue() >= 240 && c.getBlue() <=255)){
                        //gps
                        newImg.setRGB(x,y,Color.PINK.getRGB());
                        continue;

                    }

                    //other
                    newImg.setRGB(x,y,Color.RED.getRGB());

                }

            }
        }

        return newImg;
    }

    public static boolean checkColor(Color c, Rectangle rect,BufferedImage img){
        int w1 = rect.x;
        int w2 = rect.x+rect.width;
        int h1 = rect.y;
        int h2 = rect.y+rect.height;

        int rgb = c.getRGB();
        for(int y = h1; y<h2;y++){
            for(int x = w1; x<w2;x++){
                Color cc = new Color(img.getRGB(x,y));
                /*System.out.println("red : "+cc.getRed()+" green : "+cc.getGreen()+ " blue : "+cc.getBlue());
                System.out.println("red : "+c.getRed()+" green : "+c.getGreen()+ " blue : "+c.getBlue());*/

                if(img.getRGB(x,y) == rgb){
                    return true;
                }
            }
        }
        return false;
    }
}
