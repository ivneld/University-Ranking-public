package hello.vuespring.repository.dao;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DuplicateUniversityDao {

    private String name;
    private Integer count;

    @QueryProjection
    public DuplicateUniversityDao(String name, Integer count) {
        this.name = name;
        this.count = count;
    }
}
