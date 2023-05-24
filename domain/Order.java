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
@Table(name="orders")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Order {
    @jakarta.persistence.Id
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id", insertable=false, updatable=false)
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList=new ArrayList<>();
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member){
        this.member = member;
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
        for(OrderItem orderItem:orderItems){
            order.orderItemList.add(orderItem);
            orderItem.setOrder(order);
        }
        return order;
    }
//    public void cancel()
}
