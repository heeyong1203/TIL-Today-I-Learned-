class GameObject {
    constructor(container, x, y, width, height, config ={}){
        this.container=container; // 객체를 포함할 HTML 요소
        this.x=x; // 초기 x값
        this.y=y; // 초기 y값
        this.width=width; // 너비
        this.height=height; // 높이
        this.velX=config.velX; // x축 속도 (default:0)
        this.velY=config.velY; // y축 속도 (default:0)
        this.bg=config.bg || null; // 배경 이미지 (default: 없음)

        //스타일 및 조립
        this.div=document.createElement("div");

        this.div.style.position="absolute";
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
        this.div.style.width=this.width+"px";
        this.div.style.height=this.height+"px";

        if(this.bg!=null){
            this.div.style.backgroundImage=`url(${this.bg})`;
            this.div.style.backgroundSize="contain"; // 지정 요소 내 배경이미지가 다 들어감
            this.div.style.backgroundRepeat = "no-repeat";
            this.div.style.backgroundPosition = "center";
        }
        
        this.container.appendChild(this.div);
    }

    // 위치 및 속성 업데이트
    tick(){
        this.x += this.velX;
        this.y += this.velY;
    };

    // 화면에 렌더링
    render(){
        this.div.style.left=this.x+"px";
        this.div.style.top=this.y+"px";
    };
}