import java.util.concurrent.Semaphore;

public class Person extends Thread {
    public String wantTo;
    public Semaphore mutex;
    public Semaphore wrt;
    public Semaphore readCount;
    public int pNumber;
    public String status;

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
                    this.setStatus("waiting");
                    while (this.mutex.availablePermits() <= 0) {

                    }
                    this.mutex.acquire();

                    this.readCount.release();


                    if(this.readCount.availablePermits() == 1){
                        while (this.wrt.availablePermits() <= 0)
                        {

                        }
                        this.wrt.acquire();
                    }
                    this.mutex.release();

                    // doc sach
                    this.setStatus("reading");
                    sleep(10000);

                    while (this.mutex.availablePermits() <= 0) {

                    }
                    this.mutex.acquire();

                    this.readCount.acquire();
                    if (this.readCount.availablePermits() == 0)
                        this.wrt.release();
                    this.mutex.release();
                    this.setStatus("thinking");
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true);

        } else if (this.wantTo.equals("write")) {
            while (true) {
                try {
                    this.setStatus("waiting");
                    //doi de co but
                    while (this.wrt.availablePermits() <= 0) {

                    }
                    this.wrt.acquire();
                    //go to cs => viet
                    this.setStatus("writing");
                    sleep(10000);
                    this.wrt.release();
                    //ms -> nghi
                    this.setStatus("thinking");
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
