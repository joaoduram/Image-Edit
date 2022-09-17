import java.io.File;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
 * Escreva a descrição da classe Salva aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Salva
{
    public static void salvarImagem(BufferedImage image, String nome){
        
        File fi = new File("C:\\" + nome);
        try
        {
            ImageIO.write(image, "jpg", fi);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,"Essa imagem foi salva como "+nome); 
    }
}
