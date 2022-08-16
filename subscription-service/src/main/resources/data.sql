create table subscription(book_id varchar(20),id int unique, subscriber_name varchar(200),date_subscribed date,date_returned date)
	
SELECT * FROM library.subscription;
INSERT INTO  subscription (book_id , id ,  subscriber_name  , date_subscribed , date_returned ) 
values ('102' , 1,'ACC' ,'2022-3-3', '2010-2-2');



