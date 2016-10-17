package SuperTrumpsGame;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> superTrumpsDeck;

    public Deck() {
        //Make new deck
        superTrumpsDeck = makeDeck();

        // Remove Rule Cards from deck
        superTrumpsDeck.remove(62);
        superTrumpsDeck.remove(61);
        superTrumpsDeck.remove(60);

        // Shuffle Deck
        Collections.shuffle(superTrumpsDeck);
//        System.out.println(superTrumpsDeck);
    }

    public Card dealCard(){
        Card chosenCard = superTrumpsDeck.get(0);
        superTrumpsDeck.remove(0);
        return chosenCard;
    }

    // Open TEXT file and make a deck
    private ArrayList<Card> makeDeck(){

        //Store 63 Cards 52 Normal 6 Super Trump and 3 Instructions
        ArrayList<Card> stDeck = new ArrayList<>();

//        Card[] stDeck = new Card[63];
        // Cards variables all gathered in strings
        String fileName, imageName, title, subtitle, classification, crystalSystem, occurrence = "", chemistry, hardness, spGravity, cleavage, crustalAbundance, economicValue;
        // Path to TEXT file. NOT PLIST, the file was externally converted to a different format
        Path file = Paths.get("C:\\Users\\jc299390\\Desktop\\Super-Trumps\\src\\SuperTrumpsGame\\cards.txt");
        InputStream input = null;


        try{

            //open file
            input = Files.newInputStream(file);
            String strLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Read Rock Cards Break After Card 54
            int index = 0;

            while ((strLine = reader.readLine()) != null){
                strLine = strLine.replaceAll("\""," ").replaceAll("\u00A0","");
                // Check for the starting indicator "{"
                if (strLine.equals("{")) {
                    strLine = reader.readLine();
                    // If it says file name it is the start of the card
                    if(strLine.contains("fileName")) {

                        // Strip lines to get required string
                        fileName = strLine.replaceAll("\"","").replaceAll("fileName:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        imageName = reader.readLine().replaceAll("\"","").replaceAll("imageName:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        title = reader.readLine().replaceAll("\"","").replaceAll("title:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        chemistry = reader.readLine().replaceAll("\"","").replaceAll("chemistry:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        classification = reader.readLine().replaceAll("\"","").replaceAll("classification:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        crystalSystem = reader.readLine().replaceAll("\"","").replaceAll("crystal_system:","").replaceAll("\u00A0","").replaceAll(",","").trim();

                        // Obtain Occurrence String
                        if (reader.readLine().contains("occurrence")){
                            strLine = reader.readLine().replaceAll("\"","").replaceAll("\u00A0","").replaceAll(",","");;
                            occurrence = strLine;
                            strLine = reader.readLine().replaceAll("\"","").replaceAll("\u00A0","").replaceAll(",","");
                            while (!strLine.contains("]")){
                                occurrence = occurrence + "," + strLine;
                                strLine = reader.readLine().replaceAll("\"","").replaceAll("\u00A0","").replaceAll(",","");
                            }
                        }

                        // Strip lines to get required string
                        hardness = reader.readLine().replaceAll("\"","").replaceAll("hardness:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        spGravity = reader.readLine().replaceAll("\"","").replaceAll("specific_gravity:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        cleavage = reader.readLine().replaceAll("\"","").replaceAll("cleavage:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        crustalAbundance = reader.readLine().replaceAll("\"","").replaceAll("crustal_abundance:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        economicValue = reader.readLine().replaceAll("\"","").replaceAll("economic_value:","").replaceAll("\u00A0","").replaceAll(",","").trim();

                        // Make Card Class
                        stDeck.add(new Card(fileName, imageName, title, chemistry, classification, crystalSystem,
                                occurrence, hardness, cleavage, crustalAbundance, economicValue, spGravity));


                        // Increase index to make a new card
                        index++;

                        // Slide 54 is the last playing card
                        if (imageName.contains("Slide54")){
                            break;
                        }

                    }
                }
            }

            // Add instruction cards
            while ((strLine = reader.readLine()) != null) {
                strLine = strLine.replaceAll("\"", " ").replaceAll("\u00A0", "");
                if (strLine.equals("{")) {
                    strLine = reader.readLine();
                    if (strLine.contains("fileName")) {

                        // Strip lines to get required string
                        fileName = strLine.replaceAll("\"", "").replaceAll("fileName:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        imageName = reader.readLine().replaceAll("\"", "").replaceAll("imageName:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        title = reader.readLine().replaceAll("\"", "").replaceAll("title:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        subtitle = reader.readLine().replaceAll("\"", "").replaceAll("subtitle:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();

                        // Make Rule Card
                        stDeck.add(new RuleCard(fileName, imageName, title, subtitle));

                        // Display Rule Card
//                        stDeck[index].display();

                        //Increase Index
                        index++;
                    }
                }

            }

            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stDeck;
    }
}