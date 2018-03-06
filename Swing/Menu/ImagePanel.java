/**
 * ImagePanel.java
 * 
 * Created on 6/03/2018
 * @author Bayart Valentin 
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JPanel;
 
public class ImagePanel extends JPanel {
    
    /**
     * Variable contenant l'image a créer
     */
    private Image img;
     
    /**
     * Constructeur de la classe
     * Définit l'image a créer
     * 
     * @param img de type Image
     */
    public ImagePanel(Image img){
        this.img = img;
    }
     
    /**
     * Methode paintComponent
     * Permet de dessiner l'image définit au préalable
     * 
     * @param g de type graphics, objet sur lequel on dessine
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
