import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Person[] persons = new Person[5];
        Semaphore mutex = new Semaphore(1);
        Semaphore wrt = new Semaphore(1);
        Semaphore readCount = new Semaphore(0) ;
        for(int i=0; i < 5; i++)
        {
            String want;
            want = i % 2 == 0 ? "read" : "write";
            persons[i] = new Person(want, mutex, wrt, readCount, i);
        }
        for(int i=0; i < 5; i++)
        {
           persons[i].start();
        }
//        while(true){
//            for(int i=0; i < 5; i++)
//            {
//                System.out.print(persons[i].wrt.availablePermits() + " ");
//            }
//            System.out.println();
//            for(int i=0; i < 5; i++)
//            {
//                System.out.print(persons[i].wrt.availablePermits() + " ");
//            }
//            System.out.println();
//            System.out.println("-----------");
//            sleep(2000);
//        }
    }



}
