package groceryList;

import java.util.Scanner;

public class GroceryList {
	public static String[] groceryArr = new String[50];
	public static boolean[] checkOffArr = new boolean[50];
	public static int index = 0;
	
	public static void addItem(String item) {
		groceryArr[index] = item;
		checkOffArr[index] = false;
		index++;
	}
	public static void removeItem(String item) {
		String[] tempArr = new String[groceryArr.length - 1];
		int j;
		if (String target : groceryArr) {
			if (target.equalsIgnoreCase(item)) {
				for (int i = 0; i < groceryArr.length; i++) {
					if (groceryArr[i] != item) {
						tempArr[j] = groceryArr[i];
						j++;
					}
				}
				}
		      }
	}
	public static void removeItemIndex(int item) {
		String[] tempArr = new String[groceryArr.length - 1];
		int j;
		for (int i = 0; i < groceryArr.length; i++) {
		if (i != item) {
		tempArr[j++] = groceryArr[i];
		}
		}
		
	}
	
	public static void checkOffItem(String item) {
    		for (int i = 0; i < groceryArr.length; i++) {
        		if (groceryArr[i] != null && groceryArr[i].equalsIgnoreCase(item)) {
            			checkOffArr[i] = true;
            			return;
        		}
    		}
	}

	public static void checkOffIndex(int index) {
		checkOffArr[index] = true;
		}
	}
		
	public static void printList() {

    		for (int i = 0; i < groceryArr.length; i++) {
        		if (groceryArr[i] != null) { 
            			if (checkOffArr[i]) {
                			System.out.println((i + 1) + ". x " + groceryArr[i]); 
            			} else {
                			System.out.println((i + 1) + ". - " + groceryArr[i]); 
				}
			}
		}
	}

	
	public static int exists(String item) {
		for(int i = 0; i < groceryArr.length; i++) {
			if(groceryArr[i].equalsIgnoreCase(item)) {
				return i;
			}
		}
		return -1;
	}

	public Boolean existsIndex(int index) {
    		if (index < 0 || index >= groceryArr.length) {
		        return false;
    		} 
    		return groceryArr[index] != null; 
	}


	
	
	public static void main(String [] args) {
		Scanner scnr = new Scanner(System.in);
		int menuOption;
		String item = "";
		int itemIndex = 0;
		
		do {
			System.out.print("Welcome to Grocery List Management!\n1. Add Item to your GroceryList\n2. Remove Item from your Grocery List\n3. \"Check Off\" an Item from you Grocery List\n4. Display your Grocery List\n5. Exit\n\nPlease enter the number of an option above: ");
			menuOption = scnr.nextInt();
			
			switch(menuOption) {
			case 1: 
				System.out.println("Please type what item you wish to add to the list:");
				item = scnr.nextLine();
				if (exists(item) != -1) {
					System.out.println("This item already exists in the list.");
					continue;
				}
				else {
					addItem(item);
				}
				System.out.println("you chose 1\n");
				break;
			case 2:
				System.out.println("Pick one:\n1. Remove item\n2.Remove item from specifiec index");
				int removeChoice = scnr.nextInt();
					switch (removeChoice) {
						case 1:
							System.out.println("What item would you like to remove?");
							item = scnr.nextLine();
							if (exists(item) != -1) {
								removeItem(item);
							}
							else {
								System.out.println("This item does not exist. Please try again.");
								continue;
							}
							removeItem(item);
							break;
						case 2:
							System.out.println("What index would you like to remove an item from?");
							itemIndex = scnr.nextInt();
							if (existsIndex(itemIndex) == true) {
								removeItemIndex(itemIndex);
							}
							else {
							System.out.println("This index does not contain a value. Please try again.");
							continue;
							}
							break;
						default:
							System.out.println("Invalid input. Taking you back to start menu.");
							continue;
					}
				System.out.println("you chose 2\n");
				break;
			case 3:
				System.out.println("Pick one:\n1. Check off item\n2. Check off index");
				int checkOffChoice = scnr.nextInt();
				switch (checkOffChoice) {
					case 1: 
						System.out.println("What item would you like to check off?");
						item = scnr.nextLine();
						if (exists(item) != -1) {
							checkOffItem(item);
						}
						else {
							System.out.println("Item does not exist. Please try again.");
							continue;
						}
						break;
					case 2:
						System.out.println("What index would you like to check off?");
						itemIndex = scnr.nextInt();
						if (existsIndex) {
							checkOffItemIndex(itemIndex);
						}
						else {
							System.out.println("This index does not contain a value. Please try again.");
							continue;
						}
						break;
					default:
						System.out.println("Invalid input. Taking you back to start menu.");
						continue;
					}
					break;
			case 4: 
				printList();
				break;
			case 5:
				printList();
				System.out.println("Thank you for using this program!");
				break;
			default:
				System.out.println("error invalid number, try again\n");
			}			

				
		}while(menuOption != 5);
		
		
	}
}
