// This code solves a multiclass classification problem by inputting m classes
// of n inputs and outputting the index of the perceptron with the highest
// sum by calling on methods from Perceptron
public class MultiPerceptron {

    private int m; // number of classes
    private int n; // length of the input feature vector
    private Perceptron[] perceptron; // array of perceptron objects

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {

        this.m = m; // Sets local variable m to instance variable m
        this.n = n; // Sets local variable n to instance variable

        // Creates an initializes an array of m Perceptron objects with n inputs
        this.perceptron = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            this.perceptron[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max = Integer.MIN_VALUE; // declares and initializes max
        int index = -1; // declares and initializes index
        // Runs through each class to see which one has the greatest weighted sum
        for (int i = 0; i < m; i++) {
            // Finds the greatest weighted sum
            if (perceptron[i].weightedSum(x) > max) {
                // Updates values of i and max
                index = i;
                max = perceptron[i].weightedSum(x);
            }
        }
        return index;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (label == i) {
                perceptron[i].train(x, 1);
            }
            else {
                perceptron[i].train(x, -1);
            }
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String x = ""; //
        for (int i = 0; i < m; i++) {
            if (i < m - 1) {
                x += perceptron[i] + ", ";
            }
            else {
                x += perceptron[i];
            }
        }
        return "(" + x + ")";
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        MultiPerceptron p1 = new MultiPerceptron(2, 3);
        StdOut.println(p1);
        double[] x = { 1, 2, 3, 4, 5, 6, 7 };

        StdOut.println(p1.numberOfClasses());
        StdOut.println(p1.numberOfInputs());
        StdOut.println(p1.predictMulti(x));

        // The following code was obtained from the assignment instructions
        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
    }
}
