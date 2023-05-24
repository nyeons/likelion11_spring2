package likelion.springbootnyong.repository;

import likelion.springbootnyong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
