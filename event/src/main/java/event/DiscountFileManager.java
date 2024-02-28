package event;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DiscountFileManager {

	public static void editDiscountInFile(String filename, Discount oldDiscount, Discount newDiscount) {
        List<Discount> discounts = readDiscountsFromFile(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Discount discount : discounts) {
                if (discount.equals(oldDiscount)) {
                    writer.write(newDiscount.toString());
                } else {
                    writer.write(discount.toString());
                }
                writer.newLine();
            }
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
                    String applicableProductsServices = parts[4];
                    Discount discount = new Discount( discountPercentage, discountId,validityPeriod, discountCode);
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

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String filename = "discounts.txt";
//
//        try {
//            System.out.println("Enter discount ID:");
//            int discountId = scanner.nextInt();
//            scanner.nextLine();
//
//            System.out.println("Enter discount code:");
//            String discountCode = scanner.nextLine();
//
//            System.out.println("Enter discount percentage:");
//            double discountPercentage = scanner.nextDouble();
//            scanner.nextLine();
//
//            System.out.println("Enter validity period (YYYY-MM-DD):");
//            String validityPeriod = scanner.nextLine();
//            if (!isValidDate(validityPeriod)) {
//                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
//                return;
//            }
//
//            System.out.println("Enter applicable products/services:");
//            String applicableProductsServices = scanner.nextLine();
//
//            Discount userDiscount = new Discount( discountPercentage, discountId,validityPeriod, discountCode);
//
//            writeDiscountToFile(filename, userDiscount);
//
//            System.out.println("Discount successfully added to the file.");
//
//            List<Discount> discountsFromFile = readDiscountsFromFile(filename);
//            System.out.println("All discounts:");
//            for (Discount discount : discountsFromFile) {
//                System.out.println(discount);
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input format for discount ID or discount percentage.");
//        } finally {
//            scanner.close();
//        }
//    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "discount.txt";

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
                	editDiscountInFile(scanner, filename);
                    break;
                case 2:
                    editDiscount(scanner, filename);
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
