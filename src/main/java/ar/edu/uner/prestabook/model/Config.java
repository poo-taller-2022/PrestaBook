package ar.edu.uner.prestabook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "configs")
public class Config {

    @Id
    @Column(unique = true, nullable = false, name = "key_col")
    private String key;
    @Column(name = "value_col")
    private String value;

}
