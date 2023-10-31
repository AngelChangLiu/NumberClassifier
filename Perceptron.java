// This code inputs a vector x of n real numbers and outputs either +1 or -1
// based on the computed sum which is calculated by a weight vector w of n real numbers
public class Perceptron {

    private int n; // integer n
    private double[] weight; // array of weights

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        this.n = n; // Sets local variable n to instance variable n
        weight = new double[n]; // Creates weight array with n inputs
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return n;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        double sum = 0;
        // Adds the product of the weight value and x to compute the sum
        for (int i = 0; i < n; i++) {
            sum += x[i] * weight[i];
        }
        return sum;
    }

    // Predicts the label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        // Calls weightedSum to see if the weighedSum instance method is positive
        if (weightedSum(x) > 0)
            return +1;
        else
            return -1;
    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int label) {
        // Calls predict to compare predict to label
        // For false negative
        if (label > predict(x)) {
            for (int i = 0; i < n; i++) {
                weight[i] += x[i];
            }
        }
        // For false positive
        else if (label < predict(x)) {
            for (int i = 0; i < n; i++) {
                weight[i] -= x[i];
            }
        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String s = ""; // Creates empty String s
        for (int i = 0; i < n; i++) {
            // For each element in array except the last, add element and ", "
            if (i < n - 1)
                s += weight[i] + ", ";
                // For last element in the array just add the element
            else
                s += weight[i];
        }
        // Returns string of weights separated by commas and enclosed by parentheses
        return "(" + s + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        // Creates object and calls predict, numberOfInputs, and weightedSum
        Perceptron p1 = new Perceptron(7);
        double[] x = { 1, 2, 3, 4, 5, 6, 7 };
        StdOut.println(p1.predict(x));
        StdOut.println(p1.numberOfInputs());
        StdOut.println(p1.weightedSum(x));

        // The following code was obtained from the assignment instructions
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // yes
        double[] training2 = { 2.0, 0.0, -2.0 };  // no
        double[] training3 = { -2.0, 0.0, 2.0 };  // yes
        double[] training4 = { 5.0, 4.0, 3.0 };  // no

        Perceptron perceptron = new Perceptron(n);
        StdOut.println(perceptron);
        perceptron.train(training1, +1);
        StdOut.println(perceptron);
        perceptron.train(training2, -1);
        StdOut.println(perceptron);
        perceptron.train(training3, +1);
        StdOut.println(perceptron);
        perceptron.train(training4, -1);
        StdOut.println(perceptron);
    }
}
