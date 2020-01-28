package com.richardallenonline.wonders.test.base;

import com.richardallenonline.wonders.base.Wonder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Laptop on 18/12/2015.
 */
public class WonderHelper {
    protected Wonder tst = null;

    protected void checkWonder(int point, int stagesbuilt, String msg) throws Exception {
        String txt = msg;
        try{
            txt = txt + " Points ";
            assertThat(tst.getPoints()).isEqualTo(point);
            System.out.println(txt + " - passed");

            txt = msg + " Stages ";
            assertThat(tst.getStagesBuilt()).isEqualTo(stagesbuilt);
            System.out.println(txt + " - passed");
        }catch(AssertionError e){
            System.out.println(txt + " - failed");
            throw e;
        }
    }
}
