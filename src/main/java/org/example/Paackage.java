package org.example;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paackage {
private int id;
private String description;
private double price ;
private String validityPeriod;
private String title; 
	
	
public Paackage(){}

public void setId(int id) {
    this.id = id;
}

public void setDescription(String description) {
    this.description = description;
}

public void setPrice(double price) {
    this.price = price;
}

public void setValidityPeriod(String validityPeriod) {
    this.validityPeriod = validityPeriod;
}

public void setTitle(String title) {
    this.title = title;
}

// Getter methods
public int getId() {
    return id;
}

public String getDescription() {
    return description;
}

public double getPrice() {
    return price;
}

public String getValidityPeriod() {
    return validityPeriod;
}

public String getTitle() {
    return title;
}


Paackage(int id2, String name,String description,double price, String validityDate)
{
	this.id=id2; this.description=description;this.price=price; this.validityPeriod=validityPeriod;
	}

@Override
public String toString() {
    return "Package ID: " + id +
            "\nName: " + title +
            "\nDescription: " + description +
            "\nPrice: " + price +
            "\nValidity Date: " + validityPeriod +
            "\n";
}
public static void addPackageToFile(String filename, String packageDetails) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
        writer.write(packageDetails);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static void addPackage(Scanner scanner, String filename) {
    System.out.println("Adding a new package...");
    int id;
    while (true) {
        System.out.print("Enter package ID: ");
        id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (!isPackageIdExists(filename, id)) {
            break;
        }

        System.out.println("Package ID already exists in the file. Please enter a new ID.");
    }

    System.out.print("Enter package name: ");
    String name = scanner.nextLine();

    System.out.print("Enter package description: ");
    String description = scanner.nextLine();

    System.out.print("Enter package price: ");
    double price = scanner.nextDouble();
    scanner.nextLine(); // Consume newline character

    System.out.print("Enter package validity period (YYYY-MM-DD): ");
    String validityPeriod = scanner.nextLine();

    String packageDetails = String.format("%d,%s,%s,%.2f,%s", id, name, description, price, validityPeriod);
    addPackageToFile(filename, packageDetails);
    System.out.println("Package successfully added.");
}

public static boolean isPackageIdExists(String filename, int id) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && Integer.parseInt(parts[0]) == id) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String filename = "package.txt";
    
    // Uncomment the lines below to test different functionalities
    // addPackage(scanner, filename);
    // deletePackageById(scanner, filename);
    updatePackage(scanner,filename);    
}

public static void updatePackage(Scanner scanner, String filename) {
    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        System.out.println("No packages found.");
        return;
    }

    while (true) {
        System.out.println("All Packages:");
        viewAllPackagesFromFile("package.txt");
        

        System.out.print("\nEnter ID for package you want to update (or 'exit' to quit): ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("exit")) {
            break;
        }

        int packageId = Integer.parseInt(userInput);

        // Find the package with the specified ID
        Paackage packageToUpdate = null;
        for (Paackage p : packages) {
            if (p.getId() == packageId) {
                packageToUpdate = p;
                break;
            }
        }

        if (packageToUpdate == null) {
            System.out.println("Package with ID " + packageId + " not found.");
            continue; // Prompt for another ID
        } else {
            System.out.println("1. ID");
            System.out.println("2. Title");
            System.out.println("3. Price");
            System.out.println("4. Validity Date");
            System.out.println("5. Description");
            System.out.println("6. Exit");

            System.out.print("Select a number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    int newId;
                    do {
                        System.out.print("Enter a new ID: ");
                        newId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (isPackageIdExists(filename, newId)) {
                            System.out.println("ID already exists in the file. Please enter a new ID.");
                        } else {
                            packageToUpdate.setId(newId);
                            System.out.println("ID is updating successfully.");
                            break;
                        }
                    } while (true);
                    break;
                case 2:
                    System.out.print("Enter a new title: ");
                    String newTitle = scanner.nextLine();
                    packageToUpdate.setTitle(newTitle);
                    System.out.println("The title is updating successfully.");
                    break;
                case 3:
                    System.out.print("Enter a new price: ");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    packageToUpdate.setPrice(newPrice);
                    System.out.println("Price is updating successfully.");
                    break;
                case 4:
                    System.out.print("Enter a new validity date: ");
                    String newValidityDate = scanner.nextLine();
                    packageToUpdate.setValidityPeriod(newValidityDate);
                    System.out.println("Validity date is updating successfully.");
                    break;
                case 5:
                    System.out.print("Enter a new description: ");
                    String newDescription = scanner.nextLine();
                    packageToUpdate.setDescription(newDescription);
                    System.out.println("Description is updating successfully.");
                    break;
                case 6:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            // Save updated packages to file
            savePackagesToFile(filename, packages);

            System.out.print("Do you want to update another package? (yes/no): ");
            userInput = scanner.nextLine();
            if (!userInput.equalsIgnoreCase("yes")) {
                break;
            }
        }}
    }

public static void deletePackageById(Scanner scanner, String filename) {

    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        System.out.println("No packages found.");
        return;
    }

    System.out.println("All Packages:");
    for (Paackage p : packages) {
        System.out.println("Package ID: " + p.getId() + " Name: " + p.getTitle());
    }

    while (true) {
        System.out.print("Enter the ID of the package to remove: ");
        int packageIdToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        for (Paackage p : packages) {
            if (p.getId() == packageIdToRemove) {
                found = true;
                packages.remove(p);
                System.out.println("Package with ID " + packageIdToRemove + " successfully removed.");
                break;
            }
        }

        if (found) {
            break; // Exit the loop if the package is found and removed
        } else {
            System.out.println("Package with ID " + packageIdToRemove + " not found.");
            System.out.println("Please insert a new ID.");
        }
    }

    savePackagesToFile(filename, packages);
}

public static List<Paackage> readPackagesFromFile(String filename) {
    List<Paackage> packages = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            String description = parts[2];
            double price = Double.parseDouble(parts[3]);
            String validityDate = parts[4];
            Paackage p = new Paackage();
            p.setTitle(title); // Set the title
            p.setDescription(description);
            p.setId(id);
            p.setPrice(price);
            p.setValidityPeriod(validityDate);
            packages.add(p);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return packages;
}



public static void savePackagesToFile(String filename, List<Paackage> packages) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Paackage p : packages) {
            writer.write(p.toFileString());
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public String toFileString() {
    return id + "," + title + "," + description + "," + price + "," + validityPeriod;
}


public static void viewAllPackagesFromFile(String filename) {
    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        System.out.println("No packages found in the file.");
        return;
    }

    System.out.println("All Packages:");
    for (Paackage p : packages) {
        System.out.println("ID: " + p.getId() +
                "  Name: " + p.getTitle() +
                "  Description: " + p.getDescription() +
                "  Price: " + p.getPrice() +
                "  Validity Date: " + p.getValidityPeriod());
    }
}}




