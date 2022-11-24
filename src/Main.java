import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input:");
        String userInput = input.nextLine();
        String[] primer = userInput.split(" ");
        int provOper1 = primer.length;
        if (provOper1 > 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println(e + "//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else if (provOper1 < 2) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println(e + "//т.к. строка не является математической операцией");
            }
        } else {
            String strResultat = calc(userInput);
            System.out.println("Output:");
            System.out.println(strResultat);
        }
    }

    static byte calculated(byte operand1, byte operand2, char operator) {
        int resultCalc = 0;
        switch (operator) {
            case '+' -> resultCalc = operand1 + operand2;
            case '-' -> resultCalc = operand1 - operand2;
            case '*' -> resultCalc = operand1 * operand2;
            case '/' -> {
                try {
                    resultCalc = operand1 / operand2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println(e + "Делить на ноль нельзя");
                }
            }
        }
        return (byte) resultCalc;
    }

    public static String calc(String input) {
        String[] primer = input.split(" ");
        String strOperand1 = primer[0];
        String strOperand2 = primer[2];
        String strOperator = primer[1];
        char operator = 0;
        switch (strOperator) {
            case "+" -> operator = '+';
            case "-" -> operator = '-';
            case "*" -> operator = '*';
            case "/" -> operator = '/';
            default -> {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(e + "//т.к. строка не является математической операцией");
                }
            }
        }
        byte operandRom1 = Roman.romSearch(strOperand1);
        byte operandRom2 = Roman.romSearch(strOperand2);
        if (operandRom1 < 0 && operandRom2 < 0) {
            byte operand1 = Byte.parseByte(strOperand1);
            byte operand2 = Byte.parseByte(strOperand2);
            if (operand1 > 10 || operand2 > 10) {
                System.out.println("Недопустимое количество символов");
            } else {
                int resultat = calculated(operand1, operand2, operator);
                return (Integer.toString(resultat));
            }
        } else {
            if (operandRom1 < 0 || operandRom2 < 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(e + "//т.к. используются одновременно разные системы счисления");
                }
            } else if (operandRom1 > 10 || operandRom2 > 10) {
                System.out.println("Недопустимое количество символов");
            } else {
                byte resultatRom = calculated(operandRom1, operandRom2, operator);
                if (resultatRom < 0) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        System.out.println(e + "//т.к. в римской системе нет отрицательных чисел");
                    }
                } else {
                    return (Roman.roman[resultatRom]);
                }
            }
        }
        System.exit(0);
        return null;
    }
}
