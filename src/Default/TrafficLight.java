package Default;

import java.util.Random;

public class TrafficLight {

    private boolean red;
    private int time;
    private int currentTime;
    private char dir01;
    private char dir02;

    public TrafficLight(int time, int x, int y, char dir01, char dir02){
        Random r = new Random();
        this.red = r.nextBoolean();
        this.time = time;
        this.currentTime = time;
        this.dir01 = dir01;
        this.dir02 = dir02;
    }

    public void decreaseTime(){
        if(this.currentTime==0){
            this.red = !this.red;
            this.currentTime = this.time;
        }else{
            this.currentTime--;
        }
    }

    public boolean isRed() {
        return red;
    }

    public char getDir01() {
        return dir01;
    }

    public char getDir02() {
        return dir02;
    }

    public int getCurrentTime() {
        return currentTime;
    }
}
