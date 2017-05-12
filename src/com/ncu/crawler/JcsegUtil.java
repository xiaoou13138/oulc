package com.ncu.crawler;

import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ncu.crawler.dealInfo.DealData;
import org.apache.commons.lang.StringUtils;
import org.lionsoul.jcseg.core.ADictionary;
import org.lionsoul.jcseg.core.DictionaryFactory;
import org.lionsoul.jcseg.core.ISegment;
import org.lionsoul.jcseg.core.IWord;
import org.lionsoul.jcseg.core.JcsegTaskConfig;
import org.lionsoul.jcseg.core.SegmentFactory;

public class JcsegUtil {
	private static final JcsegTaskConfig CONFIG = new JcsegTaskConfig();
	private static final ADictionary DIC = DictionaryFactory.createDefaultDictionary(CONFIG);
	static {
	    CONFIG.setLoadCJKSyn(false);
	    CONFIG.setLoadCJKPinyin(false);
	}
	public String segText(String text, int segMode) {
	    StringBuilder result = new StringBuilder();        
	    try {
	        ISegment seg = SegmentFactory.createJcseg(segMode, new Object[]{new StringReader(text), CONFIG, DIC});
	        IWord word = null;
	        seg.reset(new StringReader(text));
	        while((word=seg.next())!=null) {         
	            result.append(word.getValue()).append(",");
	        }
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	    return result.toString();
	}


	public static Set<String> devideWord(String content){
		Set<String> set = new HashSet<String>();
		JcsegUtil util = new JcsegUtil();
		String result = util.segText(content, JcsegTaskConfig.COMPLEX_MODE);
		String resultS[] = result.split(",");
		for(String str:resultS){
			if(StringUtils.isNotBlank(str) && DealData.isChinese(str))
				set.add(str);
		}
		return set;
	}
	/*public Map<String, String> segMore(String text) {
	    Map<String, String> map = new HashMap();

	    map.put("复杂模式", segText(text, JcsegTaskConfig.COMPLEX_MODE));
	    map.put("简易模式", segText(text, JcsegTaskConfig.SIMPLE_MODE));

	    return map;
	}*/
}
