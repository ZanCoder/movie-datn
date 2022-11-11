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
	
	@Query(value="select * from movie where id=1 or id=8 or id=17 or id=18 or id=20", nativeQuery=true)
	List<Movie> getSpotlights();
	
	@Query(value="select * from movie where id=2 or id=3 or id=4 or id=5 or id=8 or id=9", nativeQuery=true)
	List<Movie> getTrends();
	
	@Query(value="select * from movie where id=1 or id=2 or id=3 or id=4 or id=5", nativeQuery=true)
	List<Movie> getAirings();
	
	@Query(value="select * from movie where id=6 or id=7 or id=8 or id=9 or id=10", nativeQuery=true)
	List<Movie> getPopulars();
	
	@Query(value="select * from movie where id=11 or id=12 or id=13 or id=14 or id=15", nativeQuery=true)
	List<Movie> getFavorites();
	
	@Query(value="select * from movie where id=16 or id=17 or id=18 or id=19 or id=20", nativeQuery=true)
	List<Movie> getCompletes();
	
	@Query(value="select top 12 * from [movie] order by id DESC", nativeQuery=true)
	List<Movie> getLatestEp();
	
	@Query(value="select top 12 * from [movie]", nativeQuery=true)
	List<Movie> getNewMovies();
	
	@Query(value="select top 10 * from [movie]", nativeQuery=true)
	List<Movie> getMostViews();
	
	@Query(value="select movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type from [movie] join movie_genre on movie.id = movie_genre.movie "
			+ "join movie_country on movie.id = movie_country.movie "
			+ "join genre on genre.id = movie_genre.genre "
			+ "join country on country.id = movie_country.country "
			+ "where movie.id <> ?1 "
			+ "and (genre.id in (select movie_genre.genre from [movie_genre] where movie_genre.movie = ?1) "
			+ "or country.id in (select movie_country.country from movie_country where movie_country.movie = ?1)) "
			+ "group by movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type ", nativeQuery=true)
	List<Object[]> getRecommends(long id);
	
	@Query(value="SELECT o FROM Movie o WHERE o.title like ?1")
	Page<Movie> findByTitleLike(String title, Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.title like ?1")
	List<Movie> findByTitleLike(String title);
	
	@Query(value="select movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.release_date, movie.status, movie.trailer, movie.add_date, movie.budget, movie.casts, movie.productions, movie.cover, movie.description, movie.imdb_rate, movie.quality, movie.rate, movie.rates_count, movie.views_count from movie movie "
			+ " join [movie_genre] on movie.id = movie_genre.movie "
			+ " join [genre] on movie_genre.genre = genre.id "
			+ " where genre.genre_slug = ?1 ", nativeQuery=true)
	Page<Object[]> getByGenre(String slug, Pageable pageable);
	
	@Query(value="select movie.id, movie.title, movie.poster, movie.slug, movie.vip, movie.duration_min, movie.type, movie.release_date, movie.status, movie.trailer, movie.add_date, movie.budget, movie.casts, movie.productions, movie.cover, movie.description, movie.imdb_rate, movie.quality, movie.rate, movie.rates_count, movie.views_count "
			+ " from [movie] movie "
			+ " join [movie_country] movie_country on movie.id = movie_country.movie "
			+ " join [country] country on movie_country.country = country.id "
			+ " where country.country_slug = ?1 ", nativeQuery=true)
	Page<Object[]> getByCountry(String slug, Pageable pageable);
	
	@Query(value="SELECT o FROM Movie o WHERE o.type.id = ?1")
	Page<Movie> findByType(Integer id, Pageable pageable);
}
