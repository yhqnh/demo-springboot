package com.yhq.demospringboot;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * yanghq
 * 2018/3/12
 */
public class PreToAfterUtil {

    private static String leftChar = "(";
    private static String rightChar = ")";
    private static Map<String, Integer> operationSymbolMap = new HashMap<>();

    static {
        //初始化符号和优先级
        operationSymbolMap.put(")",00); //右括号需匹配左括号，故优先级最低
        operationSymbolMap.put("+",10);
        operationSymbolMap.put("-",10);
        operationSymbolMap.put("*",20);
        operationSymbolMap.put("/",20);
        operationSymbolMap.put("(",30);
    }

    /**
     * 中缀表达式转化为后缀表达式
     * @param strings
     * @return
     */
    public Queue parsePre(String[] strings) {
        Stack<String> preStack = new Stack<String>();
        Queue<String> queue = new LinkedBlockingQueue();
        int i = 0;
        while(i<strings.length && Objects.nonNull(strings[i])) {
            if(StringUtils.isNumeric(strings[i])) {
                queue.add(strings[i]);
            }else if(StringUtils.isNotEmpty(strings[i])) {
                if(preStack.isEmpty()) {
                    preStack.push(strings[i]);
                } else {
                    String top = preStack.pop();
                    if(comparePriority(strings[i], top) < 0) {
                        if(top.equals(leftChar)) {
                            preStack.push(top);
                            preStack.push(strings[i]);
                        }else if(strings[i].equals(rightChar)) {
                            appendTo(queue, top);
                            preStack.pop();
                        } else{
                            appendTo(queue, top);
                            popPre(preStack, strings[i], queue);
                            preStack.push(strings[i]); //当前元素入栈
                        }
                    } else {
                        preStack.push(top);
                        preStack.push(strings[i]);
                    }
                }
            }
            i++;
        }

        while (!preStack.isEmpty()) {
            queue.add(preStack.pop());
        }
        return queue;
    }

    /**
     * 递归比较当前元素与栈顶元素优先级
     * @param preStatck
     * @param charTemp
     * @param queue
     */
    public void popPre(Stack<String> preStatck, String charTemp, Queue queue) {
        if(!preStatck.isEmpty()) {
            String top = preStatck.pop();
            if(comparePriority(charTemp, top) <= 0) {
                //低于栈顶元素，成为后缀表达式一部分
                appendTo(queue, top);
                popPre(preStatck, charTemp, queue);
            } else {
                preStatck.push(top);
            }
        }
    }

    private void appendTo(Queue queue, String s) {
        if(!s.equals(leftChar) && !s.equals(rightChar)) {
            queue.add(s);
        }
    }

    /**
     * 比较优先级
     * @param start
     * @param to
     * @return
     */
    private int comparePriority(String start, String to) {
        return operationSymbolMap.get(start).compareTo(operationSymbolMap.get(to));
    }

    /**
     * 计算后缀表达式结果
     * @param queue
     * @return
     */
    public int computeResult(Queue<String> queue) {
        int result = 0;
        if(Objects.isNull(queue)) {
            return result;
        }
        String s = queue.poll();
        Stack<Integer> stack = new Stack();
        while(Objects.nonNull(s)) {
            if(StringUtils.isNumeric(s)) {
                stack.push(Integer.valueOf(s));
            }else if(!StringUtils.isEmpty(s)) {
                int first = 0;
                int second = 0;
                switch (s) {
                    case "+" :
                        first = stack.pop();
                        second = stack.pop();
                        result = first + second;
                        stack.push(result);
                        break;
                    case "-" :
                        first = stack.pop();
                        second = stack.pop();
                        result = second - first;
                        stack.push(result);
                        break;
                    case "*" :
                        first = stack.pop();
                        second = stack.pop();
                        result = first * second;
                        stack.push(result);
                        break;
                    case "/" :
                        first = stack.pop();
                        second = stack.pop();
                        result = second/first;
                        stack.push(result);
                        break;
                }
            }
            s = queue.poll();
        }
        return result;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String[] pre = new String[]{"8","+","(","6","-","1",")","*","2","+","10","/","2"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pre.length; i++) {
            sb.append(pre[i]);
        }
        System.out.println("前缀表达式：" + sb.toString());
        Queue queue = new PreToAfterUtil().parsePre(pre);
        System.out.println("后缀表达式：" + queue.toString());
        System.out.println("后缀表达式计算结果：" + new PreToAfterUtil().computeResult(queue));
    }
}
