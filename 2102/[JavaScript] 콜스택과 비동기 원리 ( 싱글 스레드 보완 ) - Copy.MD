url : [https://subbak2.tistory.com/41](https://subbak2.tistory.com/41)

참고 자료 :

[alstn2468.github.io/Javascript/2020-02-28-callstack/](https://alstn2468.github.io/Javascript/2020-02-28-callstack/)

[jongbeom-dev.tistory.com/119](https://jongbeom-dev.tistory.com/119)

**1\. 스택(Stack)이란?**

 스택은 한쪽 끝에서만 자료를 넣거나 뺄 수 있는

후입선출(LIFO - Last In First Out)로 데이터를 저장하는 형식을 말한다.

일반적으로 4가지 연산이 존재한다.

pop, push, peek, isEmpty

[##_Image|kage@bjOHYQ/btqYkbjjuW4/c1CXIBkechmSImd5OHSB90/img.png|alignCenter|data-origin-width="0" data-origin-height="0" width="389" height="NaN" data-ke-mobilestyle="widthContent"|||_##]

**2\. 스택의 활용사례**

\- 콜스택 (함수의 재귀호출)  
\- 웹 브라우저 방문기록  
\- 실행 취소 (undo)  
\- 괄호 검사  
\- 후위 표기법 계산

**3\. 자바스크립트에서의 콜스택**

자바스크립트는 하나의 스레드로 1개의 동시성만 다루는 언어이며,

(Main single thread, and one call stack)

메모리 힙과 콜스택으로 구성되어있다. 

```
var func1 = () => {
	func2();
    console.log('Call func1');
}

var func2 = () => {
	func3();
    console.log('Call func2');
}

var func3 = () => {
	console.log('Call func3');
}
func1();
```

콜스택에서 위의 코드를 실행하면 어떻게 될까?

\* 힌트

[##_Image|kage@ObMrx/btqYmInxcCG/Gob7P5EKd3NIjLsttpGVsK/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]

위의 그림처럼

실행 순서는 func1 → func2→ func3 이고

console.log 순서는 func3 → func2→ func1이다.

[##_Image|kage@bGwU1K/btqYn4cxMZs/CdlJ23f7ZZS7jkEs6qI2b0/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]

**4\. JavaScript의 싱글스레드를 극복하는 요소들 : Web API, Event Loop, Callback Queue **

JavaScript의 싱글스레드, 콜스택으로 인한 단점을 극복하기 위해,

런타임 환경 ( 실제 JavaScript를 구동하는 환경 ) 에서는

Web API, Event Loop, Callback Queue 등이 활용됩니다.

런타임 환경은 웹브라우저, 또는 Node.js입니다.

[##_Image|kage@yg59e/btqYiBI9e8O/ylsplKfGFeEikLZhkcmNK1/img.png|alignCenter|width="577" height="NaN" data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]

```
var func1 = () => {
	setTimeout(()=>{
    	console.log('2초 뒤에 실행하는 콜백함수');
    }, 2000);
}

var func2 = () => {
    for (var i=0; i < 8000000000; i++){
    }
    console.log('약 5초 걸리는 콜스택 함수');
}

func1();
func2();
```

위의 JS 코드를 실행하면 어떻게 될까?

\* 힌트

[##_Image|kage@erGTf2/btqYfXGhQ42/kKleppKi3uSS3yi9ceVjfK/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##][##_Image|kage@bn8gcK/btqYn5P6KkK/g6KLq5P7Cqs1nnm1wKAUD1/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##][##_Image|kage@cEHDAW/btqYpVGalsu/S5JBzFtrUsIXijGnLsRpD0/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##][##_Image|kage@EqKgp/btqYpXxezV5/Dv9jDuKaRGc6hifc7K1IU0/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]

→ 실제 실행결과 

[##_Image|kage@4io2v/btqYo0HIVzp/Zu4YaAIeJOD7TxDRLs13Wk/img.png|alignCenter|data-origin-width="0" data-origin-height="0" data-ke-mobilestyle="widthContent"|||_##]