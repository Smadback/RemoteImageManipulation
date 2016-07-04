package edu.tuc.bsvs.remoteimagemanipulation.client;

import edu.tuc.bsvs.remoteimagemanipulation.objects.ImageRepresentation;
import edu.tuc.bsvs.remoteimagemanipulation.server.ImageProcessor;
import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private BufferedImage bimg;
    private String host;


    public Client(){
        initComponents();
        host = JOptionPane.showInputDialog("Specify the server IP");
    }

    /**
     * Let the client chose a picture from his computer
     */
    public void chosePicture() {

        boolean again;
        do {
            again = false;

            try {
                JFileChooser fileChooser = new JFileChooser();
                int file = fileChooser.showOpenDialog(null);
                String filename = fileChooser.getSelectedFile().getPath();
                String extensions[] = {"jpg", "png", "JPG", "PNG"};

                if (file == JFileChooser.APPROVE_OPTION && FilenameUtils.isExtension(filename, extensions)) {
                    System.out.println("[INFO] " + filename + " has been chosen.");
                    bimg = javax.imageio.ImageIO.read(new File(filename));
                    jPanel1.getGraphics().drawImage(bimg, 0, 0, null);

                } else {
                    JOptionPane.showMessageDialog(null, "You have to choose an image type. (jpg, png)");
                    again = true;
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An unknown error occurred.");
            }
        } while (again);
    }

    /**
     * Manipulate and show the new picture
     */
    private void manipulatePicture() {

        Registry registry = null;
        ImageRepresentation image = null;

        try {
           // registry = LocateRegistry.getRegistry();
            registry = LocateRegistry.getRegistry(host);
            ImageProcessor imageProcessor = (ImageProcessor) registry.lookup("ImageProcessor");

            image = new ImageRepresentation(bimg);
            image = imageProcessor.manipulateImage(image);

            int[] pixel = image.getPixels();
            int width = image.getWidth();
            int height = image.getHeight();

            Image img = createImage(new MemoryImageSource(width, height, pixel, 0, width));
            jPanel1.getGraphics().drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), jPanel1);
            //jPanel1.getGraphics().drawImage(image.getImage(), 0, 0, null);
            //jPanel1.getGraphics().drawImage(bimg.getScaledInstance(bimg.getWidth(),bimg.getHeight(),Image.SCALE_FAST),0,0,bimg.getWidth(),bimg.getHeight(),this);
        } catch (UnknownHostException e) {
            host = JOptionPane.showInputDialog("Host " + host + " couldn't be found. Please enter a new hostname (or IP)");
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No connection to the server could be established. Check if it is running.");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "You have to choose a picture.");
        }
    }

    /**
     * Build the Frame
     */
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {

                exitForm(evt);

            }

        });

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        getContentPane().add(jPanel1, new java.awt.GridBagConstraints());
        jPanel2.setMinimumSize(new java.awt.Dimension(400, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 50));
        jButton1.setText("Chose picture");
        jButton2.setText("Start manipulation");

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jPanel2.add(jButton1);
        jPanel2.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel2, gridBagConstraints);

        pack();
    }

    /**
     * Chose a picture button
     * @param evt
     */
    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {
        chosePicture();
    }

    /**
     * Manipulate the picture button
     * @param evt
     */
    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {
        manipulatePicture();
    }

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    public void paint(Graphics g){
        super.paint(g);
        if(bimg !=null)
            jPanel1.getGraphics().drawImage(bimg.getScaledInstance(bimg.getWidth(),bimg.getHeight(),Image.SCALE_FAST),0,0,bimg.getWidth(),bimg.getHeight(),this);
    }


}
