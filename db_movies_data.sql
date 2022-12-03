use db_movies
go

insert into [account_role]
values	('ADMIN'),
		('MOD'),
		('USER')
go

insert into [account]([username], [email], [password_hash], [role])
values	(N'sK0rpion', 'nhatphay722@gmail.com', '$2a$10$rh3IlC/fdXP8QhXY.0kvouhDEWpzwGlBCGYEiSa/JNgbb.h8QnYqK', 3), -- nhatphat0
		(N'Admin', 'admin@gmail.com', '$2a$10$ZkSRUbURvjSZyyNQdJYV1OTPOwqgXPy34/ROVV9Ug8LUm7oQsmcjO', 1), -- admin123
		(N'Modder', 'mod@gmail.com', '$2a$10$HhjLSruiV5.GJ0xi3DSKiOslw9WvzcI6PkVIf47uCqT7dwnMD6OVG', 2), -- mod123
		(N'Đỗ Kiều Phương', 'phuong@gmail.com', '$2a$10$Di.zPVTjpeXAmyxYNxSBY.YGOLRoTWe4.erltk/bRuEKZE8sWKHau', 3), -- phuong123
		(N'Lương Thị Thúy Lành', 'lanh@gmail.com', '$2a$10$xyLXAOOuP3xlIe3o.s38WewfvOPo55158vWIfL7gfl4NQFm2zHBe6', 3), -- lanh123
		(N'Nguyễn Văn Giang', 'giang@gmail.com', '$2a$10$7mwpU3eXpVCcQ7fBd./dcuVyN0IPLu5L5kLp4/L.SKRPCwvNJ4dYe', 3), -- giang123
		(N'Ngô Xuân Bảo Tâm', 'tam@gmail.com', '$2a$10$vHvqRMTKJLSJHEF2.qgg/.q2tDuqm0uhZm8tT4fY7pbm1/jQh/gSi', 3), -- tam123
		(N'Trần Đức Anh', 'anh@gmail.com', '$2a$10$5rS/d9jD4f4TWAh3DGhEQ.OhN8Z3h7GUlA4/H/MX9Txug0fF5XVOi', 3) -- anh123
go

insert into [genre]	([genre_name])
values	('Action'), -- 1
		('Action & Adventure'), -- 2
		('Adventure'), -- 3
		('Animation'), -- 4
		('Biography'), -- 5
		('Comedy'), -- 6
		('Crime'), -- 7
		('Documentary'), -- 8
		('Drama'), -- 9
		('Family'), -- 10
		('Fantasy'), -- 11
		('History'), -- 12
		('Horror'), -- 13
		('Kids'), -- 14
		('Music'), -- 15
		('Mystery'), -- 16
		('News'), -- 17
		('Reality'), -- 18
		('Romance'), -- 19
		('Sci-Fi & Fantasy'), -- 20
		('Science Fiction'), -- 21
		('Soap'), -- 22
		('Talk'), -- 23
		('Thriller'), -- 24
		('TV Movie'), -- 25
		('War'), -- 26
		('War & Politics'), -- 27
		('Western') -- 28
go

insert into [type] ([movie_type_name])
values	('Movie'),
		('TV-Series')
go

insert into [country] ([country_name])
values	('Argentina'), -- 1
		('Australia'), -- 2
		('Austria'), -- 3
		('Belgium'), -- 4
		('Brazil'), -- 5
		('Canada'), -- 6
		('China'), -- 7
		('Czech Republic'), -- 8
		('Denmark'), -- 9
		('Finland'), -- 10
		('France'), -- 11
		('Germany'), -- 12
		('Hong Kong'), -- 13
		('Hungary'), -- 14
		('India'), -- 15
		('Ireland'), -- 16
		('Israel'), -- 17
		('Italy'), -- 18
		('Japan'), -- 19
		('Luxembourg'), -- 20
		('Mexico'), -- 21
		('Netherlands'), -- 22
		('New Zealand'), -- 23
		('Norway'), -- 24
		('Poland'), -- 25
		('Romania'), -- 26
		('Russia'), -- 27
		('South Africa'), -- 28
		('South Korea'), -- 29
		('Spain'), -- 30
		('Sweden'), -- 31
		('Switzerland'), -- 32
		('Taiwan'), -- 33
		('Thailand'), -- 34
		('United Kingdom'), -- 35
		('United States of America') -- 36
go

insert into [quality]
values	('HD'), -- 1
		('HDRip'), -- 2
		('SD'), -- 3
		('TS'), -- 4
		('CAM') -- 5
go

insert into [server]
values	('HYD'),
		('SB')
go

insert into [status]
values	('Currently Airing'),
		('Completed'),
		('Upcoming')

-- movie 1
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('John Wick', 2, 1, '101 min', 'https://iili.io/D81t8x.jpg', 'https://iili.io/D8EK8v.jpg', 'Ex-hitman John Wick comes out of retirement to find the gangsters that required everything from him.',
		'2014-10-24', 'Summit Entertainment, Thunder Road Pictures, 87Eleven', 0, 0,
		7.4, 'C0BMx-qxsP4', 1)
go
insert into [movie_episode]
values	(1, 'John Wick Full', 1, 1, 1, '5SQvjRNC3'),
		(1, 'John Wick Full', 1, 1, 2, '11pecl5imk3v')
go
insert into [movie_genre]
values	(1, 24),
		(1, 1)
go
insert into [movie_country]
values	(1, 36),
		(1, 35),
		(1, 7)
go

-- movie 2
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Spider-Man: Into the Spider-Verse', 2, 1, '117 min', 'https://iili.io/D81Q3b.jpg', 'https://iili.io/D81DyQ.jpg', 'Miles Morales is juggling his lifetime and also being a spiderman. When Wilson"Kingpin" Fisk uses a super collider, others from across the SpiderVerse are hauled to the measurement.',
		'2018-12-06', 'Columbia Pictures, Marvel Entertainment, Sony Pictures Entertainment SPE', 0, 0,
		 8.4, 'g4Hbz2jLxvQ', 1)
go
insert into [movie_episode]
values	(2, 'Spider-Man: Into the Spider-Verse Full', 1, 1, 1, 'F32OHLOs3'),
		(2, 'Spider-Man: Into the Spider-Verse Full', 1, 1, 2, 'jtp95qh35dro')
go
insert into [movie_genre]
values	(2, 1),
		(2, 4),
		(2, 21),
		(2, 6)
go
insert into [movie_country]
values	(2, 36)
go

-- movie 3
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Joker', 2, 1, '118 min', 'https://iili.io/D8EHn1.jpg', 'https://iili.io/D81O4s.jpg', 'Throughout the 1980s, while becoming an infamous crime figure, there is a comedian driven insane and turns to a life of chaos and crime in Gotham City.',
		'2019-10-02', 'Village Roadshow Pictures, Warner Bros', 0, 0,
		8.4, 'zAGVQLHvwOY', 1)
go
insert into [movie_episode]
values	(3, 'Joker Full', 1, 1, 1, '655-ij0ss'),
		(3, 'Joker Full', 1, 1, 2, 'j3kby21rplbi')
go
insert into [movie_genre]
values	(3, 9),
		(3, 24),
		(3, 7)
go
insert into [movie_country]
values	(3, 36),
		(3, 6)
go

-- movie 4
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Kingsman: The Secret Service ', 2, 1, '129 min', 'https://iili.io/D814Z7.jpg', 'https://iili.io/D8EJMF.jpg', 'A spy company that recruits an unrefined but promising street kid into the agency training regime as a world wide threat''s narrative stems out of a tech ace.',
		'2015-02-13', 'Marv Films, Twentieth Century Fox', 0, 0,
		7.7, 'kl8F-8tR8to', 1)
go
insert into [movie_episode]
values	(4, 'Kingsman: The Secret Service Full', 1, 1, 1, 'fjtjnRes-'),
		(4, 'Kingsman: The Secret Service Full', 1, 1, 2, 'gs29cfqc1hvu')
go
insert into [movie_genre]
values	(4, 7),
		(4, 6),
		(4, 1),
		(4, 3)
go
insert into [movie_country]
values	(4, 35),
		(4, 36)
go

-- movie 5
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ready Player One', 2, 1, '140 min', 'https://iili.io/D81iGe.jpg', 'https://iili.io/D81XjI.jpg', 'After the founder of a well known video game system expires, there is a virtual competition established to compete due to his or her fortune.',
		'2018-03-11', 'Village Roadshow Pictures, Warner Bros, Amblin Entertainment', 0, 0,
		7.4, 'cSp1dM2Vj48', 1)
go
insert into [movie_episode]
values	(5, 'Ready Player One Full', 1, 1, 1, 'H0gO_Se-E'),
		(5, 'Ready Player One Full', 1, 1, 2, 'ts1azsytw5aw')
go
insert into [movie_genre]
values	(5, 3),
		(5, 21)
go
insert into [movie_country]
values	(5, 36),
		(5, 15)
go

-- movie 6
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ted', 2, 1, '107 min', 'https://iili.io/D81hQt.jpg', 'https://iili.io/D81vaf.jpg', 'John Bennett, a person whose of earning his teddy bear to 15, youth wish came true has to pick between keeping the association with his girl friend or the bear.',
		'2012-06-29', 'Universal Pictures, Media Rights Capital MRC', 0, 0,
		6.9, '9fbo_pQvU7M', 1)
go
insert into [movie_episode]
values	(6, 'Ted Full', 1, 1, 1, 'Ao9ciEbtN'),
		(6, 'Ted Full', 1, 1, 2, '99gmz7ad01dy')
go
insert into [movie_genre]
values	(6, 6),
		(6, 11)
go
insert into [movie_country]
values	(6, 36)
go

-- movie 7
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Rush Hour', 2, 1, '98 min', 'https://iili.io/D81muV.jpg', 'https://iili.io/D81pwB.jpg', 'When hongkong Inspector Lee is summoned into Los Angeles to investigate a kidnapping, the FBI doesn''t want any outside support and assigns Lee to distract from the situation. Not satisfied to watch the activity from the sidelines, Carter and Lee form an unlikely venture and inquire into the case themselves.',
		'1998-09-18', 'Roger Birnbaum Productions, New Line Cinema', 0, 0,
		null, 'JMiFsFQcFLE', 1)
go
insert into [movie_episode]
values	(7, 'Rush Hour Full', 1, 1, 1, 'mFMkae-l_'),
		(7, 'Rush Hour Full', 1, 1, 2, 'hteymssu0nda')
go
insert into [movie_genre]
values	(7, 1),
		(7, 6),
		(7, 7),
		(7, 24)
go
insert into [movie_country]
values	(7, 36)
go

-- movie 8
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Batman', 2, 1, '176 min', 'https://iili.io/D81rjS.jpg', 'https://iili.io/D81Zaj.jpg', 'In his second year fighting crime, Batman finds corruption in Gotham City that involves his own family. He also faces a serial killer known as the Riddler.',
		'2022-03-04', 'Warner Bros, DC Comics', 0, 0,
		7.9, 'mqqft2x_Aa4', 1)
go
insert into [movie_episode]
values	(8, 'The Batman Full', 1, 1, 1, 'JFmXyI9cb'),
		(8, 'The Batman Full', 1, 1, 2, 'lc18jne85ech')
go
insert into [movie_genre]
values	(8, 1),
		(8, 9),
		(8, 7),
		(8, 11),
		(8, 24)
go
insert into [movie_country]
values	(8, 36)
go


-- movie 9
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Mortal Kombat Legends: Scorpion’s Revenge', 2, 1, '80 min', 'https://iili.io/D8E3Fa.jpg', 'https://iili.io/D81EYv.jpg', 'Hanzo Hasashi is exiled to the tortuous Netherrealm after the heinous slaughter of his family by the stone-cold mercenary Sub-Zero. In exchange for his servitude to the sinister Quan Chi, he is given the opportunity to avenge his family – and is resurrected asScorpion, a lost soul bent on vengeance. Back on Earthrealm, Lord Raiden assembles a group of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade, and action star Johnny Cage – an unlikely band of heroes with only one chance to save humanity. To accomplish this, they must defeat Shang Tsung''s horde of Outworld gladiators and take control of the Mortal Kombat tournament.',
		'2020-04-14', 'Warner Bros Animation, DC Entertainment, Midway Games', 0, 0,
		7.4, 'I1vccr3yWBU', 1)
insert into [movie_episode]
values	(9, 'Mortal Kombat Legends: Scorpion’s Revenge Full', 1, 1, 1, 'KKGyVhOXR'),
		(9, 'Mortal Kombat Legends: Scorpion’s Revenge Full', 1, 1, 2, '9i67kf0vvkv2')
go
insert into [movie_genre]
values	(9, 1),
		(9, 4),
		(9, 11),
		(9, 3)
go
insert into [movie_country]
values	(9, 36)
go

-- movie 10
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('El Camino: A Breaking Bad Movie', 2, 1, '123 min', 'https://iili.io/D8Eq9R.jpg', 'https://iili.io/D81WTN.jpg', 'In the wake of the dramatic escape from captivity, Jesse Pinkman must come to terms with his past in order to forge some kind of future.',
		'2019-10-11', 'Netflix, Sony Pictures Television', 0, 0,
		7.3, '1JLUn2DFW4w', 1)
go
insert into [movie_episode]
values	(10, 'El Camino: A Breaking Bad Movie Full', 1, 1, 1, 'Wo6XvcxuN'),
		(10, 'El Camino: A Breaking Bad Movie Full', 1, 1, 2, '4frtkdslltl9')
go
insert into [movie_genre]
values	(10, 24),
		(10, 7),
		(10, 9)
go
insert into [movie_country]
values	(10, 36)
go

-- movie 11
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Pixels', 2, 1, '105 min', 'https://iili.io/D81s6u.jpg', 'https://iili.io/D81gu2.jpg', 'Game pros are recruited by the military to fight game characters that have attacked newyork.',
		'2015-07-16', 'China Film Co., Columbia Pictures Corporation, Film Croppers Entertainment, Prime Focus, 1492 Pictures, LStar Capital, Columbia Pictures, Happy Madison Productions, China Film Group Corporation, Sony Pictures', 0, 0,
		5.6, 'XAHprLW48no', 1)
go
insert into [movie_episode]
values	(11, 'Pixels Full', 1, 1, 1, 'XBNdBWH-s'),
		(11, 'Pixels Full', 1, 1, 2, 'l4enu8h1z4wv')
go
insert into [movie_genre]
values	(11, 1),
		(11, 21),
		(11, 6)
go
insert into [movie_country]
values	(11, 36),
		(11, 7)
go

-- movie 12
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('A Silent Voice', 2, 1, '130 min', 'https://iili.io/D8EBup.jpg', 'https://iili.io/D81Mpp.jpg', 'As she''s deaf, shouya Ishida starts raping the brand newest girl in class, Shouko Nishimiya. But since the stink continues, the rest of the class starts to turn because of his lack of empathy Shouya. If they leave elementary school, Shouko and Shouya usually do not talk with each other again... before a young, more educated Shouya, tormented by his past behaviour, decides he must see Shouko yet again. He wants to atone for his sins, but is it already too late...?',
		'2016-09-17', 'Kyoto Animation', 0, 0,
		8.1, 'nfK6UgLra7g', 1)
go
insert into [movie_episode]
values	(12, 'A Silent Voice Full', 1, 1, 1, 'bxeoqMiim2'),
		(12, 'A Silent Voice Full', 1, 1, 2, 'adzp64tx6iye')
go
insert into [movie_genre]
values	(12, 9),
		(12, 4),
		(12, 19)
go
insert into [movie_country]
values	(12, 19)
go

-- movie 13
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ip Man', 2, 1, '108 min', 'https://iili.io/D81NGn.jpg', 'https://iili.io/D8Ed6g.jpg', 'A semi-biographical account of Yip Man to show the martial art of Wing Chun. The film centers on events surrounding Ip that took place throughout the Second Sino-Japanese War to 1940s. Directed by Wilson Yip, the film features fight choreography by Sammo Hung, and stars Donnie Yen at the lead character.',
		'2008-12-12', 'Mandarin Films Distribution Co.', 0, 0,
		8, 'RBYbqO_FUA4', 1)
go
insert into [movie_episode]
values	(13, 'Ip Man Full', 1, 1, 1, 'Gw8xJ4YGh'),
		(13, 'Ip Man Full', 1, 1, 2, 'd8u2gklis2e7')
go
insert into [movie_genre]
values	(13, 9),
		(13, 1),
		(13, 12)
go
insert into [movie_country]
values	(13, 13)
go

-- movie 14
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Social Network', 2, 1, '121 min', 'https://iili.io/D81l4a.jpg', 'https://iili.io/D8EFcJ.jpg', 'Education genius Mark Zuckerberg sits at his personal laptop and Harvard undergrad and heatedly begins taking care of a fresh idea. At a fury of programming and blogging, what begins within his dorm room to get a site among friends becomes a global media and also a revolution in communication. Only six decades and 500 million friends later, Mark Zuckerberg will be your youngest billionaire ever sold... but for this entrepreneur, success contributes to both legal and personal complications.',
		'2010-10-01', 'Columbia Pictures, Relativity Media, Scott Rudin Productions, Trigger Street Productions, Michael De Luca Productions', 0, 0,
		7.8, 'lB95KLmpLR4', 1)
go
insert into [movie_episode]
values	(14, 'The Social Network Full', 1, 1, 1, 'jldx8_2f-'),
		(14, 'The Social Network Full', 1, 1, 2, '2k4nt0na744w')
go
insert into [movie_genre]
values	(14, 9)
go
insert into [movie_country]
values	(14, 36)
go

-- movie 15
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Godzilla vs. Kong', 2, 1, '113 min' , 'https://iili.io/D8hPOG.jpg', 'https://iili.io/D8hibf.jpg', 'In a time when beasts walk the Planet, humankind''s defend its future sets Godzilla and also Kong on a clash that will certainly see the two most effective pressures of nature on earth clash in a magnificent fight for the ages.',
		'2021-03-24', 'Legendary Entertainment, Warner Bros. Pictures', 0, 0,
		6.3, 'odM92ap8_c0', 1)
go
insert into [movie_episode]
values	(15, 'Godzilla vs. Kong Full', 1, 1, 1, 'LlpiXuMhY'),
		(15, 'Godzilla vs. Kong Full', 1, 1, 2, 'ac5lbxlihaet')
go
insert into [movie_genre]
values	(15, 1),
		(15, 21)
go
insert into [movie_country]
values	(15, 36)
go

-- movie 16
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Edge of Tomorrow', 2, 1, '113 min', 'https://iili.io/D8hyJe.jpg', 'https://iili.io/D8j95u.jpg', 'Leading Bill Cage can be an officer who hasn''t seen a time of combat if he dropped and is demoted into combat. Cage is murdered within moments, managing to take an alien down . He awakens back at the start of the same day and can be made to struggle and die again... and - as physical contact with all the alien has thrown him into a time loop.',
		'2014-05-27', 'RatPac-Dune Entertainment, Village Roadshow Pictures, Viz Media, 3 Arts Entertainment, Warner Bros. Pictures', 0, 0,
		7.9, 'vw61gCe2oqI', 1)
go
insert into [movie_episode]
values	(16, 'Edge of Tomorrow Full', 1, 1, 1, 'TmonNb8_Y'),
		(16, 'Edge of Tomorrow Full', 1, 1, 2, '7rom4jqyorqe')
go
insert into [movie_genre]
values	(16, 1),
		(16, 21)
go
insert into [movie_country]
values	(16, 36),
		(16, 2)
go

-- movie 17
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Sandman', 2, 1, '45 min', 'https://iili.io/D8jBg1.jpg', 'https://iili.io/D8jndF.jpg', 'Morpheus, the King of Dreams, embarks on a journey across worlds to recover what was stolen from him and reclaim his power after years of imprisonment.',
		'2022-08-05', 'Phantom Four, DC Entertainment, Warner Bros. Television, Blank Corporation, Purepop', 19.99, 1,
		7.7, '83ClbRPRDXU', 2)
go
insert into [movie_episode]
values	(17, 'Dream Of A Thousand Cats Calliope', 1, 1, 1, 'WbKWNnrS2'),
		(17, 'Sleep of the Just', 2, 1, 1, '5YwdIcTCE'),
		(17, 'Imperfect Hosts', 3, 1, 1, 'zq7Qw60c-'),
		(17, 'Dream a Little Dream of Me', 4, 1, 1, 'dY6nQcrpi'),
		(17, 'A Hope in Hell', 5, 1, 1, '7cNuswYMx'),
		(17, '24 and 7', 6, 1, 1, '_nwRthD_1'),
		(17, 'The Sound of Her Wings', 7, 1, 1, 'BZPfiPq4L'),
		(17, 'The Dolls House', 8, 1, 1, 'ZNJuy4e6I'),
		(17, 'Playing House', 9, 1, 1, 'd0OYXcdx2'),
		(17, 'Collectors', 10, 1, 1, 'jUbxp_-wl'),
		(17, 'Lost Hearts', 11, 1, 1, 'nTlhQHJhS'),

		(17, 'Dream Of A Thousand Cats Calliope', 1, 1, 2, 'v4yosinnpcq8'),
		(17, 'Sleep of the Just', 2, 1, 2, 'qeze4zp233j7'),
		(17, 'Imperfect Hosts', 3, 1, 2, 'b9m2fooqun1c'),
		(17, 'Dream a Little Dream of Me', 4, 1, 2, 'bny1c07r5b9y'),
		(17, 'A Hope in Hell', 5, 1, 2, '4qyp8pxvaspx'),
		(17, '24 and 7', 6, 1, 2, '2642yv0r4318'),
		(17, 'The Sound of Her Wings', 7, 1, 2, 'z6ejw8hntqz0'),
		(17, 'The Dolls House', 8, 1, 2, 'f77b5vw0vwsz'),
		(17, 'Playing House', 9, 1, 2, '11r3quh811w2'),
		(17, 'Collectors', 10, 1, 2, 'zfob81249j10'),
		(17, 'Lost Hearts', 11, 1, 2, '0rj63cd2woh5')
go
insert into [movie_genre]
values	(17, 20),
		(17, 9)
go
insert into [movie_country]
values	(17, 36)
go

-- movie 18
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Moon Knight', 2, 1, '45 min', 'https://iili.io/D8juXR.jpg', 'https://iili.io/D8jAsp.jpg', 'Mercenary Marc Spector and mild-mannered gift shop employee Steven Grant find that they both suffer from dissociative identity disorder and share the same body. It''s a delicate balancing act for Steven/Marc as their foes close in on them, and they''re forced to deal with a deadly Egyptian enigma.',
		'2022-03-30', 'Marvel Studios', 0, 0,
		7.3, 'x7Krla_UxRg', 2)
go
insert into [movie_episode]
values	(18, 'The Goldfish Problem', 1, 1, 1, 'aER8E9c6b'),
		(18, 'Summon the Suit', 2, 1, 1, '3vWNoiVO3'),
		(18, 'The Friendly Type', 3, 1, 1, 'kYeJw3lgH'),
		(18, 'The Tomb', 4, 1, 1, 'C7hbgbW8J'),
		(18, 'Asylum', 5, 1, 1, 'c6wdGg3L5'),
		(18, 'Gods and Monsters', 6, 1, 1, '04FxRbc6B'),

		(18, 'The Goldfish Problem', 1, 1, 2, '8fih1t066fwm'),
		(18, 'Summon the Suit', 2, 1, 2, 'bul0x974piwd'),
		(18, 'The Friendly Type', 3, 1, 2, 'xigqbneg86st'),
		(18, 'The Tomb', 4, 1, 2, '54c8rim5ouii'),
		(18, 'Asylum', 5, 1, 2, '66f9p5o3p79p'),
		(18, 'Gods and Monsters', 6, 1, 2, 'x3zh8lm6qkm4')
go
insert into [movie_genre]
values	(18, 20),
		(18, 9),
		(18, 2)
go
insert into [movie_country]
values	(18, 36)
go

-- movie 19
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Tekken: Bloodline', 2, 1, '25 min', 'https://iili.io/D8jl7n.jpg', 'https://iili.io/D8j1mG.jpg', 'A hot-tempered fighter trains under his fanatical grandfather after losing his house to a powerful enemy.',
		'2022-08-18', 'Bandai Namco Entertainment', 0, 0,
		6.6, 'x6-donCAnCc', 2)
go
insert into [movie_episode]
values	(19, 'Episode 01', 1, 1, 1, 'rbFYCsWxY'),
		(19, 'Episode 02', 2, 1, 1, 'A2be2fJ9n'),
		(19, 'Episode 03', 3, 1, 1, '0oZo79ABJ'),
		(19, 'Episode 04', 4, 1, 1, '0CqcADY1V'),
		(19, 'Episode 05', 5, 1, 1, 'TC_19ii_l'),
		(19, 'Episode 05', 5, 1, 1, 'OfhrE6Kuf'),

		(19, 'Episode 01', 1, 1, 2, 'fkrqpmey9jo0'),
		(19, 'Episode 02', 2, 1, 2, 'nbc6vd43q0qw'),
		(19, 'Episode 03', 3, 1, 2, 'tqi9r56w49tb'),
		(19, 'Episode 04', 4, 1, 2, 'u8cq6tcrwdv3'),
		(19, 'Episode 05', 5, 1, 2, '3zf2ok7ftaox'),
		(19, 'Episode 06', 6, 1, 2, '6ygfxkdeplpf')
go
insert into [movie_genre]
values	(19, 2),
		(19, 20),
		(19, 4)
go
insert into [movie_country]
values	(19, 36)
go

-- movie 20
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Loki', 2, 1, '52 min', 'https://iili.io/D8jMX4.jpg', 'https://iili.io/D8jVLl.jpg', 'After seizing the Tesseract during the events of "Avengers: Endgame," a parallel Loki is transported to the enigmatic Time Variance Authority, a governmental institution that exists outside of time and space and oversees the timeline. They give Loki the option of being eliminated from existence as a "time variation" or assisting in repairing the timeline and preventing a greater threat.',
		'2021-06-09', 'Marvel Studios', 0, 0,
		8.2, 'nW948Va-l10', 2)
go
insert into [movie_episode]
values	(20, 'Glorious Purpose', 1, 1, 1, 'sg0HDkCeP'),
		(20, 'The Variant', 2, 1, 1, 'yUyBv3Qbo'),
		(20, 'Lamentis', 3, 1, 1, 'nfhF5GT32'),
		(20, 'The Nexus Event', 4, 1, 1, 'qrakWHKjp'),
		(20, 'Journey Into Mystery', 5, 1, 1, '48zc9NO5t'),
		(20, 'For All Time. Always', 6, 1, 1, 'sEUpiqWig'),

		(20, 'Glorious Purpose', 1, 1, 2, '63zzrzwnaq2x'),
		(20, 'The Variant', 2, 1, 2, 'b37joxh1uwhr'),
		(20, 'Lamentis', 3, 1, 2, 'e8rnr7pvenso'),
		(20, 'The Nexus Event', 4, 1, 2, 'hoey0phok35i'),
		(20, 'Journey Into Mystery', 5, 1, 2, 'yklphscbk1bv'),
		(20, 'For All Time. Always', 6, 1, 2, 'jgzaiz4zx7dx')
go
insert into [movie_genre]
values	(20, 20),
		(20, 2),
		(20, 9),
		(20, 7)
go
insert into [movie_country]
values	(20, 36)
go

-- movie 21
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Invincible', 2, 1, '25 min', 'https://iili.io/D8jsYF.jpg', 'https://iili.io/D8jQpa.jpg', 'Mark Grayson is a typical teenager besides the fact that his papa is one of the most effective superhero on earth. Shortly after his seventeenth birthday celebration, Mark begins to develop powers of his very own and also participates in his papa''s tutoring.',
		'2021-03-26', 'Amazon Studios, Skybound Entertainment, Image Comics', 49.99, 1,
		8.7, '-bfAVpuko5o', 2)
go
insert into [movie_episode]
values	(21, 'IT’S ABOUT TIME', 1, 1, 1, 'n9UG1uaqw'),
		(21, 'HERE GOES NOTHINGE', 2, 1, 1, 'c-T962jdI'),
		(21, 'WHO YOU CALLING UGLY?', 3, 1, 1, 'dw2b__YUy'),
		(21, 'Neil Armstrong Eat Your Heart Out', 4, 1, 1, 'CjMp6o4KI4'),
		(21, 'That Actually Hurt', 5, 1, 1, '0C3bqp82d'),
		(21, 'You Look Kinda Dead', 6, 1, 1, 'glv2mMY6s'),
		(21, 'We Need to Talk', 7, 1, 1, 'X84NrGjlv'),
		(21, 'Where I Really Come From', 8, 1, 1, 'RDi7dwvmw'),

		(21, 'IT’S ABOUT TIME', 1, 1, 2, 'pomqngu0j0gh'),
		(21, 'HERE GOES NOTHINGE', 2, 1, 2, '1iqxjy3ipvc1'),
		(21, 'WHO YOU CALLING UGLY?', 3, 1, 2, 'utg0gch08w79'),
		(21, 'Neil Armstrong Eat Your Heart Out', 4, 1, 2, 'wfez2303fpke'),
		(21, 'That Actually Hurt', 5, 1, 2, 'mirsmnmi2xbc'),
		(21, 'You Look Kinda Dead', 6, 1, 2, 'r8cs4src21dl'),
		(21, 'We Need to Talk', 7, 1, 2, '0s4dz7zx94a7'),
		(21, 'Where I Really Come From', 8, 1, 2, 'gthj4363orwq')
insert into [movie_genre]
values	(21, 9),
		(21, 24),
		(21, 4),
		(21, 2),
		(21, 21),
		(21, 13)
go
insert into [movie_country]
values	(21, 36)
go

-- movie 22
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Breaking Bad', 2, 1, '45 min', 'https://iili.io/H2JQlnI.jpg', 'https://iili.io/H2JQMas.jpg', 'When a New Mexico chemistry educator, Walter White, has been diagnosed with Stage III cancer and given a prognosis of two years ago to live. He''s filled to secure his family''s financial future at any cost since he enters the dangerous environment of crime and drugs.',
		'2008-01-20', 'Sony Pictures Television, High Bridge Entertainment, Gran Via Productions', 0, 0,
		9.5, 'HhesaQXLuRY', 2)
go
insert into [movie_episode]
values	(22, 'Pilot', 1, 1, 1, 'B0VYI6zch'),
		(22, 'The Cat''s in the Bag', 2, 1, 1, '59c0OznD6'),
		(22, 'And the Bag''s in the River', 3, 1, 1, 'aHsRmmULi'),
		(22, 'Cancer Man', 4, 1, 1, 'S_WTw2hkvu'),
		(22, 'Gray Matter', 5, 1, 1, 'BW7XV1GyYl'),
		(22, 'Crazy Handful of Nothin', 6, 1, 1, '9SsTPcpi2I'),
		(22, 'A No-Rough-Stuff-Type Deal', 7, 1, 1, 'N_4JoG0xwV'),
		(22, 'Seven Thirty-Seven', 1, 2, 1, '4JXaO_IUeh'),
		(22, 'Grilled', 2, 2, 1, 'sGZ6_uvcD'),
		(22, 'Bit by a Dead Bee', 3, 2, 1, 'UuJTXAvaXp'),
		(22, 'Down', 4, 2, 1, 'Efn834sW7i'),
		(22, 'Breakage', 5, 2, 1, 'zkbhy03np'),
		(22, 'Peekaboo', 6, 2, 1, 'DtpBWjbfE'),
		(22, 'Negro y Azul', 7, 2, 1, 'SOCT7UDyw'),
		(22, 'Better Call Saul', 8, 2, 1, 'Ps0gZq2tA'),
		(22, '4 Days Out', 9, 2, 1, 'XGOA3R1Bq'),
		(22, 'Over', 10, 2, 1, 'VJwje0ryH'),
		(22, 'Mandala', 11, 2, 1, 'xsX9xsiIvJ'),
		(22, 'Phoenix', 12, 2, 1, 'Eg7k5hGyxK'),
		(22, 'ABQ', 13, 2, 1, 'Q70_ywILbv'),
		(22, N'No Más', 1, 3, 1, 'cu9S9zrdL'),
		(22, 'Caballo Sin Nombre', 2, 3, 1, 'bIehtFsoiO'),
		(22, 'I.F.T', 3, 3, 1, 'Wusu1Sa4qN'),
		(22, 'Green Light', 4, 3, 1, 'KA_3CUWfj4'),
		(22, 'Mas', 5, 3, 1, 'MBVarCFt-G'),
		(22, 'Sunset', 6, 3, 1, 'WARsTjdRe'),
		(22, 'One Minute', 7, 3, 1, 'IKzmFMHTk'),
		(22, 'I See You', 8, 3, 1, 'Ym1juAEl7'),
		(22, 'Kafkaesque', 9, 3, 1, 'g5lldDxxEu'),
		(22, 'Fly', 10, 3, 1, 'SpJllCGBL'),
		(22, 'Abiquiu', 11, 3, 1, '2wkzdDYcW'),
		(22, 'Half Measures', 12, 3, 1, '7RrytQKYG'),
		(22, 'Full Measure', 13, 3, 1, '7Lc23y992'),
		(22, 'Box Cutter', 1, 4, 1, 'Ke-19pLqh'),
		(22, 'Thirty-Eight Snub', 2, 4, 1, 'wx2V43X7t'),
		(22, 'Open House', 3, 4, 1, 'GMzxNEu-0'),
		(22, 'Bullet Points', 4, 4, 1, '8arhwNY2HI'),
		(22, 'Shotgun', 5, 4, 1, '-gL_Nz6pV'),
		(22, 'Cornered', 6, 4, 1, 'hYFA3DTdv'),
		(22, 'Problem Dog', 7, 4, 1, '77VPrpkCy'),
		(22, 'Hermanos', 8, 4, 1, 'L23GQfQFf'),
		(22, 'Bug', 9, 4, 1, 'BqrOUjRqXZ'),
		(22, 'Salud', 10, 4, 1, '4meP82D4F'),
		(22, 'Crawl Space', 11, 4, 1, 'bVRZl3Aozx'),
		(22, 'End Times', 12, 4, 1, 'WQI9KXDf32'),
		(22, 'Face Off', 13, 4, 1, 'JgQFBK-Uuh'),
		(22, 'Live Free Or Die', 1, 5, 1, 'Jx32tGPE-'),
		(22, 'Madrigal', 2, 5, 1, 'EjtY0jQDo'),
		(22, 'Hazard Pay', 3, 5, 1, 'GPuWsASwNW'),
		(22, 'Fifty-One', 4, 5, 1, 'ZBTyM8mtg'),
		(22, 'Dead Freight', 5, 5, 1, 'oGRKOskD6'),
		(22, 'Buyout', 6, 5, 1, 'ASnWybI_r'),
		(22, 'Say My Name', 7, 5, 1, 'gOTTJGxWl'),
		(22, 'Gliding Over All', 8, 5, 1, '_pEi0pWEpm'),
		(22, 'Blood Money', 9, 5, 1, 'DuoaD7dYV'),
		(22, 'Buried', 10, 5, 1, '25ogAHHLY'),
		(22, 'Confessions', 11, 5, 1, 'crp0sfFb7'),
		(22, 'Rabid Dog', 12, 5, 1, 'j6GF6dmrWj'),
		(22, 'To''hajiilee', 13, 5, 1, 'a_mGYztOW'),
		(22, 'Ozymandias', 14, 5, 1, 'v2P0RYhW3'),
		(22, 'Granite State', 15, 5, 1, '4RdpiqWgdj'),
		(22, 'Felina', 16, 5, 1, 'nEdZF6Gcd'),

		(22, 'Pilot', 1, 1, 2, 'x090keh32y5i'),
		(22, 'The Cat''s in the Bag', 2, 1, 2, 'bavrwnz7exhn'),
		(22, 'And the Bag''s in the River', 3, 1, 2, '71qvodrbh53n'),
		(22, 'Cancer Man', 4, 1, 2, 'c2ljssduk8jq'),
		(22, 'Gray Matter', 5, 1, 2, 'ddcfvv23lwiv'),
		(22, 'Crazy Handful of Nothin', 6, 1, 2, 'c4d2zvnjllr0'),
		(22, 'A No-Rough-Stuff-Type Deal', 7, 1, 2, 'lf5c9ijvka7n'),
		(22, 'Seven Thirty-Seven', 1, 2, 2, 'xgfxifwkqbhe'),
		(22, 'Grilled', 2, 2, 2, '550bujzpqh1j'),
		(22, 'Bit by a Dead Bee', 3, 2, 2, 'hkzlc4y0wv0i'),
		(22, 'Down', 4, 2, 2, '3xm4rpgm4hcf'),
		(22, 'Breakage', 5, 2, 2, '9kw5j7m93rbg'),
		(22, 'Peekaboo', 6, 2, 2, '6etr9lrzz6ag'),
		(22, 'Negro y Azul', 7, 2, 2, '0imskvg83yph'),
		(22, 'Better Call Saul', 8, 2, 2, '5rhnhsiwhwh3'),
		(22, '4 Days Out', 9, 2, 2, 'bzid6c12gzfb'),
		(22, 'Over', 10, 2, 2, 'gaju8ikbxe52'),
		(22, 'Mandala', 11, 2, 2, 'yj374v4mfugs'),
		(22, 'Phoenix', 12, 2, 2, 'wfz2xy4vytba'),
		(22, 'ABQ', 13, 2, 2, 'x071tk9d8pfo'),
		(22, N'No Más', 1, 3, 2, 'o15t33bxtsl5'),
		(22, 'Caballo Sin Nombre', 2, 3, 2, 'nht11ouv5r95'),
		(22, 'I.F.T', 3, 3, 2, 'fmpxuk8ponv0'),
		(22, 'Green Light', 4, 3, 2, 'rldk58k7af5u'),
		(22, 'Mas', 5, 3, 2, 'l3xmtdet6lpl'),
		(22, 'Sunset', 6, 3, 2, 'p650na5r8cne'),
		(22, 'One Minute', 7, 3, 2, 'ej6mtk9cjnqu'),
		(22, 'I See You', 8, 3, 2, 'qay1gejt17hq'),
		(22, 'Kafkaesque', 9, 3, 2, 'wupxvxnunttj'),
		(22, 'Fly', 10, 3, 2, 'd0zojjy6exa7'),
		(22, 'Abiquiu', 11, 3, 2, 'cwf8hnlma5ol'),
		(22, 'Half Measures', 12, 3, 2, 'yqj73is3dbm0'),
		(22, 'Full Measure', 13, 3, 2, '7p36cpfikd2n'),
		(22, 'Box Cutter', 1, 4, 2, 't0dir0q5a8sl'),
		(22, 'Thirty-Eight Snub', 2, 4, 2, 'npo09enx7a2i'),
		(22, 'Open House', 3, 4, 2, '0qoky1zqdvli'),
		(22, 'Bullet Points', 4, 4, 2, 'p8csbsr0hcg5'),
		(22, 'Shotgun', 5, 4, 2, '6gqnd73moh43'),
		(22, 'Cornered', 6, 4, 2, 'w8floxxq6dbr'),
		(22, 'Problem Dog', 7, 4, 2, '98d4vh5nkgt7'),
		(22, 'Hermanos', 8, 4, 2, 'xhu6esanr1k4'),
		(22, 'Bug', 9, 4, 2, '02qq8hqbq80d'),
		(22, 'Salud', 10, 4, 2, 'vtz1x77iwxg0'),
		(22, 'Crawl Space', 11, 4, 2, 'ajw4lqh8yavr'),
		(22, 'End Times', 12, 4, 2, 'csjzxs3ig9a2'),
		(22, 'Face Off', 13, 4, 2, 'n8qyjjxbowsw'),
		(22, 'Live Free Or Die', 1, 5, 2, '7nekd8mbg230'),
		(22, 'Madrigal', 2, 5, 2, '2js4hsh0r8k4'),
		(22, 'Hazard Pay', 3, 5, 2, '69mipj5lkj9x'),
		(22, 'Fifty-One', 4, 5, 2, 'r5qik5gtytqn'),
		(22, 'Dead Freight', 5, 5, 2, 'ytqf2rsg8mcb'),
		(22, 'Buyout', 6, 5, 2, 'hus2gmu3cin5'),
		(22, 'Say My Name', 7, 5, 2, 'ihy4pu8zpyui'),
		(22, 'Gliding Over All', 8, 5, 2, 'kxys0mzeztq0'),
		(22, 'Blood Money', 9, 5, 2, 'spuo0yqoylp3'),
		(22, 'Buried', 10, 5, 2, 'wjogoc7qqml2'),
		(22, 'Confessions', 11, 5, 2, 'iq9mxrrqn3h2'),
		(22, 'Rabid Dog', 12, 5, 2, 'jxc9ji6694lx'),
		(22, 'To''hajiilee', 13, 5, 2, 'oh0ii8wkxz8e'),
		(22, 'Ozymandias', 14, 5, 2, '9j1wojuj31fi'),
		(22, 'Granite State', 15, 5, 2, '2hux7y5qbtxk'),
		(22, 'Felina', 16, 5, 2, 'upgnoaenwpm6')
go
insert into [movie_genre]
values	(22, 9)
go
insert into [movie_country]
values	(22, 36)
go

-- movie 23
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Black Adam', 2, 1, '126 min', 'https://iili.io/mBJE7t.jpg', 'https://iili.io/mBJGkX.jpg', 'Black Adam emerges from his earthly tomb about 5,000 years after being granted the Egyptian gods'' awesome powers and imprisoned.',
		'2022-10-19', 'Flynn Picture Company, New Line Cinema, Seven Bucks Productions, DC Films, Warner Bros. Pictures', 0, 0,
		7.1, 'mkomfZHG5q4', 1)
go
insert into [movie_episode]
values	(23, 'Black Adam Full', 1, 1, 1, 'T46mgso4S'),
		(23, 'Black Adam Full', 1, 1, 2, 'i7zqezba5ae8')
go
insert into [movie_genre]
values	(23, 11),
		(23, 1),
		(23, 3)
go
insert into [movie_country]
values	(23, 36)
go


-- movie 24
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Boys', 2, 1, '60 min', 'https://iili.io/H2FWIpa.jpg', 'https://iili.io/H2FW1vn.jpg', 'A group of vigilantes known informally as"The Boys" set out to shoot down corrupt superheroes with no further than blue-collar grit and also a willingness to fight dirty.',
		'2019-07-25', 'Amazon Studios, Original Film, Sony Pictures Television, Kripke Enterprises, Point Grey Pictures, Kickstart, NightSky Productions', 39.99, 1,
		8.7, 'https://www.youtube.com/watch?v=M1bhOaLV4FU', 2)
go
insert into [movie_episode]
values	(24, 'The Name of the Game', 1, 1, 1, 'SZTIYDzvuQ'),
		(24, 'Cherry', 2, 1, 1, 'AQ4h3X08AS'),
		(24, 'Get Some', 3, 1, 1, 'XHj8NRqzH'),
		(24, 'The Female of the Species', 4, 1, 1, 'agrvgsIHpE'),
		(24, 'Good for the Soul', 5, 1, 1, 'bUpKuUQvB'),
		(24, 'The Innocents', 6, 1, 1, 'ODFGf1auxQ'),
		(24, 'The Self-Preservation Society', 7, 1, 1, 'QaH5yuJO9'),
		(24, 'You Found Me', 8, 1, 1, 'NPTdNjvVeI'),
		(24, 'The Big Ride', 1, 2, 1, 'IguxVT9UX'),
		(24, 'Proper Preparation and Planning', 2, 2, 1, 'TE8ZaLQIK'),
		(24, 'Over the Hill with the Swords of a Thousand.Men', 3, 2, 1, 'anvUp3JifA'),
		(24, 'Nothing Like It in the World', 4, 2, 1, 'Uo_htj2LeM'),
		(24, 'We Gotta Go Now', 5, 2, 1, '6djSniGdY'),
		(24, 'The Bloody Doors Off', 6, 2, 1, 'hFddpN972'),
		(24, 'Butcher Baker Candlestick Maker', 7, 2, 1, 'JVg40o94Z'),
		(24, 'What I Know', 8, 2, 1, 'zr2fSxOx4c'),
		(24, 'Payback', 1, 3, 1, '3coILsaTv'),
		(24, 'The Only Man In The Sky', 2, 3, 1, 'a5IABAflV'),
		(24, 'Barbary Coast', 3, 3, 1, 'gV1FPv2uu'),
		(24, 'Glorious Five Year Plan', 4, 3, 1, 'ltTCEJfQo'),
		(24, 'The Last Time to Look on This World of Lies', 5, 3, 1, 'z4Ouli7TA'),
		(24, 'Herogasm', 6, 3, 1, 'Rd11galfe'),
		(24, 'Here Comes a Candle to Light You to Bed', 7, 3, 1, 'X6rlAgzNg'),
		(24, 'The Instant White-Hot Wild', 8, 3, 1, '5v9pSoKsP'),

		(24, 'The Name of the Game', 1, 1, 2, 'zmy37gp5uund'),
		(24, 'Cherry', 2, 1, 2, 'ta2v4dwxh98c'),
		(24, 'Get Some', 3, 1, 2, 'whdtrvdxt0jh'),
		(24, 'The Female of the Species', 4, 1, 2, 'w2ki5ml5beog'),
		(24, 'Good for the Soul', 5, 1, 2, 'vcfirdwi8tj2'),
		(24, 'The Innocents', 6, 1, 2, 'ch9svhqcj0i0'),
		(24, 'The Self-Preservation Society', 7, 1, 2, 'qhjqmtx8fzxz'),
		(24, 'You Found Me', 8, 1, 2, '88f3pe8p4pa2'),
		(24, 'The Big Ride', 1, 2, 2, 'jlosxjaegqu1'),
		(24, 'Proper Preparation and Planning', 2, 2, 2, '76i7h7wmp8ie'),
		(24, 'Over the Hill with the Swords of a Thousand.Men', 3, 2, 2, 'u068fifnws6d'),
		(24, 'Nothing Like It in the World', 4, 2, 2, '05817zgshbdl'),
		(24, 'We Gotta Go Now', 5, 2, 2, '3ogmqn7zued9'),
		(24, 'The Bloody Doors Off', 6, 2, 2, 'w6q8nk8438u0'),
		(24, 'Butcher Baker Candlestick Maker', 7, 2, 2, 'fbnzj10xwzm8'),
		(24, 'What I Know', 8, 2, 2, '15fvk7xnqjdi'),
		(24, 'Payback', 1, 3, 2, 'ujxaji6ey5ih'),
		(24, 'The Only Man In The Sky', 2, 3, 2, 'q8teq26wxaqj'),
		(24, 'Barbary Coast', 3, 3, 2, 'eaxtfagvmsoh'),
		(24, 'Glorious Five Year Plan', 4, 3, 2, 'lx2pg2xujypd'),
		(24, 'The Last Time to Look on This World of Lies', 5, 3, 2, 'sd0fdytiwn4r'),
		(24, 'Herogasm', 6, 3, 2, '5uikx3mr4mbj'),
		(24, 'Here Comes a Candle to Light You to Bed', 7, 3, 2, 'foboa7izq8i8'),
		(24, 'The Instant White-Hot Wild', 8, 3, 2, 'nmgj2t36qz02')
go
insert into [movie_genre]
values	(24, 20),
		(24, 4)
go
insert into [movie_country]
values	(24, 36)
go

-- movie 25
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Falcon and the Winter Soldier', 2, 1, '45 min', 'https://iili.io/H2KTK22.jpg', 'https://iili.io/H2KTf7S.jpg', 'Following the events of "Avengers: Endgame," the Falcon, Sam Wilson, and Bucky Barnes join forces in a global quest that puts their powers and patience to the test.',
		'2021-03-19', 'Marvel Studios', 0, 0,
		7.2, '', 2)
go
insert into [movie_episode]
values	(25, 'New World Order', 1, 1, 1, 'qwWxoHuHoT'),
		(25, 'The Star Spangled Man', 2, 1, 1, 'DChKsMOV-'),
		(25, 'Power Broker', 3, 1, 1, 'gGBWzfD76'),
		(25, 'The Whole World Is Watching', 4, 1, 1, 'Oe0V1Qbfck'),
		(25, 'Truth', 5, 1, 1, 'rBDah4MKwa'),
		(25, 'One World One People', 6, 1, 1, 'PwAd2OL4C'),

		(25, 'New World Order', 1, 1, 2, 'dc5z9ouv8xoq'),
		(25, 'The Star Spangled Man', 2, 1, 2, '1ucydp4wy2q4'),
		(25, 'Power Broker', 3, 1, 2, 'sqajxrf6mnco'),
		(25, 'The Whole World Is Watching', 4, 1, 2, 'yfwhgo8hl4yl'),
		(25, 'Truth', 5, 1, 2, 'eydsw67kgqtc'),
		(25, 'One World One People', 6, 1, 2, 'yunc05h2lp8o')
go
insert into [movie_genre]
values	(25, 20),
		(25, 4),
		(25, 9),
		(25, 6)
go
insert into [movie_country]
values	(25, 36)
go

-- movie 26
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('What If...?', 2, 1, '34 min', 'https://iili.io/H2KOxJ2.jpg', 'https://iili.io/H2KOIO7.jpg', 'Taking ideas from the comic books of the very same name, each episode explores a pivotal moment from the Wonder Cinematic World as well as transforms it on its head, leading the target market into uncharted territory.',
		'2021-08-11', 'Marvel Studios, Flying Bark Productions, Blue Spirit Animation, Stellar Creative Lab, Squeeze', 9.99, 1,
		7.4, 'x9D0uUKJ5KI', 2)
go
insert into [movie_episode]
values	(26, 'What If… Captain Carter Were The First Avenger?', 1, 1, 1, 'V-QHOSZp5'),
		(26, 'What If… T’Challa Became a Star-Lord?', 2, 1, 1, 'GgV25tifIs'),
		(26, 'What If The World Lost Its Mightiest Heroes', 3, 1, 1, 'nPQ78QQ-R'),
		(26, 'What If… Doctor Strange Lost His Heart Instead of His Hands?', 4, 1, 1, 'HOsVtFC4r'),
		(26, 'What If Zombies', 5, 1, 1, 'wy30S7K9W'),
		(26, 'What If Killmonger Rescued Tony Stark', 6, 1, 1, 'HOEHaA7_0'),
		(26, 'What If Thor Were An Only Chld', 7, 1, 1, 'MMLNrtaHE'),
		(26, 'What If… Ultron Won?', 8, 1, 1, 'myHvWiYOx0'),
		(26, 'What If The Watcher Broke His Oath', 9, 1, 1, 'BUisdktApf'),

		(26, 'What If… Captain Carter Were The First Avenger?', 1, 1, 2, 'fqb1eh4bd83m'),
		(26, 'What If… T’Challa Became a Star-Lord?', 2, 1, 2, 'xhuto9yw538f'),
		(26, 'What If The World Lost Its Mightiest Heroes', 3, 1, 2, 'xk2rugqen136'),
		(26, 'What If… Doctor Strange Lost His Heart Instead of His Hands?', 4, 1, 2, '85z369r7ps78'),
		(26, 'What If Zombies', 5, 1, 2, 'zrj2l3h3nel9'),
		(26, 'What If Killmonger Rescued Tony Stark', 6, 1, 2, 'i7hp1zt4yomc'),
		(26, 'What If Thor Were An Only Chld', 7, 1, 2, 'gy7rtjjjxhq0'),
		(26, 'What If… Ultron Won?', 8, 1, 2, '6w1wq63ag7hl'),
		(26, 'What If The Watcher Broke His Oath', 9, 1, 2, 'mkup336yiisc')
go
insert into [movie_genre]
values	(26, 20),
		(26, 4),
		(26, 2)
go
insert into [movie_country]
values	(26, 36)
go

-- movie 27
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Hawkeye', 2, 1, '45 min', 'https://iili.io/H2fBfQR.jpg', 'https://iili.io/H2fBCEN.jpg', 'Clint Barton, a former Avenger, has a seemingly straightforward mission: to return to his family for the holiday season. Possible? Possibly with the assistance of Kate Bishop, a 22-year-old archer who aspires to become a Super Hero. When a presence from Barton''s past threatens to ruin more than the holiday joy, the two are forced to collaborate.',
		'2021-11-24', 'Marvel Studios', 0, 0,
		7.5, '5VYb3B1ETlk', 2)
go
insert into [movie_episode]
values	(27, 'Never Meet Your Heroes', 1, 1, 1, 'WACBhyUEg'),
		(27, 'Hide And Seek', 2, 1, 1, '3O71tXRv9'),
		(27, 'Echoes', 3, 1, 1, 'd_cXHfOIM'),
		(27, 'Partners, Am I Right?', 4, 1, 1, '8UENToMQ1D'),
		(27, 'Ronin', 5, 1, 1, '0nOYWCEm9w'),
		(27, 'So This Is Christmas', 6, 1, 1, 'umgHMPHaA'),

		(27, 'Never Meet Your Heroes', 1, 1, 2, '7mhj8t5d9rqz'),
		(27, 'Hide And Seek', 2, 1, 2, 'oa0iwpfg6jts'),
		(27, 'Echoes', 3, 1, 2, 'd8niwwhypg2t'),
		(27, 'Partners, Am I Right?', 4, 1, 2, 'npa04u909m55'),
		(27, 'Ronin', 5, 1, 2, '2k7pvq6tcu07'),
		(27, 'So This Is Christmas', 6, 1, 2, '3f5h89p8za8e')
go
insert into [movie_genre]
values	(27, 2),
		(27, 9)
go
insert into [movie_country]
values	(27, 36)
go

-- movie 28
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Arcane', 2, 1, '43 min', 'https://iili.io/H2fatjV.jpg', 'https://iili.io/H2faDZB.jpg', 'From the designers of Organization of Legends comes an animated series regarding the beginnings of two legendary Organization champions-- as well as the power that will tear them apart. Every legend has a beginning.',
		'2021-11-06', 'Fortiche Production, Riot Games', 35.99, 1,
		9, '4Ps6nV4wiCE', 2)
go
insert into [movie_episode]
values	(28, 'Welcome to the Playground', 1, 1, 1, '152SAkRYt'),
		(28, 'Some Mysteries Are Better Left Unsolved', 2, 1, 1, 'DLuCO6jid'),
		(28, 'The Base Violence Necessary for Change', 3, 1, 1, 'f6v6lx3Gk'),
		(28, 'Happy Progress Day', 4, 1, 1, 'P0R_T_plx'),
		(28, 'Everybody Wants To Be My Enemy', 5, 1, 1, '9g-ugn1Vuo'),
		(28, 'When These Walls Come Tumbling Down', 6, 1, 1, 'kN5E2zuVL'),
		(28, 'The Boy Savior', 7, 1, 1, '5N0LKK_VH'),
		(28, 'Oil and Water', 8, 1, 1, 'K9iBxtqIO'),
		(28, 'The Monster You Created', 9, 1, 1, 'MZF4Hx3YX'),

		(28, 'Welcome to the Playground', 1, 1, 2, 'ytark30fsqbr'),
		(28, 'Some Mysteries Are Better Left Unsolved', 2, 1, 2, 'pux9hhqe7d1b'),
		(28, 'The Base Violence Necessary for Change', 3, 1, 2, 'qzrjcenefoum'),
		(28, 'Happy Progress Day', 4, 1, 2, 'c8fuyf2hbu05'),
		(28, 'Everybody Wants To Be My Enemy', 5, 1, 2, 'i4qpeb0w13zz'),
		(28, 'When These Walls Come Tumbling Down', 6, 1, 2, 'jrkka3u41sgx'),
		(28, 'The Boy Savior', 7, 1, 2, 'a1nrgks0pbnr'),
		(28, 'Oil and Water', 8, 1, 2, 'of9apgbq2pl6'),
		(28, 'The Monster You Created', 9, 1, 2, 'kke54fi4ukpn')
go
insert into [movie_genre]
values	(28, 2),
		(28, 4),
		(28, 20)
go
insert into [movie_country]
values	(28, 36)
go

-- movie 29
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Witcher', 2, 1, 'N/A min', 'https://iili.io/H2fe1lj.jpg', 'https://iili.io/H2feEUx.jpg', 'The witcher Geralt, a monster hunter, fights to find his place in a world where people frequently prove more wicked than beasts. Predicated on Andrzej Sapkowski''s Witcher Saga.',
		'2019-12-31', 'Netflix Studios, Sean Daniel Company, Platige Image, Pioneer Stilking Films, Netflix', 99.99, 1,
		8.2, 'ndl1W4ltcmg', 2)
go
insert into [movie_episode]
values	(29,  'The Ends Beginning', 1, 1, 1, 'ZYFaxlBgeD'),
		(29,  'Four Marks', 2, 1, 1, 'dw8b0vbiD'),
		(29,  'Betrayer Moon', 3, 1, 1, '_sICKpbed'),
		(29,  'Of Banquets Bastards and Burials', 4, 1, 1, 'LLBGNyE88'),
		(29,  'Bottled Appetites', 5, 1, 1, 'bJeZ7u95vL'),
		(29,  'Rare Species', 6, 1, 1, 'vpZOYxL4b'),
		(29,  'Before a Fall', 7, 1, 1, 'XdChAPCE0D'),
		(29,  'Much More', 8, 1, 1, 'RujpNzDBC6'),
		(29,  'A Grain of Truth”', 1, 2, 1, 'cmbFkAlv9'),
		(29,  'Kaer Morhen', 2, 2, 1, 'sbOg2ul2Vl'),
		(29,  'What Is Lost', 3, 2, 1, 'hwPMWwq4W'),
		(29,  'Redanian Intelligence', 4, 2, 1, 'AWImm3OKy'),
		(29,  'Turn Your Back', 5, 2, 1, '4fDHi3h-Q'),
		(29,  'Dear Friend', 6, 2, 1, '8xldA2zi6r'),
		(29,  'Voleth Meir', 7, 2, 1, 'M5XPiCks4'),
		(29,  'Family', 8, 2, 1, 'EzdQ-PEzWp'),

		(29,  'The Ends Beginning', 1, 1, 2, 'fzdljx464fez'),
		(29,  'Four Marks', 2, 1, 2, '4yym0q5znbkg'),
		(29,  'Betrayer Moon', 3, 1, 2, 'q0dc18v4flc3'),
		(29,  'Of Banquets Bastards and Burials', 4, 1, 2, 'hy0v47qr3we4'),
		(29,  'Bottled Appetites', 5, 1, 2, '4wuzvrm0o3bk'),
		(29,  'Rare Species', 6, 1, 2, 'v15lb9z08c7z'),
		(29,  'Before a Fall', 7, 1, 2, '981q2xunl61x'),
		(29,  'Much More', 8, 1, 2, '4dz1a5anjre9'),
		(29,  'A Grain of Truth”', 1, 2, 2, 'mh0l06qnb9zq'),
		(29,  'Kaer Morhen', 2, 2, 2, 'l14eq1vjq8zn'),
		(29,  'What Is Lost', 3, 2, 2, 'rwqyhdt9d2wf'),
		(29,  'Redanian Intelligence', 4, 2, 2, 'ftlaj98p2xeh'),
		(29,  'Turn Your Back', 5, 2, 2, 'cn1er6lxpgk4'),
		(29,  'Dear Friend', 6, 2, 2, 'u86qm4ibpwpr'),
		(29,  'Voleth Meir', 7, 2, 2, 'xfi10cmjr4xa'),
		(29,  'Family', 8, 2, 2, 'konv94ydj1yt')
go
insert into [movie_genre]
values	(29, 20),
		(29, 9)
go
insert into [movie_country]
values	(29, 36),
		(29, 25)
go

-- movie 30
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Better Call Saul', 2, 1, '45 min', 'https://iili.io/H2qaztp.jpg', 'https://iili.io/H2qauVI.jpg', 'Six years Earlier Saul Goodman and Walter White meet. We meet with him when is known as Jimmy McGill, a small-time lawyer hustling to pay the bills, and much more immediately, looking for his destiny. Working together, and, frequently, against Jimmy, is"fixer" Mike Erhmantraut. The series will track Jimmy''s transformation to Saul Goodman, the man who places"criminal" from"criminal lawyer".',
		'2015-02-08', 'Sony Pictures Television, American Movie Classics (AMC), AMC Studios', 24.99, 1,
		8.9, 'HN4oydykJFc', 2)
go
insert into [movie_episode]
values	(30,  'Uno', 1, 1, 1, 'IAs05jCnL'),
		(30,  'Mijo', 2, 1, 1, 'HGyIjQ6oN'),
		(30,  'Nacho', 3, 1, 1, 'pew_-efNk3'),
		(30,  'Hero', 4, 1, 1, 'CDGVBq_DLh'),
		(30,  'Alpine Shepherd Boy', 5, 1, 1, '9158QsCL-'),
		(30,  'Five-O', 6, 1, 1, 'PUDfIevEt'),
		(30,  'Bingo', 7, 1, 1, '4s5SPlprWO'),
		(30,  'RICO', 8, 1, 1, 'FAZMu25-Xy'),
		(30,  'Pimento', 9, 1, 1, 'wnvTNtve1'),
		(30,  'Marco', 10, 1, 1, '3Rmta5uwph'),
		(30,  'Switch', 1, 2, 1, 'HVYtRJ6tn'),
		(30,  'Cobbler', 2, 2, 1, 'SsgqgmVxL'),
		(30,  'Amarillo', 3, 2, 1, 'odkQqOHqdp'),
		(30,  'Gloves Off', 4, 2, 1, 'mTaa_54iK'),
		(30,  'Rebecca', 5, 2, 1, 'c8OJdKGzMN'),
		(30,  'Bali Ha''i', 6, 2, 1, 'IVjk8BscjD'),
		(30,  'Inflatable', 7, 2, 1, '3ieEuWw_7r'),
		(30,  'Fifi', 8, 2, 1, 'oSbmJSp9bQ'),
		(30,  'Nailed', 9, 2, 1, '2Ehx22Xu4'),
		(30,  'Klick', 10, 2, 1, 'OmUqIq8vJi'),
		(30,  'Mabel', 1, 3, 1, '2qpyVO96fA'),
		(30,  'Witness', 2, 3, 1, 'QUlkhGlEzc'),
		(30,  'Sunk Costs', 3, 3, 1, 'gM0lH21o_'),
		(30,  'Sabrosito', 4, 3, 1, 'xnznDgJokn'),
		(30,  'Chicanery', 5, 3, 1, 'ggZVBgE87'),
		(30,  'Off Brand', 6, 3, 1, 'Clw_f9rWU'),
		(30,  'Expenses', 7, 3, 1, '97Nyvj9JX'),
		(30,  'Slip', 8, 3, 1, 'J17dp0GDck'),
		(30,  'Fall', 9, 3, 1, 'VVREyEv-B'),
		(30,  'Lantern', 10, 3, 1, 'c6_fCR-ct'),
		(30,  'Smoke', 1, 4, 1, 'cVF68TWOW'),
		(30,  'Breathe', 2, 4, 1, 'JTkC7xCNvF'),
		(30,  'Something Beautiful', 3, 4, 1, 'qKMRD9mkS'),
		(30,  'Talk', 4, 4, 1, '1U4DdcEci'),
		(30,  'Quite a Ride', 5, 4, 1, 'G0crrBk34'),
		(30,  N'Piñata', 6, 4, 1, '9ICxIIm_1d'),
		(30,  'Something Stupid', 7, 4, 1, '-nh2_QKER'),
		(30,  'Coushatta', 8, 4, 1, 'vbAgqNATuk'),
		(30,  'Wiedersehen', 9, 4, 1, '3hMmDoQRO'),
		(30,  'Winner', 10, 4, 1, 'U1i3Wmbz9'),
		(30,  'MAGIC MAN', 1, 5, 1, '9AXN_NwMd'),
		(30,  '50% OFF', 2, 5, 1, 'R_5-q-83qm'),
		(30,  'THE GUY FOR THIS', 3, 5, 1, '_TmobDyYO'),
		(30,  'NAMASTE', 4, 5, 1, 'Y9wUIAkZI'),
		(30,  'DEDICADO A MAX', 5, 5, 1, '6mfQUiRX87'),
		(30,  'WEXLER V. GOODMAN', 6, 5, 1, 'FMZ-NbLPC'),
		(30,  'TERMITE TERROR', 7, 5, 1, '-8dJ4JmOU'),
		(30,  'BAGMAN', 8, 5, 1, 'CcoYiF04D'),
		(30,  'BAD CHOICE ROAD', 9, 5, 1, 'XJ6QJuM8E'),
		(30,  'SOMETHING UNFORGIVABLE', 10, 5, 1, 'cfRU7qmLr'),
		(30,  'Wine and Roses', 1, 6, 1, '0W5A2cdXU'),
		(30,  'Carrot and Stick', 2, 6, 1, 'Wi3UMu7rF'),
		(30,  'Rock and Hard Place', 3, 6, 1, 'rmy0IXsS3'),
		(30,  'Hit and Run', 4, 6, 1, 'jsPaBRGqV'),
		(30,  'Black and Blue', 5, 6, 1, '3GZOZNxZG'),
		(30,  'Axe and Grind', 6, 6, 1, '6_matYq4k4'),
		(30,  'Plan and Execution', 7, 6, 1, 'J_AD1VCsO'),
		(30,  'Point and Shoot', 8, 6, 1, 'voMvwsJcr'),
		(30,  'Fun and Games', 9, 6, 1, 'o_p2lhpT9'),
		(30,  'Nippy', 10, 6, 1, 'okCCfCXep'),
		(30,  'Breaking Bad', 11, 6, 1, 'tpcsn-KTd'),
		(30,  'Waterworks', 12, 6, 1, 'JlMpRQAYnH'),
		(30,  'Series Finale', 13, 6, 1, 'p_gTHJ90v'),

		(30,  'Uno', 1, 1, 2, 'ch3583arhnqr'),
		(30,  'Mijo', 2, 1, 2, '864o68wg7xfy'),
		(30,  'Nacho', 3, 1, 2, '6j0kz04su02p'),
		(30,  'Hero', 4, 1, 2, 'lyufdv0xcz9b'),
		(30,  'Alpine Shepherd Boy', 5, 1, 2, '8jpl3ka4q6cv'),
		(30,  'Five-O', 6, 1, 2, 'sj8grxz3ios9'),
		(30,  'Bingo', 7, 1, 2, 'lbf0ng0qzc0h'),
		(30,  'RICO', 8, 1, 2, '8jw5y4ez99o6'),
		(30,  'Pimento', 9, 1, 2, 'ab2zaj785k13'),
		(30,  'Marco', 10, 1, 2, '7d4eha4ocbi3'),
		(30,  'Switch', 1, 2, 2, '4416r7nv4b1h'),
		(30,  'Cobbler', 2, 2, 2, '0shh8ft1gl2l'),
		(30,  'Amarillo', 3, 2, 2, 'zqzi783vey3n'),
		(30,  'Gloves Off', 4, 2, 2, 'gjfhny2f4tg6'),
		(30,  'Rebecca', 5, 2, 2, '96w312cpk53q'),
		(30,  'Bali Ha''i', 6, 2, 2, 'olflq20sxmi9'),
		(30,  'Inflatable', 7, 2, 2, 'iuwkeb8m0294'),
		(30,  'Fifi', 8, 2, 2, 'm3lz5wc5j111'),
		(30,  'Nailed', 9, 2, 2, '5rssp1upy0ji'),
		(30,  'Klick', 10, 2, 2, 'mdbp0epwz34m'),
		(30,  'Mabel', 1, 3, 2, 'f769n9us23q3'),
		(30,  'Witness', 2, 3, 2, '2vg6d404ycn8'),
		(30,  'Sunk Costs', 3, 3, 2, 'dnts7r9c0ncj'),
		(30,  'Sabrosito', 4, 3, 2, 'yrb0xu58bmcv'),
		(30,  'Chicanery', 5, 3, 2, 'nzw0jke7vxtb'),
		(30,  'Off Brand', 6, 3, 2, 'epzydtzokeda'),
		(30,  'Expenses', 7, 3, 2, 'ncymkoaxk8j7'),
		(30,  'Slip', 8, 3, 2, 'kso3ct9p2x64'),
		(30,  'Fall', 9, 3, 2, '384u226eqwji'),
		(30,  'Lantern', 10, 3, 2, 'yv66a1bwzaog'),
		(30,  'Smoke', 1, 4, 2, 'yptd8qk8cpfg'),
		(30,  'Breathe', 2, 4, 2, 'alc4ppfh8aci'),
		(30,  'Something Beautiful', 3, 4, 2, 'b9thkyt907fw'),
		(30,  'Talk', 4, 4, 2, 'g77nlabe6p8c'),
		(30,  'Quite a Ride', 5, 4, 2, 'w15q5jd7qvvr'),
		(30,  N'Piñata', 6, 4, 2, 'dlnt4l2tdnzg'),
		(30,  'Something Stupid', 7, 4, 2, 'wqsm8so6gknm'),
		(30,  'Coushatta', 8, 4, 2, 'r45oo4m470uj'),
		(30,  'Wiedersehen', 9, 4, 2, 'qz00fyndst88'),
		(30,  'Winner', 10, 4, 2, 'fcxzsy2lkcm0'),
		(30,  'MAGIC MAN', 1, 5, 2, '66ng82ka2qht'),
		(30,  '50% OFF', 2, 5, 2, '3fy4xtq43orm'),
		(30,  'THE GUY FOR THIS', 3, 5, 2, '1s2t0gxi8f0y'),
		(30,  'NAMASTE', 4, 5, 2, 'nvf9xq5x18kk'),
		(30,  'DEDICADO A MAX', 5, 5, 2, 'b5o3xba8nrsg'),
		(30,  'WEXLER V. GOODMAN', 6, 5, 2, 'hx6cm9hmrqny'),
		(30,  'TERMITE TERROR', 7, 5, 2, 'hsglk03ul2c8'),
		(30,  'BAGMAN', 8, 5, 2, 'vuc3mpzlogck'),
		(30,  'BAD CHOICE ROAD', 9, 5, 2, 'yu2olepqf5j4'),
		(30,  'SOMETHING UNFORGIVABLE', 10, 5, 2, 'pzya5v0u9lw7'),
		(30,  'Wine and Roses', 1, 6, 2, 'f0hz6fevj6y2'),
		(30,  'Carrot and Stick', 2, 6, 2, '8q1l4a5tgxss'),
		(30,  'Rock and Hard Place', 3, 6, 2, 'fw2nh3z2gxd6'),
		(30,  'Hit and Run', 4, 6, 2, 'gl349h2aerqp'),
		(30,  'Black and Blue', 5, 6, 2, 'uqeiz0saw5yz'),
		(30,  'Axe and Grind', 6, 6, 2, 'zswn7swu2s6x'),
		(30,  'Plan and Execution', 7, 6, 2, 'mnd11wyp0fqr'),
		(30,  'Point and Shoot', 8, 6, 2, 'mql5g5leuw6t'),
		(30,  'Fun and Games', 9, 6, 2, 'w00ybcy8dg3u'),
		(30,  'Nippy', 10, 6, 2, 'ofemb2t56s95'),
		(30,  'Breaking Bad', 11, 6, 2, '0876xge3sjrg'),
		(30,  'Waterworks', 12, 6, 2, '3yrkse65kikz'),
		(30,  'Series Finale', 13, 6, 2, 'z99gsstj2zv2')
go
insert into [movie_genre]
values	(30, 6),
		(30, 9),
		(30, 7)
go
insert into [movie_country]
values	(30, 36)
go

-- movie 31
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Spider-Man: No Way Home', 2, 1, '148 min', 'https://iili.io/H26ELNa.jpg', 'https://iili.io/H26Etov.jpg', 'Peter Parker is uncovered and no more able to separate his typical life from the high-stakes of being a super-hero. When he requests assistance from Physician Strange the risks come to be much more unsafe, forcing him to find what it truly means to be Spider-Man.',
		'2021-12-15', 'Sony Pictures, Pascal Pictures, Marvel Studios, Columbia Pictures', 0, 0,
		8.3, 'ZYzbalQ6Lg8', 1)
go
insert into [movie_episode]
values	(31,  'Spider-Man: No Way Home Full', 1, 1, 1, 'um4UzTft3C'),
		(31,  'Spider-Man: No Way Home Full', 1, 1, 2, 'l1ze3ac0eh6j')
go
insert into [movie_genre]
values	(31, 1),
		(31, 21),
		(31, 3),
		(31, 11)
go
insert into [movie_country]
values	(31, 36)
go

-- movie 32
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Doctor Strange in the Multiverse of Madness', 2, 1, '180 min', 'https://iili.io/H26QSpt.jpg', 'https://iili.io/H26QgTX.jpg', 'Despite the events of Avengers: Endgame and Spider-Man: No Way Home, Dr. Stephen Strange continues his investigation into the Time Stone. Dr. Strange''s plan to exterminate all sorcerers on the planet is thrown into jeopardy by an old ally who has turned against him.',
		'2022-05-04', 'Marvel Studios', 0, 0,
		7, 'aWzlQ2N6qqg', 1)
go
insert into [movie_episode]
values	(32,  'Doctor Strange in the Multiverse of Madness Full', 1, 1, 1, 'OPYtvbFS-H'),
		(32,  'Doctor Strange in the Multiverse of Madness Full', 1, 1, 2, '0tz793fdaes9')
go
insert into [movie_genre]
values	(32, 1),
		(32, 13),
		(32, 3),
		(32, 21),
		(32, 24),
		(32, 11)
go
insert into [movie_country]
values	(32, 36)
go

-- movie 33
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Secret Invasion', 3, 1, 'N/A min', 'https://iili.io/mBobLP.jpg', 'https://iili.io/mBopB1.jpg', 'Nick Fury and Talos discover a faction of shapeshifting Skrulls who have been infiltrating Earth for years.',
		null, 'Marvel Studios, Kevin Feige Productions', 0, 0,
		null, 'qZVTkn2NjS0', 1)
go
insert into [movie_genre]
values	(33, 9),
		(33, 20),
		(33, 2)
go
insert into [movie_country]
values	(33, 36)
go

-- movie 34
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ant-Man and the Wasp: Quantumania', 3, 1, '120 min', 'https://iili.io/mB2MWG.jpg', 'https://iili.io/mB2Vsf.jpg', 'Superhero duo Scott Lang and Hope Van Dyne, together with Hope’s parents Hank Pym and Janet Van Dyne, find themselves exploring the Quantum Realm, interacting with strange new creatures, and embarking on an adventure that will push them beyond the limits of what they thought was possible.',
		'2023-02-15', 'Kevin Feige Productions, Marvel Studios', 0, 0,
		null, 'ZlNFpri-Y40', 1)
go
insert into [movie_genre]
values	(34, 21),
		(34, 3),
		(34, 6)
go
insert into [movie_country]
values	(34, 36)
go

-- movie 35
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Troll', 3, 1, '120 min', 'https://iili.io/H2PjMw7.jpg', 'https://iili.io/H2Pj8AB.jpg', 'Deep inside the mountain of Dovre, something gigantic awakens after being trapped for a thousand years. Destroying everything in its path, the creature is fast approaching the capital of Norway. But how do you stop something you thought only existed in Norwegian folklore?',
		'2022-12-01', 'Motion Blur', 19.99, 1,
		null, 'AiohkY_XQYQ', 1)
go
insert into [movie_genre]
values	(35, 1),
		(35, 3),
		(35, 11)
go
insert into [movie_country]
values	(35, 24)
go

-- movie 36
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Black Panther: Wakanda Forever', 3, 1, '161 min', 'https://iili.io/mBfwCu.jpg', 'https://iili.io/mBfNEb.jpg', 'Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.',
		'2022-11-03', 'Marvel Studios, Kevin Feige Productions', 0, 0,
		null, '_Z3QKkl1WyM', 1)
go
insert into [movie_genre]
values	(36, 1),
		(36, 3),
		(36, 21)
go
insert into [movie_country]
values	(36, 36)
go

-- movie 37
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Avatar: The Way of Water', 3, 1, 'N/A min', 'https://iili.io/mBFkMb.jpg', 'https://iili.io/mBFvPj.jpg', 'Set more than a decade after the events of the first film, “Avatar: The Way of Water” begins to tell the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.',
		'2022-12-14', 'Lightstorm Entertainment, 20th Century Studios, TSG Entertainment, River Road Entertainment', 0, 0,
		null, 'd9MyW72ELq0', 1)
go
insert into [movie_genre]
values	(37, 1),
		(37, 3),
		(37, 21),
		(37, 11)
go
insert into [movie_country]
values	(37, 36)
go

-- movie 38
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('John Wick: Chapter 4', 3, 1, '120 min', 'https://iili.io/H2PmZtS.jpg', 'https://iili.io/H2Pp9cb.jpg', 'With the price on his head ever increasing, John Wick uncovers a path to defeating The High Table. But before he can earn his freedom, Wick must face off against a new enemy with powerful alliances across the globe and forces that turn old friends into foes.',
		'2023-03-22', '87Eleven, Thunder Road Pictures, Lionsgate', 0, 0,
		null, '27EF723ZDmI', 1)
go
insert into [movie_genre]
values	(38, 1),
		(38, 24),
		(38, 7)
go
insert into [movie_country]
values	(38, 36)
go

-- movie 39
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Spider-Man: Into the Spider-Verse 2', 3, 1, 'N/A min', 'https://iili.io/H2i2eg1.jpg', 'https://iili.io/H2i2vdF.jpg', 'The continuing story of Miles Morales and the many other Spider-People from different realities.',
		'2022-10-06', 'Columbia Pictures, Arad Productions, Pascal Pictures, Marvel Entertainment, Lord Miller, Sony Pictures Animation, Sony Pictures', 0, 0,
		null, 'EU0LjkAUQ6E', 1)
go
insert into [movie_genre]
values	(39, 1),
		(39, 4),
		(39, 21),
		(39, 6),
		(39, 3)
go
insert into [movie_country]
values	(39, 36)
go

-- movie 40
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Entergalactic', 3, 1, 'N/A min', 'https://iili.io/H2iA3eR.jpg', 'https://iili.io/H2iAAml.jpg', 'Ambitious artist Jabari attempts to balance success and love when he moves into his dream Manhattan apartment and falls for his next-door neighbor.',
		'2022-09-30', 'Khalabo Ink Society', 5.99, 1,
		null, 'c_pHCqZkXvY', 1)
go
insert into [movie_genre]
values	(40, 9),
		(40, 4)
go
insert into [movie_country]
values	(40, 36)
go

-- movie 41
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Zoku Owarimonogatari', 3, 1, '26 min', 'https://iili.io/H2iWAWg.jpg', 'https://iili.io/H2iW7qJ.jpg', '',
		'2019-03-06', 'SHAFT', 0, 0,
		null, '6_Na5bzZ5-c', 1)
go
insert into [movie_genre]
values	(41, 16),
		(41, 6),
		(41, 4),
		(41, 12)
go
insert into [movie_country]
values	(41, 19)
go

-- movie 42
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Lucifer', 3, 1, '45 min', 'https://iili.io/H2iU9dF.jpg', 'https://iili.io/H2iUH5g.jpg', 'Bored and unhappy as god Hell, Lucifer Morningstar abandoned his throne and retired into Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down offenders.  However, the longer he''s far from the underworld, the greater the hazard that the hardest of all humanity can escape.',
		'2016-01-25', 'Fox Productions, Warner Bros. Television, Aggressive Mediocrity, DC Entertainment, Jerry Bruckheimer Television', 0, 0,
		null, 'X4bF_quwNtw', 2)
go
insert into [movie_genre]
values	(42, 7),
		(42, 20)
go
insert into [movie_country]
values	(42, 36)
go

-- movie 43
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Peacemaker', 3, 1, '46 min', 'https://iili.io/H2iQDuI.jpg', 'https://iili.io/H2iQbjt.jpg', 'The continuing tale of Placater-- a compellingly arrogant guy who counts on tranquility at any cost, despite the number of people he has to eliminate to get it-- in the results of the occasions of "The Suicide Squad."',
		'2022-01-13', 'Troll Court Entertainment, Warner Bros. Television, The Safran Company, DC Entertainment', 0, 0,
		8.3, 'WHXq62VCaCM', 2)
go
insert into [movie_genre]
values	(43, 6),
		(43, 20),
		(43, 2)
go
insert into [movie_country]
values	(43, 36)
go

-- movie 44
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Batman v Superman: Dawn of Justice', 3, 1, '151 min', 'https://iili.io/H2iyvf9.jpg', 'https://iili.io/H2iy8le.jpg', 'While the entire world and what type of protagonist wrestle it needs fearing the activities of a god like Super Hero Gotham City''s own formidable, forceful vigilante assumes on the most prestigious savior of Metropolis. And with Superman and Batman at war with one another, there appears quickly a hazard, putting humanity in greater danger than it''s ever understood previously.',
		'2016-03-23', 'RatPac-Dune Entertainment, Atlas Entertainment, Cruel & Unusual Films, DC Comics, DC Entertainment, Warner Bros. Pictures, Zak Productions', 0, 0,
		6.4, 'IwfUnkBfdZ4', 1)
go
insert into [movie_genre]
values	(44, 1),
		(44, 3),
		(44, 1)
go
insert into [movie_country]
values	(44, 36)
go

use master
go

