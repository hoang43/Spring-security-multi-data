package com.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Report {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
}
