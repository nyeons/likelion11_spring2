package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static likelion.springbootnyong.domain.Delivery.DeliveryStatus.ESTABLISHED;

//클래스 위에 선언하여 이 클래스가 엔티티임을 알려준다.
@Entity
//무분별한 객체 생성에 대해 한번 더 체크할 수 있다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//각 필드값을 조회할 수 있는 getter를 자동 생성
@Getter
public class Delivery {
    //@Id는 해당 엔티티의 주요 키가 될 값을 지정
    //@GeneratedValue는 주요 키의 값을 위한 자동 생성 전략 명시
    @Id @GeneratedValue
    private Long id;
//Order엔티티에 있는 delivery 필드와 매핑
    @OneToOne(mappedBy = "delivery")
    private Order order;
//enum 이름을 DB에 저장
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    
    private String city;
    private String state;
    private String street;
    private String zipcode;

//주어진 주문 정보와 배송 관련 정보를 사용하여 새로운 Delivery 객체를 생성하고 반환
    public static Delivery createDelivery(Order order, String city, String state, String street, String zipcode) {
        Delivery delivery = new Delivery();
        delivery.order = order;
        delivery.deliveryStatus = ESTABLISHED;
        delivery.city = city;
        delivery.state = state;
        delivery.street = street;
        delivery.zipcode = zipcode;
        return delivery;
    }
//배송 상태를 표현하기 위해 사용
    public enum DeliveryStatus{
        ESTABLISHED, PROGRESS, DONE
    }
}
