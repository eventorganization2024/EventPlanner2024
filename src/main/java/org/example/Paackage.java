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
private static final String ERROR_MESSAGE = "An error occurred while writing package details to file: ";
public Paackage(){}
 static final String PACKAGE_WITH_ID = "Package with ID ";
 static final String ALL_PACKAGES = "All Packages:";
static Printing print=new Printing ();
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


Paackage(int id2, String description,double price)
{
	this.id=id2; this.description=description;this.price=price; 
	
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
    	print.printSomething(ERROR_MESSAGE + e.getMessage());
    }
}

public static void addPackage(Scanner scanner, String filename) {
	print.printSomething("Adding a new package...");
    int id;
    while (true) {
    	print.printSomething("Enter package ID: ");
        id = scanner.nextInt();
        scanner.nextLine(); 

        if (!isPackageIdExists(filename, id)) {
            break;
        }

print.printSomething("Package ID already exists in the file. Please enter a new ID.");
    }

    print.printSomething("Enter package name: ");
    String name = scanner.nextLine();

    print.printSomething("Enter package description: ");
    String description = scanner.nextLine();

    print.printSomething("Enter package price: ");
    double price = scanner.nextDouble();
    scanner.nextLine(); // Consume newline character

    print.printSomething("Enter package validity period (YYYY-MM-DD): ");
    String validityPeriod = scanner.nextLine();

    String packageDetails = String.format("%d,%s,%s,%.2f,%s", id, name, description, price, validityPeriod);
    addPackageToFile(filename, packageDetails);
    print.printSomething("Package successfully added.");
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
    	print.printSomething(ERROR_MESSAGE + e.getMessage());

    }
    return false;
}


public static void updatePackage(Scanner scanner, String filename) {
    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        print.printSomething("No packages found.");
        return;
    }

    while (true) {
        print.printSomething(ALL_PACKAGES);
        viewAllPackages(packages);

        print.printSomething("\nEnter ID for package you want to update (or 'exit' to quit): ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("exit")) {
            break;
        }

        int packageId = Integer.parseInt(userInput);
        Paackage packageToUpdate = findPackageById(packages, packageId);

        if (packageToUpdate == null) {
            print.printSomething(PACKAGE_WITH_ID + packageId + " not found.");
        } else {
            updatePackageDetails(scanner, packageToUpdate, filename, packages);
        }
    }
}



public static void deletePackageById(Scanner scanner, String filename) {
    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        print.printSomething("No packages found.");
        return;
    }

    print.printSomething(ALL_PACKAGES);
    for (Paackage p : packages) {
        print.printSomething("Package ID: " + p.getId() + " Name: " + p.getTitle());
    }

    while (true) {
        print.printSomething("Enter the ID of the package to remove: ");
        int packageIdToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;

        for (Paackage p : packages) {
            if (p.getId() == packageIdToRemove) {
                found = true;
                packages.remove(p);
                print.printSomething(PACKAGE_WITH_ID + packageIdToRemove + " successfully removed.");
                break;
            }
        }

        if (found) {
            break;
        } else {
            print.printSomething(PACKAGE_WITH_ID + packageIdToRemove + " not found.");
            print.printSomething("Please insert a new ID.");
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
    	print.printSomething(ERROR_MESSAGE + e.getMessage());

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
    	print.printSomething(ERROR_MESSAGE + e.getMessage());

    }
}
public String toFileString() {
    return id + "," + title + "," + description + "," + price + "," + validityPeriod;
}


public static void viewAllPackagesFromFile(String filename) {
    List<Paackage> packages = readPackagesFromFile(filename);

    if (packages.isEmpty()) {
        print.printSomething("No packages found in the file.");
        return;
    }

    print.printSomething(ALL_PACKAGES);
    for (Paackage p : packages) {
        print.printSomething("ID: " + p.getId() +
                "  Name: " + p.getTitle() +
                "  Description: " + p.getDescription() +
                "  Price: " + p.getPrice() +
                "  Validity Date: " + p.getValidityPeriod());
    }
}


public static void updatePackageDetails(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("1. ID");
	print.printSomething("\n2. Title");
	print.printSomething("\n3. Price");
	print.printSomething("\n4. Validity Date");
	print.printSomething("\n5. Description");
	print.printSomething("\n6. Exit");

	print.printSomething("Select a number: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); 

    switch (choice) {
        case 1:
        	 updatePackageId(scanner, packageToUpdate, filename);
            break;
        case 2:
            updatePackageTitle(scanner, packageToUpdate, filename, packages);
            break;
        case 3:
            updatePackagePrice(scanner, packageToUpdate, filename, packages);
            break;
        case 4:
            updatePackageValidityDate(scanner, packageToUpdate, filename, packages);
            break;
        case 5:
            updatePackageDescription(scanner, packageToUpdate, filename, packages);
            break;
        case 6:
        	print.printSomething("Exit.");
            break;
        default:
        	print.printSomething("Invalid choice.");
    }
}
public static void updatePackageValidityDate(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new validity date (YYYY-MM-DD): ");
    String newValidityDate = scanner.nextLine();
    packageToUpdate.setValidityPeriod(newValidityDate);
    print.printSomething("Validity date is updating successfully.");

    
    savePackagesToFile(filename, packages);
}


public static void updatePackageDescription(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new description: ");
    String newDescription = scanner.nextLine();
    packageToUpdate.setDescription(newDescription);
    print.printSomething("Description is updating successfully.");

    
    savePackagesToFile(filename, packages);
}

public static void updatePackagePrice(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new price: ");
    double newPrice = scanner.nextDouble();
    scanner.nextLine(); // Consume newline character
    packageToUpdate.setPrice(newPrice);
    print.printSomething("Price is updating successfully.");

    savePackagesToFile(filename, packages);
}

public static void updatePackageId(Scanner scanner, Paackage packageToUpdate, String filename) {
    int newId;
    do {
    	 print.printSomething("Enter a new ID: ");
        newId = scanner.nextInt();
        scanner.nextLine(); 

        if (!isPackageIdExists(filename, newId)) {
            packageToUpdate.setId(newId);
            print.printSomething("ID is updating successfully.");
            break;
        } else {
        	 print.printSomething("ID already exists in the file. Please enter a new ID.");
        }
    } while (true);
}




public static void viewAllPackages(List<Paackage> packages) {
    if (packages.isEmpty()) {
    	print.printSomething("No packages found in the file.");
        return;
    }

    print.printSomething(ALL_PACKAGES);
    for (Paackage p : packages) {
    	print.printSomething("ID: " + p.getId() +
                "  Name: " + p.getTitle() +
                "  Description: " + p.getDescription() +
                "  Price: " + p.getPrice() +
                "  Validity Date: " + p.getValidityPeriod());
    }
}


public static void updatePackageTitle(Scanner scanner, Paackage packageToUpdate, String filename, List<Paackage> packages) {
	print.printSomething("Enter a new title: ");
    String newTitle = scanner.nextLine();
    packageToUpdate.setTitle(newTitle);
    print.printSomething("The title is updating successfully.");

   
    savePackagesToFile(filename, packages);
}

public static Paackage findPackageById(List<Paackage> packages, int packageId) {
    for (Paackage p : packages) {
        if (p.getId() == packageId) {
            return p;
        }
    }
    return null; 
}

}


