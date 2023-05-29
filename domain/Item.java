package likelion.springbootnyong.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Setter는 멤버 변수에 값을 할당하기 위한 메서드
@Getter @Setter
@NoArgsConstructor
public class Item {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    //OrderItem 엔티티 클래스의 item 필드에 대한 일대다 관계
    //해당 필드가 다수의 OrderItem 객체를 참조할 수 있음
    @OneToMany(mappedBy = "item")
    //ArrayList 클래스는 초기화된 빈 리스트로 orderItem 필드를 초기화
    private List<OrderItem> orderItem = new ArrayList<>();
    private String brand;
    private String name;

    private Integer price;
    private Integer stock;


    //비즈니스 로직
    @Comment("재고 추가")
    //현재 객체의 stock 필드에 quantity 값을 더해줘서 재고가 추가됨
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    @Comment("재고 감소")
    public void removeStock(int stockQuantity) {
        //재고에서 감소된 값 계산
        int restStock = this.stock - stockQuantity;
        //재고가 부족한 경우
        if (restStock < 0) {
            throw new IllegalStateException("need more stock");
        }
        //stock 필드를 restStock 값으로 업데이트하여 재고 감소
        this.stock = restStock;
    }
}
