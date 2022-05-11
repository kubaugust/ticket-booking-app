package com.example.ticketbookingapp.models.keys;

import java.io.Serializable;
import java.util.Objects;

public class ScreeningSeatsPK implements Serializable {
    private Long idScreening;
    private Long idSeat;

    public ScreeningSeatsPK(Long idScreening, Long idSeat) {
        this.idScreening = idScreening;
        this.idSeat = idSeat;
    }

    public ScreeningSeatsPK() {
    }

    public long getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(Long idScreening) {
        this.idScreening = idScreening;
    }

    public long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Long idSeat) {
        this.idSeat = idSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningSeatsPK that = (ScreeningSeatsPK) o;
        return idScreening == that.idScreening && idSeat == that.idSeat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idScreening, idSeat);
    }
}
