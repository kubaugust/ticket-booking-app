package com.example.ticketbookingapp.helpers;

import java.math.BigDecimal;

public class AmountAndNumberOfTicketsWrapper {
    private BigDecimal amount;
    private int numberOfTickets;

    public AmountAndNumberOfTicketsWrapper(BigDecimal amount, int numberOfTickets) {
        this.amount = amount;
        this.numberOfTickets = numberOfTickets;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
