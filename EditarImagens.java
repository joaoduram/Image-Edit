import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
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
 * Escreva a descrição da classe EditarImagens aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class EditarImagens extends JFrame
{
    OpcoesDeEdicao ode = new OpcoesDeEdicao();
    private JButton jbC = new JButton("Cinza");
    private JButton jbPeB = new JButton("Preto e Branco");
    private JButton jbNe = new JButton("Negativo");
    private JButton jbBri = new JButton("Brilho");
    private JButton jbSalvar = new JButton("Salvar");
    private JToolBar barraComandos = new JToolBar();
    private JPanel panel = new JPanel();
    private JMenuBar jmbBarra = new JMenuBar();
    BufferedImage imagem = null;
    JFrame frame = new JFrame();
    
    public EditarImagens(){
        super("Jogo de dados");
        //setVisible(true);
        //setSize(larg, alt);
        frame.add(barraComandos, BorderLayout.NORTH);
        //frame.setBounds(100, 100, 450, 300);
        //add(panel, BorderLayout.SOUTH);    
        //add(frame);
        barraComandos.add(jbC);
        barraComandos.add(jbPeB);
        barraComandos.add(jbNe);
        barraComandos.add(jbBri);
        barraComandos.add(jbBri);
        String img = JOptionPane.showInputDialog("Digite o endere�o da imagem");
        File fileImg = new File(img);
        try
        {
            imagem = ImageIO.read(fileImg);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }  
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(imagem)));
        frame.pack();
        frame.setVisible(true);
        jbC.addActionListener(e -> {
            ode.transformaCinza(imagem);
            try
            {
                imagem = ImageIO.read(fileImg);
            }
            catch (java.io.IOException ioe)
            {
                ioe.printStackTrace();
            }  
        }); 
        jbPeB.addActionListener(e -> {
            ode.transformaPeB(imagem);
            try
            {
                imagem = ImageIO.read(fileImg);
            }
            catch (java.io.IOException ioe)
            {
                ioe.printStackTrace();
            }  
        });
        jbNe.addActionListener(e -> {
            ode.transformaNegativo(imagem);
            try
            {
                imagem = ImageIO.read(fileImg);
            }
            catch (java.io.IOException ioe)
            {
                ioe.printStackTrace();
            }  
        }); 
        jbBri.addActionListener(e -> {
            ode.tranformaIlumina(imagem);
            try
            {
                imagem = ImageIO.read(fileImg);
            }
            catch (java.io.IOException ioe)
            {
                ioe.printStackTrace();
            }  
        });
    }
}
