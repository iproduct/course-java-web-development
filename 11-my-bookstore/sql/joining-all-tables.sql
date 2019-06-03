use javaweb;
select book.title, 
	CONCAT_WS(' ', author.first_name, author.last_name) as author_name,
    publisher.`name` as publisher,
    `format`.`name` as `format`,
    price, isbn
from book 
	JOIN book_author on  book.id = book_author.book_id 
    JOIN author on book_author.author_id  = author.id
	JOIN publisher on  book.publisher = publisher.id 
	JOIN `format` on  book.`format` = `format`.id
ORDER BY author.last_name DESC, author.first_name;