package cn.lichengwu.test.conurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * ExchangerTest
 * 
 * @author lichengwu
 * @version 1.0
 * @created 2013-02-06 9:55 PM
 */
public class ExchangerTest {

    @Test
    public void test() throws InterruptedException {
        
        final int size =10;

        final Exchanger<List<Integer>> exchanger = new Exchanger<>();
        
        final List<Integer> emptyList = new ArrayList<>();
        final List<Integer> fullList = new ArrayList<>();
        
        
        
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> currentList = emptyList;
                while (true){
                   if(currentList.size()<size){
                       int num = (int)( Math.random() * 10000);
                       currentList.add(num);
                       System.out.println("producer : " + num);
                       try {
                           TimeUnit.MILLISECONDS.sleep((long) (Math.random()*1000));
                       } catch (InterruptedException ignore) {

                       }
                   }else {
                       try {
                           System.out.println("producer : list full wait exchange");
                           currentList =  exchanger.exchange(currentList);
                           System.out.println("producer : exchanged");
                       } catch (InterruptedException ignore) {
                           
                       }
                   }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> currentList = fullList;
                while (true){
                    if(currentList.size()!=0){
                        Integer remove = currentList.remove(0);
                        System.out.println("consumer : " + remove);
                        try {
                            TimeUnit.MILLISECONDS.sleep((long) (Math.random()*1000));
                        } catch (InterruptedException ignore) {
                        }
                    }else {
                        try {
                            System.out.println("consumer : list empty wait exchange");
                            currentList =  exchanger.exchange(currentList);
                            System.out.println("consumer : exchanged");
                        } catch (InterruptedException ignore) {

                        }
                    }
                }
            }
        });

        producer.start();

        consumer.start();

        producer.join();

        consumer.join();


    }
}
