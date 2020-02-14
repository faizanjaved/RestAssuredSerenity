package model;

import java.util.Objects;

/**
 * POJO for Booking API Response
 *
 * @author Faizan Javed
 */
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private BookingDates bookingdates;
    private boolean depositpaid;
    private String additionalneeds;

    public Booking() {
        this.firstname = "John";
        this.lastname = "Doe";
        this.totalprice = 100;
        this.bookingdates = new BookingDates("2020-03-03", "2020-04-04");
        this.depositpaid = true;
        this.additionalneeds = "Breakfast";
    }

    public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates==null ? new BookingDates("2020-03-03", "2020-04-04") : bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return totalprice == booking.totalprice &&
                depositpaid == booking.depositpaid &&
                Objects.equals(firstname, booking.firstname) &&
                Objects.equals(lastname, booking.lastname) &&
                Objects.equals(bookingdates, booking.bookingdates) &&
                Objects.equals(additionalneeds, booking.additionalneeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, totalprice, bookingdates, depositpaid, additionalneeds);
    }
}
