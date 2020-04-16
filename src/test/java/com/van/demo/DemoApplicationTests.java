package com.van.demo;

import com.sun.media.jfxmedia.logging.Logger;
import com.van.demo.dto.Row;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import strman.Strman;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Test
    public void contextLoads() {


        //   | van   add | zhh  delete | zzz  refresh
        List<Row> rowList = new ArrayList<>();
        rowList.add(new Row("van","add"));
        rowList.add(new Row("zhh","delete"));
        rowList.add(new Row("zzz","refresh"));

        boolean contains = rowList.contains(new Row("zhh","delete"));


        int i = 0;
        for (Row row : rowList) {
            if ("zhh".equals(row.getText())) {
                break;
            }
            i++;
        }


        System.out.println(rowList.get(i));
        String toDo = rowList.get(i + 1).getOperate();
        System.out.println(toDo);



    }

}
