package xyz.likailing.cloud.common.base.util;



import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;

/**
 * @author summer
 * @date 2020/07/30
 */
public class SummaryUtils {

    private static float min_diff = 0.001f; //差值最小
    private static int max_iter = 200;//最大迭代次数
    private static int k = 2;  //窗口大小/2
    private static float d = 0.85f;

    public static List<String> textRank(String field, int keywordNum) {
        //分词
        List<WOD<String>> wods = ToAnalysisParse(field);
        // StopWord.filter(wods);//过滤掉不需要的分词，可省略

        Map<String, Set<String>> relationWords = new HashMap<>();
        //获取每个关键词 前后k个的组合
        for (int i = 0; i < wods.size(); i++) {
            String keyword = wods.get(i).getName();
            Set<String> keySets = relationWords.get(keyword);
            if (keySets == null) {
                keySets = new HashSet<>();
                relationWords.put(keyword, keySets);
            }

            for (int j = i - k; j <= i + k; j++) {
                if (j < 0 || j >= wods.size() || j == i) {
                    continue;
                } else {
                    keySets.add(wods.get(j).getName());
                }
            }
        }

        Map<String, Float> score = new HashMap<>();
        //迭代
        for (int i = 0; i < max_iter; i++) {
            Map<String, Float> m = new HashMap<>();
            float max_diff = 0;
            for (String key : relationWords.keySet()) {
                Set<String> value = relationWords.get(key);
                //先给每个关键词一个默认rank值
                m.put(key, 1 - d);
                //一个关键词的TextRank由其它成员投票出来
                for (String other : value) {
                    int size = relationWords.get(other).size();
                    if (key.equals(other) || size == 0) {
                        continue;
                    } else {
                        m.put(key, m.get(key) + d / size * (score.get(other) == null ? 0 : score.get(other)));
                    }
                }
                max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
            }
            score = m;
            if (max_diff <= min_diff) {
//                System.out.println("迭代次数：" + i);
                break;
            }
        }
        List<WOD<String>> scores = new ArrayList<>();
        for (String s : score.keySet()) {
            WOD<String> score1 = new WOD(s, score.get(s));
            scores.add(score1);
        }

        scores.sort(new Comparator<WOD<String>>() {
            @Override
            public int compare(WOD<String> o1, WOD<String> o2) {
                return o1.compareTo(o2);
            }
        });
        List<String> keywords = new ArrayList<>();
        int index = 0;
        for (WOD<String> score1 : scores) {
            keywords.add(score1.getName());
            index++;
            if (index==keywordNum)
                break;
        }
        return keywords;
    }

    public static List<WOD<String>> ToAnalysisParse(String str) {
        List<WOD<String>> wods = new ArrayList();
        Result result = ToAnalysis.parse(str);
        List<Term> terms = result.getTerms();
        Iterator var4 = terms.iterator();

        while(var4.hasNext()) {
            Term term = (Term)var4.next();
            wods.add(new WOD(term.getName(), term.getNatureStr()));
        }

        return wods;
    }
}