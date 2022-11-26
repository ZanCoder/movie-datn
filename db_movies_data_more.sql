
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

insert into [notification_movie](account, movie_episode, description, new_noti)
values	(1, 1, 'Full Movie Available NOW!', 1),
	    (1, 3, 'Full Movie Available NOW!', 1),
		(1, 7, 'Full Movie Available NOW!', 1),
		(1, 9, 'Full Movie Available NOW!', 1),
		(1, 34, 'Episode 2 Available NOW!', 1)

