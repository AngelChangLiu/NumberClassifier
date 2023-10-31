import java.awt.Color;

// This code inputs training and testing files and outputs files that don't
// predict the right numbers, the supposed label and the predicted value. Then
// it outputs the test error rate.
public class ImageClassifier {

    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture picture) {
        int width = picture.width(); // gets width
        int height = picture.height(); // gets height
        int n = width * height; // length of grayscaleValues array
        double[] grayscaleValues = new double[n];
        int counter = 0; // index of grayscaleValues array
        // Fills grayscaleValues array with grayscale of each pixel
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = picture.get(col, row);
                int gray = color.getRed();
                grayscaleValues[counter] = gray;
                counter++;
            }
        }
        return grayscaleValues;
    }

    /* Calls the extractFeatures method as well as methods from Perceptron and
    MultiPerceptron in order to find the files that do not predict the right
    numbers.
     */
    public static void main(String[] args) {
        // Creates new training object from command-line argument
        In training = new In(args[0]);
        int m1 = training.readInt(); // reads number of classes
        int width1 = training.readInt(); // reads width
        int height1 = training.readInt(); // reads height
        MultiPerceptron multiPerceptron = new MultiPerceptron(m1, width1 * height1);
        // Runs loop until training object is empty
        while (!training.isEmpty()) {
            String filename = training.readString(); // reads filename
            // Creates a new object for each image
            Picture picture = new Picture(filename);
            int label = training.readInt(); // reads label
            // Invokes trainMulti method to train with grayscale values of picture
            // and label.
            multiPerceptron.trainMulti(extractFeatures(picture), label);
        }

        // Creates new testing object from command-line argument
        In testing = new In(args[1]);
        testing.readInt(); // reads number of classes
        testing.readInt(); // reads width
        testing.readInt(); // reads height
        int sum = 0; // sum of wrong predictions
        int total = 0; // number of images
        // Runs loop until testing object is empty
        while (!testing.isEmpty()) {
            String filename = testing.readString(); // reads filename
            // Creates a new object for each image
            // Creates picture object for filename
            Picture picture = new Picture(filename);
            int label = testing.readInt(); // reads label
            // Invokes predictMulti method to predict with grayscale values of picture
            int predict = multiPerceptron.predictMulti(extractFeatures(picture));
            // Checks to see if the predicted value is incorrect
            if (predict != label) {
                sum++; // increments sum
                StdOut.println(filename + ", label = " + label + ", predict = "
                                       + predict);
            }
            total++;
        }
        // Casts sum to accurately calculate error rate
        double error = (double) sum / total;
        StdOut.println("Test error rate = " + error);
    }
}
