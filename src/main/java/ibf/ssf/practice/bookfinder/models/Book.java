package ibf.ssf.practice.bookfinder.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class Book {
    
    private String id; // Auto generate author's name + 5 random digits, not part of POST request
    
    @NotEmpty(message = "Title is mandatory")
    @Size(min = 1, max = 64, message = "Title must be between 1 and 64 characters long")
    private String title;

    @NotEmpty(message = "Author's name is mandatory")
    @Size(min = 1, max = 20, message = "Author's first name must be between 1 and 20 characters long")
    private String author;

    @NotNull(message = "ISBN is mandatory")
    @Pattern(regexp = "\\d{5}", message = "ISBN should be 5 digits long")
    private String isbn; // 13 numbers
    
    @Min(value = 1, message = "Price must be greater than 0")
    @NotNull(message = "Price is mandatory")
    private Double price; // > 0

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", price=" + price
                + "]";
    }
    

}
