package Ex04;

import java.io.*;
import java.util.*;

public class DualMatrixTransport {

    private static class Node {
        int row;
        int column;
        int cost;

        Node(int row, int column, int cost) {
            this.row = row;
            this.column = column;
            this.cost = cost;
        }
    }

    private static class TransportData {
        int[] supply;
        int[] demand;
        int[][] costMatrix;
        boolean dummyDestination;
        boolean dummySource;

        TransportData(int[] supply, int[] demand, int[][] costMatrix, boolean dummyDestination, boolean dummySource) {
            this.supply = supply;
            this.demand = demand;
            this.costMatrix = costMatrix;
            this.dummyDestination = dummyDestination;
            this.dummySource = dummySource;
        }
    }

    private static TransportData balanceProblem(int[] supply, int[] demand, int[][] costMatrix, int originalRows, int originalCols) {
        int totalSupply = Arrays.stream(supply).sum();
        int totalDemand = Arrays.stream(demand).sum();

        // cloning arrays to avoid modifying the originals
        int[] balancedSupply = Arrays.copyOf(supply, supply.length);
        int[] balancedDemand = Arrays.copyOf(demand, demand.length);
        int[][] balancedCostMatrix = new int[costMatrix.length][costMatrix[0].length];

        // cloning the cost matrix
        for (int i = 0; i < costMatrix.length; i++) {
            balancedCostMatrix[i] = Arrays.copyOf(costMatrix[i], costMatrix[i].length);
        }

        boolean dummyDestination = false;
        boolean dummySource = false;

        if (totalSupply > totalDemand) {
            // excess supply: add dummy destination
            int difference = totalSupply - totalDemand;
            balancedDemand = Arrays.copyOf(balancedDemand, balancedDemand.length + 1);
            balancedDemand[balancedDemand.length - 1] = difference; // dummy demand

            // add a new column of dummy costs (0) for all supply points
            for (int i = 0; i < balancedCostMatrix.length; i++) {
                balancedCostMatrix[i] = Arrays.copyOf(balancedCostMatrix[i], balancedCostMatrix[i].length + 1);
                balancedCostMatrix[i][balancedCostMatrix[i].length - 1] = 0;
            }

            dummyDestination = true;
            System.out.println("\nunbalanced problem detected (excess supply). dummy destination added.");
        } else if (totalDemand > totalSupply) {
            // excess demand: add dummy source
            int difference = totalDemand - totalSupply;
            balancedSupply = Arrays.copyOf(balancedSupply, balancedSupply.length + 1);
            balancedSupply[balancedSupply.length - 1] = difference; // dummy supply

            // add a new row of dummy costs (0) for all demand points
            int[][] newCostMatrix = new int[balancedCostMatrix.length + 1][balancedCostMatrix[0].length];
            for (int i = 0; i < balancedCostMatrix.length; i++) {
                System.arraycopy(balancedCostMatrix[i], 0, newCostMatrix[i], 0, balancedCostMatrix[i].length);
            }
            // dummy row with zero costs
            for (int j = 0; j < balancedCostMatrix[0].length; j++) {
                newCostMatrix[newCostMatrix.length - 1][j] = 0;
            }
            balancedCostMatrix = newCostMatrix;

            dummySource = true;
            System.out.println("\nunbalanced problem detected (excess demand). dummy source added.");
        } else {
            System.out.println("\nproblem is already balanced.");
        }

        return new TransportData(balancedSupply, balancedDemand, balancedCostMatrix, dummyDestination, dummySource);
    }

    private static int solveDualTransport(int[] supply, int[] demand, int[][] costMatrix, int[][] transportPlan) {
        int rows = supply.length;
        int cols = demand.length;
        int totalCost = 0;

        // initialize copies of supply and demand
        int[] remainingSupply = Arrays.copyOf(supply, rows);
        int[] remainingDemand = Arrays.copyOf(demand, cols);

        // use a list of nodes sorted by ascending cost
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nodes.add(new Node(i, j, costMatrix[i][j]));
            }
        }
        nodes.sort(Comparator.comparingInt(n -> n.cost));

        // process transport
        for (Node node : nodes) {
            int i = node.row;
            int j = node.column;

            if (remainingSupply[i] > 0 && remainingDemand[j] > 0) {
                int amount = Math.min(remainingSupply[i], remainingDemand[j]);
                transportPlan[i][j] = amount;
                remainingSupply[i] -= amount;
                remainingDemand[j] -= amount;
                totalCost += amount * costMatrix[i][j];
            }

            // stop if all supply or demand is fulfilled
            boolean allSupplyFulfilled = Arrays.stream(remainingSupply).allMatch(s -> s == 0);
            boolean allDemandFulfilled = Arrays.stream(remainingDemand).allMatch(d -> d == 0);
            if (allSupplyFulfilled || allDemandFulfilled) {
                break;
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the file name containing the data: ");
        String fileName = scanner.nextLine();

        try {
            // read the file
            BufferedReader reader = new BufferedReader(new FileReader("./Ex04/" + fileName + ".txt"));
            String[] firstLine = reader.readLine().trim().split("\\s+");
            int originalRows = Integer.parseInt(firstLine[0]); // number of original supply points
            int originalCols = Integer.parseInt(firstLine[1]); // number of original demand points

            // read the supply
            int[] supply = new int[originalRows];
            for (int i = 0; i < originalRows; i++) {
                supply[i] = Integer.parseInt(reader.readLine().trim());
            }

            // read the demand
            int[] demand = new int[originalCols];
            for (int i = 0; i < originalCols; i++) {
                demand[i] = Integer.parseInt(reader.readLine().trim());
            }

            // read the costs
            int[][] costMatrix = new int[originalRows][originalCols];
            for (int i = 0; i < originalRows; i++) {
                String[] line = reader.readLine().trim().split("\\s+");
                for (int j = 0; j < originalCols; j++) {
                    costMatrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            reader.close();

            // balance the problem if needed
            TransportData transportData = balanceProblem(supply, demand, costMatrix, originalRows, originalCols);

            // solve the problem using the dual method
            int[][] transportPlan = new int[transportData.supply.length][transportData.demand.length];
            int totalCost = solveDualTransport(transportData.supply, transportData.demand, transportData.costMatrix, transportPlan);

            // display results with indication of dummies
            System.out.println("\noptimal solution:");
            System.out.println("total cost: " + totalCost);
            System.out.println("transported quantities:");

            // prepare labels for destinations
            String[] destinationLabels = new String[transportData.demand.length];
            for (int j = 0; j < transportData.demand.length; j++) {
                if (j < originalCols) {
                    destinationLabels[j] = "D" + (j + 1);
                } else {
                    destinationLabels[j] = "F"; // dummy destination
                }
            }

            // print destination header
            System.out.print("\t");
            for (String label : destinationLabels) {
                System.out.print(label + "\t");
            }
            System.out.println();

            // print transport matrix with source labels
            for (int i = 0; i < transportPlan.length; i++) {
                String sourceLabel = (i < originalRows) ? "S" + (i + 1) : "F"; // dummy source
                System.out.print(sourceLabel + "\t");
                for (int j = 0; j < transportPlan[0].length; j++) {
                    System.out.print(transportPlan[i][j] + "\t");
                }
                System.out.println();
            }

            // inform about dummy sources or destinations
            if (transportData.dummyDestination) {
                System.out.println("\ndummy destination added to balance the problem.");
            }
            if (transportData.dummySource) {
                System.out.println("dummy source added to balance the problem.");
            }

        } catch (IOException e) {
            System.out.println("error reading the file: " + e.getMessage());
        }
    }
}
