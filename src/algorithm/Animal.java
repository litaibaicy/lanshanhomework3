package algorithm;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Animal {
    Deque<Map<Integer, Integer>> queue0;
    Deque<Map<Integer, Integer>> queue1;
    public Animal() {
        queue0 = new LinkedList<>();
        queue1 = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        Map<Integer, Integer> map = new HashMap();
        if(animal[1] == 0){
            map.put(animal[0],0);
            queue0.offer(map);
        }else{
            map.put(animal[0],1);
            queue1.offer(map);
        }
    }

    public int[] dequeueAny() {
        Map<Integer, Integer> map0 = queue0.peek();
        int k = -1;
        if(map0 != null){
            for(int i : map0.keySet()){
                k = i;
            }
        }
        Map<Integer, Integer> map1 = queue1.peek();
        if(map1 != null){
            for(int i : map1.keySet()){
                if(i > k && k != -1){
                    queue0.poll();
                    return new int[]{k,map0.get(k)};
                }
                queue1.poll();
                return new int[]{i,map1.get(i)};
            }
        }else{
            if(k != -1){
                queue0.poll();
                return new int[]{k,map0.get(k)};
            }
        }
        return new int[]{-1,-1};
    }

    public int[] dequeueDog() {
        Map<Integer, Integer> map = queue1.poll();
        if(map != null){
            for(int i : map.keySet()){
                return new int[]{i,map.get(i)};
            }
        }
        return new int[]{-1,-1};
    }

    public int[] dequeueCat() {
        Map<Integer, Integer> map = queue0.poll();
        if(map != null){
            for(int i : map.keySet()){
                return new int[]{i,map.get(i)};
            }
        }
        return new int[]{-1,-1};
    }
}


