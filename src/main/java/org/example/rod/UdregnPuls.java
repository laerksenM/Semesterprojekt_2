/*package org.example.rod;

public static class UdregnPuls(int[][] Data) {


        //double RED_Diff[] = new double[100]; //vi
        double IR_Diff[] = new double[100]; // her får vi de største differenser.
        //double Red_Diff_Diff[] = new double[100];
        double IR_Diff_Diff[] = new double[100];
        double minPunkt = -100.0;
        double maxPunkt = 100.0;


        for (int i = 0; i < antal - 1; i++) {
            IR_Diff[i] = values[i + 1][IR] - values[i][IR];
            if (i >= 1) {
                IR_Diff_Diff[i - 1] = IR_Diff[i] - IR_Diff[i - 1];
                //System.out.println("DiffDiff ; " + IR_Diff_Diff[i - 1]);

                for (int j = 1; j <= IR_Diff.length; j++) ;
                double Puls;
                {
                    if (IR_Diff[i] < IR_Diff[i + 1]) {
                        minPunkt = IR_Diff[i];
                    }
                    if (IR_Diff[i] > IR_Diff[i + 1]) {
                        maxPunkt = IR_Diff[i];
                    }
                    //System.out.println("maxpunkt ; " + maxPunkt );
                    //System.out.println("minpunkt ; " + minPunkt );
                    for (int h = 0; h < 1; h++) {

                        double interval = minPunkt - maxPunkt;
                        double ThresHold = ((0.04 * interval));
                        double NegativPuls = (ThresHold * -1);

                        Puls = (60 / NegativPuls);
                        if (Puls > 120) Puls = 120;

                        //System.out.println("Puls: " + Puls);
                    }
                }
            }
        }
    }
}
*/