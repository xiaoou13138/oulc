package com.ncu.crawler;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

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
	/*public Map<String, String> segMore(String text) {
	    Map<String, String> map = new HashMap();

	    map.put("复杂模式", segText(text, JcsegTaskConfig.COMPLEX_MODE));
	    map.put("简易模式", segText(text, JcsegTaskConfig.SIMPLE_MODE));

	    return map;
	}*/
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
}
