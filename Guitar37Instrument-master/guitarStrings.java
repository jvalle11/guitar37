import java.util.*;

public class guitarStrings implements Instrument
{
    double decayFactor = 0.996;
    String keys = "qwertyuiopasdfghjklzxcvbnm1234567890,";
    ArrayList<LinkedList<Double>> strings = new ArrayList<LinkedList<Double>>();
    
    public guitarStrings()
    {
        double concertA = 440;
        
        for(int i=0; i < keys.length(); i++)
        { //sets frequency for each key
            double frequency = concertA * Math.pow(2.0, ((i - 24) / 12.0));
            double size = 44100/frequency;
            LinkedList<Double> b = new LinkedList<>();
            
            for(int x = 0; x < size; x++) //adds 0s based on size per key string
            {
                b.add(0.0);
            }
           strings.add(b); 
        }
        
    }
    
    public void pluck(char key) 
    {
            //keys.indexOf(key); //gets index of key pressed in string 'keys'
           int y = keys.indexOf(key); //creats and sets y to index of key pressed 
           //strings.get(y); //gets index of key pressed from strings buffer 
           
            LinkedList<Double> ada = strings.get(y); //sets buffer size of 'y'(number of 0s) to 'ada'
            
           
            for(int a = 0; a <ada.size(); a++) //random number from -0.5 to 0.5
            {
                ada.set(a, Math.random()-0.5);
            } 
        
        //double random = (Math.random() - .5);
        //strings.set(key, random);
        
    }

    public void tick() //gets 1st and 2nd index of strings and applies Karplus-Strong algorithm
    {
       
        for (int x = 0; x < strings.size(); x++)
        {

        double first = strings.get(x).get(0);
        double second = strings.get(x).get(1);

        double sum = ((first+second)/2*decayFactor);

        strings.get(x).remove();
        strings.get(x).addLast(sum);

        // LinkedList<Double> algor = new LinkedList<>();
        
            
        // algor.addAll(strings.get(x)); // adds elemnts of string to algor
        
        // double first = algor.get(0);
        // double second = algor.get(1);
        // double sum = ((first+second)/2*(decayFactor));
        
        // algor.remove(0);
        // algor.add(sum);
        // strings.set(x, algor);

        }
    }

    public double superposition() {
        double output = 0.0;
        for(int x = 0; x<strings.size(); x++)
        {
            output += strings.get(x).peek();
        }         
        return output; 
    }
    
}
