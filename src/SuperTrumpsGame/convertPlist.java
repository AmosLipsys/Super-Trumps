package SuperTrumpsGame;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class convertPlist {


    public static void main(String argv[]){
        String fileName, imageName, title, subtitle, classification                                                                                     , crystalSystem, occurrence = "", chemistry, hardness, spGravity, cleavage, crustalAbundance, economicValue;
        Path file = Paths.get("C:\\Users\\jc299390\\Desktop\\Super-Trumps\\src\\SuperTrumpsGame\\cards.txt");
        InputStream input = null;
        Card[] stDeck = new Card[54];
        Card[] ruleDeck = new Card[9];
        try{
            input = Files.newInputStream(file);
            String strLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Read Rock Cards Break After Card 54
            int index = 0;
            while ((strLine = reader.readLine()) != null){
                strLine = strLine.replaceAll("\""," ").replaceAll("\u00A0","");
                if (strLine.equals("{")) {
                    strLine = reader.readLine();
                    if(strLine.contains("fileName")) {
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
                        hardness = reader.readLine().replaceAll("\"","").replaceAll("hardness:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        spGravity = reader.readLine().replaceAll("\"","").replaceAll("specific_gravity:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        cleavage = reader.readLine().replaceAll("\"","").replaceAll("cleavage:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        crustalAbundance = reader.readLine().replaceAll("\"","").replaceAll("crustal_abundance:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        economicValue = reader.readLine().replaceAll("\"","").replaceAll("economic_value:","").replaceAll("\u00A0","").replaceAll(",","").trim();
                        stDeck[index] = new Card(fileName, imageName, title, chemistry, classification, crystalSystem,
                                occurrence, hardness, cleavage, crustalAbundance, economicValue, spGravity);
                        stDeck[index].display();
                        index++;
                        if (imageName.contains("Slide54")){
                            break;
                        }

                    }
                }
            }
            while ((strLine = reader.readLine()) != null) {
                strLine = strLine.replaceAll("\"", " ").replaceAll("\u00A0", "");
                if (strLine.equals("{")) {
                    strLine = reader.readLine();
                    if (strLine.contains("fileName")) {
                        fileName = strLine.replaceAll("\"", "").replaceAll("fileName:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        imageName = reader.readLine().replaceAll("\"", "").replaceAll("imageName:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        title = reader.readLine().replaceAll("\"", "").replaceAll("title:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();
                        subtitle = reader.readLine().replaceAll("\"", "").replaceAll("subtitle:", "").replaceAll("\u00A0", "").replaceAll(",", "").trim();

                        System.out.println(fileName);
                        System.out.println(imageName);
                        System.out.println(title);
                        System.out.println(subtitle);
                        System.out.println("~~~~~~~~~~~\n");
                    }
                }

            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}