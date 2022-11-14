SELECT * FROM reviews;

SELECT count(*) FROM reviews WHERE user_id = 1;

INSERT INTO reviews(movie_id, user_id, rating) VALUES(56, 1, 'Cool');


--does this work with different movies being reviewed?
select count(*) from (
select movie_id, rating from reviews WHERE user_id = 1 OR user_id = 2 group by movie_id,rating having count(*)>1) as x

-- write statement that gets all movies both users have rated
--SELECT * FROM reviews WHERE user_id = 1;
select count(*) from (
select movie_id from reviews WHERE user_id = 1 OR user_id = 2 group by movie_id having count(*)>1) as x

select * from (select user_id, rating, movie_id from reviews) as yep