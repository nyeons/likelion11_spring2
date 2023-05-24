package likelion.springbootnyong.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Member {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    public static Member createMember(String name, Address address){
        Member member= new Member();
        member.name=name;
        member.address=address;
        return member;
    }
}
