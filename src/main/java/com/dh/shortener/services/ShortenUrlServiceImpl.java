package com.dh.shortener.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dh.shortener.model.ShortenUrl;
import com.dh.shortener.repository.ShortenUrlRepository;
import com.dh.shortener.repository.UserRepository;

@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

	@Autowired
	private ShortenUrlRepository shortenUrlRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManager entityManager;

	private Character[] asciiArray = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'Q', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'q', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z' };
	private LinkedList<Character> asciiList = new LinkedList<Character>(
			Arrays.asList(asciiArray));

	@Override
	@Transactional
	public void addShortenUrl(ShortenUrl shortenUrl) {
		shortenUrlRepository.save(shortenUrl);
	}

	@Override
	@javax.transaction.Transactional
	public boolean deleteShortenUrl(long shortenUrlId) {
		shortenUrlRepository.delete(shortenUrlId);
		return false;
	}

	@Override
	@Transactional
	public boolean updateShortenUrl(ShortenUrl shortenUrl) {
		shortenUrlRepository.save(shortenUrl);
		return false;
	}

	@Override
	@Transactional
	public ShortenUrl getShortenUrl(long shortenUrlId) {
		return shortenUrlRepository.findOne(shortenUrlId);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ShortenUrl> getAll() {
		return new HashSet<ShortenUrl>(shortenUrlRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public String findLastShorten() throws DataAccessException {
		return shortenUrlRepository.findLastShorten();
	}

	@Override
	@Transactional(readOnly = true)
	public String findUrlByShorten(String shorten) throws DataAccessException {
		return shortenUrlRepository.findUrlByShorten(shorten);
	}

	@Override
	public String generateUrl() {
		try {
			String temp = shortenUrlRepository.findLastShorten();
			char[] charTemp = temp.toCharArray();
			for (int i = charTemp.length - 1; i == 0; i--) {
				if (charTemp[i] < 'z') {
					charTemp[i] = asciiList
							.get(asciiList.indexOf(charTemp[i]) + 1);
					return String.valueOf(charTemp);
				} else {
					return String.valueOf(temp + asciiList.get(0));
				}
			}

		} catch (NullPointerException ex) {
			return String.valueOf(asciiList.get(0));
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public int getCountByShorten(String shorten) {
		return shortenUrlRepository.getCountByShorten(shorten);
	}

	@Override
	@Transactional(readOnly = true)
	public ShortenUrl findByShorten(String shorten) {
		return shortenUrlRepository.findByShorten(shorten);
	}

	@Override
	@Transactional
	public void incrementCount(String shorten) {
		int counter = this.getCountByShorten(shorten);
		int newCounter = counter + 1;
		shortenUrlRepository.countIncrement(newCounter, shorten);

	}

}
