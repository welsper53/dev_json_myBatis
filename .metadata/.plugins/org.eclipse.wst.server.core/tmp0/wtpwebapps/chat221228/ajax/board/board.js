// 비동기통신 객체 생성해서 담을 변수 선언
let xhrObject = null;   // 변수선언

// 비동기통신 객체 생성하는 함수 구현 - 단 리턴예약어가 없어서 반환 받을 수 없음
function createRequest() {
    // 자바스크립트에도 예외처리가 가능하다
    try {
        xhrObject = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            // MS의 IE사용자 위한 객체 생성
            xhrObject = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (error) {
            xhrObject = null;
        }
    }

    if (xhrObject == null) {
        alert("비동기 통신 객체 생성 에러")
    } 
} // end of createRequest

function createRequest2() {
    try {
        xhrObject = new XMLHttpRequest(); // constuctor: 생성자
    } catch (trymicrosoft) {
        try {
            // MS의 IE사용자 위한 객체 생성
            xhrObject = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (error) {
            xhrObject = null;
        }
    }

    if (xhrObject == null) {
        alert("비동기 통신 객체 생성 에러")
    } 
    return xhrObject
} // end of createRequest2


//span태그가 가지고 있는 텍스트 노드값을 읽어오기
/*  <td>100</td> 태그이름이 없다 -> 속성을 정의못함 
    -> 아이디나 클래스를 정의못함 -> 접근불가 -> 어떡하지?
    ==> 해결방법: 텍스트노드에 span태그를 사용
        document.querySelector("#id")  */ 
function getText(el){
    let text="";    // ES6 -> ECMAScript2015 <- 적용이 안되는 브라우저면 "babel"(js컴파일러) 또는 "Pacel번들러"(웹팩-리액트)이 필요하다.
    if(el!=null){
        if(el.childNodes){  // el == [0, false, "", null, undefined, NaN] 일 경우 스킵
            for(let i=0;i<el.childNodes.length;i++){    // el.
                // constEL, priceEl
                let childNode = el.childNodes[i];

                //너 텍스트 노드니?
                if(childNode.nodeValue !=null){
                    text = text+childNode.nodeValue;
                }		
            }
        }
    }
    return text;
}

//기존 TextNode의 값을 다른 문자열로 바꾸기
/***********************************************
param1 :document.getElementById("boardSold")
param1 :document.querySelector("#boardSold")
param2 :xhrObject. 
************************************************/
function replaceText(el, value){    // el->boardEl(보드판매량),cashEl(마진)
    if(el !=null){
        clearText(el);//기존에 있던 10을 지워주세요.
        //새로운 텍스트 노드 15를 생성하기
        let newNode = document.createTextNode(value);//15
        //위에서 생성한 텍스트 노드 값을 el에 붙이는 함수 호출하기
        el.appendChild(newNode);    // el->boardEl(보드판매량) => <span id="boardSold" or cash>10</span>
    }
}

//기존 태그안에 문자열 지우는 함수 구현
function clearText(el){
    if(el !=null){
        if(el.childNodes){  // 자바스크립트는 0이 아닌것은 모두 참이다
            for(let i=0;i<el.childNodes.length;i++){
                let childNode = el.childNodes[i];
                el.removeChild(childNode); // 해당 el의 자식노드 삭제 <- DOM API (직관적X, 유지보수 어려움)
            }
        }
    }
}
