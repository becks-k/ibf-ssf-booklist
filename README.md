## Task
Create a book list. Users should be able to add new books to the list with the following properties:
- Title
- ID (to be randomly populated based on author name)
- Author
- ISBN (5 digits long)
- Price (>$0)
  
All fields are mandatory. 

### Controller
- The controller should implement GET and POST requests.
- Utilize query strings and path variables in your controller methods to retrieve book details and handle navigation between pages.

### Thymeleaf Templates
- Display the list of books (books.html)
- View details of a specific book (book-details.html)
- Add a new book (add-book.html)
- Include hyperlinks in your Thymeleaf templates to navigate between different pages of the bookstore application.

### Validation
Implement validation for the book form fields to ensure that required information is provided and in the correct format (e.g., ISBN format, non-empty fields).