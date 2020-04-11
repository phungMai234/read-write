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
        for(int i=0; i<5;i++){
            persons[i].start();
        }
        while(true){
            for(int i = 0; i < 5; i++)
            {
                System.out.println(persons[i].pNumber + " want to " + persons[i].wantTo + ", now " + persons[i].status);
            }
            System.out.println("------------");
            sleep(2000);

        }
        /*
        manr dua[];
        nha triet hoc co: left, right, pNumer
        * */
        /*
            wait left
            wait right

            // cs
            eat

            signal left;
            signal right;

            thinking

        */
    }



}
