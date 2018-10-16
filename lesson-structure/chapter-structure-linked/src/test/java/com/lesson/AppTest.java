package com.lesson;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */




    private int a;
    @Test
    public void shouldAnswerWithTrue()
    {
        String[] office ={"律所1","律所2","律所3","律所4"};


        String[][] classes = {{"律师1","律师2","律师3"},
                {"律师1","律师2","律师3"},
                {"律师1","律师2","律师3"},
                {"律师1","律师2","律师3"},
                {"律师1","律师2","律师3"}
        };
        forEach(office,classes);
    }
    private void forEach(String[]  classes,String[][] en){
        for (int i=0;i<classes.length;i++) {
            System.out.println(classes[i]+" " +en[i][a]);
        }
        a++;
        if (en[0].length > a){
            forEach(classes,en);
        }
    }



    @Test
    public void test1(){

        System.out.println(1<<5);


        System.out.println(Integer.toBinaryString(14));


    }
}
