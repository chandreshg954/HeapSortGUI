import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GUI {
    public static int arr[];
    public static int n,size;
    int length;
    public int [][] cord = new int[n][2];
    int pos = n-1;
    private void maxheap(int i , int size)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left < size && arr[left] > arr[i])
            max = left;
        if (right < size && arr[right] > arr[max])        
            max = right;
 
        if (max != i)
        {
            swap(i, max);
            maxheap(max , size);
        }
    }
    private JButton tree = new JButton("Click to see heap."); 
    private RectsPanel panel = new RectsPanel();
    private void heapify()
    {
        size = n;
        for (int i = (size/2); i >= 0; i--)
            maxheap(i , size);        
    }
    private void swap(int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }    
    int level(int number){
        int levelno=0,i=1;
        while(i<=number){
            i=i*2;
            levelno++;
        }
        return levelno;
    }
    public void sort()
    {
        heapify();
        panel.repaint();
        tree.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(pos>=0){
                    swap(0,pos);
                    maxheap(0,pos);
                    panel.repaint();
                    System.out.print("\n Array :");
                    for (int i=0; i<n; ++i)
                    {
                        System.out.print(arr[i]+" ");
                    }
                    System.out.println();
                    pos= pos-1;
                }
                else
                {
                    pos=n-1;
                    panel.repaint();
                }
            }
        });
        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(tree, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class RectsPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawOval(600,10,40,40);
                cord[0][0]=600;
                cord[0][1]=10;
                g.setColor(Color.BLUE);
                g.drawString(String.valueOf(arr[0]),cord[0][0]+10,cord[0][1]+20);
                for(int i=1;i<=pos;i++){
                    String value = String.valueOf(arr[i]);
                    int level = level(i);
                    level = level-1;
                    int x = (i-1)/2;
                    if(2*x + 1 == i){
                        g.setColor(Color.RED);
                        g.drawOval(cord[x][0]-150 + 40*level,cord[x][1]+100,40,40);
                        cord[i][0] = cord[x][0]-150+40*level;
                    }
                    else{
                        g.setColor(Color.RED);
                        g.drawOval(cord[x][0]+150-40*level,cord[x][1]+100,40,40);
                        cord[i][0] = cord[x][0]+150 - 40*level;
                    }
                    cord[i][1] = cord[x][1]+100;
                    g.setColor(Color.BLUE);
                    g.drawString(value,cord[i][0]+10,cord[i][1]+20);
                    g.drawLine(cord[i][0]+20,cord[i][1],cord[x][0]+20,cord[x][1]+40);
            }
        }
        public Dimension getPreferredSize() {
            return new Dimension(1300,500);
        }
    }

    public static void main(String[] args) {
        String numele, element;
        
        numele = JOptionPane.showInputDialog("Enter the number of elements");
        n = Integer.parseInt(numele);
            
        arr = new int[n];
        for(int i=0;i<n;i++){
            element = JOptionPane.showInputDialog("Enter the elements");
            arr[i] = Integer.parseInt(element);
        }
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                GUI gui = new GUI();
                gui.sort();
            }
        });
    }
}