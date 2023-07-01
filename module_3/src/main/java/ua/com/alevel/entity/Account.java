package ua.com.alevel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "accounts")
public class Account extends BaseEntity {

    private Long balance;

    @Column(unique = true)
    private Long number;

    @ManyToOne
    private User user;
}
