BEGIN TRANSACTION;

DROP TABLE IF EXISTS reviews;


CREATE TABLE reviews (
	review_id SERIAL PRIMARY KEY,
	movie_id int,
	user_id int,
	movie_title varchar(50),
	rating varchar(5),
	
	CONSTRAINT PK_review PRIMARY KEY (review_id)
	--CONSTRAINT FK_review
);