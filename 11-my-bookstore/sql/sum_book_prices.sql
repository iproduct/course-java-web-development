CREATE DEFINER=`root`@`localhost` PROCEDURE `my_procedure_cursors`(OUT return_val DECIMAL(10,2))
BEGIN
DECLARE a,b INT; 
DECLARE c DECIMAL(10,2); 
DECLARE cur_1 CURSOR FOR 
SELECT book.price FROM book;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET b = 1;
SET c  = 0;
SET b = 0;
OPEN cur_1; 
WHILE b < 1 
DO
FETCH cur_1 INTO a;
SET c = c + a;
END WHILE;
CLOSE cur_1;
SET return_val = c;
END