package cn.lilongsha.hibernate.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author lilongsha
 * @Description
 * @Date 2021/1/29 2:27 下午
 */
@Entity
@Setter
@Getter
@Table(name = "phone_call")
public class Call {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Phone phone;

    @Column(name = "call_timestamp")
    private Date timestamp;

    private int duration;

    //Getters and setters are omitted for brevity
}
