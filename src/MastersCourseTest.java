import java.util.Scanner;

class PlaneCube {

    private String[][] planeCube = {{"R", "R", "W"}, {"G", "C", "W"}, {"G", "B", "B"}};
    private boolean isEnd = false;

    public void print() {
        for (String[] i : this.planeCube) {
            for (String j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void play() {
        Scanner scan = new Scanner(System.in);

        while(!this.isEnd) {
            System.out.print("CUBE> ");
            this.runCommands(scan.nextLine());
        }

        scan.close();
    }

    private void runCommands(String commands) {
        for(int i = 0; i < commands.length(); i++) {
            if(commands.charAt(i) == 'Q') {
                this.isEnd = true;
                System.out.println("Bye~");
                return;
            }

            boolean direction = true;
            if(i != commands.length()-1 && commands.charAt(i+1) == '\'')
                direction = false;

            if(commands.charAt(i) == 'U') {
                System.out.print("U");
                pushLineX(0, direction);
            } else if(commands.charAt(i) == 'B') {
                System.out.print("B");
                pushLineX(2, !direction);
            } else if(commands.charAt(i) == 'R') {
                System.out.print("R");
                pushLineY(2, direction);
            } else if(commands.charAt(i) == 'L') {
                System.out.print("L");
                pushLineY(0, !direction);
            } else {
                continue;
            }

            if(direction)
                System.out.println();
            else
                System.out.println("'");

            print();
        }
    }

    private void pushLineX(int line, boolean direction) {
        if(direction) {
            String swap = planeCube[line][0];

            planeCube[line][0] = planeCube[line][1];
            planeCube[line][1] = planeCube[line][2];
            planeCube[line][2] = swap;
        } else {
            String swap = planeCube[line][2];

            planeCube[line][2] = planeCube[line][1];
            planeCube[line][1] = planeCube[line][0];
            planeCube[line][0] = swap;
        }
    }

    private void pushLineY(int line, boolean direction) {
        if(direction) {
            String swap = planeCube[0][line];

            planeCube[0][line] = planeCube[1][line];
            planeCube[1][line] = planeCube[2][line];
            planeCube[2][line] = swap;
        } else {
            String swap = planeCube[2][line];

            planeCube[2][line] = planeCube[1][line];
            planeCube[1][line] = planeCube[0][line];
            planeCube[0][line] = swap;
        }
    }
}

public class MastersCourseTest {

    public static void main(String[] args) {
        PlaneCube cube = new PlaneCube();
        cube.print();
        cube.play();
    }
}