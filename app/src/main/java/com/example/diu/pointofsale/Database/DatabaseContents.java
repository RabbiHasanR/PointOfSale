package com.example.diu.pointofsale.Database;

public enum DatabaseContents {
    DATABASE("pointOfSale.db"),
    TABLE_USER("user"),
    TABLE_PRODUCT_CATALOG("product_catalog"),
    TABLE_STOCK("stock"),
    TABLE_SALE("sale"),
    TABLE_SALE_LINEITEM("sale_lineitem"),
    TABLE_STOCK_SUM("stock_sum"),
    LANGUAGE("language");

    private String name;

    /**
     * Constructs DatabaseContents.
     * @param name name of this content for using in database.
     */
    private DatabaseContents(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
