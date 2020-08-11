package com.blejson.webapp.bootstrap;

import com.blejson.webapp.domain.Author;
import com.blejson.webapp.domain.Book;
import com.blejson.webapp.domain.Publisher;
import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.AuthorRepository;
import com.blejson.webapp.repositories.BookRepository;
import com.blejson.webapp.repositories.PublisherRepository;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final UserRepository userRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        User admin = new User("admin","admin", "ADMIN","admin@test.pl");
        admin.setActive(true);
        userRepository.save(admin);
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Desing", "123456");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Developement without EJB", "123456789");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Publisher count: "+publisherRepository.count());
        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Publisher Number of Books: "+publisher.getBooks().size());
    }
}
