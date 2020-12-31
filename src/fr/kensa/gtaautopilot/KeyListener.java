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
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12){
            toggle = true;
           // System.out.println("toggle");
        }else if((nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_D || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_Q || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_S || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_Z) && main.enabled){
            main.paused = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        if(nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_F12){
            if(toggle){
                main.enabled = !main.enabled;
                System.out.println(main.enabled);
            }
        }else if((nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_D || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_Q || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_S || nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_Z) && main.enabled){
            main.paused = false;
        }
    }
}
