CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `author_stats` AS
    SELECT 
        CONCAT_WS(' ',
                `author`.`first_name`,
                `author`.`last_name`) AS `author`,
        COUNT(`book`.`id`) AS `count`,
        AVG(`book`.`price`) AS `average_price`
    FROM
        ((`author`
        LEFT JOIN `book_author` ON ((`author`.`id` = `book_author`.`author_id`)))
        JOIN `book` ON ((`book`.`id` = `book_author`.`book_id`)))
    GROUP BY `author`.`last_name` , `author`.`first_name`