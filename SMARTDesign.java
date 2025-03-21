import java.util.*;
public class SMARTDesign {

  public static double calcTotalTime(String material) { // Method to calculate total time to decay 1g of material
    double time = 0;
    //Calculate time
    if (material.equals("Wood")){
      time = 60.6; //in days
  }
  else if(material.equals("PLA")){
    time = 393.4; //in days
  }
    return time;
  }
  
  
  public static void main(String[] args) {
    Scanner scnr = new Scanner(System.in);
    // Declare and initialize variables
    double time = 0;
    String material = "";
    String headerRow = "";
    String dataRow = "";

    // Prompt user to enter data until the user enters '0' (Uses for loop and possibly arrays)
    while(true){
    System.out.println("What material? Enter 'A' for PLA (Polylactic Acid), 'B' for Wood, or 'C' to quit the program.");
    String userLetter = scnr.nextLine();

      if (userLetter.equals("A")){
        material = "PLA";
      }
      else if (userLetter.equals("B")){
        material = "Wood";
      }
      else if (userLetter.equals("C")) {
        System.out.println("Thank you for using this program.");
        break;
      }
      else {
        System.out.println("Invalid input, Please enter a valid letter.");
        continue;
      }
      
      //Calcuations
      time = calcTotalTime(material);

      //Prints header row
      headerRow = String.format("%1$-" + String.valueOf(20 - "Material".length() + "s"), "Material");
      headerRow += String.format("%1$-" + String.valueOf(30 - "Rate of Decay".length() + "s"), "Rate of Decay");
      headerRow += String.format("%1$-" + String.valueOf(45 - "Total Time to Decay 1g of Material (Days)".length() + "s"), "Total Time to Decay 1g of Material (Days)");
      System.out.println(headerRow);

      //Determines rate of decay based on material
      String rateOfDecay = "";
      if(material.equals("PLA")){
        rateOfDecay = "1.14%";
      }
      else if (material.equals("Wood")){
        rateOfDecay = "1.74%";
      }
      
      //Prints out data row
      dataRow = String.format("%1$-" + String.valueOf(20 - "Material".length()) + "s", material);
      dataRow += String.format("%1$-" + String.valueOf(30 - "Rate of Decay".length()) + "s", rateOfDecay);
      dataRow += String.format("%1$-" + String.valueOf(45 - "Total Time to Decay 1g of Material (Days)".length()) + "s", time + " days");
      
      System.out.println(dataRow + "\n");
      //System.out.println(material + "\t" + rateOfDecay + "\t" + time + "days");
      
    }
  }
}
