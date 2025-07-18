// Corn 클래스 (고정 객체)
class Corn extends GameObject{
    constructor(container, x, y, width, height, bg){
        super(container, x, y, width, height, { bg });
    }
    
    // Corn은 움직이지 않는 객체이므로 tick()을 비워둠
    tick() {}
    
    // 렌더링은 Object에서 처리하므로 그대로 사용
    render() {
        super.render();
    }
}