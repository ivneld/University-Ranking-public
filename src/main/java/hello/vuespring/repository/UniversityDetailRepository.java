package hello.vuespring.repository;

import hello.vuespring.entity.UniversityDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityDetailRepository extends JpaRepository<UniversityDetail, Long> {
}
