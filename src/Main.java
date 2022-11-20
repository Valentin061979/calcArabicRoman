import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        byte operand1, operand2;
        char operator = 0;
        int resultat;

        System.out.println("Input:");
        String userInput = input.nextLine();
        String[] primer = userInput.split(" ");
        int provOper2 = primer.length;
        if (provOper2 > 3) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (provOper2 < 2) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        } else {
            String strOperand1 = primer[0];
            String strOperand2 = primer[2];
            String strOperator = primer[1];

            switch (strOperator) {
                case "+" -> operator = '+';
                case "-" -> operator = '-';
                case "*" -> operator = '*';
                case "/" -> operator = '/';
                default -> System.out.println("throws Exception //т.к. строка не является математической операцией");
            }
            byte operandRom1 = Roman.romSearch(strOperand1);
            byte operandRom2 = Roman.romSearch(strOperand2);
            if (operandRom1 < 0 && operandRom2 < 0) {
                operand1 = Byte.parseByte(strOperand1);
                operand2 = Byte.parseByte(strOperand2);
                if (operand1 > 10 || operand2 > 10) {
                    System.out.println("Недопустимое количество символов");
                } else {
                    resultat = calculated(operand1, operand2, operator);
                    System.out.println("Output:");
                    System.out.println(resultat);
                }
            } else {
                if (operandRom1 < 0 || operandRom2 < 0) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                } else {
                    if (operandRom1 > 10 || operandRom2 > 10) {
                        System.out.println("Недопустимое количество символов");
                    } else {
                        byte resultatRom = calculated(operandRom1, operandRom2, operator);
                        if (resultatRom < 0) {
                            System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                        } else {
                            System.out.println("Output:");
                            System.out.println(Roman.roman[resultatRom]);
                        }
                    }
                }
            }
        }
    }

    static byte calculated(byte operand1, byte operand2, char operator) {
        int resultCalc = 0;
        switch (operator) {
            case '+':
                resultCalc = operand1 + operand2;
                break;
            case '-':
                resultCalc = operand1 - operand2;
                break;
            case '*':
                resultCalc = operand1 * operand2;
                break;
            case '/':
                try {
                    resultCalc = operand1 / operand2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Делить на ноль нельзя");
                    break;
                }
                break;
        }
        return (byte) resultCalc;
    }
}