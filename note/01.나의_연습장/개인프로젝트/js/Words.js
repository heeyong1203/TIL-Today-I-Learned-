// Words 클래스 (텍스트 및 배경 변경)
class Words extends GameObject{
    constructor(container, x, y, width, height, textConfig={}){
        super(container, x, y, width, height, textConfig);
        this.text=textConfig.text; // 텍스트 내용
        this.originalFontSize=textConfig.originalFontSize; // 기본 폰트 크기
        this.targetFontSize=textConfig.targetFontSize; // 강조 시 폰트 크기
        this.fontSize=this.originalFontSize;
        this.textColor=textConfig.textColor || "#000"; // 기본 글씨 색상
        this.bgColor=textConfig.bgColor; // 배경 색상
        this.isActive=false; // 강조 여부
        this.targetY=textConfig.targetY; // 목표 y 위치
        this.originalY = y;
        this.opacity=0;

        // style 적용
        this.div.classList.add("word");
        this.div.innerText=this.text;
        this.div.style.fontSize=this.originalFontSize+"px";
        this.div.style.top=this.y+"px";
        this.div.style.color=this.textColor;
        this.div.style.lineHeight=this.originalFontSize+"px";
        this.div.style.fontWeight="bold";
        this.div.style.opacity=this.opacity;
    }
    
    // Data 업데이트 (글자 위치, 글자 크기, 글자 투명도, 배경 색상)
    tick(){
        if(this.isActive){ 
            // 글자 위치 변경
            this.y+=0.05*(this.targetY-this.y);
            
            // 글자 크기 변경
            let currentSize = this.fontSize;
            let newSize = currentSize + 0.05 * (this.targetFontSize - currentSize);
            this.fontSize = newSize;
            
            // 글자 투명도 변경
            this.opacity=1;
            
            // 배경색상 변경
            document.getElementById("wrapper").style.backgroundColor = this.bgColor;

        } else {
            // 글자 위치 변경
            this.y = this.originalY;
            
            // 글자 크기 변경
            this.fontSize=this.originalFontSize;
            
            // 글자 투명도 변경
            this.opacity=0;
        }
    }
    
    // UI에 반영
    render(){
        this.div.style.top=this.y+"px"; // 글자 위치 반영
        this.div.style.fontSize=this.fontSize+"px" // 글자 크기 반영
        this.div.style.opacity=this.opacity;  // 글자 투명도 반영    
    }
}