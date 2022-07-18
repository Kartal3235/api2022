package pojos;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingdatesPojo {


    //1) Tum keyler için private variable'lar oluşturuyoruz

    private String checkin;
    private String checkout;

    // 2) Tum parametrelerle ve parametresiz constructor'larımızı oluşturuyoruz.


    public BookingdatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingdatesPojo() {

    }

    //3) Getters ve Setters larımızı oluşturuyoruz

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
    public String toString() {
        return "BookingdatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
