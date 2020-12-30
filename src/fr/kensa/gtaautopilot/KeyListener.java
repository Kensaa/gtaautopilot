package fr.kensa.gtaautopilot;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.event.KeyEvent;

public class KeyListener implements NativeKeyListener {

    Main main;
    public KeyListener(Main main){
        this.main = main;
    }

    boolean toggle = false;
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
       // System.out.println("pressed : "+nativeKeyEvent.getKeyCode());
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12){
            toggle = true;
           // System.out.println("toggle");
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
       // System.out.println("released : "+nativeKeyEvent.getKeyCode());
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12){
            if(toggle){
                main.enabled = !main.enabled;
                System.out.println(main.enabled);
            }
        }
    }
}
