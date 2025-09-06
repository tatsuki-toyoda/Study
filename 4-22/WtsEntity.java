package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "water_server")   
public class WtsEntity  {

  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

 
  @Column(name = "server_name")
  private String serverName;

  
  @Column(name = "maker")
  private String maker;

  @Column(name = "price")
  private Integer price;
}
