package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    
    private String city;
    private String state;
    private String street;
    private String zipcode;
    
    @OneToOne(mappedBy = "delivery")
    private Order order;

    public static Delivery createDelivery(Order order, String city, String state, String street, String zipcode) {
        return null;
    }

    private enum DeliveryStatus{
        ESTABLISHED, PROGRESS, DONE
    }
}
