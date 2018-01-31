public class GeneralizedChangemakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_USA();
        test_Euro();
        test_35();
        test_92620();
        test_941();
        test_81349();
        test_3198();
        test_1573();
        test_semiBig();
        test_big();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        int[] usaDenominations = new int[] { 25, 10, 5, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 99);
        try {
            displaySuccessIfTrue(3 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_Euro() {
        int[] euroDenominations = new int[] { 50, 20, 10, 5, 2, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(euroDenominations, 99);
        try {
            displaySuccessIfTrue(1 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_35() {
        int[] threeFiveDenominations = new int[] { 3, 5 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(threeFiveDenominations, 9);
        try {
            displaySuccessIfTrue(3 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_92620() {
        int[] nineTwoSixTwoZeroDenominations = new int[] { 92, 6, 20 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(nineTwoSixTwoZeroDenominations, 58);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(3 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_941() {
        int[] nineFourOneDenominations = new int[] { 9, 4, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(nineFourOneDenominations, 12);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(3 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_81349() {
        int[] eightThirteenFourNineDenominations = new int[] { 8, 13, 4, 9 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(eightThirteenFourNineDenominations, 16);
        try {
            displaySuccessIfTrue(2 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_3198() {
        int[] threeOneNineEightDenominations = new int[] { 3, 1, 9, 8 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(threeOneNineEightDenominations, 14);
        try {
            displaySuccessIfTrue(2 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_1573() {
        int[] oneFiveSevenThreeDenominations = new int[] { 1, 5, 7, 3 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(oneFiveSevenThreeDenominations, 100);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(13 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(3 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_semiBig() {
        int[] semiBigNumsDenominations = new int[] { 32, 99, 124, 18, 59 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(semiBigNumsDenominations, 187);
        try {
            displaySuccessIfTrue(4 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_big() {
        int[] bigNumsDenominations = new int[] { 124, 206, 326, 999, 674 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(bigNumsDenominations, 1236);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(6 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}
