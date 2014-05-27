package com.xxx.crawl.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class GoodsFactory {
private static GoodPrint[] data={new GoodPrint("GOO1",70,100,new BigDecimal(10),"M"),
new GoodPrint("GOO2",50,200,new BigDecimal(20),"PCS")}; 
public static Object[] getBeanArray() {
     return data;
}
public static Collection<?> getBeanCollection() {
        return Arrays.asList(data);
    }

}