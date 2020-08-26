public class MainDeadLock {

    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.resourceA = resourceA;
        myThread2.resourceB = resourceB;
        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;
        myThread1.start();
        myThread2.start();

    }
}

class MyThread1 extends Thread {
    ResourceA resourceA;

    @Override
    public void run() {
        System.out.println(resourceA.getI());
    }
}

class MyThread2 extends Thread {
    ResourceB resourceB;

    @Override
    public void run() {
        System.out.println(resourceB.getI());
    }
}

class ResourceA {
    ResourceB resourceB;
    private int i = 1;

    public synchronized int getI()  {
        return resourceB.returnI();
    }

    public synchronized int returnI() {
        return i;
    }

}

class ResourceB {
    ResourceA resourceA;
    private int i = 2;

    public synchronized int getI() {
        return resourceA.returnI();
    }

    public synchronized int returnI() {
        return i;
    }
}
