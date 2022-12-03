
insert into continue_watching(account, movie_episode)
values	(1, 1),
		(1, 3),
		(1, 5),
		(1, 7),
		(1, 9),
		(1, 11),
		(1, 13),
		(1, 15),
		(1, 17),
		(1, 19),
		(1, 21),
		(1, 23),
		(1, 25),
		(1, 27),
		(1, 29),
		(1, 31),	
		(1, 33),
		(1, 55),
		(1, 67),
		(1, 79),
		(1, 91)
go

insert into [watch_list](account, movie, status)
values	(1, 1, 1),
		(1, 2, 1),
		(1, 3, 2),
		(1, 4, 3),
		(1, 5, 4),
		(1, 6, 5)
go

insert into movie_rate(account, movie, rate)
values	(1, 1, 2),
		(1, 2, 3),
		(1, 3, 1),
		(1, 4, 5),
		(1, 6, 4)
go

insert into movie_view (movie)
values	(1), (1), (2), (3), (4), (5), (3), (3), (3), (4)
go

select * from [account]
select * from [movie]
select * from [movie_view]
select * from [continue_watching]
select * from [watch_list]
select * from [notification_movie]
select * from [movie_episode]
select * from [coin_transaction_history]
select * from [movie_purchase_history]
select * from [comment_movie_detail]

insert into [notification_movie](account, movie_episode, description, new_noti)
values	(1, 1, 'Full Movie Available NOW!', 1),
	    (1, 3, 'Full Movie Available NOW!', 1),
		(1, 7, 'Full Movie Available NOW!', 1),
		(1, 9, 'Full Movie Available NOW!', 1),
		(1, 34, 'Episode 2 Available NOW!', 1)

select * from [comment_movie]
delete from [movie_view]
delete from comment_movie_detail
delete from comment_movie
where id <> 1
DBCC CHECKIDENT ('comment_movie', RESEED, 1)
GO

insert into [movie_view](view_date, movie)
values	('2022-11-22 21:55:07.497', 19)

insert into [comment_movie] (account, movie_episode, text, spoil)
values	(1, 1, 'hello', 0)

insert into [account]([username], [email], [password_hash], [role])
values	(N'Moder', 'mod@gmail.com', '$2a$10$HhjLSruiV5.GJ0xi3DSKiOslw9WvzcI6PkVIf47uCqT7dwnMD6OVG', 2)

SELECT * FROM [comment_movie]
WHERE movie_episode = 1 AND parent_cmt IS NULL 
ORDER BY timestamp DESC 
OFFSET 0 * 1 rows FETCH NEXT 2 ROWS ONLY   

select * from [account]