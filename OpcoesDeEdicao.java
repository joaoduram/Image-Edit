import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.*;
/**
 * Escreva a descrição da classe OpcoesDeEdicao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class OpcoesDeEdicao
{
    Salva s = new Salva();
    public void transformaCinza(BufferedImage image){
        BufferedImage imageCinza = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_BYTE_GRAY);  
        Graphics g = imageCinza.getGraphics();  
        g.drawImage(image, 0, 0, null);  
        g.dispose();
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(imageCinza)));
        frame.pack();
        frame.setVisible(true);
        String nome = "cinza.jpeg";
        s.salvarImagem(imageCinza, nome);
    }
    
    public void transformaPeB(BufferedImage image) {
        BufferedImage newImage = image;
        int width = newImage.getWidth();
        int height = newImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {               
                int rgb = newImage.getRGB(i, j);               
                int r = (int)((rgb&0x00FF0000)>>>16);
                int g = (int)((rgb&0x0000FF00)>>>8);
                int b = (int) (rgb&0x000000FF);
                int media = (r + g + b) / 3;
                Color white = new Color(255, 255, 255);
                Color black = new Color(0, 0, 0);
                //como explicado no artigo, no threshold definimos um limiar,
                //que é um valor "divisor de águas"
                //pixels com valor ABAIXO do limiar viram pixels PRETOS,
                //pixels com valor ACIMA do limiar viram pixels BRANCOS
                if (media < 128){
                    newImage.setRGB(i, j, black.getRGB());
                }
                else{
                    newImage.setRGB(i, j, white.getRGB());
                }
            }
        }
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(newImage)));
        frame.pack();
        frame.setVisible(true);
        String nome = "preto e branco.jpeg";
        s.salvarImagem(newImage, nome);
    }
    
    public void transformaNegativo(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage imageN = null;
        imageN = image;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){               
                int rgb = imageN.getRGB(i, j);  //a cor inversa é dado por 255 menos o valor da cor                 
                int r = 255 - (int)((rgb&0x00FF0000)>>>16);
                int g = 255 - (int)((rgb&0x0000FF00)>>>8);
                int b = 255 - (int) (rgb&0x000000FF);
                Color color = new Color(r, g, b);
                imageN.setRGB(i, j, color.getRGB());
            }
        }
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(imageN)));
        frame.pack();
        frame.setVisible(true);
        String nome = "negativo.jpeg";
        s.salvarImagem(imageN, nome);
    }
    
    public void tranformaIlumina(BufferedImage image) {
        //Ajuste da intensidade
        float in = 14;
        if (in < 0.0f) {
            in = 1.0f + in;
        }

        //Cria a imagem de saída
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                //Le o pixel
                Color p = new Color(image.getRGB(x, y));

                //Multiplica canal por canal
                int r = (int) (p.getRed() * 14);
                if(r>255){
                    r=255;
                }else if(r<0){
                    r=0;
                }
                int g = (int) (p.getGreen() * 14);
                if(g>255){
                    g=255;
                }else if(r<0){
                    g=0;
                }
                int b = (int) (p.getBlue() * 14);
                if(b>255){
                    b=255;
                }else if(b<0){
                    b=0;
                }
                //Gera a cor de saída. Observe que estamos chamando a função toColor para garantir que r, g e b
                //estejam no intervalo de 0 a 255.
                Color o = new Color(r, g, b);

                //Define a cor de saída na imagem de saída (out).
                newImage.setRGB(x, y, o.getRGB());
            }
        }
        frameImage(newImage);
        String nome = "brilho.jpeg";
        s.salvarImagem(newImage, nome);
    }
    
    public void frameImage(BufferedImage image){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        //frame.add(barraComandos, BorderLayout.NORTH);
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.pack();
        //frame.add(jbSalvar);
        frame.setVisible(true);
    
    }
}
