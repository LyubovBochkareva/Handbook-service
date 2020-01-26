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
@Table(name = "city", schema = "handbook_service")
public class CityEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4814179599767359779L;

    @Column(name = "city_name", unique = true)
    @NonNull
    @NotEmpty
    private String name;
}

