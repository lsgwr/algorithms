/***********************************************************
 * @Description : 猫狗队列
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 22:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第04题_猫狗队列;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getPetType() {
        return type;
    }
}

class Cat extends Pet {
    public Cat() {
        super("dog");
    }
}

class Dog extends Pet {
    public Dog() {
        super("cat");
    }
}

class PetEnterQueue {
    private Pet pet;
    /**
     * 每个宠物实例的时间戳
     */
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}

public class DogCatQueue {
    private Queue<PetEnterQueue> dogQueue;
    private Queue<PetEnterQueue> catQueue;
    /**
     * 猫狗队列的元素个数
     */
    private long count;

    public DogCatQueue() {
        this.dogQueue = new ArrayDeque<>();
        this.catQueue = new ArrayDeque<>();
        this.count = 0;
    }

    public void add(Pet pet, int num) {
        if (pet.getPetType().equals("dog")) {
            this.dogQueue.add(new PetEnterQueue(pet, this.count++));
        } else if (pet.getPetType().equals("cat")) {
            this.catQueue.add(new PetEnterQueue(pet, this.count++));
        } else {
            throw new RuntimeException("err, not dog or cat!");
        }
    }

    public void pollAll() {
        while (!this.catQueue.isEmpty() || !this.dogQueue.isEmpty()) {
            poll();
        }
    }

    public Pet poll() {
        if (!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
            if (this.dogQueue.peek().getCount() < this.catQueue.peek().getCount()) {
                return this.dogQueue.poll().getPet();
            } else {
                return this.catQueue.poll().getPet();
            }
        } else if (!this.dogQueue.isEmpty()) {
            return this.dogQueue.poll().getPet();
        } else if (!this.catQueue.isEmpty()) {
            return this.catQueue.poll().getPet();
        } else {
            throw new RuntimeException("err, queue is empty!");
        }
    }

    public Dog pollDog() {
        if (!this.dogQueue.isEmpty()) {
            return (Dog) this.dogQueue.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty!");
        }
    }

    public Cat pollCat() {
        if (!this.catQueue.isEmpty()) {
            return (Cat) this.catQueue.poll().getPet();
        } else {
            throw new RuntimeException("Cat Queue is empty!");
        }
    }

    public boolean isEmpty() {
        return this.dogQueue.isEmpty() && this.catQueue.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return this.dogQueue.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return this.catQueue.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 操作总数
        int N = sc.nextInt();
        int i = 0;
        while (i < N) {
            String cmd = sc.nextLine();
            if (cmd.contains("add")) {
                String[] arr = cmd.split(" ");
                int cnt = Integer.parseInt(args[2]);
                if (arr[1].equals(""));
            }
            i++;
        }
    }
}
