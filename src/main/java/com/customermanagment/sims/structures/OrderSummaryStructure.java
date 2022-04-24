package com.customermanagment.sims.structures;
public class OrderSummaryStructure {

    double totalPrice;
    String newInventory;
    int amountProducts;
    String exVAT;
    String VAT;

    public OrderSummaryStructure() {
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setNewInventory(String newInventory) {
        this.newInventory = newInventory;
    }

    public void setAmountProducts(int amountProducts) {
        this.amountProducts = amountProducts;
    }

    public void setExVAT(String exVAT) {
        this.exVAT = exVAT;
    }

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