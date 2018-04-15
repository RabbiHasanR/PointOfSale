package com.example.diu.pointofsale.Model.Sale;

import com.example.diu.pointofsale.Database.NoDaoSetException;
import com.example.diu.pointofsale.Database.Sale.SaleDatabase;

import java.util.Calendar;
import java.util.List;

public class SaleLedger {
    private static SaleLedger instance = null;
    private static SaleDatabase saleDao = null;

    private SaleLedger() throws NoDaoSetException {
        if (!isDaoSet()) {
            throw new NoDaoSetException();
        }
    }

    /**
     * Determines whether the DAO already set or not.
     * @return true if the DAO already set; otherwise false.
     */
    public static boolean isDaoSet() {
        return saleDao != null;
    }

    public static SaleLedger getInstance() throws NoDaoSetException {
        if (instance == null) instance = new SaleLedger();
        return instance;
    }

    /**
     * Sets the database connector.
     * @param dao Data Access Object of Sale.
     */
    public static void setSaleDao(SaleDatabase dao) {
        saleDao = dao;
    }

    /**
     * Returns all sale in the records.
     * @return all sale in the records.
     */
    public List<Sale> getAllSale() {
        return saleDao.getAllSale();
    }

    /**
     * Returns the Sale with specific ID.
     * @param id ID of specific Sale.
     * @return the Sale with specific ID.
     */
    public Sale getSaleById(int id) {
        return saleDao.getSaleById(id);
    }

    /**
     * Clear all records in SaleLedger.
     */
    public void clearSaleLedger() {
        saleDao.clearSaleLedger();
    }

    /**
     * Returns list of Sale with scope of time.
     * @param start start bound of scope.
     * @param end end bound of scope.
     * @return list of Sale with scope of time.
     */
    public List<Sale> getAllSaleDuring(Calendar start, Calendar end) {
        return saleDao.getAllSaleDuring(start, end);
    }
}
