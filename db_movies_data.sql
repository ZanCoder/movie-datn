use db_movies
go

insert into [account_role]
values	('ADMIN'),
		('MOD'),
		('USER')
go

insert into [account]([username], [email], [password_hash], [role])
values	(N'sK0rpion', 'nhatphay722@gmail.com', '$2a$10$rh3IlC/fdXP8QhXY.0kvouhDEWpzwGlBCGYEiSa/JNgbb.h8QnYqK', 3), -- nhatphat0
		(N'Admin', 'admin@gmail.com', '$2a$10$ZkSRUbURvjSZyyNQdJYV1OTPOwqgXPy34/ROVV9Ug8LUm7oQsmcjO', 1) -- admin123
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
values	('Hydrax'),
		('Streamlare')
go

insert into [status]
values	('Currently Airing'),
		('Completed'),
		('Upcoming')

-- movie 1
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('John Wick', 2, 1, '101 min', 'https://iili.io/D81t8x.jpg', 'https://iili.io/D8EK8v.jpg', 'Ex-hitman John Wick comes out of retirement to find the gangsters that required everything from him.',
		'2014-10-24', 'Summit Entertainment, Thunder Road Pictures, 87Eleven', 'Keanu Reeves, Michael Nyqvist, Alfie Allen', 0, 0,
		7.4, 'https://www.youtube.com/watch?v=C0BMx-qxsP4', 1)
go
insert into [movie_episode]
values	(1, 'John Wick Full', 1, 1, 1, '5SQvjRNC3'),
		(1, 'John Wick Full', 1, 1, 2, 'rJmXD0kNXMonGg8o')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Spider-Man: Into the Spider-Verse', 2, 1, '117 min', 'https://iili.io/D81Q3b.jpg', 'https://iili.io/D81DyQ.jpg', 'Miles Morales is juggling his lifetime and also being a spiderman. When Wilson"Kingpin" Fisk uses a super collider, others from across the SpiderVerse are hauled to the measurement.',
		'2018-12-06', 'Columbia Pictures, Marvel Entertainment, Sony Pictures Entertainment SPE', 'Oscar Isaac, Lily Tomlin, Nicolas Cage, Hailee Steinfeld, Chris Pine, Lake Bell, Kathryn Hahn, Liev Schreiber, Jake Johnson, Joaquín Cosio, Zoë Kravitz, Jorma Taccone, Shameik Moore, Mahershala Ali, Kimiko Glenn, John Mulaney, Stan Lee, Mimi Davila, Natalie Morales, Claudia Choi, Greta Lee, Brian Tyree Henry, Luna Lauren Velez, Kim Yarbrough, Marvin Krondon Jones III, Edwin H Bravo, Jessica Mikayla Adams, Kelby Joseph, Gredel Berrios Calladine, Sarah D Cole', 0, 0,
		 8.4, 'https://www.youtube.com/watch?v=g4Hbz2jLxvQ', 1)
go
insert into [movie_episode]
values	(2, 'Spider-Man: Into the Spider-Verse Full', 1, 1, 1, 'F32OHLOs3'),
		(2, 'Spider-Man: Into the Spider-Verse Full', 1, 1, 2, 'vJ41zYNXoowlwA3g')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Joker', 2, 1, '118 min', 'https://iili.io/D8EHn1.jpg', 'https://iili.io/D81O4s.jpg', 'Throughout the 1980s, while becoming an infamous crime figure, there is a comedian driven insane and turns to a life of chaos and crime in Gotham City.',
		'2019-10-02', 'Village Roadshow Pictures, Warner Bros', 'Joaquin Phoenix, Zazie Beetz, Brett Cullen, Frances Conroy, Robert De Niro', 0, 0,
		8.4, 'https://www.youtube.com/watch?v=zAGVQLHvwOY', 1)
go
insert into [movie_episode]
values	(3, 'Joker Full', 1, 1, 1, '655-ij0ss'),
		(3, 'Joker Full', 1, 1, 2, '270kn3GWagjn8Wo6')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Kingsman: The Secret Service ', 2, 1, '129 min', 'https://iili.io/D814Z7.jpg', 'https://iili.io/D8EJMF.jpg', 'A spy company that recruits an unrefined but promising street kid into the agency training regime as a world wide threat''s narrative stems out of a tech ace.',
		'2015-02-13', 'Marv Films, Twentieth Century Fox', 'Nicholas Agnew, Samantha Womack, Tom Prior, Lee Nicholas Harris, Nicholas Banks', 0, 0,
		7.7, 'https://www.youtube.com/watch?v=kl8F-8tR8to', 1)
go
insert into [movie_episode]
values	(4, 'Kingsman: The Secret Service Full', 1, 1, 1, 'fjtjnRes-'),
		(4, 'Kingsman: The Secret Service Full', 1, 1, 2, 'gNXKzZOWob5lEk6P')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ready Player One', 2, 1, '140 min', 'https://iili.io/D81iGe.jpg', 'https://iili.io/D81XjI.jpg', 'After the founder of a well known video game system expires, there is a virtual competition established to compete due to his or her fortune.',
		'2018-03-11', 'Village Roadshow Pictures, Warner Bros, Amblin Entertainment', 'Letitia Wright, Daniel Eghan, Antonio Mattera, Ralph Ineson, Hannah John-Kamen', 0, 0,
		7.4, 'https://www.youtube.com/watch?v=cSp1dM2Vj48', 1)
go
insert into [movie_episode]
values	(5, 'Ready Player One Full', 1, 1, 1, 'H0gO_Se-E'),
		(5, 'Ready Player One Full', 1, 1, 2, 'qwo9ly1KLXKlrWvP')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ted', 2, 1, '107 min', 'https://iili.io/D81hQt.jpg', 'https://iili.io/D81vaf.jpg', 'John Bennett, a person whose of earning his teddy bear to 15, youth wish came true has to pick between keeping the association with his girl friend or the bear.',
		'2012-06-29', 'Universal Pictures, Media Rights Capital MRC', 'Mark Wahlberg, Mila Kunis, Seth MacFarlane, Joel McHale, Giovanni Ribisi', 0, 0,
		6.9, 'https://www.youtube.com/watch?v=9fbo_pQvU7M', 1)
go
insert into [movie_episode]
values	(6, 'Ted Full', 1, 1, 1, 'Ao9ciEbtN'),
		(6, 'Ted Full', 1, 1, 2, 'WwM7lMBkomXnZKbo')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Rush Hour', 2, 1, '98 min', 'https://iili.io/D81muV.jpg', 'https://iili.io/D81pwB.jpg', 'When hongkong Inspector Lee is summoned into Los Angeles to investigate a kidnapping, the FBI doesn''t want any outside support and assigns Lee to distract from the situation. Not satisfied to watch the activity from the sidelines, Carter and Lee form an unlikely venture and inquire into the case themselves.',
		'1998-09-18', 'Roger Birnbaum Productions, New Line Cinema', 'Ken Leung, Jackie Chan, Chris Tucker', 0, 0,
		null, 'https://www.youtube.com/watch?v=JMiFsFQcFLE', 1)
go
insert into [movie_episode]
values	(7, 'Rush Hour Full', 1, 1, 1, 'mFMkae-l_'),
		(7, 'Rush Hour Full', 1, 1, 2, 'K7j0lgW5oG6lXkPv')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Batman', 2, 1, '176 min', 'https://iili.io/D81rjS.jpg', 'https://iili.io/D81Zaj.jpg', 'In his second year fighting crime, Batman finds corruption in Gotham City that involves his own family. He also faces a serial killer known as the Riddler.',
		'2022-03-04', 'Warner Bros, DC Comics', 'Jeffrey Wright, Robert Pattinson, Zoë Kravitz', 0, 0,
		7.9, 'https://www.youtube.com/watch?v=mqqft2x_Aa4', 1)
go
insert into [movie_episode]
values	(8, 'The Batman Full', 1, 1, 1, 'JFmXyI9cb'),
		(8, 'The Batman Full', 1, 1, 2, '0rwmD7pZmk0zRaZ8')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Mortal Kombat Legends: Scorpion’s Revenge', 2, 1, '80 min', 'https://iili.io/D8E3Fa.jpg', 'https://iili.io/D81EYv.jpg', 'Hanzo Hasashi is exiled to the tortuous Netherrealm after the heinous slaughter of his family by the stone-cold mercenary Sub-Zero. In exchange for his servitude to the sinister Quan Chi, he is given the opportunity to avenge his family – and is resurrected asScorpion, a lost soul bent on vengeance. Back on Earthrealm, Lord Raiden assembles a group of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade, and action star Johnny Cage – an unlikely band of heroes with only one chance to save humanity. To accomplish this, they must defeat Shang Tsung''s horde of Outworld gladiators and take control of the Mortal Kombat tournament.',
		'2020-04-14', 'Warner Bros Animation, DC Entertainment, Midway Games', 'Joel McHale, Jordan Rodrigues, Patrick Seitz, Steve Blum, Kevin Michael Richardson', 0, 0,
		7.4, 'https://www.youtube.com/watch?v=I1vccr3yWBU', 1)
insert into [movie_episode]
values	(9, 'Mortal Kombat Legends: Scorpion’s Revenge Full', 1, 1, 1, 'KKGyVhOXR'),
		(9, 'Mortal Kombat Legends: Scorpion’s Revenge Full', 1, 1, 2, 'r8WdlWmBoR9Djx4q')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('El Camino: A Breaking Bad Movie', 2, 1, '123 min', 'https://iili.io/D8Eq9R.jpg', 'https://iili.io/D81WTN.jpg', 'In the wake of the dramatic escape from captivity, Jesse Pinkman must come to terms with his past in order to forge some kind of future.',
		'2019-10-11', 'Netflix, Sony Pictures Television', 'Robert Forster, Bryan Cranston, David Mattey, Aaron Paul, Jonathan Banks, Jesse Plemons', 0, 0,
		7.3, 'https://www.youtube.com/watch?v=1JLUn2DFW4w', 1)
go
insert into [movie_episode]
values	(10, 'El Camino: A Breaking Bad Movie Full', 1, 1, 1, 'Wo6XvcxuN'),
		(10, 'El Camino: A Breaking Bad Movie Full', 1, 1, 2, 'AoNaDdvgorNlmZg5')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Pixels', 2, 1, '105 min', 'https://iili.io/D81s6u.jpg', 'https://iili.io/D81gu2.jpg', 'Game pros are recruited by the military to fight game characters that have attacked newyork.',
		'2015-07-16', 'China Film Co., Columbia Pictures Corporation, Film Croppers Entertainment, Prime Focus, 1492 Pictures, LStar Capital, Columbia Pictures, Happy Madison Productions, China Film Group Corporation, Sony Pictures', 'William S. Taylor, Jack Fulton, Sunny Sandler, Bill Lake, Affion Crockett', 0, 0,
		5.6, 'https://www.youtube.com/watch?v=XAHprLW48no', 1)
go
insert into [movie_episode]
values	(11, 'Pixels Full', 1, 1, 1, 'XBNdBWH-s'),
		(11, 'Pixels Full', 1, 1, 2, 'YvAyz2GoxGklg9kL')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('A Silent Voice', 2, 1, '130 min', 'https://iili.io/D8EBup.jpg', 'https://iili.io/D81Mpp.jpg', 'As she''s deaf, shouya Ishida starts raping the brand newest girl in class, Shouko Nishimiya. But since the stink continues, the rest of the class starts to turn because of his lack of empathy Shouya. If they leave elementary school, Shouko and Shouya usually do not talk with each other again... before a young, more educated Shouya, tormented by his past behaviour, decides he must see Shouko yet again. He wants to atone for his sins, but is it already too late...?',
		'2016-09-17', 'Kyoto Animation', 'Miyu Irino, Saori Hayami, Aoi Yuki, Kensho Ono, Yuki Kaneko', 0, 0,
		8.1, 'https://www.youtube.com/watch?v=nfK6UgLra7g', 1)
go
insert into [movie_episode]
values	(12, 'A Silent Voice Full', 1, 1, 1, 'bxeoqMiim2'),
		(12, 'A Silent Voice Full', 1, 1, 2, 'BQe7lBoa6jMn59bO')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ip Man', 2, 1, '108 min', 'https://iili.io/D81NGn.jpg', 'https://iili.io/D8Ed6g.jpg', 'A semi-biographical account of Yip Man to show the martial art of Wing Chun. The film centers on events surrounding Ip that took place throughout the Second Sino-Japanese War to 1940s. Directed by Wilson Yip, the film features fight choreography by Sammo Hung, and stars Donnie Yen at the lead character.',
		'2008-12-12', 'Mandarin Films Distribution Co.', 'Donnie Yen, Simon Yam, Lynn Hung, Hiroyuki Ikeuchi, Gordon Lam', 0, 0,
		8, 'https://www.youtube.com/watch?v=RBYbqO_FUA4', 1)
go
insert into [movie_episode]
values	(13, 'Ip Man Full', 1, 1, 1, 'Gw8xJ4YGh'),
		(13, 'Ip Man Full', 1, 1, 2, 'PjrQlo0N8MEnyOYk')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Social Network', 2, 1, '121 min', 'https://iili.io/D81l4a.jpg', 'https://iili.io/D8EFcJ.jpg', 'Education genius Mark Zuckerberg sits at his personal laptop and Harvard undergrad and heatedly begins taking care of a fresh idea. At a fury of programming and blogging, what begins within his dorm room to get a site among friends becomes a global media and also a revolution in communication. Only six decades and 500 million friends later, Mark Zuckerberg will be your youngest billionaire ever sold... but for this entrepreneur, success contributes to both legal and personal complications.',
		'2010-10-01', 'Columbia Pictures, Relativity Media, Scott Rudin Productions, Trigger Street Productions, Michael De Luca Productions', 'Jesse Eisenberg, Andrew Garfield, Justin Timberlake, Armie Hammer, Max Minghella', 0, 0,
		7.8, 'https://www.youtube.com/watch?v=lB95KLmpLR4', 1)
go
insert into [movie_episode]
values	(14, 'The Social Network Full', 1, 1, 1, 'jldx8_2f-'),
		(14, 'The Social Network Full', 1, 1, 2, '270kn3GWA8gn8Wo6')
go
insert into [movie_genre]
values	(14, 9)
go
insert into [movie_country]
values	(14, 36)
go

-- movie 15
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Godzilla vs. Kong', 2, 1, '113 min' , 'https://iili.io/D8hPOG.jpg', 'https://iili.io/D8hibf.jpg', 'In a time when beasts walk the Planet, humankind''s defend its future sets Godzilla and also Kong on a clash that will certainly see the two most effective pressures of nature on earth clash in a magnificent fight for the ages.',
		'2021-03-24', 'Legendary Entertainment, Warner Bros. Pictures', 'Alexander Skarsgård, Millie Bobby Brown, Shun Oguri, Brian Tyree Henry, Rebecca Hall', 0, 0,
		6.3, 'https://www.youtube.com/watch?v=odM92ap8_c0', 1)
go
insert into [movie_episode]
values	(15, 'Godzilla vs. Kong Full', 1, 1, 1, 'LlpiXuMhY'),
		(15, 'Godzilla vs. Kong Full', 1, 1, 2, '270kn3GWA8gn8Wo6')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Edge of Tomorrow', 2, 1, '113 min', 'https://iili.io/D8hyJe.jpg', 'https://iili.io/D8j95u.jpg', 'Leading Bill Cage can be an officer who hasn''t seen a time of combat if he dropped and is demoted into combat. Cage is murdered within moments, managing to take an alien down . He awakens back at the start of the same day and can be made to struggle and die again... and - as physical contact with all the alien has thrown him into a time loop.',
		'2014-05-27', 'RatPac-Dune Entertainment, Village Roadshow Pictures, Viz Media, 3 Arts Entertainment, Warner Bros. Pictures', 'David Kaye, Lara Pulver, Sebastian Blunt, Beth Goddard, Aaron Romano', 0, 0,
		7.9, 'https://www.youtube.com/watch?v=vw61gCe2oqI', 1)
go
insert into [movie_episode]
values	(16, 'Edge of Tomorrow Full', 1, 1, 1, 'TmonNb8_Y'),
		(16, 'Edge of Tomorrow Full', 1, 1, 2, 'OE0wDKMdwN0l3oMa')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('The Sandman', 2, 1, '45 min', 'https://iili.io/D8jBg1.jpg', 'https://iili.io/D8jndF.jpg', 'Morpheus, the King of Dreams, embarks on a journey across worlds to recover what was stolen from him and reclaim his power after years of imprisonment.',
		'2022-08-05', 'Phantom Four, DC Entertainment, Warner Bros. Television, Blank Corporation, Purepop', 'Tom Sturridge, Gwendoline Christie, Boyd Holbrook, Kirby Howell-Baptiste, Jenna Coleman', 19.99, 1,
		7.7, 'https://www.youtube.com/watch?v=83ClbRPRDXU', 2)
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

		(17, 'Dream Of A Thousand Cats Calliope', 1, 1, 2, 'YvAyz2GoxLklg9kL'),
		(17, 'Sleep of the Just', 2, 1, 2, 'OEbPDaQL2pMD3M1k'),
		(17, 'Imperfect Hosts', 3, 1, 2, 'b95OD11oLp7D4GdE'),
		(17, 'Dream a Little Dream of Me', 4, 1, 2, 'Jj8GzOqRx0MnvPog'),
		(17, 'A Hope in Hell', 5, 1, 2, 'gNXKzZOW9GKlEk6P'),
		(17, '24 and 7', 6, 1, 2, 'Gm80z5508X4zr1xW'),
		(17, 'The Sound of Her Wings', 7, 1, 2, 'vb6NnJyEv71lPOkZ'),
		(17, 'The Dolls House', 8, 1, 2, 'vBgrD8GkmX8nMZ64'),
		(17, 'Playing House', 9, 1, 2, 'vJ41zYNXW4vlwA3g'),
		(17, 'Collectors', 10, 1, 2, 'vjPWlqR1Odmn34JK'),
		(17, 'Lost Hearts', 11, 1, 2, 'WwM7lMBkY9ZnZKbo')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Moon Knight', 2, 1, '45 min', 'https://iili.io/D8juXR.jpg', 'https://iili.io/D8jAsp.jpg', 'Mercenary Marc Spector and mild-mannered gift shop employee Steven Grant find that they both suffer from dissociative identity disorder and share the same body. It''s a delicate balancing act for Steven/Marc as their foes close in on them, and they''re forced to deal with a deadly Egyptian enigma.',
		'2022-03-30', 'Marvel Studios', 'Ethan Hawke, Oscar Isaac, May Calamawy, Saffron Hocking, Gaspard Ulliel', 0, 0,
		7.3, '', 2)
go
insert into [movie_episode]
values	(18, 'The Goldfish Problem', 1, 1, 1, 'aER8E9c6b'),
		(18, 'Summon the Suit', 2, 1, 1, '3vWNoiVO3'),
		(18, 'The Friendly Type', 3, 1, 1, 'kYeJw3lgH'),
		(18, 'The Tomb', 4, 1, 1, 'C7hbgbW8J'),
		(18, 'Asylum', 5, 1, 1, 'c6wdGg3L5'),
		(18, 'Gods and Monsters', 6, 1, 1, '04FxRbc6B'),

		(18, 'The Goldfish Problem', 1, 1, 2, 'rZ45DrpomaylM81R'),
		(18, 'Summon the Suit', 2, 1, 2, 'PjrQlo0N8o1nyOYk'),
		(18, 'The Friendly Type', 3, 1, 2, 'Gm80z55083jzr1xW'),
		(18, 'The Tomb', 4, 1, 2, 'MqN9nG3BkrbngxeA'),
		(18, 'Asylum', 5, 1, 2, 'padWzjB0YNXz9NyA'),
		(18, 'Gods and Monsters', 6, 1, 2, 'vJ41zYNXW6mlwA3g')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Tekken: Bloodline', 2, 1, '25 min', 'https://iili.io/D8jl7n.jpg', 'https://iili.io/D8j1mG.jpg', 'A hot-tempered fighter trains under his fanatical grandfather after losing his house to a powerful enemy.',
		'2022-08-18', 'Bandai Namco Entertainment', 'Kaiji Tang, Hiroshi Watanabe', 0, 0,
		6.6, 'https://www.youtube.com/watch?v=x6-donCAnCc', 2)
go
insert into [movie_episode]
values	(19, 'Episode 01', 1, 1, 1, 'rbFYCsWxY'),
		(19, 'Episode 02', 2, 1, 1, 'A2be2fJ9n'),
		(19, 'Episode 03', 3, 1, 1, '0oZo79ABJ'),
		(19, 'Episode 04', 4, 1, 1, '0CqcADY1V'),
		(19, 'Episode 05', 5, 1, 1, 'TC_19ii_l'),
		(19, 'Episode 05', 5, 1, 1, 'OfhrE6Kuf'),

		(19, 'Episode 01', 1, 1, 2, 'OEbPDaQL2jgD3M1k'),
		(19, 'Episode 02', 2, 1, 2, 'LMEmnAk5GAyzgPvq'),
		(19, 'Episode 03', 3, 1, 2, 'qwo9ly1K0oolrWvP'),
		(19, 'Episode 04', 4, 1, 2, 'K7j0lgW5kyklXkPv'),
		(19, 'Episode 05', 5, 1, 2, 'kEL0lpY10eqnavXV'),
		(19, 'Episode 06', 6, 1, 2, 'GWaolEGOQYpDdPVQ')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Loki', 2, 1, '52 min', 'https://iili.io/D8jMX4.jpg', 'https://iili.io/D8jVLl.jpg', 'After seizing the Tesseract during the events of "Avengers: Endgame," a parallel Loki is transported to the enigmatic Time Variance Authority, a governmental institution that exists outside of time and space and oversees the timeline. They give Loki the option of being eliminated from existence as a "time variation" or assisting in repairing the timeline and preventing a greater threat.',
		'2021-06-09', 'Marvel Studios', 'Tom Hiddleston, Owen Wilson, Wunmi Mosaku, Gugu Mbatha-Raw, Tara Strong', 0, 0,
		8.2, 'https://www.youtube.com/watch?v=nW948Va-l10', 2)
go
insert into [movie_episode]
values	(20, 'Glorious Purpose', 1, 1, 1, 'sg0HDkCeP'),
		(20, 'The Variant', 2, 1, 1, 'yUyBv3Qbo'),
		(20, 'Lamentis', 3, 1, 1, 'nfhF5GT32'),
		(20, 'The Nexus Event', 4, 1, 1, 'qrakWHKjp'),
		(20, 'Journey Into Mystery', 5, 1, 1, '48zc9NO5t'),
		(20, 'For All Time. Always', 6, 1, 1, 'sEUpiqWig'),

		(20, 'Glorious Purpose', 1, 1, 2, '6mwWD4okxN0nPGrY'),
		(20, 'The Variant', 2, 1, 2, 'qR7MzRraxRqzwA3d'),
		(20, 'Lamentis', 3, 1, 2, 'rB0an6wYao0D3dWb'),
		(20, 'The Nexus Event', 4, 1, 2, 'AQ9qzxNp01dn4mME'),
		(20, 'Journey Into Mystery', 5, 1, 2, 'qwo9ly1K0pvlrWvP'),
		(20, 'For All Time. Always', 6, 1, 2, 'BQe7lBoaRvMn59bO')
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
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Invincible', 2, 1, '25 min', 'https://iili.io/D8jsYF.jpg', 'https://iili.io/D8jQpa.jpg', 'Mark Grayson is a typical teenager besides the fact that his papa is one of the most effective superhero on earth. Shortly after his seventeenth birthday celebration, Mark begins to develop powers of his very own and also participates in his papa''s tutoring.',
		'2021-03-26', 'Amazon Studios, Skybound Entertainment, Image Comics', 'Sandra Oh, Kevin Michael Richardson, Andrew Rannells, Grey Griffin, Zachary Quinto', 49.99, 1,
		8.7, 'https://www.youtube.com/watch?v=-bfAVpuko5o', 2)
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

		(21, 'IT’S ABOUT TIME', 1, 1, 2, 'PjrQlo0NXZknyOYk'),
		(21, 'HERE GOES NOTHINGE', 2, 1, 2, 'WwyJnL9gvyVDMbPa'),
		(21, 'WHO YOU CALLING UGLY?', 3, 1, 2, 'OE0wDKMd48Ol3oMa'),
		(21, 'Neil Armstrong Eat Your Heart Out', 4, 1, 2, 'Jj8GzOqRYBxnvPog'),
		(21, 'That Actually Hurt', 5, 1, 2, 'ARPonQ3AX6Bzb1aE'),
		(21, 'You Look Kinda Dead', 6, 1, 2, 'r8WdlWmBON2Djx4q'),
		(21, 'We Need to Talk', 7, 1, 2, 'rB0an6wYa5YD3dWb'),
		(21, 'Where I Really Come From', 8, 1, 2, 'PjrQlo0NX2XnyOYk')
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

/* -- movie 22
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Black Adam', 3, 5, '126 min', 'https://iili.io/mBJE7t.jpg', 'https://iili.io/mBJGkX.jpg', 'Black Adam emerges from his earthly tomb about 5,000 years after being granted the Egyptian gods'' awesome powers and imprisoned.',
		'2022-10-19', 'Flynn Picture Company, New Line Cinema, Seven Bucks Productions, DC Films, Warner Bros. Pictures', 'Dwayne Johnson, Noah Centineo, Sarah Shahi, Aldis Hodge, Quintessa Swindell', 0, 0,
		7.1, 'https://www.youtube.com/watch?v=mkomfZHG5q4', 1)
go
insert into [movie_genre]
values	(22, 11),
		(22, 3),
		(22, 1)
go
insert into [movie_country]
values	(22, 36)
go

-- movie 23
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Ant-Man and the Wasp: Quantumania', 3, 1, '120 min', 'https://iili.io/mB2MWG.jpg', 'https://iili.io/mB2Vsf.jpg', 'Superhero duo Scott Lang and Hope Van Dyne, together with Hope’s parents Hank Pym and Janet Van Dyne, find themselves exploring the Quantum Realm, interacting with strange new creatures, and embarking on an adventure that will push them beyond the limits of what they thought was possible.',
		'2023-02-15', 'Kevin Feige Productions, Marvel Studios', 'Paul Rudd, Ruben Rabasa, Evangeline Lilly, Bill Murray, Jonathan Majors', 0, 0,
		null, 'https://www.youtube.com/watch?v=ZlNFpri-Y40', 1)
go
insert into [movie_genre]
values	(23, 21),
		(23, 3),
		(23, 6)
go
insert into [movie_country]
values	(23, 36)
go

-- movie 24
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Avatar: The Way of Water', 3, 1, 'N/A min', 'https://iili.io/mBFkMb.jpg', 'https://iili.io/mBFvPj.jpg', 'Set more than a decade after the events of the first film, “Avatar: The Way of Water” begins to tell the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.',
		'2022-12-14', 'Lightstorm Entertainment, 20th Century Studios, TSG Entertainment, River Road Entertainment', 'Zoe Saldana, Sam Worthington, Kate Winslet, Stephen Lang, Oona Chaplin', 0, 0,
		null, 'https://www.youtube.com/watch?v=a8Gx8wiNbs8', 1)
go
insert into [movie_genre]
values	(24, 1),
		(24, 3),
		(24, 11),
		(24, 21)
go
insert into [movie_country]
values	(24, 36)
go

-- movie 25
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Black Panther: Wakanda Forever', 3, 1, '161 min', 'https://iili.io/mBfwCu.jpg', 'https://iili.io/mBfNEb.jpg', 'Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death. As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.',
		'2022-11-03', 'Marvel Studios, Kevin Feige Productions', 'Letitia Wright, Winston Duke, Martin Freeman, Lupita Nyong''o, Tenoch Huerta', 0, 0,
		null, 'https://www.youtube.com/watch?v=_Z3QKkl1WyM', 1)
go
insert into [movie_genre]
values	(25, 1),
		(25, 3),
		(25, 21)
go
insert into [movie_country]
values	(25, 36)
go

-- movie 26
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('1899', 3, 1, '45 min', 'https://iili.io/mBCiGe.jpg', 'https://iili.io/mBCs6u.jpg', 'Passengers on an immigrant ship traveling to the new continent get caught in a mysterious riddle when they find a second vessel adrift on the open sea.',
		'2022-11-17', 'Dark Ways', N'Emily Beecham, Miguel Bernardeau, Rosalie Craig, Clara Rosager, José Pimentão', 0, 1,
		null, 'https://www.youtube.com/watch?v=p7OUQ9U2qIw', 2)
go
insert into [movie_genre]
values	(26, 16),
		(26, 9)
go
insert into [movie_country]
values	(26, 12)
go

-- movie 27
insert into [movie]([title], [status], [quality], [duration_min], [poster], [cover], [description], 
					[release_date], [productions], [casts], [budget], [vip],
					[imdb_rate], [trailer], [type])
values	('Secret Invasion', 3, 1, 'N/A min', 'https://iili.io/mBobLP.jpg', 'https://iili.io/mBopB1.jpg', 'Nick Fury and Talos discover a faction of shapeshifting Skrulls who have been infiltrating Earth for years.',
		'0000-00-00', 'Marvel Studios, Kevin Feige Productions', 'Ben Mendelsohn, Samuel L. Jackson, Kingsley Ben-Adir, Olivia Colman, Emilia Clarke', 0, 0,
		null, 'https://www.youtube.com/watch?v=qZVTkn2NjS0', 1)
go
insert into [movie_genre]
values	(27, 9),
		(27, 2),
		(27, 20)
go
insert into [movie_country]
values	(27, 36)
go
*/

use master
go

