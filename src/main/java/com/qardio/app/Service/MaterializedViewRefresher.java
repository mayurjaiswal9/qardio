package com.qardio.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class MaterializedViewRefresher {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Scheduled(cron="0 * * ? * *")
    public void refresh(){
        this.entityManager.createNativeQuery("call material_view_refresh();").executeUpdate();
        System.out.println("call material_view_refresh() - ");
    }

}
