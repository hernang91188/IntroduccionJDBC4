package test;
import org.apache.commons.lang.StringUtils;

public class test {

    public static void main(String[] args) {

        String result = StringUtils.leftPad("wrwer", 10, "0");
        System.out.println("The String : " + result);

    }
}