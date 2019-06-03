use javaweb;
CREATE VIEW books AS
select book.id as id, book.title as title, 
	CONCAT_WS(' ', author.first_name, author.last_name) as authors,
    `format`.`name` as `format`,
    isbn, publisher.`name` as publisher, published_date, price
from book 
	JOIN book_author on  book.id = book_author.book_id 
    JOIN author on book_author.author_id  = author.id
	JOIN publisher on  book.publisher = publisher.id 
	JOIN `format` on  book.`format` = `format`.id
ORDER BY author.last_name DESC, author.first_name;