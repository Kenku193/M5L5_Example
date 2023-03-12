package org.example;

import org.example.config.AppConfig;
import org.example.entity.Customer;
import org.example.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerService customerService = context.getBean(CustomerService.class);
        Customer customer = customerService.get(1L);
        System.out.println(customer);

        customer.setPassword("someNewPassword");
        System.out.println(customer);

        customerService.update(customer);
        System.out.print("UPDATED: ");
        System.out.println(customer);

    }
}
