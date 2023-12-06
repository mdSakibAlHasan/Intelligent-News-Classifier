package com.example.steaming;
public class Main {
    public static void main(String[] args) {
        String inputString = "In the quietude of the twilight, a gentle breeze rustled the leaves, carrying with it the fragrant whispers of nature. The moon, a silvery orb in the indigo sky, cast a serene glow upon the landscape. Crickets played a symphony, and stars adorned the heavens, painting a canvas of tranquility.";
        String inputString2 = "Such an analysis can reveal features that are not easily visible from the variations in the individual genes and can lead to a picture of expression that is more biologically transparent and accessible to interpretation";
        String inputString3 = "playing is a plays played to working works the work , play worked?";
        Stemming stemming = new Stemming();
        System.out.println(stemming.stem(inputString3));

    }


}
