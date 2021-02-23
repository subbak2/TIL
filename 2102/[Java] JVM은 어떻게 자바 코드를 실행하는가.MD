URL : [https://subbak2.tistory.com/40](https://subbak2.tistory.com/40)

참고 출처 : [wonyong-jang.github.io/java/2020/11/08/Java-JVM.html](https://wonyong-jang.github.io/java/2020/11/08/Java-JVM.html)

[

\[Java\] JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가 - SW Developer

1)Java Virtual Machine 은 자바를 실행하기 위한 자바 가상머신이다. 자바와 운영체제 사이에서 중계자 역할을 하며, 자바가 운영체제 종류에 영향을 받지 않고 실행 가능하도록 한다. 2) 운영체제 위

wonyong-jang.github.io



](https://wonyong-jang.github.io/java/2020/11/08/Java-JVM.html)

**0\. 들어가며**

 Java 기반 프레임워크의 IT서비스 운영을 하며 가끔 의문이 생길 때가 있었다.

 1) GC는 어떤 원리로 동작하는가?

 2) Decompiler는 어떤 원리로 동작할까? (Jadclipse 등)

 3) 2차원 배열에서 int\[10\]\[10만\]의 성능이 int\[10만\]\[10\] 보다 좋은 이유는 무엇일까?

아직 온전히 이해하지 못했지만 JVM을 공부해보며 그 실마리를 조금이나마 찾을 수 있었다.

**1\. JVM이란?**

  1) Java Virtual Machine 은 자바를 실행하기 위한 가상머신이다.   
  자바와 운영체제 사이의 중계자로, 자바가 운영체제 종류에 영향을 받지않고 실행 가능하도록 한다.  
  
  2) 운영체제 위에서 동작하는 프로세스로 자바 코드를 컴파일 해서 얻은 바이트 코드를   
  운영체제가 이해할 수 있는 기계어로 바꿔실행 시켜주는 역할을 한다.  
  
  3) GC(Garbage Collection)을 통해 자동으로 메모리 관리를 해준다.

**2\. JVM이 자바 소스파일(.java)을 실행하는 과정**

1) 프로그램이 실행되면 JVM은 운영체제로 부터 이 프로그램이 필요로 하는 메모리를 할당 받음  
  \* JVM은 이 메모리를 용도에 따라 여러 영역으로 나누어 관리  
  
2) 자바 컴파일러(javac)가 자바 소스코드(.java)를 읽어들여 자바 바이트코드(.class)로 변환  
  .java -> .class  
  
3) Class Loader를 통해 class파일을 JVM 메모리에 적재  
  
4) JVM 메모리 영역에 적재된 class 파일을 Execution engine을 통해 해석

**3\. 자바 컴파일 및 실행방법**

1) javac.exe를 통해 .java를 .class로 컴파일

2) java.exe를 통해 .class를 바이트코드로 실행

\* JDK : javac.exe와 java.exe를 모두 갖고 있음

\* JRE : java.exe만 갖고 있음

**4\. 바이너리코드와 바이트코드의 차이**

**1) 바이너리 코드 - Ex> C언어**

C언어는 컴파일러에 의해 소스파일(.c)이 목적파일(.obj)로 변환될 때 

0과 1로 이루어진 바이너리코드로 변환된다.

즉, 컴파일 후에 이미 컴퓨터가 이해할 수 있는 이진코드로 변환되는 것이다.  
  
목적파일은 바이너리코드의 형태이지만 실행될 수는 없다. 

그 이유는 완전한 기계어가 아니기 때문이다. 

변화된 목적 파일은 링커에 의해 실행 가능한 실행파일(.ex)로 변환 될 때 

100% 기계어가 될 수 있다.

**2) 바이트 코드 - Ex> Java**

C언어와 다르게 Java에서는 컴파일러(javac)에 의해 소스파일(.java)이

컴퓨터가 바로 인식할 수 없는 바이트코드(.class)로 변환된다.  
  
컴퓨터가 이해할 수 있는 언어가 바이너리코드라면 

바이트코드는 가상 머신이 이해할 수 있는 언어이다.  
  
고급언어로 작성된 소스코드를 가상 머신이 이해할 수 있는 

중간 코드로 컴파일한 것을 말한다.  
  
이러한 과정을 거치는 이유는 

어떠한 플랫폼에도 종속되지 않고 

JVM에 의해 실행 될수 있도록 하기 위함이다.

**5\. JVM 구성요소**

[##_Image|kage@6OlBx/btqX6fNnVyd/XkHPtZJVpvbMuRuTTIyqbK/img.png|alignCenter|data-origin-width="0" data-origin-height="0" width="588" height="NaN" data-ke-mobilestyle="widthContent"|||_##]

**1) Java Compiler**  
java source(.java)파일은 ByteCode(.class)로 변환된다.  
  
**2) Class Loader**  
변환된 ByteCode(.class)파일을 JVM이 운영체제로 부터 할당 받은 메모리 영역인 Runtime Data Area로 적재하는 역할을 한다.  
  
**3) Execution Engine**  
Class Loader 를 통해 JVM 내부로 넘어와 Runtime Data Area(JVM 메모리)에 배치된 ByteCode들을 기계어로 변경하게 되는데 이때 두가지 방식을 사용하게 된다.  
(인터프리터, JIT 컴파일러)  
  
\* 실행엔진

  - 실행 엔진 내부적으로는 인터프리터, JIT, GC가 있다.  
  
**① Interpreter(인터프리터)**  
   기존 바이트 코드를 실행하는 방법은 인터프리터 방식이 기본이다.  
   실행엔진은 자바 바이트 코드를 명령어 단위로 읽어서 실행한다.  
  
   이 방식은 한줄씩 수행하기 때문에 느리다는 

   인터프리터 언어의 단점을 그대로 가지고 있다.  
  
**② JIT(Just-In-Time)**  
   JIT 컴파일러는 실행 시점에 인터프리터 방식으로 기계어 코드를 생성 하면서 그 코드를 캐싱한다. 

   그리고 같은 함수가 여러 번 불릴 때 매번 기계어 코드를 생성하는 것을 방지한다.  
  
   전체 컴파일 후 캐싱 → 변경된 부분만 컴파일 하고 나머지는 캐시에서 가져다가 바로 실행 한다.  
  
   변경된 부분만 컴파일 하기 때문에 수행속도가 인터프리터 방식에 비해 빠르다.  
  
**③ GC (Garbage Collection)**  
  자바에서 개발자는 힙을 사용할 수 있는 만큼 자유롭게 사용하고,

  더이상 사용되지 않는 오브젝트들은 GC에 의해 자동으로 메모리에서 제거된다.  
  
  자세한 내용은 아래 링크를 통해 확인 가능하다.

[wonyong-jang.github.io/java/2020/03/14/Java-GC.html](https://wonyong-jang.github.io/java/2020/03/14/Java-GC.html)

[

\[Java\] GC - SW Developer

GC 프로그래머는 힙을 사용할 수 있는 만큼 자유롭게 사용하고, 더 이상 사용되지 않는 오브젝트들은 가비지 컬렉션을 담당하는 프로세스가 자동으로 메모리에서 제거하도록 하는 것이 가비지

wonyong-jang.github.io



](https://wonyong-jang.github.io/java/2020/03/14/Java-GC.html)

**4) Runtime Data Area**  
프로그램을 수행하기 위해 운영체제로부터 할당받은 메모리 공간

① PC Register  
Thread가 시작될 때 생성되는 공간으로 Thread마다 하나씩 존재한다.  
Thread가 어떤 부분을 어떤 명령으로 실행해야 할 지에 대한 기록을 하는 부분  
  
② Stack Area  
프로그램 실행과정에서 임시로 할당되었다가 메소드를 빠져나가면 바로 소멸되는 특성의 데이터를 저장하기 위한 영역  
  
Stack 영역은 Thread별로 각각 독립적으로 생성된다.  
  
각종 형태의 변수나 임시데이터, 스레드나 메소드의 정보를 저장한다.  
호출된 메서드의 매개변수, 지역변수, 리턴 값 및 연산 시 일어나는 값들을 임시로 저장한다.  
  
③ Heap Area  
new 연산자로 생성된 객체와 배열을 저장하는 메모리 공간  
  
④ Native method stack  
Java가 아닌 다른 언어로 작성된 코드를 위한 공간  
자바 프로그램이 컴파일되어 생성되는 바이트 코드가 아닌 실제 수행할 수 있는 기계어로 작성된 프로그램을 실행 시키는 영역  
  
⑤ Method Area  
클래스와 인터페이스의 정보를 처음 메모리 공간에 올릴 때 초기화 되는 대상을 저장하기 위한 메모리 공간  
  
Method Area는 모든 Thread에 의해 공유되는 영역이며, JVM이 시작될 때 생성된다.  
  
런타임 상수풀(runtime constant pool), Field, Method, constructor 등 클래스와 인터페이스와 관련된 데이터들을 분류하고 저장한다.