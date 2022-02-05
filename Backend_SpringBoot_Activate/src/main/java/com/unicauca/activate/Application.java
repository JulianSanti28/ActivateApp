package com.unicauca.activate;

import com.unicauca.activate.model.Category;
import com.unicauca.activate.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class Application.
 *
 * @author jccalderon
 */
@SpringBootApplication
public class Application {

    //@Autowired
    //private static ICategoryService CategoryService;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


        
    }
}
