import java.util.*;

public class InventoryManager {
    private Map<String , Product> inventory;
    private List<Sale> salesHistory;
    private Scanner scanner;
    public InventoryManager(){
        this.inventory = new HashMap<>();
        this.salesHistory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    public void run() {
        while (true) {
            System.out.println("-----------Inventory Management System-----------");
            System.out.println("1.Add new Product");
            System.out.println("2.Update Product Quantity");
            System.out.println("3.View All Products");
            System.out.println("4.Process sale");
            System.out.println("5.View Low Stock Products");
            System.out.println("6.View Sale Reports");
            System.out.println("7.Exit");
            System.out.println("Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    addProductMenu();
                    break;
                case 2:
                    updateProductQuantityMenu();
                    break;
                case 3:
                    viewAllProducts();
                    break;
                case 4:
                    processSaleMenu();
                    break;
                case 5:
                    viewLowStockProducts();
                    break;
                case 6:
                    viewSalesReport();
                    break;
                case 7:
                    System.out.println("Thank you for using inventory Management System");
                    return;
                default:
                    System.out.println("Invalid Choice: Try Again");
            }
        }
    }
    public void addProductMenu(){
        System.out.print("Enter Product ID");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Quantity");
        int quantity = scanner.nextInt();
        Product product = new Product(id,name,price,quantity);
        inventory.put(id,product);
        System.out.println("Product Added Successfully");
    }
    public void updateProductQuantityMenu(){
        System.out.print("Enter Product ID");
        String id = scanner.nextLine();
        Product product = inventory.get(id);
        if(product == null){
            System.out.println("Product Not Found");
            return;
        }
        System.out.println("Current Quantity: " + product.getQuantity());
        System.out.println("Enter New Quantity");
        int newQuantity = scanner.nextInt();
        product.setQuantity(newQuantity);
        System.out.println("Quantity Updated Successfully");
    }
    public void viewAllProducts(){
        if (inventory.isEmpty()){
            System.out.println("No Products in Inventory");
            return;
        }
        for(Product product : inventory.values()){
            System.out.println(product);

        }
    }
    public void processSaleMenu(){
        Map<String,Integer> salesItem = new HashMap<>();
        double total = 0;
        while (true){
            System.out.print("Enter Product ID(or Done to finish): ");
            String id = scanner.nextLine();
            if (id.equalsIgnoreCase("done")){
                break;
            }
            Product product = inventory.get(id);
            if (product == null){
                System.out.println("Product Not Found");
            }
            System.out.print("Enter Product Quantity");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if(quantity > product.getQuantity()){
                System.out.println("Insufficient Stock. Available Stock: " + product.getQuantity());
                continue;
            }
            salesItem.put(id,quantity);
            total += product.getPrice() * quantity;
            product.setQuantity(product.getQuantity() - quantity);
        }
        if (!salesItem.isEmpty()){
            Sale sale = new Sale(salesItem,total);
            salesHistory.add(sale);
            System.out.println("Sal Processed. Total: Ksh. " + String.format("%.2f"));
        }
    }
//    A mthod to view low  stock in inventory
    public void viewLowStockProducts(){
        System.out.print("Enter the Low Stock Threshold");
        int Threshold = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Products with Stock Below "+ Threshold);
        for (Product product : inventory.values()){
            if(product.getQuantity() < Threshold){
                System.out.println(product);

            }
        }
    }
    public void viewSalesReport(){
        if(salesHistory.isEmpty()){
            System.out.println("No Sales Recorded Yet");
            return;
        }
        double totalRevenue = 0;
        Map<String,Integer> productSales = new HashMap<>();
        for(Sale sale : salesHistory){
            totalRevenue += sale.getTotal();
            for(Map.Entry<String,Integer> entry : sale.getItems().entrySet()){
                productSales.merge(entry.getKey(),entry.getValue(),Integer::sum);

            }
        }
        System.out.println("Sales Report");
        System.out.println("Total Revenue: Ksh."+ String.format("%s.2f",totalRevenue));
        System.out.println("Products Sold.");
        for(Map.Entry<String,Integer> entry : productSales.entrySet()){
            Product product = inventory.get(entry.getKey());
            System.out.println(product.getName() + "-" + entry.getValue());

        }

    }

}
