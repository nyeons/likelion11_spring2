package likelion.springbootnyong.repository;

import likelion.springbootnyong.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
