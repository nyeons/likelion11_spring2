package likelion.springbootnyong.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
새로운 값 타입을 직접 정의해서 사용
값 타입을 사용하면 주소를 포함한 클래스 내에, 주소의 필드들이 그대로 표시
 Address가 아닌 city, street, zipcode 가 바로 뜬다.
 */
@Embeddable
 // 접근자와 설정자 자동 생성
@Data
//클래스의 모든 필드에 대한 생성자 자동 생성
@AllArgsConstructor
//파라미터가 없는 기본 생성자 생성
@NoArgsConstructor
public class Address {
    private String city;
    private String state;
    private String street;
    private String zipcode;
}

