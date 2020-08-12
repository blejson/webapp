package com.blejson.webapp.bootstrap;

import com.blejson.webapp.domain.*;
import com.blejson.webapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostMessageRepository postMessageRepository;

    public BootStrapData(UserRepository userRepository, PostMessageRepository postMessageRepository) {
        this.userRepository = userRepository;
        this.postMessageRepository = postMessageRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin","admin", "ADMIN");
        admin.setActive(true);
        userRepository.save(admin);

        PostMessage postMessage = new PostMessage("Witam", admin);
        postMessageRepository.save(postMessage);
        PostMessage postMessage1 = new PostMessage("Nazywam się Jacek Bleja, to jest mój testowy post", admin);
        postMessageRepository.save(postMessage1);
        PostMessage postMessage2 = new PostMessage("Ej, kiedy melanż?", admin);
        postMessageRepository.save(postMessage2);

        System.out.println("Server ready, now you can start testing your app!");
    }
}
