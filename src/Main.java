import java.io.IOException;
import java.util.Scanner;

    public class Main {


        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Calculator example = new Calculator();
            String strToParse = sc.nextLine();
            example.parse(strToParse);
            System.out.println(example.calculate());
        }

    }

    class Calculator {
        private int a, b, arabicOrRoman = 0;
        private String sign;

        //void
        void parse(String strToParse) {
            String[] expression = strToParse.split(" ");
            checkLengthInput(expression.length);
            try {
                a = Integer.parseInt(expression[0]);
            } catch (NumberFormatException ne) {
                    arabicOrRoman += 1;
                    RomanNumbers romanNumberA = RomanNumbers.valueOf(expression[0]);
                    a = romanNumberA.getArabicNumbers();
            }

            sign = expression[1];

            try {
                b = Integer.parseInt(expression[2]);
            } catch (NumberFormatException e) {
                arabicOrRoman += 1;
                RomanNumbers romanNumberB = RomanNumbers.valueOf(expression[2]);
                b = romanNumberB.getArabicNumbers();
            }
            checkInput();
            if (arabicOrRoman == 1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    System.exit(0);
                }
            }
        }

        void checkLengthInput(int lenExpression) {
            if (lenExpression > 3) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. формат математической" +
                            " операции не удовлетворяет заданию - два операнда и один" +
                            " оператор (+, -, /, *)");
                    System.exit(0);
                }
            } else if (lenExpression < 3) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. строка не является математической операцией");
                    System.exit(0);
                }
            }
        }

        void checkInput() {
            if ((1 > a) || (a > 10) || (1 > b) || (b > 10)) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. значения меньше 1 или больше 10");
                    System.exit(0);
                }
            }
        }

        String convertToRoman(int value) {
            String outString = "Error";
            if (value < 1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел и 0");
                    System.exit(0);
                }
            }
            else if (value < 40){
                outString = "X".repeat(value/10);
                if (RomanNumbers.getRomanNumbersName(value%10) != null)
                    outString += RomanNumbers.getRomanNumbersName(value%10);
            } else if (value < 50) {
                outString = "XL";
                if (RomanNumbers.getRomanNumbersName(value - 40) != null)
                    outString += RomanNumbers.getRomanNumbersName(value - 40);
            } else if (value < 90) {
                value -= 50;
                outString = "L" + "X".repeat(value/10);
                if (RomanNumbers.getRomanNumbersName(value%10) != null)
                    outString += RomanNumbers.getRomanNumbersName(value%10);
            } else if (value < 100) {
                outString = "XC";
                if (RomanNumbers.getRomanNumbersName(value - 90) != null)
                    outString += RomanNumbers.getRomanNumbersName(value - 90);
            } else
                outString = "C";
            return outString;
        }
        String calculate(){
            int value;
            String strValue = null;
            switch (sign){
                case ("-"):
                    value = a - b;
                    if (arabicOrRoman == 0) 
                        strValue = Integer.toString(value);
                    else
                        strValue = convertToRoman(value);
                    break;
                case ("+"):
                    value = a + b;
                    if (arabicOrRoman == 0)
                        strValue = Integer.toString(value);
                    else
                        strValue = convertToRoman(value);
                    break;
                case ("*"):
                    value = a * b;
                    if (arabicOrRoman == 0) 
                        strValue = Integer.toString(value);
                    else
                        strValue = convertToRoman(value);
                    break;
                case ("/"):
                    value = a / b;
                    if (arabicOrRoman == 0) 
                        strValue = Integer.toString(value);
                    else
                        strValue = convertToRoman(value);
                    break;
            }
            return strValue;
        }

        enum RomanNumbers {
            I(1), II(2), III(3), IV(4),
            V(5), VI(6), VII(7), VIII(8), IX(9),
            X(10);
            private int arabicNumbers;

            RomanNumbers(int arabicNumbers) {
                this.arabicNumbers = arabicNumbers;
            }

            public int getArabicNumbers() {
                return arabicNumbers;
            }

            public static RomanNumbers getRomanNumbersName(int value) {

                for (RomanNumbers curValue : RomanNumbers.values()) {
                    if (value == curValue.getArabicNumbers()) {
                        return curValue;
                    }
                }
                return null;
            }
        }
    }