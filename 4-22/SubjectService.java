package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.WtsEntity;
import com.example.demo.form.WtsForm;
import com.example.demo.repository.WtsRepository;

@Service
public class SubjectService {   

    @Autowired
    private WtsRepository wtsRepository;

    
    public List<WtsEntity> searchAll() {
        return wtsRepository.findAll();
    }

    
    public void create(WtsForm wtsRequest) {
        
        if (wtsRequest.getPrice() != null && wtsRequest.getPrice() == 0) {
            throw new IllegalArgumentException("値段に0は指定できません");
        }

        WtsEntity server = new WtsEntity();
        server.setServerName(wtsRequest.getServerName());
        server.setMaker(wtsRequest.getMaker());
        server.setPrice(wtsRequest.getPrice());
        wtsRepository.save(server);
    }

    
    public WtsEntity findById(Integer id) {
        return wtsRepository.findById(id).get();
    }

    
    public void update(WtsForm wtsUpdateRequest) {
        
        if (wtsUpdateRequest.getPrice() != null && wtsUpdateRequest.getPrice() == 0) {
            throw new IllegalArgumentException("値段に0は指定できません");
        }

        WtsEntity server = findById(wtsUpdateRequest.getId());
        server.setServerName(wtsUpdateRequest.getServerName());
        server.setMaker(wtsUpdateRequest.getMaker());
        server.setPrice(wtsUpdateRequest.getPrice());
        wtsRepository.save(server);
    }

    
    public void delete(Integer id) {
        WtsEntity wts = findById(id);
        wtsRepository.delete(wts);
    }
}