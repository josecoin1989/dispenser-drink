package com.drink.dispenser.config;

import com.drink.dispenser.services.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitApplication {

    Logger logger = LoggerFactory.getLogger(InitApplication.class);

    @Autowired
    private MachineService machineService;


    @EventListener
    @Transactional
    public void appReady(final ApplicationReadyEvent event) throws Exception {

        try {

            this.machineService.startMachine();
        }catch (Exception ex){
            this.logger.error("There was an error starting the machine");
        }

    }
}