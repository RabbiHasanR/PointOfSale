package com.example.diu.pointofsale.Model.Inventory;

import com.example.diu.pointofsale.Database.Inventory.InventoryDatabase;
import com.example.diu.pointofsale.Database.NoDaoSetException;

public class Inventory {
    private Stock stock;
    private ProductCatalog productCatalog;
    private static Inventory instance = null;
    private static InventoryDatabase inventoryDao = null;

    /**
     * Constructs Data Access Object of inventory.
     * @throws NoDaoSetException if DAO is not exist.
     */
    private Inventory() throws NoDaoSetException {
        if (!isDaoSet()) {
            throw new NoDaoSetException();
        }
        stock = new Stock(inventoryDao);
        productCatalog = new ProductCatalog(inventoryDao);
    }

    /**
     * Determines whether the DAO already set or not.
     * @return true if the DAO already set; otherwise false.
     */
    public static boolean isDaoSet() {
        return inventoryDao != null;
    }

    /**
     * Sets the database connector.
     * @param dao Data Access Object of inventory.
     */
    public static void setInventoryDao(InventoryDatabase dao) {
        inventoryDao = dao;
    }

    /**
     * Returns product catalog using in this inventory.
     * @return product catalog using in this inventory.
     */
    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    /**
     * Returns stock using in this inventory.
     * @return stock using in this inventory.
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the instance of this singleton class.
     * @return instance of this class.
     * @throws NoDaoSetException if DAO was not set.
     */
    public static Inventory getInstance() throws NoDaoSetException {
        if (instance == null)
            instance = new Inventory();
        return instance;
    }
}
