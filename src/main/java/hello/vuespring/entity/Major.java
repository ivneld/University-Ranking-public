package hello.vuespring.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "major")
@Entity(name = "major")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major {

    @Id
    @GeneratedValue
    @Column(name = "major_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL)
    List<OverallRank> overallRanks = new ArrayList<>();

    public Major(String name) {
        this.name = name;
    }
}
