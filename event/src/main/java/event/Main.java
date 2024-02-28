package event;

import java.util.Scanner;
import java.io.*;
	import java.util.*;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;

public class Main {


	


	 public static void editDiscountfrom(Scanner scanner, String filename) {
	        List<Discount> discounts = readDiscountsFromFile(filename);
	        System.out.print("Enter the ID of the discount to edit: ");
	        int discountId = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	        
	        Discount oldDiscount = null;
	        for (Discount discount : discounts) {
	            if (discount.getDiscountId() == discountId) {
	                oldDiscount = discount;
	                break;
	            }
	        }
	        
	        if (oldDiscount == null) {
	            System.out.println("Discount not found.");
	            return;
	        }

	        System.out.println("Enter new discount details:");
	        System.out.print("Discount ID: ");
	        int newDiscountId = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	        System.out.print("Discount code: ");
	        String newDiscountCode = scanner.nextLine();
	        System.out.print("Discount percentage: ");
	        double newDiscountPercentage = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline
	        System.out.print("Validity period (YYYY-MM-DD): ");
	        String newValidityPeriod = scanner.nextLine();
	        
	        Discount newDiscount = new Discount(newDiscountPercentage, newDiscountId, newValidityPeriod, newDiscountCode);

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            for (Discount discount : discounts) {
	                if (discount.equals(oldDiscount)) {
	                    writer.write(newDiscount.toString());
	                } else {
	                    writer.write(discount.toString());
	                }
	                writer.newLine();
	            }
	            System.out.println("Discount successfully edited.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void addDiscountToFile(String filename, String discountDetails) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
	            writer.write(discountDetails);
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    

	    public static void writeDiscountToFile(String filename, Discount discount) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
	            writer.write(discount.toString());
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void deleteDiscountFromFile(String filename, List<Discount> updatedDiscounts) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            for (Discount discount : updatedDiscounts) {
	                writer.write(discount.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	    public static void editDiscount(Scanner scanner, String filename) {
	        // Implement logic to edit discount
	        System.out.println("Editing a discount...");
	    }

	    public static void removeDiscount(Scanner scanner, String filename) {
	        System.out.println("Removing a discount...");
	        System.out.print("Enter the ID of the discount to remove: ");
	        int discountIdToRemove = scanner.nextInt();
	        scanner.nextLine(); 

	        List<Discount> discounts = readDiscountsFromFile(filename);
	        boolean found = false;

	      
	        List<Discount> updatedDiscounts = new ArrayList<>();

	        // Iterate over the discounts to find and remove the specified discount
	        for (Discount discount : discounts) {
	            if (discount.getDiscountId() == discountIdToRemove) {
	                found = true;
	                System.out.println("Discount found:");
	                System.out.println(discount);
	            } else {
	                // Add discounts other than the one to be removed to the updated list
	                updatedDiscounts.add(discount);
	            }
	        }

	        // If the discount to remove was found, update the file with the updated list of discounts
	        if (found) {
	        	deleteDiscountFromFile(filename, updatedDiscounts);
	            System.out.println("Discount successfully removed.");
	        } else {
	            System.out.println("Discount not found.");
	        }
	    }


	    public static void addDiscount(Scanner scanner, String filename) {
	        System.out.println("Adding a discount...");
	        System.out.print("Enter discount ID: ");
	        int discountId = scanner.nextInt();
	        scanner.nextLine(); // Consume newline
	        System.out.print("Enter discount code: ");
	        String discountCode = scanner.nextLine();
	        System.out.print("Enter discount percentage: ");
	        double discountPercentage = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline
	        System.out.print("Enter validity period (YYYY-MM-DD): ");
	        String validityPeriod = scanner.nextLine();
	       

	        Discount newDiscount = new Discount( discountPercentage,discountId, validityPeriod, discountCode);
	        String discountDetails = newDiscount.toString();

	        addDiscountToFile(filename, discountDetails);
	        System.out.println("Discount successfully added.");
	    }


	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        String filename = "discounts.txt";

	        while (true) {
	            System.out.println("Discount Management System");
	            System.out.println("1. Add Discount");
	            System.out.println("2. Edit Discount");
	            System.out.println("3. Remove Discount");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    addDiscount(scanner, filename);
	                    break;
	                case 2:
	                	editDiscountfrom(scanner, filename);
	                    break;
	                case 3:
	                    removeDiscount(scanner, filename);
	                    break;
	                case 4:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    public static List<Discount> readDiscountsFromFile(String filename) {
	        List<Discount> discounts = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length >= 5) {
	                    int discountId = Integer.parseInt(parts[0]);
	                    String discountCode = parts[1];
	                    double discountPercentage = Double.parseDouble(parts[2]);
	                    String validityPeriod = parts[3];
	                    
	                    Discount discount = new Discount(discountPercentage,discountId,  validityPeriod, discountCode);
	                    discounts.add(discount);
	                } else {
	                    System.out.println("Invalid format in discounts file: " + line);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return discounts;
	    }

	    public static boolean isValidDate(String date) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            sdf.setLenient(false);
	            sdf.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	  
	    }
	
}
