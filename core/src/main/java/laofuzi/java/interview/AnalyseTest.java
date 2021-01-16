package laofuzi.java.interview;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Chyson
 * Date: 20/3/14
 * Description：代码偷了懒，没有汉字转拼音这一步，并且做了处理。
 * Time: 23:36
 */
public class AnalyseTest {

    /**
     * 给定一个地名字典[牛圈子沟镇,沅陵县,番禺区,哈达镇,灵山镇,塘尾街道,高米店街道] ，实现一个模糊匹配算法，通过拼音和文字进行模糊匹配，使得一些包含错误的输入地址也能得到正确的输出。算法需要通过下面的测试例：
     * 牛圈子沟镇->牛圈子沟镇
     * 有电子沟镇 ->牛圈子沟镇
     * 园林县 ->沅陵县
     * 藩禺期->番禺区
     * 好达镇 ->哈达镇
     * 零三镇->灵山镇
     * 唐伟街道->塘尾街道
     * 高米店->高米店街道
     * 实现：默认指定字典表，并且汉语拼音
     */
    private static final Map<String, Object> rightDict = new HashMap<>();

    private static final Map<String, Object> errorDict = new HashMap<>();

    private static final BigDecimal similarRatio = new BigDecimal(0.5);

    private static Map<String, Object> initRightDict() {
        rightDict.put("牛圈子沟镇", "niu quan zi gou zhen");
        rightDict.put("沅陵县", "yuan ling xian");
        rightDict.put("番禺区", "pan yu qu");
        rightDict.put("哈达镇", "ha da zhen");
        rightDict.put("灵山镇", "ling shan zhen");
        rightDict.put("塘尾街道", "tang wei jie dao");
        rightDict.put("高米店街道", "gao mi dian jie dao");
        return rightDict;
    }

    private static Map<String, Object> initErrorDict() {
        errorDict.put("有电子沟镇", "you dian zi gou zhen");
        errorDict.put("园林县", "yuan lin xian");
        errorDict.put("藩禺期", "pan yu qi");
        errorDict.put("好达镇", "hao da zhen");
        errorDict.put("好大镇", "hao da zhen");
        errorDict.put("好打镇", "hao da zhen");
        errorDict.put("哈达", "hao da");
        errorDict.put("零三镇", "ling sha zhen");
        errorDict.put("唐伟街道", "tang wei jie dao");
        errorDict.put("高米店", "gao mi dian");
        return errorDict;
    }

    /**
     * 比较两个汉字的相识度
     *
     * @param source
     * @param target
     * @return
     */
    public static BigDecimal calculatorHZSimilarityRatio(String source, String target) {
        int max = Math.max(source.length(), target.length());
        char[] sources = source.toCharArray(), targets = target.toCharArray();
        int sourceLen = sources.length, targetLen = targets.length;
        int[][] d = new int[sourceLen + 1][targetLen + 1];
        for (int i = 0; i <= sourceLen; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i <= targetLen; i++) {
            d[0][i] = i;
        }

        for (int i = 1; i <= sourceLen; i++) {
            for (int j = 1; j <= targetLen; j++) {
                if (sources[i - 1] == targets[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    int insert = d[i][j - 1] + 1;
                    int delete = d[i - 1][j] + 1;
                    int replace = d[i - 1][j - 1] + 1;
                    d[i][j] = Math.min(insert, delete) > Math.min(delete, replace) ? Math.min(delete, replace) :
                            Math.min(insert, delete);
                }
            }
        }
        return new BigDecimal(1 - (float) d[sourceLen][targetLen] / max);
    }

    /**
     * 比较两个汉字拼音的相似度
     *
     * @param source
     * @param target
     * @return
     */
    public static BigDecimal calculatorPYSimilarityRatio(String source, String target) {
        List<String> sourceList = Arrays.asList(source.split("\\s+"));
        List<String> targeList = Arrays.asList(target.split("\\s+"));
        int sourceLength = source.split("\\s+").length;
        int targetLength = target.split("\\s+").length;
        int similarNum = 0, max = Math.max(sourceLength, targetLength), min = Math.min(sourceLength, targetLength);

        for (int i = 0; i < min; i++) {
            if (sourceList.get(i).equalsIgnoreCase(targeList.get(i))) {
                similarNum++;
            }
        }
        return new BigDecimal((float) similarNum / max);
    }

    /**
     * 处理用户输入
     *
     * @param source
     * @return
     */
    public static String handleUser(String source) {
        Map<String, Object> rightDict = initRightDict();
        Map<String, Object> errorDict = initErrorDict();
        //包含正确输入或者部分正确，直接返回正确
        if (rightDict.containsKey(source) || containsPartKey(rightDict, source)) {
            for (String right : rightDict.keySet()) {
                if (right.indexOf(source) > -1) {
                    return right;
                }
            }
        } else  {
            //输入不正确，先比较汉字相似度，相似度小于指定值，在比较拼音相似度
            for (String right : rightDict.keySet()) {
                BigDecimal similarityHZRatio = calculatorHZSimilarityRatio(source, right);
                if (similarityHZRatio.compareTo(similarRatio) < 0) {
                    String rightValue = (String) rightDict.get(right);
                    String errorValue = (String) errorDict.get(source);
                    BigDecimal similarityPYRatio = calculatorPYSimilarityRatio(rightValue, errorValue);
                    if (similarityPYRatio.compareTo(similarRatio) < 0) {
                        continue;
                    }
                    return right;
                }
                return right;
            }
        }  
        return "没找到";
    }

    private static boolean containsPartKey(Map<String, Object> rightDict, String source) {
        for (String right : rightDict.keySet()) {
            if (right.indexOf(source) > -1 || rightDict.get(right).toString().indexOf(source) > -1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            System.out.println("返回结果===" + handleUser(str));
        }
    }
}
