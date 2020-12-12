import java.util.Scanner;

public class MastersCourseTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String word = scan.next();

        // 입력된 값이 변수 형식에 맞지 않는 경우에 대한 처리
        if(!scan.hasNextInt()) {
            System.out.println("밀어낼 횟수가 올바르지 않습니다. 정수만 사용가능합니다.");
            return;
        }
        int number = scan.nextInt();

        String direction = scan.next();
        scan.close();

        System.out.println(shiftWord(word, number, direction));
    }

    public static String shiftWord(String word, int number, String direction) {
        // 단어 길이와 같은 횟수만큼 밀어내면 처음과 동일해지므로 과정을 생략
        number %= word.length();

        // L = 양수, R = 음수로 처리
        // L방향으로 -n번 밀어내기 = -n = R방향으로 n번 밀어내기
        // R방향으로 -n번 밀어내기 = -(-n) = n = L방향으로 n번 밀어내기
        if(direction.toUpperCase().equals("R"))
            number *= -1;
        // 입력된 값이 L 또는 R이 아닌 경우에 대한 처리
        else if(!direction.toUpperCase().equals("L"))
            return "방향이 올바르지 않습니다. L, l, R, r만 사용가능합니다.";

        // number가 양수인 경우 왼쪽으로 밀기
        // 밀어내고 남는 부분을 앞에 두고 밀려난 만큼을 뒤에 붙이기
        if(number > 0)
            return word.substring(number) + word.substring(0, number);

        // number가 양수가 아닌 경우(음수, 0) 오른쪽으로 밀기
        // 밀려난 만큼을 앞에 두고 밀어내고 남는 부분을 뒤에 붙이기
        return word.substring(number + word.length()) + word.substring(0, number + word.length());
    }

}
