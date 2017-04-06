package com.qi.common.util;

import com.qi.common.constants.i18n.I18NConstants;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class Abc
 *
 * @author 张麒 2016/5/31.
 * @version Description:
 */
public class Abc {

    public Abc() {
        System.out.println(this.getClass());
    }

    @Test
    public void aaa() {
        for (I18NConstants.Tips tips : I18NConstants.Tips.values()) {
            System.out.println(StringUtil.convertNumber(tips.getValue()));
        }
    }


    @Test
    public void test() {
        Country india = new Country("India", 1000);
        Country japan = new Country("Japan", 10000);

        Country france = new Country("France", 2000);
        Country russia = new Country("Russia", 20000);

        HashMap<Country, String> countryCapitalMap = new HashMap<>();
        countryCapitalMap.put(india, "Delhi");
        countryCapitalMap.put(japan, "Tokyo");
        countryCapitalMap.put(france, "Paris");
        countryCapitalMap.put(russia, "Moscow");

        Iterator<Country> countryCapitalIter = countryCapitalMap.keySet().iterator();//put debug point at this line
        while (countryCapitalIter.hasNext()) {
            Country countryObj = countryCapitalIter.next();
            String capital = countryCapitalMap.get(countryObj);
            System.out.println(countryObj.getName() + "----" + capital);
        }
    }
}
