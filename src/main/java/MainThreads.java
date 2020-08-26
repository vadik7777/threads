public class MainThreads {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        MyThread one = new MyThread(resource);
        MyThread two = new MyThread(resource);
        one.start();
        two.start();
        one.join();
        two.join();
    }
}

class MyThread extends Thread{

    Resource resource;

    MyThread(Resource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
       resource.setI();
       System.out.println(Thread.currentThread().getName()+" "+resource.getI());
    }



}

class Resource{
    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI() {
        int i = this.i;
        i++;
        this.i = i;
    }
}
