INSERT INTO reviews(movie_id, user_id, rating) VALUES(50, 1, 'Cool');
INSERT INTO reviews(movie_id, user_id, rating) VALUES(52, 1, 'Cool');
INSERT INTO reviews(movie_id, user_id, rating) VALUES(54, 1, 'Sucks');

INSERT INTO reviews(movie_id, user_id, rating) VALUES(50, 2, 'Cool');
INSERT INTO reviews(movie_id, user_id, rating) VALUES(52, 2, 'Cool');
INSERT INTO reviews(movie_id, user_id, rating) VALUES(54, 2, 'Cool');

SELECT * FROM reviews;

SELECT movie_id, user_id, rating, review_id FROM reviews WHERE user_id = 1 OR user_id = 2 GROUP BY movie_id, user_id, review_id;
SELECT movie_id FROM reviews WHERE user_id = 1 OR user_id = 2 GROUP BY rating;
SELECT COUNT(*), rating, user_id, movie_id FROM reviews GROUP BY movie_id, rating;
SELECT COUNT(*) FROM reviews WHERE user_id = 1 OR user_id = 2 GROUP BY movie_id, rating;

SELECT COUNT(*) FROM reviews WHERE user_id = 1;


--
SELECT t1.rating AS user1Rating, t2.rating AS user2Rating FROM reviews t1 INNER JOIN reviews t2 ON t2.user_id = 2 WHERE t1.rating = t2.rating;

SELECT * FROM reviews WHERE user_id = 1 UNION ON (SELECT * FROM reviews WHERE user_id = 2) ORDER BY user_id;