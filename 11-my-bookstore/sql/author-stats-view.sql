CREATE OR REPLACE VIEW `author_stats` AS
select CONCAT_WS(' ', author.first_name, author.last_name) as `author`,
	count(book.id) as `count`, avg(book.price) `average_price`
from author 
LEFT JOIN book_author ON author.id = book_author.author_id
JOIN book on  book.id = book_author.book_id 
group by author.last_name, author.first_name;