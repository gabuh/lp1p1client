package br.edu.ifsp.lp1p1client.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Library implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        GuestUI.show();
    }
}
