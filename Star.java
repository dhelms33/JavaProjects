/*
 * CS 1050 - Computer Science I - Fall 2020
 * Instructor: Thyago Mota
 * Description: Activity 34 - Star
 */

// TODO: implement the Star class
public class Star {
    //private double x, y = 0;
    private double x;
    private double y;
    private double brightness;
    public static final double MAX_BRIGHTNESS = 8;

    public Star(double x, double y, double brightness) {
        this.x = x;
        this.y = y;
        if (brightness < 0 ) {
            this.brightness = 0;
        }
        else if (brightness > MAX_BRIGHTNESS)
            this.brightness = MAX_BRIGHTNESS;
        else
            this.brightness = brightness;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getBrightness() {
        return brightness;
    }
    public int getGUIX() {
        return (int) ((x+1)* StarMapFrame.WIDTH/2);
        //must maintain same ratio from star catalog to picture proportions
    }
    public int getGUIY(){
        return (int) ((1-y)*StarMapFrame.HEIGHT/2);
        //1 - -1 must be in proportion to 0 - 1000
    }


}