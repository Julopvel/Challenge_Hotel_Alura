package tests;

public class TestStringInt {

    public static void main(String[] args) {
        String value = "2.570"; //El punto (.) en el String me genera el error en la conversion con Integer.parseInt()

        try {
            System.out.println("Class de value: " + value.getClass());
            int val = Integer.parseInt(value);

        } catch (NumberFormatException e){
            System.out.println("Not a number");
            System.out.println(value);
            //System.out.println(val);
        }






    }
}
