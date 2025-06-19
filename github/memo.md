# TeamProject를 위한 github 환경 구축

## 팀프로젝트 github 환경

1. 포크하여 팀프로젝트 github 내용을 직접 수정하지 않도록 내 github 환경으로 불러오기
2. 포크한 내 github 환경으로부터 git clone 하여 프로젝트 폴더 로컬로 불러오기
```
git clone [포크한 repo 주소] - 포크 프로젝트 폴더를 받음
```

※ **git upstream 원본주소** 이용 시 원본 팀프로젝트에 직접 접근하는 것이므로 사용하지 말 것

## 포크한 github 환경

1. 원본 github 내용 동기화 : 원본에서 업데이트된 내용을 내 github 환경에 불러오기
```
github 홈페이지의 Sync fork
```
2. 동기화된 github 내용을 내려받기
```
git pull
```

## 로컬 환경
1. 수정한 파일 내용을 포크 환경에 올리기
```
git push [브랜치명]
```
2. 포크 환경에서 확인 후 문제 없을 시 팀원들에게 PR 요청 (원본에 브랜치 생성 요청)
3. PR 수락되면 팀원들 협의하에 원본에서 merge 진행

