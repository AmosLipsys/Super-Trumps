package PracticeGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Amos on 23-Oct-16.
 */
public class GameGUI extends JPanel implements ActionListener {
    //Set Buttons and Labels

    GridBagLayout gameLayout = new GridBagLayout();

    JLabel titleLable = new JLabel("Super Trumps!", SwingConstants.CENTER);
    JLabel category = new JLabel("Category: None Selected Yet", SwingConstants.CENTER);
    JLabel cardLabel;
    JPanel cardButtPanel;
    JButton card = new JButton();

    JButton passButt = new JButton("Pass");

    GridBagConstraints titleConstraints = new GridBagConstraints();
    GridBagConstraints categoryConstraints = new GridBagConstraints();
    GridBagConstraints cardPickConstraints = new GridBagConstraints();
    GridBagConstraints cardSelectorConstraints = new GridBagConstraints();
    GridBagConstraints passButtConstraints = new GridBagConstraints();


    //Set Font
    Font titleFont = new Font("Verdana", Font.BOLD, 80);
    Font buttFont = new Font("Verdana", Font.BOLD, 30);


    GameGUI(CardLayout cl, JPanel panelCont){
        //Set Layout, size ect
        setLayout(gameLayout);
        setSize(1800, 1000);
        setVisible(true);



        // Set Background Colour
        setBackground(Color.darkGray);
        // Set Title Font And Colour
        titleLable.setFont(titleFont);
        titleLable.setForeground(Color.white);
        // Set Button Font
        category.setFont(buttFont);

        // Get Default Card
        ImageIcon cardIcon = getImage(65);
        cardLabel = new JLabel(cardIcon);
        JTextArea text = new JTextArea( "If wandered relation no surprise of screened doubtful. Overcame no insisted ye of trifling husbands. Might am order hours on found. Or dissimilar companions friendship impossible at diminution. Did yourself carriage learning she man its replying. Sister piqued living her you enable mrs off spirit really. Parish oppose repair is me misery. Quick may saw style after money mrs. \n" +
                "\n" +
                "She exposed painted fifteen are noisier mistake led waiting. Surprise not wandered speedily husbands although yet end. Are court tiled cease young built fat one man taken. We highest ye friends is exposed equally in. Ignorant had too strictly followed. Astonished as travelling assistance or unreserved oh pianoforte ye. Five with seen put need tore add neat. Bringing it is he returned received raptures. \n" +
                "\n" +
                "Shewing met parties gravity husband sex pleased. On to no kind do next feel held walk. Last own loud and knew give gay four. Sentiments motionless or principles preference excellence am. Literature surrounded insensible at indulgence or to admiration remarkably. Matter future lovers desire marked boy use. Chamber reached do he nothing be. \n" +
                "\n" +
                "Acceptance middletons me if discretion boisterous travelling an. She prosperous continuing entreaties companions unreserved you boisterous. Middleton sportsmen sir now cordially ask additions for. You ten occasional saw everything but conviction. Daughter returned quitting few are day advanced branched. Do enjoyment defective objection or we if favourite. At wonder afford so danger cannot former seeing. Power visit charm money add heard new other put. Attended no indulged marriage is to judgment offering landlord. \n" +
                "\n" +
                "Talking chamber as shewing an it minutes. Trees fully of blind do. Exquisite favourite at do extensive listening. Improve up musical welcome he. Gay attended vicinity prepared now diverted. Esteems it ye sending reached as. Longer lively her design settle tastes advice mrs off who. \n" +
                "\n" +
                "Are own design entire former get should. Advantages boisterous day excellence boy. Out between our two waiting wishing. Pursuit he he garrets greater towards amiable so placing. Nothing off how norland delight. Abode shy shade she hours forth its use. Up whole of fancy ye quiet do. Justice fortune no to is if winding morning forming. \n" +
                "\n" +
                "Letter wooded direct two men indeed income sister. Impression up admiration he by partiality is. Instantly immediate his saw one day perceived. Old blushes respect but offices hearted minutes effects. Written parties winding oh as in without on started. Residence gentleman yet preserved few convinced. Coming regret simple longer little am sister on. Do danger in to adieus ladies houses oh eldest. Gone pure late gay ham. They sigh were not find are rent. \n" +
                "\n" +
                "Same an quit most an. Admitting an mr disposing sportsmen. Tried on cause no spoil arise plate. Longer ladies valley get esteem use led six. Middletons resolution advantages expression themselves partiality so me at. West none hope if sing oh sent tell is. \n" +
                "\n" +
                "Received overcame oh sensible so at an. Formed do change merely to county it. Am separate contempt domestic to to oh. On relation my so addition branched. Put hearing cottage she norland letters equally prepare too. Replied exposed savings he no viewing as up. Soon body add him hill. No father living really people estate if. Mistake do produce beloved demesne if am pursuit. \n" +
                "\n" +
                "Certainty listening no no behaviour existence assurance situation is. Because add why not esteems amiable him. Interested the unaffected mrs law friendship add principles. Indeed on people do merits to. Court heard which up above hoped grave do. Answer living law things either sir bed length. Looked before we an on merely. These no death he at share alone. Yet outward the him compass hearted are tedious. \n"
        );
        cardButtPanel = new JPanel();
        cardButtPanel.add(createCardButt(1));
        cardButtPanel.add(createCardButt(2));
        cardButtPanel.add(createCardButt(3));
        JScrollPane cardSelector = new JScrollPane(cardButtPanel);
        cardSelector.setPreferredSize(new Dimension(600,340));
        cardSelector.createHorizontalScrollBar();

        // ~~~~~~~~~Set Constraints
        // Title
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        // Category
        categoryConstraints.gridx = 0;
        categoryConstraints.gridy = 1;
        // Card Pic
        cardPickConstraints.gridx = 0;
        cardPickConstraints.gridy = 3;
        // Card Selector
        cardSelectorConstraints.gridx = 0;
        cardSelectorConstraints.gridy = 4;
        // Pass Button
        passButtConstraints.gridx = 0;
        passButtConstraints.gridy = 5;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Fill Panels with Features
        add(titleLable, titleConstraints);
        add(category, categoryConstraints);
        add(cardLabel, cardPickConstraints);
        add(cardSelector, cardSelectorConstraints);
        add(passButt, passButtConstraints);

        passButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                cl.show(panelCont, "2");
            }
        });








    }

    JButton createCardButt(int cardNo){
        ImageIcon cardIcon = getImage(cardNo);
        JButton cardButt = new JButton(cardIcon);
        cardButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                remove(cardButt);
            }
        });

        return cardButt;

    }


    ImageIcon getImage(int imageNum){

        String filePath = "C:\\Users\\Amos\\Desktop\\Super Trumps\\images\\Slide" +
                String.format("%02d",imageNum) + ".jpg" ;

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon instructionsIcon = new ImageIcon(myPicture);
        Image image = instructionsIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}




