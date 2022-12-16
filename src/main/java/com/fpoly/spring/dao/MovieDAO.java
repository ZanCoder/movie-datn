package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Movie;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Integer>{
	@Query(value="SELECT o FROM Movie o WHERE o.slug like ?1")
	Movie findBySlugLike(String slug);
	
	@Query(value="select * from movie where id=22 or id=30 or id=17 or id=18 or id=20", nativeQuery=true)
	List<Movie> getSpotlights();
	
	@Query(value="select top 10 * from [movie]"
			+ " order by "
			+ " (select count(*) from [movie_view] "
			+ " where movie_view.movie = movie.id and movie_view.view_date > = DATEDIFF(hour, -72, GETDATE())) desc", nativeQuery=true)
	List<Movie> getTrends();
	
	@Query(value="select top 5 * from [movie] order by dbo.get_movie_viewed(movie.id) desc", nativeQuery=true)
	List<Movie> getTop5Populars();
	
	@Query(value="select top 10 * from [movie] order by dbo.get_movie_viewed(movie.id) desc", nativeQuery=true)
	List<Movie> getTop10Populars();
	
	@Query(value="select * from [movie] order by dbo.get_movie_viewed(movie.id) desc", nativeQuery=true)
	Page<Movie> getPopulars(Pageable pageable);
	
	@Query(value="select top 5 * from [movie] order by dbo.get_movie_rated(movie.id) desc", nativeQuery=true)
	List<Movie> getTop5Favorites();
	
	@Query(value="select * from [movie] order by dbo.get_movie_rated(movie.id) desc", nativeQuery=true)
	Page<Movie> getFavorites(Pageable pageable);
	
	@Query(value="select top 5 * from [movie] where movie.status = 2 order by movie.add_date desc", nativeQuery=true)
	List<Movie> getTop5Completes();
	
	@Query(value="select * from [movie] where movie.status = 2 order by movie.add_date desc", nativeQuery=true)
	Page<Movie> getCompletes(Pageable pageable);
	
	@Query(value="select top 12 * from [movie] where movie.type = 2 and movie.status <> 3 order by add_date DESC", nativeQuery=true)
	List<Movie> getTop12LatestEps();
	
	@Query(value="select top 12 * from [movie] where movie.type = 1 and movie.status <> 3 order by add_date desc", nativeQuery=true)
	List<Movie> getTop12NewMovies();
	
	@Query(value="select top 12 * from [movie] where movie.status = 3 order by add_date desc", nativeQuery=true)
	List<Movie> getTop12Upcomings();
	
	@Query(value="select * from [movie] where movie.status = 3 order by add_date desc", nativeQuery=true)
	Page<Movie> getUpcomings(Pageable pageable);
	
	@Query(value="select top 5 * from [movie] order by movie.imdb_rate desc", nativeQuery=true)
	List<Movie> getTop5TopIMDB();
	
	@Query(value="select * from [movie] order by movie.imdb_rate desc", nativeQuery=true)
	Page<Movie> getTopIMDB(Pageable pageable);
	
	@Query(value="select top 10 * from [movie] "
			+ " order by "
			+ " (select count(*) from [movie_view] "
			+ " where movie_view.movie = movie.id and cast(movie_view.view_date as date) = cast(getdate() as date)) desc", nativeQuery=true)
	List<Movie> getTop10MostViewsByToday();
	
	@Query(value="select count(*) from [movie_view] "
			+ " where movie_view.movie = ?1 and cast(movie_view.view_date as date) = cast(getdate() as date)", nativeQuery=true)
	int getMovieMostViewByTodayCount(int movie);
	
	@Query(value="select top 10 * from [movie] "
			+ " order by "
			+ " (select count(*) from [movie_view] "
			+ " where movie_view.movie = movie.id and DATEDIFF(week, getdate(), movie_view.view_date) = 0) desc", nativeQuery=true)
	List<Movie> getTop10MostViewsByWeek();
	
	@Query(value="select count(*) from [movie_view] "
			+ " where movie_view.movie = ?1 and DATEDIFF(week, getdate(), movie_view.view_date) = 0", nativeQuery=true)
	int getMovieMostViewByWeekCount(int movie);
	
	@Query(value="select top 10 * from [movie] "
			+ " order by "
			+ " (select count(*) from [movie_view] "
			+ " where movie_view.movie = movie.id and DATEDIFF(month, getdate(), movie_view.view_date) = 0) desc", nativeQuery=true)
	List<Movie> getTop10MostViewsByMonth();
	
	@Query(value="select count(*) from [movie_view] "
			+ " where movie_view.movie = ?1 and DATEDIFF(month, getdate(), movie_view.view_date) = 0", nativeQuery=true)
	int getMovieMostViewByMonthCount(int movie);
	
	@Query(value="select * from [movie] "
			+ " order by "
			+ " (select count(*) from [movie_view] "
			+ " where movie_view.movie = movie.id) desc", nativeQuery=true)
	List<Movie> getTopMostViewsAllTime();
	
	@Query(value="select count(*) from [movie_view] "
			+ " where movie_view.movie = ?1", nativeQuery=true)
	int getMovieMostViewCount(int movie);
	
	@Query(value="select top 16 movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.quality from [movie] join movie_genre on movie.id = movie_genre.movie "
			+ "join movie_country on movie.id = movie_country.movie "
			+ "join genre on genre.id = movie_genre.genre "
			+ "join country on country.id = movie_country.country "
			+ "where movie.id <> ?1 "
			+ "group by movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.quality "
			+ "ORDER BY NEWID()", nativeQuery=true)
	List<Object[]> getRecommends(long id);
	
	@Query(value="SELECT o FROM Movie o WHERE o.title like ?1")
	Page<Movie> findByTitleLike(String title, Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.title like ?1")
	List<Movie> findByTitleLike(String title);
	
	@Query(value="select movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.quality"
			+ " from [movie] movie "
			+ " join [movie_genre] on movie.id = movie_genre.movie "
			+ " join [genre] on movie_genre.genre = genre.id "
			+ " where genre.genre_slug = ?1 ", nativeQuery=true)
	Page<Object[]> getByGenre(String slug, Pageable pageable);
	
	@Query(value="select movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.quality"
			+ " from [movie] movie "
			+ " join [movie_country] movie_country on movie.id = movie_country.movie "
			+ " join [country] country on movie_country.country = country.id "
			+ " where country.country_slug = ?1 ", nativeQuery=true)
	Page<Object[]> getByCountry(String slug, Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.type.id = ?1 ORDER BY o.add_date DESC")
	Page<Movie> findByTypeOrderByAddDateDesc(Integer id, Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.vip = ?1 ORDER BY o.add_date DESC")
	Page<Movie> findByVipOrderByAddDateDesc(boolean vip, Pageable pageable);
	
	@Query(value="select count(*) from movie", nativeQuery=true)
	Integer getCountAll();
	
	@Query(value="SELECT o FROM Movie o ORDER BY o.add_date DESC")
	Page<Movie> findAllOrderByAddDateDesc(Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.title like ?1 ORDER BY o.add_date DESC")
	Page<Movie> findAllLikeTitleOrderByAddDateDesc(String title, Pageable pageable);
}
