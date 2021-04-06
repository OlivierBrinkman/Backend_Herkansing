package com.customermanagment.sims.model.structures;
/**
 * Order_Summary_Structure Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class OrderSummaryStructure {

    //ATTRIBUTES
    double totalPrice;
    String newInventory;
    int amountProducts;
    String exVAT;
    String VAT;

    //CONSTRUCTORS
    public OrderSummaryStructure(double totalPrice, String newInventory, int amountProducts, String exVAT, String VAT) {
        this.totalPrice = totalPrice;
        this.newInventory = newInventory;
        this.amountProducts = amountProducts;
        this.exVAT = exVAT;
        this.VAT = VAT;
    }
    public OrderSummaryStructure() { }

    //GETTERS AND SETTERS
    public String getVAT() {
        return VAT;
    }
    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNewInventory() {
        return newInventory;
    }
    public void setNewInventory(String newInventory) {
        this.newInventory = newInventory;
    }

    public int getAmountProducts() {
        return amountProducts;
    }
    public void setAmountProducts(int amountProducts) {
        this.amountProducts = amountProducts;
    }

    public String getExVAT() {
        return exVAT;
    }
    public void setExVAT(String exVAT) {
        this.exVAT = exVAT;
    }

    //To string
    @Override
    public String toString() {
        return "Order_Summary_Structure{" +
                "totalPrice=" + totalPrice +
                ", newInventory='" + newInventory + '\'' +
                ", amountProducts=" + amountProducts +
                ", exVAT='" + exVAT + '\'' +
                ", VAT='" + VAT + '\'' +
                '}';
    }
}
