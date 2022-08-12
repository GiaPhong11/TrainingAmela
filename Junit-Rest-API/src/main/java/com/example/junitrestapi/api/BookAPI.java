package com.example.junitrestapi.api;

import com.example.junitrestapi.entity.BookEntity;
import com.example.junitrestapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*import javax.validation.Valid;*/
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookAPI {

    final
    BookRepository bookRepository;

    public BookAPI(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<BookEntity> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public BookEntity getBookById(@PathVariable("id") Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping
    public BookEntity createBook (@RequestBody @Validated BookEntity book){
        return bookRepository.save(book);
    }

    @PutMapping
    public  BookEntity updateBook(@RequestBody @Validated BookEntity book){
        if(book == null || book.getId() == null ){
            throw new NullPointerException("Book or ID must not be null!!");
        }
        Optional<BookEntity> optionalBook = bookRepository.findById(book.getId());
        if(!optionalBook.isPresent()){
            throw new NullPointerException("Book with id " + book.getId()+ " does not exist!");
        }
        BookEntity bookUpdate = optionalBook.get();
        bookUpdate.setName(book.getName());
        bookUpdate.setRating(book.getRating());
        bookUpdate.setSummary(book.getSummary());
        return bookRepository.save(bookUpdate);
    }

}
