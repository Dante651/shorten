package com.dh.shortener.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dh.shortener.model.ShortenUrl;

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long>{	
	
	@Query("select shortenUrl.shorten from ShortenUrl shortenUrl where shortenUrl.id = (select max(shorten.id) from ShortenUrl shorten)")
	public String findLastShorten() throws DataAccessException;
	
	@Query("select shortenUrl.url from ShortenUrl shortenUrl where shortenUrl.shorten = :shortenUrl")
	public String findUrlByShorten(@Param("shortenUrl") String shorten) throws DataAccessException;
	
	public ShortenUrl findByUserId(long userId);
	
	public ShortenUrl findByShorten(String shorten);

	@Query("SELECT shortenUrl.count FROM ShortenUrl shortenUrl WHERE shortenUrl.shorten = :shorten")
	public int getCountByShorten(@Param("shorten") String shorten);
	
	@Modifying
	@Query("UPDATE ShortenUrl shortenUrl SET shortenUrl.count = :count WHERE shortenUrl.shorten = :shorten")
	public void countIncrement(@Param("count") int count, @Param("shorten") String shorten);
	
}
