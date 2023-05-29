package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//Database에 생성될 table의 이름 지정
@Table(name="orders")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Order {
    @jakarta.persistence.Id
    @Id @GeneratedValue
    private Long id;

    //@ManyToOne은 해당 필드가 다른 엔티티와의 관계를 가지며, 여러 개의 Order 엔티티가 하나의 Member 엔티티를 참조할 수 있음을 나타내는 주석
    //fetch = FetchType.LAZY는 연관된 엔티티(Member)를 가져올 때 지연 로딩을 사용하도록 설정
    @ManyToOne(fetch = FetchType.LAZY)
    //member 필드와 매핑된 외래 키 지정
    //Order 엔티티와 Member 엔티티 연결
    @JoinColumn(name = "member_id")
    private Member member;
    //cascade = CascadeType.ALL는 Order 엔티티의 변경이 Delivery 엔티티에도 전파되도록 설정
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //delivery 필드와 매핑된 외래 키를 지정
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList=new ArrayList<>();
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member){
        this.member = member;
        //member 객체의 getOrderList() 메서드를 호출하여 현재 객체(this)를 OrderList에 추가
        member.getOrderList().add(this);
    }
    public static Order createOrder(Member member, OrderItem...orderItems){
        Order order = new Order();
        order.setMember(member);
        order.orderDate=LocalDateTime.now();
        order.orderStatus = OrderStatus.ORDERED;
        order.delivery=Delivery.createDelivery(order,
                member.getAddress().getCity(),
                member.getAddress().getState(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode());
        //OrderItem 객체 배열 orderItems 를 순회
        for(OrderItem orderItem:orderItems){
            //각 항목을 order 객체의 orderItemList에 추가
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }
        return order;
    }
    //주문 취소
    public void cancel() {
        //주문의 배송 상태를 확인하는 조건문
        if (delivery.getDeliveryStatus() == Delivery.DeliveryStatus.DONE) {
            throw new IllegalStateException("배송이 이미 완료됨");
        }
        //주문의 상태를 취소로 변경
        this.orderStatus = OrderStatus.CANCELED;
        //주문에 속한 각 주문 항목(OrderItem)을 순회하면서 cancel 메서드를 호출
        for (OrderItem orderItem : orderItemList) {
            orderItem.cancel();
        }
    }
//주문의 총 가격 계산하여 반환
    public int getTotalPrice() {
        int totalPrice = 0;
        //각 주문 항목의 가격을 총 가격에 더해줌
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
