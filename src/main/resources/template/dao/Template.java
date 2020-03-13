package com.zglu.generator.{packageName}.dao;

import lombok.Data;

import javax.persistence.*;
{importString}
@Data
@Entity
@Table(name = "{tableName}")
public class {className}{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
{fieldString}
}
