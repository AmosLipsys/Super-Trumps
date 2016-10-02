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
    }

    @Override
    void display() {
        System.out.println(fileName);
        System.out.println(imageName);
        System.out.println(title);
        System.out.println(subtitle);
        System.out.println("#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#\n");
    }
}
