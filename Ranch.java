import java.util.*; //Imports Java classes

  public class Ranch {
  //Declares and initializes global constants
  static int gBarnNum = 0;
  static final int gMAX_BARNS = 15;
  static final int gMAX_OCCUPANCY = 50;
  static int[] gBarns = new int[gMAX_BARNS];
  static Scanner gSCANNER = new Scanner(System.in);
  
  //Method getValidVal
  public static int getValidVal(int maximum, String choices) {
    int userInput = 0;
    System.out.println(choices);
    userInput = gSCANNER.nextInt();
    while ((userInput < 0) || (userInput > maximum)) { //Continues asking for user input while userInput is < 0 or > maximum
     System.out.println("ERROR, you need to enter a number between 0 and " + maximum + "(inclusive)");
     System.out.println(choices);
     userInput = gSCANNER.nextInt();
   }
    return userInput;
  }
  
  //Method emptyBarn()
  public static void emptyBarn() {
    int maxIndex = gBarnNum - 1; //Sets maximum index
    int index = getValidVal(maxIndex,"Enter the index (0 to " + (gBarnNum - 1) + "): "); //Gets user-specified index
    gBarns[index] = 0; //Sets barn at user-specified index to zero
    System.out.println("Barn at index " + index + " occupancy was set to zero.");
  }
  
  //Method addRemoveCattle()
  public static void addRemoveCattle(int[] gBarns, int index, int numCattle) {
    //Declare and initialize variables
    int maxIndex = gBarnNum - 1;
    String str = "";
    char yesNo;
    
    //Prompts user if cattle are added to barn or not
    System.out.println("Are the cattle added to the barn at index " + index + "? (Y/N): ");
    str = gSCANNER.next();
    yesNo = str.charAt(0);
    //If user enters character other than 'Y' or 'N' display error message
    while ((yesNo != 'Y') && (yesNo != 'N')) {
      System.out.println("ERROR, you need to enter either Y or N.");
      System.out.println("Are the cattle added to the barn at index " + index + "? (Y/N): ");
      str = gSCANNER.next();
      yesNo = str.charAt(0);
    }
    //If user enters 'Y' adds cattle to barn at specified index
    if (yesNo == 'Y') {
      gBarns[index] += numCattle;
      System.out.println(numCattle + " cattle were added to barn at index " + index);
      if (numCattle == 0) {
       gBarns[index] += numCattle; 
      }
      
    }
    //If user enters 'N' subtracts cattle from barn at specified index
    else if (yesNo == 'N') {
      if ((numCattle < 0) || (numCattle > gBarns[index])) { //Displays error if user tries to subtract more cattle than available in barn at index
        System.out.println("ERROR, you can't remove more cattle than the ones at barn at index " + index + ". Try again");
        System.out.println("The current occupancy at that barn is: " + gBarns[index]);
      }
      else {
      //Removes cattle from barn at specified index
        gBarns[index] -= numCattle;
        System.out.println(numCattle + " cattle were removed from barn at index " + index);
      }
    }
  }
  
  
  //Method listBarns()
  public static void listBarns() {
    for (int i = 0; i < gBarnNum; i++) { //Loops through gBarnNum and displays numCattle in each barn
      System.out.println("Barn[" + i + "] : " + gBarns[i] + " cattle");
    }
  }
  
  //Method addNewBarn()
  public static void addNewBarn() {
    if (gBarnNum >= gMAX_BARNS) { //Displays error if user tries to add more barns than gMAX_BARNS
      System.out.println("ERROR, The database is full, no more barns can be added");
    }
    else {
      gBarns[gBarnNum] = 0; //Creates new barn and initializes it to zero
      gBarnNum++; //Increments gBarnNum
    }
  }
  
  //Method getBarnsByOccupancy
  public static int getBarnByOccupancy(int num) {
    //Declare and initialize variables
    int i;
    int under = 0;
    int equal = 0;
    int over = 0;
    //Switch statement to iterate through gBarns
    switch (num) {
      case 0: //Gets number of barns under gMAX_OCCUPANCY
        for (i = 0; i < gBarnNum; i++) {
        if ((gBarns[i] >= 0) && (gBarns[i] < gMAX_OCCUPANCY)) {
          under++;
      }
      }
        break;
      case 1: //Gets number of barns equal to gMAX_OCCUPANCY
        for (i = 0; i < gBarnNum; i++) {
        if (gBarns[i] == gMAX_OCCUPANCY) {
          equal++;
      }
      }
        break;
      case 2: //Gets number of barns over gMAX_OCCUPANCY
        for (i = 0; i < gBarnNum; i++) {
        if (gBarns[i] > gMAX_OCCUPANCY) {
          over++;
      }
      }
        break;
    }
    if (num == 0) {
      return under;
    }
    else if (num == 1) {
      return equal;
    }
    else {
      return over;
    }
  }
  
  //Method report()
  public static void report() {
    System.out.println("Under capacity\t: " + getBarnByOccupancy(0));
    System.out.println("Exact capacity\t: " + getBarnByOccupancy(1));
    System.out.println("Over capacity\t: " + getBarnByOccupancy(2));
  }
  
  //Method transferCattle()
  public static void transferCattle() {
    int maxIndex = gBarnNum - 1; //Set max index value
    
    //Get values for idxFrom and quantity of cattle to move
    int idxFrom = getValidVal(maxIndex, "Enter the barn index where cattle will be transferred from (0 to " + maxIndex + ") : ");
    int quantity = getValidVal(gMAX_OCCUPANCY, "The current occupancy of the cattle at index " + idxFrom + " is : " + gBarns[idxFrom] + "\nEnter the number of cattle to transfer to the other barn (0 - 50) : ");
    //Get idxTo and display error if idxTo is our of bounds or equal to idxFrom
    int idxTo = getValidVal(maxIndex,"Enter the barn where the cattle will be transferred to (0 to " + maxIndex + ") that is not " + idxFrom + " : ");
    while (idxTo == idxFrom) { //Displays error while idxTo == idxFrom
      System.out.println("ERROR, you need to enter a different index than the barn where the cattle will be transferred from.");
      System.out.print("Enter the barn where the cattle will be transferred to (0 to " + maxIndex + ") that is not " + idxFrom + " : ");
      idxTo = gSCANNER.nextInt();
    }
    //Transfer cattle from barn idxFrom to barn idxTo
    gBarns[idxFrom] -= quantity;
    gBarns[idxTo] += quantity;
    
    System.out.println(quantity + " cattle were transferred from barn at index " + idxFrom + " to barn at index " + idxTo);
  }
    
    
  
  public static void main(String[] args) {
   //Declares and initializes variables for main method
   int userChoice = 0;
    
   //Prints header
   System.out.println("Fall 2024 - UTSA - CS1083 - Section 008 - Project 2 - Ranch - written by EMMABRANDON\n");
   
   //Prompts user for gBarnNum
   gBarnNum = getValidVal(gMAX_BARNS, "Please, enter the initial number of barns in the database (Max 15): ");
   
   do { //Loops through main menu options until user enters 6
   //Prints out main menu and options and prompts user choice
   userChoice = getValidVal(6, "MAIN MENU\n0 - Empty barn, 1 - Add/Remove cattle, 2 - List barns occupancy, 3 - Add new barn, 4 - Report, 5 - Transfer Cattle, 6 - Exit\nSelect an option: ");
   
     if (userChoice == 0) { //emptyBarn choice
       emptyBarn();
     }
     if (userChoice == 1) { //addRemoveCattle choice
       //Declares and initializes variables
       int numCattle = 0;
       int maxIndex = gBarnNum - 1; 
       int index = getValidVal(maxIndex,"Enter the index (0 to " + (gBarnNum - 1) + "): ");
       
       //Displays current occupancy and prompts user for numCattle
       numCattle = getValidVal(gMAX_OCCUPANCY, "The current occupancy of the cattle at index " + index + " is : " + gBarns[index] + "\nEnter the number of cattle in this operation (0 - 50) : ");
       
       addRemoveCattle(gBarns, index, numCattle);
     }
     if (userChoice == 2) { //listBarns choice
       System.out.println("List of Cattle Occupancy per Barn");
       listBarns();
     }
     if (userChoice == 3) { //addNewBarn choice
        addNewBarn();
     }
     if (userChoice == 4) { //report choice
       System.out.println("Barn Occupancy Classification Summary");
       report();
     }
     if (userChoice == 5) { //transferCattle choice
       transferCattle();
     }
   } while (userChoice != 6);

     if (userChoice == 6) { //Quits program choice
     System.out.println("Quitting program. Thank you for using this program!");
   }
     
  }
   
}