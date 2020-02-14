package model;

import java.util.Objects;

/**
 * POJO for BookingDates
 *
 * @author Faizan Javed
 */
public class BookingDates {
    private String checkin;
    private String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDates that = (BookingDates) o;
        return Objects.equals(checkin, that.checkin) &&
                Objects.equals(checkout, that.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkin, checkout);
    }
}
