CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `books` AS
    SELECT 
        `book`.`id` AS `id`,
        `book`.`title` AS `title`,
        CONCAT_WS(' ',
                `author`.`first_name`,
                `author`.`last_name`) AS `authors`,
        `format`.`name` AS `format`,
        `book`.`isbn` AS `isbn`,
        `publisher`.`name` AS `publisher`,
        `book`.`published_date` AS `published_date`,
        `book`.`price` AS `price`
    FROM
        ((((`book`
        JOIN `book_author` ON ((`book`.`id` = `book_author`.`book_id`)))
        JOIN `author` ON ((`book_author`.`author_id` = `author`.`id`)))
        JOIN `publisher` ON ((`book`.`publisher` = `publisher`.`id`)))
        JOIN `format` ON ((`book`.`format` = `format`.`id`)))
    ORDER BY `author`.`last_name` DESC , `author`.`first_name`