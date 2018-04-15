package com.example.diu.pointofsale.Model.Sale;

public class QuickLoadSale extends Sale{
    private Double total;
    private Integer orders;

    /**
     *
     * @param id ID of this sale.
     * @param startTime
     * @param endTime
     * @param status
     * @param total
     * @param orders numbers of lineItem in this Sale.
     */
    public QuickLoadSale(int id, String startTime, String endTime, String status, Double total, Integer orders) {
        super(id, startTime, endTime, status, null);
        this.total = total;
        this.orders = orders;
    }

    @Override
    public int getOrders() {
        return orders;
    }

    @Override
    public double getTotal() {
        return total;
    }

}
