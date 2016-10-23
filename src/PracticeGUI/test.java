package PracticeGUI;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Created by Amos on 23-Oct-16.
*/

public class test extends Applet implements ActionListener
{
    TextField t1=new TextField("    ");

    public void init()
    {
        int n = 1;
        setLayout(new GridLayout(4,4));
        add(t1);
        setFont(new Font("Tahoma",Font.BOLD,24));

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                Button b = new Button(""+n);
                b.addActionListener(this);
                this.add(b);
                n++;
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        t1.setText(str);
    }
}

