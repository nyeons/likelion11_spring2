package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;
    //Order 엔티티 클래스의 member 필드에 대한 일대다 관계
    @OneToMany(mappedBy = "member")
    //orderList 필드는 Order 객체들의 리스트를 저장하는데 사용
    private List<Order> orderList = new ArrayList<>();
//createMember 메서드는 두 개의 매개변수를 받고 Member 객체를 생성하여 반환
    public static Member createMember(String name, Address address){
        //Member 클래스의 새로운 인스턴스를 생성하여 member 변수에 할당
        Member member= new Member();
        //필드에 전달받은 값을 각각 할당
        member.name=name;
        member.address=address;
        //새로운 Member 객체 생성되고 해당 객체는 호출자에게 반환
        return member;
    }
}
