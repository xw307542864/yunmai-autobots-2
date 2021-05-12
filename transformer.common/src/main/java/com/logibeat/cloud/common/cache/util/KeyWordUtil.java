package com.logibeat.cloud.common.cache.util;

import java.util.*;

public class KeyWordUtil {

    private Map<String, Object> dictionaryMap;

    public KeyWordUtil(Set<String> wordSet) {
        this.dictionaryMap = handleToMap(wordSet);
    }

    public Map<String, Object> getDictionaryMap() {
        return dictionaryMap;
    }

    public void setDictionaryMap(Map<String, Object> dictionaryMap) {
        this.dictionaryMap = dictionaryMap;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> handleToMap(Set<String> wordSet) {
        if (wordSet == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(wordSet.size());
        Map<String, Object> curMap = null;
        Iterator<String> ite = wordSet.iterator();
        while (ite.hasNext()) {
            String word = ite.next();
            curMap = map;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                String key = String.valueOf(word.charAt(i));
                Map<String, Object> wordMap = (Map<String, Object>) curMap
                        .get(key);
                if (wordMap == null) {
                    wordMap = new HashMap<String, Object>();
                    wordMap.put("isEnd", "0");
                    curMap.put(key, wordMap);
                    curMap = wordMap;
                }
                else {
                    curMap = wordMap;
                }
                if (i == len - 1) {
                    curMap.put("isEnd", "1");
                }
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public int checkWord(String text, int beginIndex) {
        if (dictionaryMap == null) {
            throw new RuntimeException("字典不能为空！");
        }
        boolean isEnd = false;
        int wordLength = 0;
        Map<String, Object> curMap = dictionaryMap;
        int len = text.length();
        for (int i = beginIndex; i < len; i++) {
            String key = String.valueOf(text.charAt(i));
            curMap = (Map<String, Object>) curMap.get(key);
            if (curMap == null) {
                break;
            }
            else {
                wordLength++;
                if ("1".equals(curMap.get("isEnd"))) {
                    isEnd = true;
                }
            }
        }
        if (!isEnd) {
            wordLength = 0;
        }
        return wordLength;
    }

    public Set<String> getWords(String text) {
        Set<String> wordSet = new HashSet<String>();
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int wordLength = checkWord(text, i);
            if (wordLength > 0) {
                String word = text.substring(i, i + wordLength);
                wordSet.add(word);
                i = i + wordLength - 1;
            }
        }
        return wordSet;
    }





    public static Set<String> getKeySet(){
        Set<String> wordSet = new HashSet<>();
        wordSet.add("股份");
        wordSet.add("有限");
        wordSet.add("公司");
        wordSet.add("企业");
        wordSet.add("集团");
        wordSet.add("有限责任");
        wordSet.add("股份有限");
        wordSet.add("独资企业");
        wordSet.add("股份合作");
        wordSet.add("外商投资");
        wordSet.add("联营企业");
        return  wordSet;

    }






    public static void main(String[] args) {

        KeyWordUtil keyWordUtil = new KeyWordUtil(KeyWordUtil.getKeySet());
        Set<String> result = keyWordUtil.getWords("运脉有限公司");
        if(result.size()>0){
            System.out.println(1111);
        }
    }


}
