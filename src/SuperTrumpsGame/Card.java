package SuperTrumpsGame;

/**
 * Created by Amos on 26-Sep-16.
 */
public class Card {

    String fileName, imageName, title, classification, crystalSystem, chemistry, cleavage, crustalAbundance,
            economicValue, hardness, spGravity, occurrence, subtitle;
    double valueHardness, valueSpGravity, valueCleavage, valueCrustalAbundance, valueEconomicValue;
    boolean isTrump = false;
    public Card() {
    }

    Card(String fileName, String imageName, String title, String chemistry, String classification,
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

        setPropertyValues();

    }

    private void setPropertyValues(){
        // Hardness
        if(hardness.split("-").length == 2){
            valueHardness = Double.parseDouble(hardness.split("-")[1]);
        }
        else valueHardness = Double.parseDouble(hardness);

        //SP Gravity
        if(spGravity.split("-").length == 2){
            valueSpGravity = Double.parseDouble(spGravity.split("-")[1]);
        }
        else valueSpGravity = Double.parseDouble(spGravity);

        // Cleavage
        switch (cleavage) {
            case "none":
                valueCleavage = 1;
                break;
            case "poor/none":
                valueCleavage = 2;
                break;
            case "1 poor":
                valueCleavage = 3;
                break;
            case "2 poor":
                valueCleavage = 4;
                break;
            case "1 good":
                valueCleavage = 5;
                break;
            case "1 good 1 poor":
                valueCleavage = 6;
                break;
            case "2 good":
                valueCleavage = 7;
                break;
            case "3 good":
                valueCleavage = 8;
                break;
            case "1 perfect":
                valueCleavage = 9;
                break;
            case "1 perfect 1 good":
                valueCleavage = 10;
                break;
            case "1 perfect 2 good":
                valueCleavage = 11;
                break;
            case "2 perfect 1 good":
                valueCleavage = 12;
                break;
            case "3 perfect":
                valueCleavage = 13;
                break;
            case "4 perfect":
                valueCleavage = 14;
                break;
            case "6 perfect":
                valueCleavage = 15;
                break;
        }

        // Crustal Abundance
        switch (crustalAbundance) {
            case "ultratrace":
                valueCrustalAbundance = 1;
                break;
            case "trace":
                valueCrustalAbundance = 2;
                break;
            case "low":
                valueCrustalAbundance = 3;
                break;
            case "moderate":
                valueCrustalAbundance = 4;
                break;
            case "high":
                valueCrustalAbundance = 5;
                break;
            case "very high":
                valueCrustalAbundance = 6;
                break;
        }

        // Economic Value
        switch (economicValue) {
            case "trivial":
                valueEconomicValue = 1;
                break;
            case "low":
                valueEconomicValue = 2;
                break;
            case "moderate":
                valueEconomicValue = 3;
                break;
            case "high":
                valueEconomicValue = 4;
                break;
            case "very high":
                valueEconomicValue = 5;
                break;
            case "I'm rich":
                valueEconomicValue = 6;
                break;
        }


    }

    double getAttributeValue(String attribute){
        switch (attribute) {
            case "Hardness":
                return valueHardness;
            case "Specific Gravity":
                return valueSpGravity;
            case "Cleavage":
                return valueCleavage;
            case "Crustal Abundance":
                return valueCrustalAbundance;
            case "Economic Value":
                return valueEconomicValue;
        }
        return 0.0;
    }



    void display(){
        System.out.println(String.format("" +
                        "Title: %-25s |Chemistry: %-35s |Classification: %-20s \n" +
                        "Crystal System: %-16s |Occurrence: %-34s |Hardness: %-20s \n" +
                        "Special Gravity: %-15s |Cleavage: %-36s |Crustal Abundance: %-10s \n" +
                        "Economic Value: %-10s\n ",
                title, chemistry, classification,crystalSystem, occurrence,
                hardness,spGravity, cleavage, crustalAbundance, economicValue));
//        System.out.println(chemistry);
//        System.out.println(classification);
//        System.out.println(crystalSystem);
//        System.out.println(occurrence);
//        System.out.println(hardness);
//        System.out.println(spGravity);
//        System.out.println(cleavage);
//        System.out.println(crustalAbundance);
//        System.out.println(economicValue);
    }
    public void displayNameCatVal(String category) {
        System.out.println(String.format("Player plays: \n Title: %-10s \n Category: %s - %s",
                title, category, getAttributeValue(category)));

    }



}
