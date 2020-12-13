import java.util.Scanner;

public class MastersCourseTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("> ");
        String word = scan.next();

        if(!scan.hasNextInt()) {
            System.out.println("밀어낼 횟수가 올바르지 않습니다. -100 ~ 99 사이의 정수만 사용가능합니다.");
            return;
        }
        int number = scan.nextInt();
        if((number < -100) || (number > 99)) {
            System.out.println("밀어낼 횟수가 올바르지 않습니다. -100 ~ 99 사이의 정수만 사용가능합니다.");
            return;
        }

        String direction = scan.next();
        scan.close();

        System.out.println(shiftWord(word, number, direction));
    }

    public static String shiftWord(String word, int number, String direction) {
        // 단어 길이와 같은 횟수만큼 밀어내면 처음과 동일해지므로 과정을 생략
        number %= word.length();

        // L = 양의 방향, R = 음의 방향으로 처리
        // 예시> L(+)방향으로 -n번 밀어내기 = +(-n) = -n = R(-)방향으로 n번 밀어내기
        // 예시> R(-)방향으로 -n번 밀어내기 = -(-n) = +n = L(+)방향으로 n번 밀어내기
        if(direction.equalsIgnoreCase("R"))
            number *= -1;
        else if(!direction.equalsIgnoreCase("L"))
            return "방향이 올바르지 않습니다. L, l, R, r만 사용가능합니다.";

        // L 방향으로 밀어낼 경우, 밀어내고 남는 부분을 앞에 두고 밀려난 만큼을 뒤에 붙이기
        // R 방향으로 밀어낼 경우, 밀려난 만큼을 앞에 두고 밀어내고 남는 부분을 뒤에 붙이기
        if(number > 0)
            return word.substring(number) + word.substring(0, number);
        else
            return word.substring(number + word.length()) + word.substring(0, number + word.length());
    }

}
