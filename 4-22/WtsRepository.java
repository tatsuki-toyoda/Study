package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.WtsEntity;

/**
* WTS（ウォーターサーバー情報） Repository
*/
@Repository
public interface WtsRepository extends JpaRepository<WtsEntity, Integer> {
}
