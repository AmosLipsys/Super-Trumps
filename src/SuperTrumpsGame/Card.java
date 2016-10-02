package SuperTrumpsGame;

/**
 * Created by Amos on 26-Sep-16.
 */
public class Card {

    String fileName, imageName, title, classification, crystalSystem, chemistry, cleavage, crustalAbundance,
            economicValue, hardness, spGravity, occurrence, subtitle;

    public Card(String fileName, String imageName, String title, String chemistry, String classification,
                String crystalSystem, String occurrence, String hardness, String cleavage, String crustalAbundance,
                String economicValue, String spGravity) {
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrence = occurrence;
        this.hardness = hardness;
        this.spGravity = spGravity;
        this.cleavage = cleavage;
        this.crustalAbundance = crustalAbundance;
        this.economicValue = economicValue;
    }

    public Card(String fileName, String imageName, String title, String subtitle){
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.subtitle = subtitle;
    }

    void display(){
        System.out.println(fileName);
        System.out.println(imageName);
        System.out.println(title);
        System.out.println(chemistry);
        System.out.println(classification);
        System.out.println(crystalSystem);
        System.out.println(occurrence);
        System.out.println(hardness);
        System.out.println(spGravity);
        System.out.println(cleavage);
        System.out.println(crustalAbundance);
        System.out.println(economicValue);
        System.out.println("#~~~~~~~~~~~#\n");
    }


}
