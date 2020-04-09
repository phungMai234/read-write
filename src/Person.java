import java.util.concurrent.Semaphore;

public class Person extends Thread {
    public String wantTo;
    public Semaphore mutex;
    public Semaphore wrt;
    public Semaphore readCount;
    public int pNumber;

    public Person(String want, Semaphore mutex, Semaphore wrt, Semaphore readCount, int pNumber)
    {
        this.wantTo = want;
        this.mutex=mutex;
        this.wrt = wrt;
        this.readCount = readCount;
        this.pNumber =pNumber;
    }


    public void run() {
        if (this.wantTo.equals("read")) {
            do {
                try {

                    while (this.mutex.availablePermits() <= 0) {

                    }
                    System.out.println(this.mutex.availablePermits());
                    this.mutex.acquire();
                    this.readCount.acquire();
                    this.readCount.acquire();
                    System.out.println(this.readCount.availablePermits());
//                    if(this.readCount.availablePermits() == 1){
//                        while (this.wrt.availablePermits() <= 0)
//                        {
//
//                        }
//                        this.wrt.acquire();
//                    }
                    this.mutex.release();

                    // doc sach
                    System.out.println(this.pNumber + " is reading book");
                    sleep(1000);
                    while (this.mutex.availablePermits() <= 0) {

                    }
                    this.mutex.acquire();
                    this.readCount.release(this.readCount.availablePermits() - 1);
                    if (this.readCount.availablePermits() == 0)
                        this.wrt.release();
                    this.mutex.release();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true);

        } else if (this.wantTo.equals("write")) {
            while (true) {
                try {
                    //doi de co but
                    while (this.wrt.availablePermits() <= 0) {

                    }
                    this.wrt.acquire();
                    //go to cs => viet
                    System.out.println(this.pNumber + " is writing");
                    sleep(1000);
                    this.wrt.release();
                    //ms -> nghi
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
