package com.simulador.credito;

import java.awt.*;
import java.util.Locale;


class ThreadGui extends Thread {
    ThreadGui () {}

    public void run(){
        EventQueue.invokeLater(()->{
            Gui g = new Gui();
            g.setVisible(true);
        });
    }
}

class ThreadConsole extends Thread {
    public ThreadConsole (){}

    public void run(){
        new Console().capturaDeDatos();
    }
}

public class Application {
    public static void main (String[] args) {
        Locale.setDefault(new Locale("es", "CO"));

        ThreadConsole cmdApp = new ThreadConsole();
        cmdApp.start();

        ThreadGui guiApp = new ThreadGui();
        guiApp.start();
    }
}
