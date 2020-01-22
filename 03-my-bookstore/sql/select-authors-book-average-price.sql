select author.first_name, author.last_name, count(book.id), avg(book.price) 
from author 
LEFT JOIN book_author ON author.id = book_author.author_id
JOIN book on  book.id = book_author.book_id 
group by author.last_name, author.first_name;