package hello.vuespring.repository;

import hello.vuespring.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {

    @Query("select m from major m where name = :name")
    public Major findByName(@Param("name") String name);
}
