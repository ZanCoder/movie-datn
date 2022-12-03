use master
go

IF DB_ID('db_movies') IS NOT NULL
   drop database db_movies

create database db_movies
go

use db_movies
go

-- Bảng vai trò (admin, mod, user....)
create table [account_role]( 
	[id] bigint primary key identity(1, 1), -- id vai trò
	[acc_role_name] varchar(50), -- tên vai trò
)
go

-- Bảng tài khoản
create table [account](
	[id] bigint primary key identity(1, 1), -- id tài khoản
	[username] nvarchar(64), -- tên tài khoản
	[email] nvarchar(120), -- email
	[avatar] varchar(200) default 'https://iili.io/yRmaGp.png', -- hình đại diện
	[password_hash] nvarchar(128), -- mật khẩu
	[budget] float default 0, -- số dư trong tài khoản
	[power] float default 0, -- số tiền đã tiêu
	[joined_date] DATETIME DEFAULT CURRENT_TIMESTAMP, -- ngày tham gia
	[role] bigint foreign key references [account_role]([id]) default 3, -- vai trò
	[status] bit default 1, -- trạng thái (online hay offline)
	[public_wl] bit default 1 -- công khai danh sách yêu thích (có hay ko)
)
go

-- Bảng lịch sử mua xu
create table [coin_transaction_history](
	[id] bigint primary key identity(1, 1), -- id
	[card] nvarchar(100),
	[coin_value] float, -- giá trị xu
	[account] bigint foreign key references [account]([id]), -- tài khoản mua xu
	[timestamp] datetime DEFAULT CURRENT_TIMESTAMP -- thời gian mua xu
)
go

-- Bảng thể loại
create table [genre](
	[id] bigint primary key identity(1, 1), -- id thể loại
	[genre_name] nvarchar(50), -- tên thể loại
	[genre_slug] nvarchar(100), -- slug
)
go

-- Bảng loại phim (phim lẻ, phim bộ...)
create table [type](
	[id] bigint primary key identity(1, 1), -- id loại
	[movie_type_name] nvarchar(50), -- tên loại
	[type_slug] nvarchar(100), -- slug
)
go

-- Bảng quốc gia
create table [country](
	[id] bigint primary key identity(1, 1), -- id quốc gia
	[country_name] nvarchar(50), -- tên quốc gia
	[country_slug] nvarchar(100), -- slug
)
go

-- Bảng server phim
create table [server](
	[id] bigint primary key identity(1, 1), -- id server
	[movie_server_name] nvarchar(50) -- tên server
)
go

-- Bảng độ phân giải phim
create table [quality](
	[id] bigint primary key identity(1, 1), -- id quantity
	[quality_name] nvarchar(50) -- name quantity
)
go

-- Bảng trạng thái phim
create table [status](
	[id] bigint primary key identity(1, 1), -- id status
	[status_name] nvarchar(50) -- name status
)
go

-- Bảng phim
create table [movie](
	[id] bigint primary key identity(1, 1), -- id phim
	[title] nvarchar(100), -- tên phim
	[slug] nvarchar(100), -- slug title
	[rate] float default 0, -- điểm đánh giá
	[quality] bigint foreign key references [quality]([id]), -- chất lượng phim
	[duration_min] varchar(10), -- thời lượng phim
	[poster] varchar(200), -- ảnh đại diện của phim
	[cover] varchar(200), -- ảnh bìa của phim
	[description] nvarchar(1024), -- mô tả phim
	[release_date] date, -- ngày phim ra mắt
	[add_date] DATETIME DEFAULT CURRENT_TIMESTAMP, -- ngày thêm phim vào website
	[productions] nvarchar(1024), -- nhà sản xuất
	[budget] float, -- phí xem phim
	[vip] bit default 0, -- phim vip hay phim free
	[imdb_rate] float, -- điểm IMDB
	[trailer] nvarchar(200), -- link trailer
	[type] bigint foreign key references [type]([id]), -- loại phim
	[status] bigint foreign key references [status]([id]) -- trạng thái phim
)
go

-- Bảng lịch sử mua phim
create table [movie_purchase_history](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- tài khoản mua xu
	[movie] bigint foreign key references [movie]([id]), -- phim đã mua
	[timestamp] datetime DEFAULT CURRENT_TIMESTAMP -- thời gian mua xu
)
go

-- Bảng những thể loại của 1 bộ phim
create table [movie_genre](
	[id] bigint primary key identity(1, 1), -- id
	[movie] bigint foreign key references [movie]([id]), -- id phim
	[genre] bigint foreign key references [genre]([id]) -- id thể loại
)
go

-- Bảng những quốc gia của 1 bộ phim
create table [movie_country](
	[id] bigint primary key identity(1, 1), -- id
	[movie] bigint foreign key references [movie]([id]), -- id phim
	[country] bigint foreign key references [country]([id]) -- id quốc gia
)
go

-- Bảng tập phim
create table [movie_episode](
	[id] bigint primary key identity(1, 1), -- id tập
	[movie] bigint foreign key references [movie]([id]), -- id phim
	[title] nvarchar(200), -- tiêu đề tập phim
	[ep] int, -- số tập
	[season] int, -- mùa 
	[server] bigint foreign key references [server]([id]), -- id server
	[api_key] nvarchar(200) -- api để lấy phim
)
go

-- Bảng đánh giá phim
create table [movie_rate](
	[id] bigint primary key identity(1, 1),
	[account] bigint foreign key references [account]([id]),
	[movie] bigint foreign key references [movie]([id]),
	[rate] float
)
go

-- Bảng thống kê view 
create table [movie_view](
	[id] bigint primary key identity(1, 1), -- id
	[view_date] datetime DEFAULT CURRENT_TIMESTAMP, -- ngày xem
	[movie] bigint foreign key references [movie]([id]) -- id phim
)
go

-- Bảng phim đã xem
create table [continue_watching](
	[id] bigint primary key identity(1, 1),  -- id
	[account] bigint foreign key references [account]([id]), -- tài khoản
	[movie_episode] bigint foreign key references [movie_episode]([id]), -- phim
	[view_date] datetime DEFAULT CURRENT_TIMESTAMP, -- ngày xem
)
go

-- Bảng phim yêu thích
create table [watch_list](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[movie] bigint foreign key references [movie]([id]), -- id phim
	[status] varchar(50), -- trạng thái (đang xem, đã xem, dự định xem...)
	[add_date] datetime DEFAULT CURRENT_TIMESTAMP, -- ngày thêm
)
go

-- Bảng bình luận phim
create table [comment_movie](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[movie_episode] bigint foreign key references [movie_episode]([id]), -- id tập phim
	[parent_cmt] bigint foreign key references [comment_movie]([id]), -- id bình luận
	[tag_name] nvarchar(64), -- tên người bình luận
	[text] nvarchar(500), -- nội dung bình luận
	[spoil] bit, -- lộ nội dung bình luận (có hoặc ko)
	[timestamp] datetime DEFAULT CURRENT_TIMESTAMP, -- thời gian bình luận
)
go

-- Bảng bình luận phim chi tiết
create table [comment_movie_detail](
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[comment_movie] bigint foreign key references [comment_movie]([id]), -- id phim
	[favorite] bit -- thích hoặc ko thích,

	primary key (account, comment_movie) -- id
)
go

-- Bảng thông báo phim đến người dùng
create table [notification_movie](
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[movie_episode] bigint foreign key references [movie_episode]([id]), -- id tập phim
	[description] nvarchar(200), -- nội dung thông báo
	[new_noti] bit, -- mới nới cũ
	[timestamp] datetime DEFAULT CURRENT_TIMESTAMP, -- thời gian thông báo

	primary key([account], [movie_episode]) -- id
)
go

-- Bảng tag diễn đàn
create table [community_tag](
	[id] bigint primary key identity(1, 1), -- id
	[tag_name] varchar(50) -- nội dung tag
)
go

-- Bảng diễn đàn
create table [community](
	[id] bigint primary key identity(1, 1), -- id
	[comm_tag] bigint foreign key references [community_tag]([id]), -- id tag
	[timestamp] datetime, -- thời gian đăng bài
	[title] nvarchar(1024), -- nội dung bài viết
	[like] int, -- tổng like của bài viết
	[dislike] int -- tổng dislike của bài viết
)
go

-- Bảng bình luận bài viết (diễn đàn)
create table [comment_community](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[community] bigint foreign key references [community]([id]), -- id diễn đàn
	[parent_cmt] bigint foreign key references [comment_community]([id]), -- id bình luận
	[tag_name] nvarchar(64), -- tên người bình luận
	[text] nvarchar(500), -- nội dung bình luận
	[timestamp] datetime, -- thời gian bình luận
	[like] int, -- tổng like
	[dislike] int -- tổng dislike
)
go

-- Bảng bình luận diễ đàn chi tiết
create table [comment_community_detail](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[comment_com] bigint foreign key references [comment_movie]([id]), -- id diễn đàn
	[favorite] bit -- thích hoặc ko thích
)
go

-- Bảng lịch sử xem phim
create table [history_wm](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[movie] bigint foreign key references [movie]([id]), -- id phim
	[movie_ep] bigint foreign key references [movie_episode]([id]), -- id tập phim
	[timestamp] datetime, -- thời gian đã xem
)
go

-- Bảng cài đặt
create table [setting](
	[id] bigint primary key identity(1, 1), -- id
	[account] bigint foreign key references [account]([id]), -- id tài khoản
	[auto_next] bit, -- tự động chuyển tập (có hay ko)
	[auto_play] bit, -- tự động phát (có hay ko)
	[notification] bit, -- bật thông báo (có hay ko)
	[public_wl] bit -- công khai danh sách yêu thích (có hay ko)
)
go

CREATE INDEX index_movie_view
ON [movie_view] (id, view_date, movie);
go

CREATE TRIGGER generate_slug_movie ON [movie]
FOR INSERT
AS 
BEGIN
	update [movie]
	set slug = LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TRIM(concat([movie].title, '-', [movie].id)), char(145), ''), char(146), ''), '"', ''), ':', ''), ')', ''), '(', ''), ',', ''), '\\', ''), '\/', ''), '\"', ''), '?', ''), '\', ''), '&', ''), '!', ''), '.', ''), ' ', '-'), '--', '-'), '--', '-'))
	from [movie] join inserted on [movie].id = inserted.id
END
go

CREATE TRIGGER generate_slug_country ON [country]
FOR INSERT
AS 
BEGIN
	update [country]
	set country_slug = LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TRIM([country].country_name), char(145), ''), char(146), ''), '"', ''), ':', ''), ')', ''), '(', ''), ',', ''), '\\', ''), '\/', ''), '\"', ''), '?', ''), '\', ''), '&', ''), '!', ''), '.', ''), ' ', '-'), '--', '-'), '--', '-'))
	from [country] join inserted on [country].id = inserted.id
END
go

CREATE TRIGGER generate_slug_genre ON [genre]
FOR INSERT
AS 
BEGIN
	update [genre]
	set genre_slug = LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TRIM([genre].genre_name), char(145), ''), char(146), ''), '"', ''), ':', ''), ')', ''), '(', ''), ',', ''), '\\', ''), '\/', ''), '\"', ''), '?', ''), '\', ''), '&', ''), '!', ''), '.', ''), ' ', '-'), '--', '-'), '--', '-'))
	from [genre] join inserted on [genre].id = inserted.id
END
go

CREATE TRIGGER generate_slug_type ON [type]
FOR INSERT
AS 
BEGIN
	update [type]
	set type_slug = LOWER(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(TRIM([type].movie_type_name), char(145), ''), char(146), ''), '"', ''), ':', ''), ')', ''), '(', ''), ',', ''), '\\', ''), '\/', ''), '\"', ''), '?', ''), '\', ''), '&', ''), '!', ''), '.', ''), ' ', '-'), '--', '-'), '--', '-'))
	from [type] join inserted on [type].id = inserted.id
END
go

CREATE FUNCTION get_movie_rated(@movieId bigint)
RETURNS float
AS
BEGIN
    DECLARE @total int
	DECLARE @res float

	IF (NOT EXISTS(select * from movie_rate where movie = @movieId))
		RETURN 0

	select @total = count(*) from movie_rate where movie = @movieId
	select @res = sum(movie_rate.rate) / @total from movie_rate where movie = @movieId

	RETURN @res
END
GO

CREATE FUNCTION get_total_movie_rated(@movieId bigint)
RETURNS float
AS
BEGIN
    DECLARE @total int

	IF (NOT EXISTS(select * from movie_rate where movie = @movieId))
		RETURN 0

	select @total = count(*) from movie_rate where movie = @movieId

	RETURN @total
END
GO

CREATE FUNCTION get_movie_viewed(@movieId bigint)
RETURNS float
AS
BEGIN
	DECLARE @res int

	IF (NOT EXISTS(select * from movie_view where movie = @movieId))
		RETURN 0

	select @res = count(*) from movie_view where movie = @movieId

	RETURN @res
END
GO

CREATE FUNCTION get_achievement(@accountId bigint)
RETURNS float
AS
BEGIN
	DECLARE @power float

	IF (NOT EXISTS(select * from account where id = @accountId))
		RETURN 0

	select @power = account.power from account where id = @accountId

	IF (@power < 20)
		return 'New'
	ELSE IF (@power >= 20 AND @power < 40)
		return 'Angelfish'
	ELSE IF (@power >= 40 AND @power < 60)
		return 'Crab'
	ELSE IF (@power >= 60 AND @power < 80)
		return 'Crab'

	return 'Dolphin'
END
GO

select movie.type, movie.title from [movie]
join movie_genre on movie.id = movie_genre.movie
join movie_country on movie.id = movie_country.movie
join genre on genre.id = movie_genre.genre
join country on country.id = movie_country.country
where 
movie.id <> 12 and
(genre.id in (select movie_genre.genre from [movie_genre] where movie_genre.movie = 12)
or country.id in (select movie_country.country from movie_country where movie_country.movie = 12))
group by movie.type, movie.title

select * from watch_list
where account = 1
order by dbo.get_movie_rated(watch_list.movie) desc

select * from movie_rate
