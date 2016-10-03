package SuperTrumpsGame;

/**
 * Created by Amos on 02-Oct-16.
 */
public class RuleCard extends Card{
    boolean isTrump = true;

    RuleCard(String fileName, String imageName, String title, String subtitle){
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.subtitle = subtitle;
    }


    @Override
    void display() {
        System.out.println(String.format("Title: %s \nSubtitle: %s",title,subtitle));
    }

    public void displayNameCatVal(String category) {
        System.out.println(String.format("Player plays: \nTitle: %-10s \nSet Category to: %-10s ",
                title, subtitle));

    }
}
