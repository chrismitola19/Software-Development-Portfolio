/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cm.flappybird;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class Renderer extends JPanel{
    
    private static final long serialVersionUID = 1L;  //maybe not need this in tuturial he did but I didnt get an error

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        FlappyBird.flappyBird.repaint(g);
    }
 
}
