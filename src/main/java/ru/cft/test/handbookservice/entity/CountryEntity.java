package ru.cft.test.handbookservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "country", schema = "handbook_service")
public class CountryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 8240541876590409454L;

    @Column(name = "country_name", unique = true)
    @NonNull
    @NotEmpty
    private String name;
}

