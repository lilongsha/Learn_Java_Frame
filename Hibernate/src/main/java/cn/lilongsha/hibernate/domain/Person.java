package cn.lilongsha.hibernate.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

/**
 * @Author lilongsha
 * @Description
 * @Date 2021/1/29 2:11 下午
 */
@NamedQueries({
        @NamedQuery(
                name = "get_person_by_name",
                query = "select p from Person p where name = :name",
                hints = {
                        @QueryHint(name = "javax.persistence.query.timeout", value = "200"),
                        @QueryHint(name = "org.hibernate.readOnly", value = "true")
                }
        )
        ,
        @NamedQuery(
                name = "get_read_only_person_by_name",
                query = "select p from Person p where name = :name",
                hints = {
                        @QueryHint(
                                name = "org.hibernate.readOnly",
                                value = "true"
                        )
                }
        )
})
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "sp_person_phones",
                procedureName = "sp_person_phones",
                parameters = {
                        @StoredProcedureParameter(
                                name = "personId",
                                type = Long.class,
                                mode = ParameterMode.IN
                        ),
                        @StoredProcedureParameter(
                                name = "personPhones",
                                type = Class.class,
                                mode = ParameterMode.REF_CURSOR
                        )
                }
        )
)
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Setter
@Getter
@ToString
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nickName;

    private String address;

    @Temporal(TemporalType.TIMESTAMP )
    private Date createdOn;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_id")
    private List<Phone> phones = new ArrayList<>();

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    private Map<AddressType, String> addresses = new HashMap<>();

    @Version
    private int version;

    //Getters and setters are omitted for brevity

    /*@Override
    public String toString() {
        try{
            return JSONObject.toJSONString(this).toString();
        }catch (Exception e){
            return super.toString();
        }
    }*/
}
