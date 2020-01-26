package ru.cft.test.handbookservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "serving_point", schema = "handbook_service")
public class ServingPointEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -959695198810026878L;

    @Column
    @NonNull
    @NotEmpty
    private String address;

    @Column
    @NonNull
    @NotEmpty
    private String name;

    @JoinColumn(name = "city_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private CityEntity cityEntity;

    @JoinColumn(name = "country_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private CountryEntity countryEntity;

    @JoinTable(
            inverseJoinColumns = @JoinColumn(name = "services_id"),
            joinColumns = @JoinColumn(name = "serving_point_id"),
            name = "serving_point_services",
            schema = "handbook_service")
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<ServicesEntity> servicesEntityList;
}
