package com.github.prbrios.mfeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.SystemTray;
import java.awt.*;

@Configuration
public class DesktopConfiguration {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public void openTrayIcon() throws Exception {

        TrayIcon trayIcon;
        
        if (SystemTray.isSupported()) {
            
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/api.png"));

            PopupMenu popup = new PopupMenu();
            trayIcon = new TrayIcon(image, "API de Integra\u00e7\u00e3o com SAT/MFE", popup);

            MouseListener mouseListener = new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    //...                
                }

                public void mouseEntered(MouseEvent e) {
                    //...
                }

                public void mouseExited(MouseEvent e) {
                    //...
                }

                public void mousePressed(MouseEvent e) {
                    //...
                }

                public void mouseReleased(MouseEvent e) {
                    //...
                }
            };

            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SystemTray.getSystemTray().remove(trayIcon);
                    SpringApplication.exit(appContext);
                }
            };


            ActionListener configListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        java.awt.Desktop.getDesktop().browse(new URI("http://localhost:8080"));
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            };

            MenuItem abrirConfig = new MenuItem("Configura\u00e7\u00f5es");
            abrirConfig.addActionListener(configListener);
            popup.add(abrirConfig);

            MenuItem defaultItem = new MenuItem("Fechar");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);


            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    trayIcon.displayMessage("Action Event", 
                        "An Action Event Has Been Performed!",
                        TrayIcon.MessageType.INFO);
                }
            };
                    
            trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(actionListener);
            trayIcon.addMouseListener(mouseListener);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }

        } else {
            //  System Tray is not supported
        }

    }

    /* 
    @Bean
    public void openTrayIcon() throws Exception {

        TrayIcon icon = new TrayIcon(new ImageIcon(this.getClass().getResource("/spring.png")).getImage());
        icon.setImageAutoSize(true);

        icon.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemTray.getSystemTray().remove(icon);
                SpringApplication.exit(appContext);
                
            }
        });
        
        SystemTray.getSystemTray().add(icon);
        icon.displayMessage("Spring Boot", "Application started", MessageType.INFO);

    }
    */


}
