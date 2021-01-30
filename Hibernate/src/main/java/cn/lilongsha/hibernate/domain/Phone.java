package cn.lilongsha.hibernate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

/**
 * @Author lilongsha
 * @Description
 * @Date 2021/1/29 2:24 下午
 */
@NamedQueries({
        @NamedQuery(
                name = "get_phone_by_number",
                query = "select p from Phone p where p.number = :number"
        )
})
@Entity
@Setter
@Getter
@ToString
public class Phone {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>(  );

    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, Call> callHistory = new HashMap<>();

    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<>(  );

    //Getters and setters are omitted for brevity
}
