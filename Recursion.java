public class Recursion {
    public double add(double num) {
        if (num <= 0) {
            System.out.println("num has reached 0 or less than zero");
        }
        else {
            System.out.println(num);
            num -= 2;
            System.out.println(num);
            add(num);
        }

        return num;
    }
    public void sayHi(int n){
        if (n == 0) {
            System.out.println("N must be > 0 to continue");
        }
        else {
            System.out.println("Hi");
            n--; //base case must be before recursive call
            sayHi(n); //need a base case to stop recursion

        }


    }
    public void countBackwards(int n) {
        if (n == 0) {
            System.out.println("method has finished");
        }
        else {
            System.out.println("N is currently " + n);
            n--;
            System.out.println("N is now " + n);
            countBackwards(n);
        }
    }
}
