import java.util.*;

import parts.*;
import simulation.Customer;
import simulation.IztechPCFactory;
import simulation.MarketingAnalyst;
import simulation.StorageChief;
public class Main {

	/**
	 * Return a random integer
	 * @param rangeStart - Inclusive start range
	 * @param rangeStop - Inclusive stop range
	 * @return A random integer.
	 */
	private static int randomRangeGenerator(int rangeStart, int rangeStop) {
		double protoInteger = Math.random();
		int integerResult = (int) Math.floor(protoInteger * ((rangeStop + 1) - rangeStart));
		return integerResult;
	}
	
	/**
	 * Determine the number of runs simulation will go through.
	 * @return the simulation size.
	 */
	private static int determineSimulationSize() {
		Scanner userInput = new Scanner(System.in);
		int simulationSize = -1;
		while (simulationSize <= 0) {
			System.out.println("Enter the number of random request cycles: ");
			if (userInput.hasNextInt()) {
				simulationSize = userInput.nextInt();
			} else {
				userInput.next();
			}
		}
		userInput.close();
		return simulationSize;
		
	}
	
	/**
	 * Generate an example output
	 * @param outputString - Output's main string, this will append to
	 * @param addition - Additional information
	 * @param lineCount - Count of lines to keep count of lines.
	 * @return
	 */
	private static String generateOutput(String outputString, String addition, int lineCount) {
		outputString += String.valueOf(lineCount) + ". " + addition + "\n";
		return outputString;
	}
	
	/**
	 * Generate the report
	 * @param customer - Customer object.
	 * @param factory - Factory object.
	 * @param masterChief - storageChief object.
	 * @return
	 */
	private static String generateReport(Customer customer, IztechPCFactory factory, StorageChief masterChief) {
		int[] factoryCounts = factory.itemCounts();
		masterChief.initiliseWarehouseCounts();
		String str = "";
		str += "\nREPORT:\n";
		str += "Amount of RAM in Factory line: " + ((Integer) factoryCounts[0]).toString() + "\n";
		str += "Amount of CPU in Factory line: " + ((Integer) factoryCounts[1]).toString() +"\n";
		str += "Amount of Graphics Card in Factory line: " + ((Integer) factoryCounts[2]).toString() + "\n";
		str += "Amount of Motherboard in Factory line: " + ((Integer) factoryCounts[3]).toString() + "\n";
		str += "Amount of Cache in Factory line: " + ((Integer) factoryCounts[4]).toString() + "\n\n";

		str += "Amount of RAM in RAM Warehouse: " + masterChief.getWarehouseCount("RAM") + "\n";
		str += "Amount of CPU in CPU Warehouse: " + masterChief.getWarehouseCount("CPU") +"\n";
		str += "Amount of Graphics Card in Graphics Card Warehouse: " + masterChief.getWarehouseCount("Graphics Card") + "\n";
		str += "Amount of Motherboard in Motherboard Warehouse: " + masterChief.getWarehouseCount("Motherboard") + "\n";
		str += "Amount of Cache in Cache Warehouse: " + masterChief.getWarehouseCount("Cache") + "\n\n";

		str += "Amount of RAM Sold: " + customer.getItemCounts("RAM") + "\n";
		str += "Amount of CPU Sold: " + customer.getItemCounts("CPU") +"\n";
		str += "Amount of Graphics Card Sold: " + customer.getItemCounts("Graphics Card") + "\n";
		str += "Amount of Motherboard Sold: " + customer.getItemCounts("Motherboard") + "\n";
		str += "Amount of Cache Sold: " + customer.getItemCounts("Cache") + "\n\n";
		return str;
	}
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		int numberOfRequests;
		numberOfRequests = determineSimulationSize();
		IProduct[] products = {
				new RAM(),
				new CPU(),
				new GraphicsCard(),
				new Motherboard(),
				new Cache()
		};
		MarketingAnalyst<IProduct> marketingAnalyst = new MarketingAnalyst<IProduct>();
		IztechPCFactory factory = new IztechPCFactory();
		StorageChief storageChief = new StorageChief();
		Customer customer = new Customer();
		String output = "";
		int lineCount = 0;
		for (int i = 0; i < numberOfRequests; i++) {
			int factorySelector = randomRangeGenerator(0, 2);
			switch(factorySelector) {
			case 0:
				int marketingIndex = randomRangeGenerator(0, 4);
				IProduct product0 = products[marketingIndex];
				marketingAnalyst.orderPart(product0, factory);
				lineCount++;
				output = generateOutput(output, ("Marketing Analyst requesting " 
													+ product0.getType() + ", SUCCESS, "
													+ product0.getType() + ", manufactured."), lineCount);
				break;
			case 1:
				IProduct product1 = storageChief.store(factory);
				String isSuccessful = (product1 != null) ? " SUCCESS " : " FAIL ";
				lineCount++;
				if (product1 != null) {
					output = generateOutput(output, ("Storage Chief requesting " 
														+ product1.getType() + isSuccessful
														+ product1.getType() + ", stored."), lineCount);
				} else {
					output = generateOutput(output, "Storage Chief FAIL", lineCount);
				}
				break;
			case 2:
				int customerIndex = randomRangeGenerator(0, 4);
				IProduct product2 = products[customerIndex];
				boolean success2 = customer.buy(product2, storageChief);
				String successCustomer = success2 ? " SUCCESS " : " FAIL " ;
				String isSold = success2 ? " sold" : " not sold";
				lineCount++;
				output = generateOutput(output, ("Customer requesting " 
						+ product2.getType() + successCustomer
						+ product2.getType() + isSold), lineCount);
				break;
			}
		}
		System.out.println(output);
		System.out.println(generateReport(customer, factory, storageChief));
	}

}
