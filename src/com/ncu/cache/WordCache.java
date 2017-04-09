package com.ncu.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.service.impl.WordSVImpl;
import com.ncu.table.ivalue.IWordValue;

@Service
@Scope("singleton")
@DependsOn("BeanUtil")
public class WordCache {
	@Autowired
	private WordSVImpl  sv;
	@Cacheable(value="wordCache", key="#word")
	public long queryWordIdByWrod(String word) throws  Exception{
		return sv.getWordIdByWord(word);
	}
	@CacheEvict(value = "cacheTest", allEntries = true)
	public void save(IWordValue bean) throws Exception{
		sv.save(bean);
	}
}
