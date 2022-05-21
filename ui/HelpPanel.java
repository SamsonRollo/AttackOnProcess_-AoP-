package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpPanel extends JPanel {
    private BufferedImage BG_IMG, CONTENT_IMG;
    private AOP aop;
    private boolean isPlay;

    public HelpPanel(AOP aop, boolean isPlay){
        this.aop = aop;
        this.isPlay = isPlay;
        loadElements();
    }

    public void loadElements(){
        setLayout(null);
        setBounds(0,0,700,500);
        setOpaque(false);
        ImageLoader il = new ImageLoader("src/helpPanelOuter.png", "bgHelp");
        BG_IMG = il.getBuffImage();
        il.reloadImage("src/helpContent.png", "content");
        CONTENT_IMG = il.getBuffImage();

        AOPButton back = new AOPButton(600, 34, 84, 28);

        back.setIcons(
            "src/normal/back.png",
            "src/hilite/h_back.png",
            "BACK"
        );

        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                aop.setVisible(true);   
                aop.getMainClass().getContentPane().remove(getPanel());
                aop.getMainClass().revalidate();
                if(isPlay)
                    aop.playingStatus(true);
            }
        });

        JPanel contentPanel = new JPanel(null){
            @Override
            public void paintComponent(Graphics g){
                g.drawImage(CONTENT_IMG, 0, 0, null);
            }
        };
        contentPanel.setSize(CONTENT_IMG.getWidth(), CONTENT_IMG.getHeight());
        contentPanel.setPreferredSize(new Dimension(CONTENT_IMG.getWidth(), CONTENT_IMG.getHeight()));

        JScrollPane jsp = new JScrollPane(contentPanel);
        jsp.setBounds(131, 34, 439, 397);
        add(jsp);
        add(back);
    }

    public JPanel getPanel(){
        return this;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(BG_IMG,0,0, null);
    }
}
