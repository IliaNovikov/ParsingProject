package com.example.parsingproject;

public class Currency {
    private String name;
    private String purchasePrice;
    private String purchaseBank;
    private String salePrice;
    private String saleBank;
    private String centralBankPrice;

    public Currency(String name, String purchasePrice, String purchaseBank, String salePrice, String saleBank, String centralBankPrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.purchaseBank = purchaseBank;
        this.salePrice = salePrice;
        this.saleBank = saleBank;
        this.centralBankPrice = centralBankPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseBank() {
        return purchaseBank;
    }

    public void setPurchaseBank(String purchaseBank) {
        this.purchaseBank = purchaseBank;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleBank() {
        return saleBank;
    }

    public void setSaleBank(String saleBank) {
        this.saleBank = saleBank;
    }

    public String getCentralBankPrice() {
        return centralBankPrice;
    }

    public void setCentralBankPrice(String centralBankPrice) {
        this.centralBankPrice = centralBankPrice;
    }
}
