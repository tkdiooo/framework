package com.qi.common.consistentHash;

import com.qi.common.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class RoundRobinWeight
 *
 * @author 张麒 2016/5/16.
 * @version Description:
 */
public class RoundRobinWeight {
    private static final List<Node> lNodes  = new ArrayList<>();
    private static int cw = 0;
    private static int number = -1;// 当前Node的序号,开始是-1
    private static int max;// 最大权重
    private static int gcd;// 最大公约数

    private static final Object lock = new Object();

    public RoundRobinWeight() {
    }


    private static void refreshNode(){
        max = getMaxWeight(lNodes);
        gcd = gcd(lNodes);
    }


    public static void addNode(final Node n){
        synchronized (lock) {
            lNodes.add(n);
            refreshNode();

        }
    }

    public static boolean removeNode(final Node node){
        synchronized (lock) {
            if(ListUtil.isEmpty(lNodes))return false;
            boolean f = false;
            for(int i = 0 ; i < lNodes.size() ; i++){
                Node n = lNodes.get(i);
                if(n.equals(node)){
                    f = lNodes.remove(n);
                }
            }
            if(f){
                refreshNode();
            }
            return f;
        }
    }


    /**
     * 求最大公约数
     *
     * @param ary
     * @return
     */
    private static int gcd(List<Node> ary) {
        if( ary == null || ary.size() == 0){
            return 0;
        }

        int min = ary.get(0).weight;

        for (int i = 0; i < ary.size(); i++) {
            int weight =  ary.get(i).weight;
            if (weight < min) {
                min = weight;
            }
        }
        while (min >= 1) {
            boolean isCommon = true;
            for (int i = 0; i < ary.size(); i++) {
                if (ary.get(i).weight % min != 0) {
                    isCommon = false;
                    break;
                }
            }
            if (isCommon) {
                break;
            }
            min--;
        }
        // System.out.println("gcd=" + min);
        return min;
    }

    /**
     * 求最大值，权重
     *
     * @return
     */

    private static int getMaxWeight(List<Node> ary) {
        int max = 0;
        for (int i = 0; i < ary.size(); i++) {
            int weight =  ary.get(i).weight;
            if (max < weight) {
                max = weight;
            }
        }
        return max;
    }

    /**
     * 获取请求的SERVER序号
     *
     * @return
     */
    private static synchronized Integer nextIndex() {

        if( lNodes == null || lNodes.size() == 0){
            return null;
        }

        while (true) {
            number = (number + 1) % lNodes.size();
            // System.out.println("number=" + number);
            // System.out.println("cw=" + cw);
            if (number == 0) {
                cw = cw - gcd;// cw比较因子，从最大权重开始，以最大公约数为步长递减
                if (cw <= 0) {//
                    cw = max;
                    if (cw == 0)
                        return null;
                }
            }
            if (lNodes.get(number).weight >= cw)
                return number;
        }

    }

    /**
     * 获取请求的Node
     *
     * @return
     */
    public static Node nextNode(){
        synchronized (lock) {
            Integer index = nextIndex();
            if(index == null)return null;
            return lNodes.get(index);

        }
    }

    public static void main(String[] args) {

//    	Node[] nodes = new Node[5];
//    	nodes[0] = new Node("node-1","","");
//    	nodes[1] = new Node("node-2","","");
//    	nodes[2] = new Node("node-3","","");
//    	nodes[3] = new Node("node-4","","");
//    	nodes[4] = new Node("node-5","","");

//    	nodes[5] = new Node("node-6","","");
//    	nodes[6] = new Node("node-7","","");
//    	nodes[7] = new Node("node-8","","");
//    	nodes[8] = new Node("node-9","","");
//    	nodes[9] = new Node("node-0","","");

//    	List<Node> nn = new ArrayList<Node>();
//    	nn.add(new Node("node-1","",""));
//    	nn.add(new Node("node-2","",""));
//    	nn.add(new Node("node-3","",""));


//    	final RoundRobinWeight  rrw = new RoundRobinWeight();
        RoundRobinWeight.addNode(new Node("node-1","",""));
        RoundRobinWeight.addNode(new Node("node-2","",""));
        RoundRobinWeight.addNode(new Node("node-3","",""));


        try {
            ExecutorService pool = Executors.newFixedThreadPool(10);
            final AtomicInteger count1 = new AtomicInteger(0);
            final AtomicInteger count2 = new AtomicInteger(0);
            final AtomicInteger count3 = new AtomicInteger(0);
            final AtomicInteger count4 = new AtomicInteger(0);
            for (int i = 0; i < 4000; i++) {
                Runnable run = new Runnable() {
                    public void run() {
                        Node n = RoundRobinWeight.nextNode();
                        if(n.getName().equals("node-1")){
                            count1.incrementAndGet();
                        }
                        if(n.getName().equals("node-2")){
                            count2.incrementAndGet();
                        }
                        if(n.getName().equals("node-3")){
                            count3.incrementAndGet();
                        }
                        if(n.getName().equals("node-4")){
                            count4.incrementAndGet();
                        }
//                    	if(count1.get() == 100){
//                    		RoundRobinWeight.removeNode(new Node("node-1","",""));
//                    		RoundRobinWeight.addNode(new Node("node-4","",""));
//                    	}

                        if(n != null)
                            System.out.println(n.name);
                        else
                            System.out.println(n);
                    }
                };

                pool.execute(run);
//                if(count1.get() == 100){
//	                try {
//	                	Thread.sleep(500);
//	                } catch (InterruptedException e) {
//	                	// TODO Auto-generated catch block
//	                	e.printStackTrace();
//	                }
//                }
                if(i == 1200){
                    RoundRobinWeight.addNode(new Node("node-4","",""));
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
//                if(i == 10){
//                	rrw.removeNode(new Node("node-2","",""));
////                	rrw.removeNode(nodes[4]);
//                }
//
//                if(i == 15){
//                	rrw.addNode(new Node("node-4","",""));
////                	rrw.removeNode(nodes[4]);
//                }

            }

            // 关闭启动线程
            pool.shutdown();
            // 等待子线程结束，再继续执行下面的代码
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            System.out.println("count node-1: " + count1.get());
            System.out.println("count node-2: " + count2.get());
            System.out.println("count node-3: " + count3.get());
            System.out.println("count node-4: " + count4.get());
            System.out.println("all thread complete");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
