package Default;

import java.util.Random;

public class TrafficLight {

    private boolean red;
    private int time;
    private int currentTime;
    private String dir01;
    private String dir02;

    public TrafficLight(int time, String dir01, String dir02){
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

    public String getDir01() {
        return dir01;
    }

    public String getDir02() {
        return dir02;
    }
}
