package com.dh.shortener.services;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

import com.dh.shortener.model.ShortenUrl;

public interface ShortenUrlService {
	
	public void addShortenUrl(ShortenUrl shortenUrl);
	public boolean deleteShortenUrl(long shortenUrlId);
	public boolean updateShortenUrl(ShortenUrl shortenUrl);
	public ShortenUrl getShortenUrl(long shortenUrlId);
	public Set<ShortenUrl> getAll();
	public String findLastShorten() throws DataAccessException;
	public String findUrlByShorten(String shorten) throws DataAccessException;
	public String generateUrl();
	public int getCountByShorten( String shorten);
	public ShortenUrl findByShorten(String shorten);
	public void incrementCount(String shorten);

}
