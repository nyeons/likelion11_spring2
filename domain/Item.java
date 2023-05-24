package likelion.springbootnyong.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Item {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItem = new ArrayList<>();
    private String brand;
    private String name;

    private Integer price;
    private Integer stock;


    //비즈니스 로직
    public void addStock(int quantity){
        this.stock+=quantity;
    }
    public void removeStock(int quantity){
        int restStock=this.stock-quantity;
        if(restStock<0){
            throw new IllegalStateException("need more stock");
        }
        this.stock=restStock;
    }

    public int getStockQuantity() {
        return 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public <E> List<E> getOrderItem() {
        return null;
    }
}
