# 2단계: 평면 큐브 구현하기
## 1. PlaneCube클래스
* PlaneCube 클래스는 다음과 같은 멤버를 가진다.
  * `String[][] planeCube`: 평면 큐브의 상태를 저장한다.
  * `boolean isEnd`: 명령어 반복 입력을 끝내는 시점을 알리기 위한 `flag` 변수이다.
  * `void print()`: 평면 큐브의 현재 상태를 출력한다.
  * `void play()`: 명령어를 반복하여 입력받고 `runCommands()`로 전달한다.
  * `void runCommands()`: 전달받은 명령어를 분석하여 명령어에 맞는 메소드를 호출한다.
  * `void pushLineX()`: 평면 큐브를 x축(행) 방향으로 밀어낼 때 호출하는 `swap` 메소드이다.
  * `void pushLineY()`: 평면 큐브를 y축(열) 방향으로 밀어낼 때 호출하는 `swap` 메소드이다.

## 2. 명령어 분석
* runCommands() 메소드에서는 다음과 같은 과정을 통해 전달받은 명령어를 분석한다.
  1. 현재 명령어가 `Q`일 경우, `flag` 변수 `isEnd`를 `true`로 설정하고 `return`한다.
  1. 다음 명령어가 `'`인지 확인한다. `'`일 경우 `direction=false`로 설정한다.
  1. 현재 명령어가 `U` 또는 `B`일 경우, `pushLineX()`를 호출한다. 이 때, `B`는 `U`와 방향이 반대이므로 `direction` 대신 `!direction`을 인자로 넘긴다.
  1. 현재 명령어가 `R` 또는 `L`일 경우, `pushLineY()`를 호출한다. 이 때, `L`은 `R`과 방향이 반대이므로 `direction` 대신 `!direction`을 인자로 넘긴다.
  1. `pushLineX()` 또는 `pushLineY()`의 호출을 마치고 `return`하면 `print()`를 호출하여 변경된 상태를 출력한다.

## 3. 밀어내기
* `pushLineX()` 또는 `pushLineY()` 메소드는 밀어낼 줄의 번호와 밀어낼 방향을 인자로 받아 3개의 배열 구성 요소를 `swap`하는 메소드이다.
* 정방향(왼쪽으로 밀어내기)인 경우의 `pushLineX()` 메소드의 동작은 다음과 같다.
  1. |swap|　|[0]|[1]|[2]|
     |:---:|:---:|:---:|:---:|:---:|
     | |　|A|B|C|
      
  1. |swap|　|[0]|[1]|[2]|
     |:---:|:---:|:---:|:---:|:---:|
     |A|←|A|B|C|

  1. |[0]|　|[1]|[2]|swap|
     |:---:|:---:|:---:|:---:|:---:|
     |B|←|B|C|A|
  
  1. |[0]|[1]|　|[2]|swap|
     |:---:|:---:|:---:|:---:|:---:|
     |B|C|←|C|A|
  
  1. |[0]|[1]|[2]|　|swap|
     |:---:|:---:|:---:|:---:|:---:|
     |B|C|A|←|A|
  
* 이를 나타낸 코드는 다음과 같다.
  ```JAVA
  String swap = this.planeCube[line][0];

  this.planeCube[line][0] = this.planeCube[line][1];
  this.planeCube[line][1] = this.planeCube[line][2];
  this.planeCube[line][2] = swap;
  ```