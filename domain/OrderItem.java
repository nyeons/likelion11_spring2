package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class OrderItem {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;
    
    private Integer price;
    private Integer count;


    public static OrderItem createOrderItem(Item item, int orderPrice, int orderCount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.price = orderPrice;
        orderItem.count = orderCount;
        item.removeStock(orderCount);
        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
        order.getOrderItemList().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getOrderItem().add(this);
    }

    public int getTotalPrice() {
        return this.getPrice() * this.getCount();
    }

    public void cancel() {
        this.getItem().addStock(count);
    }
}

