package hello.vuespring.repository;

import hello.vuespring.entity.OverallRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<OverallRank, Long> {
}
