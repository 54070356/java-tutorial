package laofuzi.java.core.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

//http://tutorials.jenkov.com/java-regex/matcher.html
public class RegexTutorial {
	@Test
	public void matches_1() {
		Assert.assertTrue(Pattern.matches(".*电视.*", "我要看电视去了"));
	}

	@Test
	public void matches_2() {
		Assert.assertTrue(Pattern.matches("(创维)", "创维"));
	}

	@Test
	public void matches_3() {
		Assert.assertTrue(Pattern.matches(".*电视.*", "我要看电视去了"));
	}

	@Test
	public void find1() {
		String regex = "(?<date>((今天|明天|后天|)|(([0-9零一两二三四五六七八九十]{1,2}月[0-9零一两二三四五六七八九十]{1,3})(号|日)?))).?(?<period>(早上|上午|中午|下午|晚上)?)?(?<action1>(联系))?(?<action2>(上门))";

		Pattern pattern = Pattern.compile(regex);
		String txt = "能明天的下午上门吗？";
		find(pattern, txt);

		txt = "明天或者3月2日上门吗？";
		find(pattern, txt);

		txt = "3月2号联系我上门吗？";
		find(pattern, txt);
	}

	@Test
	public void find2() {
		String regex = "【.+\\@.+】";

		Pattern pattern = Pattern.compile(regex);
//		String txt = "想看小康我要看【电视剧@works-type】";
//		find(pattern, txt); 

		String txt = "想看小康我要看【电视剧@works】";
		find(pattern, txt);
	}

	@Test
	public void pattern1() {
		boolean b = Pattern.matches(".*(换一个|换一下).*", "嗯，我想换一个");
		Assert.assertTrue(b);
	}

	private void find(Pattern pattern, String txt) {
		System.out.println("---------");
		System.out.println(txt);
		Matcher matcher = pattern.matcher(txt);
//		System.out.println(matcher.matches());
//		for (int i = 0; i < matcher.groupCount() + 1; i++) {
//			System.out.println(matcher.group(i));
//		}
		while (matcher.find()) {
			System.out.println("found: " + matcher.group(1));
			// System.out.println("found: " + matcher.group(2));
			// System.out.println("found: " + matcher.group(3));
			// System.out.println("found: " + matcher.group(10));
		}
	}

	@Test
	public void find3() {
		String regex = "【.+\\@.+】";
		String txt = "想看小康我要看【电视剧@works-type】";
		System.out.println(txt);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt);

		while (matcher.find()) {
			System.out.println("found: " + txt.substring(matcher.start(), matcher.end()));
		}
	}

	/**
	 * 电视型号
	 */
	@Test
	public void patternTvModel() {
		// final Pattern pattern = Pattern.compile("([1-9][0-9]){2}[a-zA-Z]{1,}");
		final Pattern pattern = Pattern.compile("([2-9][0-9]){1}[a-zA-Z]{1}[a-zA-Z0-9]{1}");
		String[][] examples = new String[][] { { "33R1", "33" }, { "R30", null }, { "R30", null }, { "45E300", "45" },
				{ "45G300F", "45" }, { "我的是45G300FVb", "45" }, { "我的是45g300FVb", "45" }, { "我的是45G300F40从", "45" },
				{ "我的是45G300F34X", "45" }, { "我的是0F34X", null }, { "我的是10F34X", null }, { "55K5D", "55" },

		};
		for (int i = 0; i < examples.length; i++) {
			final Matcher matcher = pattern.matcher(examples[i][0]);
			if (matcher.find()) {
				final String aString = matcher.group(1);
				System.out.println(aString);
				Assert.assertEquals(examples[i][1], aString);
			} else {
				Assert.assertNull(examples[i][1]);
			}
		}

	}

	@Test
	public void matchNum() {
		String numPattern = ".*[0-9一两俩二三仨四五六七八九零].*";
		// numPattern = ".*(1).*";
		Object[][] cases = new Object[][] { { "", false }, { "1", true }, { "我1", true }, { "我1a", true },
				{ "我11a", true }, { "我1a1a", true }, { "我二", true }, { "我零", true }, { "幺", false }, };
		for (Object[] c : cases) {
			Assert.assertEquals((Boolean) c[1], Pattern.matches(numPattern, (String) c[0]));
		}

	}

	@Test
	public void multiGroups() {
		final Pattern pattern = Pattern.compile("从(.*)到(.*)");
		String text = "从公司到地铁";
		final Matcher matcher = pattern.matcher(text);
		System.out.println(matcher.groupCount());

		while (matcher.find()) {
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(1), matcher.end(1), matcher.group(1)));
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(2), matcher.end(2), matcher.group(2)));

		}
	}

	@Test
	public void multiGroups1() {
		final Pattern pattern = Pattern.compile("从公司到地铁");
		String text = "从公司到地铁";
		final Matcher matcher = pattern.matcher(text);
		System.out.println(matcher.groupCount());

		while (matcher.find()) {
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(0), matcher.end(0), matcher.group(0)));
		}
	}

	@Test
	public void test2() {
		final Pattern pattern = Pattern.compile("我有(.*)个(苹果|橘子|水果|栗子)");
		String text = "我有十个苹果";
		final Matcher matcher = pattern.matcher(text);
		System.out.println(matcher.groupCount());
		while (matcher.find()) {
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(1), matcher.end(1), matcher.group(1)));
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(2), matcher.end(2), matcher.group(2)));

		}
	}

	@Test
	public void test_version() {
		String patternString = "(([0-9]){1,3}.){2}([0-9]{1,3})";
		Assert.assertTrue(Pattern.matches(patternString, "0.0.0"));
		Assert.assertTrue(Pattern.matches(patternString, "0.0.1"));
		Assert.assertTrue(Pattern.matches(patternString, "0.01.01"));
		Assert.assertTrue(Pattern.matches(patternString, "10.01.01"));
		Assert.assertTrue(Pattern.matches(patternString, "01.010.1"));
		Assert.assertTrue(Pattern.matches(patternString, "1.01.1"));
		Assert.assertFalse(Pattern.matches(patternString, "1010.01.01"));
		Assert.assertFalse(Pattern.matches(patternString, "01.01"));
		Assert.assertFalse(Pattern.matches(patternString, "01.01"));
	}

	@Test
	public void test4() {
		final Pattern pattern = Pattern
				.compile(".*to_time.*end=(\\d{4}-\\d{2}-\\d{2}).*from_time.*begin=(\\d{4}-\\d{2}-\\d{2}).*");
		String text = "{to_time={items=[{month=6, confident=1.0, ISO_DATE={end=2020-06-30, begin=2020-06-01}}]}, from_time={items=[{month=5, confident=1.0, ISO_DATE={end=2020-05-31, begin=2020-05-01}}]}}";
		final Matcher matcher = pattern.matcher(text);
		System.out.println(matcher.groupCount());
		while (matcher.find()) {
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(1), matcher.end(1), matcher.group(1)));
			System.out.println(
					String.format("start:%s, end:%s, result=%s", matcher.start(2), matcher.end(2), matcher.group(2)));

		}
	}
	
	
	@Test
	public void test5() {
		String reg = "\"statusCode\":(.*),\\s*\"statusMessage\":(.*),\\s*\"timeUsed\":(.*),\\s*\"result\":(.*)}";
		String text = "{\"statusCode\": 0, \"statusMessage\": \"success\", \"timeUsed\": 77.93, \"result\": {\"labels\": [{\"label\": \"政治\", \"score\": 0.9997910261154175}], \"allLabels\": [\"政治\", \"外交、国际关系\", \"经济\", \"商业、外贸、海关\", \"文化、休闲娱乐\", \"军事\", \"传媒\", \"教育\", \"能源、水务、水利\", \"人物\", \"体育\", \"医药、卫生\", \"社会、劳动\", \"农业、农村\", \"法律、司法\", \"环境、气象\", \"科学技术\", \"灾难事故\", \"交通运输、邮政、物流\", \"财政、金融\", \"文学、艺术\", \"服务业、旅游业\", \"基本建设、建筑业、房地产\", \"电子信息产业\", \"矿业、工业\"]}}";
		//text = "{\"statusCode\": 0,\"statusMessage\": \"success\",\"timeUsed\": 485,\"result\":[]}";
		//text = "{\"statusCode\": 0,\"statusMessage\": \"success\",\"timeUsed\": 485,\"result\":[{\"ne\":\"中国\",\"offset\":4,\"length\":2,\"type\":\"LOC\",\"subType\":\"COUNTRY\",\"basicNEs\":null},{\"ne\":\"国药集团\",\"offset\":0,\"length\":4,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"国药集团中国生物北京公司\",\"offset\":39,\"length\":12,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"世界卫生组织\",\"offset\":153,\"length\":6,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"国家药监局\",\"offset\":166,\"length\":5,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"国药集团中国生物北京公司\",\"offset\":210,\"length\":12,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"国家药监局\",\"offset\":226,\"length\":5,\"type\":\"ORG\",\"subType\":\"ORG\",\"basicNEs\":null},{\"ne\":\"目前\",\"offset\":207,\"length\":2,\"type\":\"TIM\",\"subType\":\"TIM\",\"basicNEs\":null}]}";
		final Pattern pattern = Pattern.compile(reg);
		final Matcher matcher = pattern.matcher(text);
		System.out.println(matcher.find());
		System.out.println(matcher.group(4));
	}

}
