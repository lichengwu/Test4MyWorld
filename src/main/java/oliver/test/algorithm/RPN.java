package oliver.test.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰式
 *
 * @author lichengwu
 * @version 1.0
 * @created 2013-05-13 下午10:57
 */
public class RPN {


    @Test
    public void testToRPN() {
        Stack stack = toRPN("a+b*c+(d*e+f)*g");
        for (Object obj : stack) {
            if (obj instanceof Operation) {
                System.out.print(((Operation) obj).getOption());
            } else {
                System.out.print(obj);
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(10, calculate(toRPN("1+2*(3+4)-5")));
        Assert.assertEquals(2, calculate(toRPN("20/10+3*4-12")));
        Assert.assertEquals(15, calculate(toRPN("(3-4)*5+(12-2)*2")));
        Assert.assertEquals(1024, calculate(toRPN("2^10-(24-4)*12+240")));
    }

    private Stack toRPN(String input) {
        List<Object> output = new ArrayList<>(input.length());
        Stack<Operation> temp = new Stack<>();

        for (int i = 0; i < input.length(); i++) {

            String item = String.valueOf(input.charAt(i));
            Operation operation = Operation.getOperation(item);
            if (operation == null) {
                String number = item;
                //如果是连续数字，继续拼接数字成正数
                while (i < input.length() - 1) {
                    item = String.valueOf(input.charAt(++i));
                    operation = Operation.getOperation(item);
                    if (operation == null) {
                        number += item;
                    } else {
                        i--;
                        break;
                    }
                }
                output.add(number);
            } else {
                Operation peek = temp.isEmpty() ? null : temp.peek();
                //左括号直接添加
                if (operation == Operation.LEFT_BRACKET) {
                    temp.push(operation);

                }
                // 右括号出栈直到遇到左括号，同时左右括号舍弃
                else if (operation == Operation.RIGHT_BRACKET) {
                    while (peek != Operation.LEFT_BRACKET) {
                        output.add(temp.pop());
                        peek = temp.peek();
                    }
                    temp.pop();
                }
                //出栈，直到遇到优先级比当前小的操作符位置，然后把当前操作符入栈
                else {
                    while (peek != null && (peek.getPriority() >= operation.getPriority()) && peek != Operation.LEFT_BRACKET) {
                        output.add(temp.pop());
                        peek = temp.isEmpty() ? null : temp.peek();
                    }
                    temp.push(operation);
                }
            }
        }
        //其他操作符直接添加
        while (!temp.isEmpty()) {
            output.add(temp.pop());
        }
        // 逆序然后放入栈中
        Stack result = new Stack();
        Collections.reverse(output);

        for (Object obj : output) {
            result.push(obj);
        }
        return result;
    }

    private int calculate(Stack stack) {
        Stack<Integer> result = new Stack<>();

        while (!stack.isEmpty()) {
            Object obj = stack.pop();
            if (obj instanceof Operation) {
                Operation operation = (Operation) obj;

                Integer v1 = result.pop();
                Integer v2 = result.pop();

                switch (operation) {
                    case PLUS:
                        result.push(v2 + v1);
                        break;
                    case MINUS:
                        result.push(v2 - v1);
                        break;
                    case MULTI:
                        result.push(v2 * v1);
                        break;
                    case DIVISION:
                        result.push(v2 / v1);
                        break;
                    case POWER:
                        Double d = Math.pow(v2, v1);
                        result.push(d.intValue());
                }
            } else {
                result.push(Integer.valueOf((String) obj));
            }
        }

        return result.pop();
    }


    public enum Operation {

        PLUS(1, "+"),
        MINUS(1, "-"),
        MULTI(2, "*"),
        DIVISION(2, "/"),
        POWER(4, "^"),
        LEFT_BRACKET(Integer.MAX_VALUE, "("),
        RIGHT_BRACKET(Integer.MAX_VALUE, ")");

        private int priority;

        private String option;

        Operation(int priority, String option) {
            this.priority = priority;
            this.option = option;
        }


        public static Operation getOperation(String option) {
            for (Operation operation : Operation.values()) {
                if (operation.getOption().equals(option)) {
                    return operation;
                }
            }
            return null;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }
    }

}
