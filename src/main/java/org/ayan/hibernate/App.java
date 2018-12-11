package org.ayan.hibernate;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.ayan.hibernate.model.Book;
import org.ayan.hibernate.service.BookService;

public class App {

	public static void main(String[] args) {
		
		BookService bookService = new BookService();
		boolean flag = true;
		Scanner input = new Scanner(System.in);

		while (flag) {

			System.out.println("1. Add Book");
			System.out.println("2. Search Book");
			System.out.println("3. Delete Book");
			System.out.println("4. Sort by title");
			System.out.println("9. Exit Application");
			System.out.println("Enter option :: ");

			String choice = input.next();

			switch (choice) {
			case "1":
				bookService.persist(createBook(input));
				System.out.println("Added book");
				break;
			case "2":
				List<Book> searchedBooks = bookService.findByTitle(getTitle(input));
				for (Book book : searchedBooks) {
					System.out.println("Found book :: " + book.getTitle());
				}
				break;
			case "3":
				bookService.delete(deleteBook(input));
				System.out.println("Deleted book");
				break;
			case "4":
				sortByTitle(bookService.findAll());
				break;

			case "9":
				System.out.println("Exiting application");
				flag = false;
				input.close();
				break;
			default:
				System.out.println("Invalid choice");

			}

		}

		input.close();
		
	}


	private static String getTitle(Scanner input) {
		System.out.println("Input book title to search :: ");
		String title = input.next();

		return title;
	}


	private static void sortByTitle(List<Book> books) {
		
        List<Book> sortedByTitle = books.stream().sorted((o1, o2)->o1.getTitle().compareTo(o2.getTitle())).collect(Collectors.toList());
		
		
		
		for (Book book : sortedByTitle) {
			System.out.println("Found book :: " + book.getTitle());
		}
		
	}

	private static String deleteBook(Scanner input) {
		
		System.out.println("Input book id to delete :: ");
		String bookid = input.next();

		return bookid;
		
	}

	private static Book createBook(Scanner input) {
		Book book = new Book();
		System.out.println("Input book id :: ");
		String bookid = input.next();
		book.setId(bookid);
		System.out.println("Input book title :: ");
		String bookTitle = input.next();
		book.setTitle(bookTitle);

		return book;
		
	}
	
	

	/** table creation statement: 
	 CREATE TABLE `library`.`book` ( id VARCHAR(50) NOT NULL, title
	 VARCHAR(20) default NULL, author VARCHAR(50) default NULL, PRIMARY KEY
	 (id) );
	 */
}
