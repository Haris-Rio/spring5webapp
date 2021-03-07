package guru.springframework.spring5webapp.boostrap;

import org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	
	private final AuthorRepository authorRepository;
	
	private final BookRepository bookRepository;
	
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository= publisherRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		
		Publisher publisher = new Publisher();
		publisher.setAddressLine1("SouthCarolina");
		publisher.setCity("LA");
		publisher.setName("Rick");
		publisherRepository.save(publisher);
		
		System.out.println("Publisher Repository count : "+ publisherRepository.count());
		Author eric = new Author("Eric","Evans");
		Book ddd = new Book("Domain driven design","5464313");

		
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		publisherRepository.save(publisher);
		
		Author rod = new Author("Rod","Johnson");
		Book noEJB = new Book("J2EE development without EJB", "6468784764");
		
		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);
		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
		publisherRepository.save(publisher);
		
		System.out.println("Started in BootStrap");
		System.out.println("Number of Books in DB is " + bookRepository.count());
		System.out.println("Size of data in publisher repository is "+publisher.getBooks().size());
	}

}
