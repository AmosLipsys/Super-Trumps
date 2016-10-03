package SuperTrumpsGame;

/**
 * Created by Amos on 02-Oct-16.
 */
public class RuleCard extends Card{
    RuleCard(String fileName, String imageName, String title, String subtitle){
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.subtitle = subtitle;
        this.isTrump = true;
    }


    @Override
    void display() {
        System.out.println(String.format("Title: %s \nSubtitle: %s\n",title,subtitle));
    }

    @Override
    String getTrumpType(){
        switch (title) {
            case "The Gemmologist":
                return "Hardness";
            case "The Geophysicist":
                return  "Specific Gravity";
            case "The Mineralogist":
                return  "Cleavage";
            case "The Petrologist":
                return  "Crustal Abundance";
            case "The Miner":
                return  "Economic Value";
            default:
                return "Any Category";
        }
    }

    @Override
    public void displayNameCatVal(String category, int playersTurn) {
        playersTurn ++;
        System.out.println(String.format("Player %s plays: \nTitle: %s \nSubtitle: %s \nSet Category to: %s ",
                playersTurn,title, subtitle, category));

    }
}
