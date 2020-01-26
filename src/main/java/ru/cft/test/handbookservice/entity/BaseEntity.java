package ru.cft.test.handbookservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Lyubov Bochkareva
 * @since 24.01.20.
 */

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
