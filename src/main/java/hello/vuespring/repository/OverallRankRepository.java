package hello.vuespring.repository;

import hello.vuespring.entity.OverallRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverallRankRepository extends JpaRepository<OverallRank, Long> {

}
