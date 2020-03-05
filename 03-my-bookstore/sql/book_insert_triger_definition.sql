CREATE DEFINER=`root`@`localhost` TRIGGER `book_BEFORE_INSERT` BEFORE INSERT ON `book` FOR EACH ROW BEGIN
   SET @sum = @sum + NEW.price;
END