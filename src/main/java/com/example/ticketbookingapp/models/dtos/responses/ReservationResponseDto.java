package com.example.ticketbookingapp.models.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationResponseDto {
    private BigDecimal amountToPay;
    private LocalDateTime expirationTime;

    public ReservationResponseDto(BigDecimal amountToPay, LocalDateTime expirationTime) {
        this.amountToPay = amountToPay;
        this.expirationTime = expirationTime;
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(BigDecimal amountToPay) {
        this.amountToPay = amountToPay;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
