# TeamProject GitHub 환경 구축 가이드

## ✨ GitHub 협업 기본 전략

### 1. 팀원과 합의한 브랜치 전략

- `main`: 배포(정식) 원본 브랜치
- `develop`: 활성 개발용 통합 브랜치
- `feature/xxx`: 개발 가장이 일어나는 작업용 브랜치 (e.g. `feature/login-page`)

---

### 2. 포크(자신계정) 후 clone 하여 로컬로 보기

```bash
git clone [포크한 repo URL]
```

> ⚠ 원본(upstream)에는 **직접 push 금지** (올리다가 실수 발생 방지 위해)

---

### 3. Fork 후 원본의 업데이트 받기

#### 홈페이지 업데이트

- GitHub > 포크한 repo 홈페이지 > "Sync fork"

#### CLI 업데이트

```bash
git remote add [포크 repo URL]   # 최초 1회만
git pull origin main
git push origin main
```

```bash
git remote add upstream [원본 repo URL] # synk fork 없이 바로 fetch 및 pull 원할 때 추가
git fetch upstream
git merge upstream/main
```

---

### 4. 작업 방식

```bash
git checkout -b feature/개발자이름-작업명 # branch 생성
# 작업

git add . # 변경 사항 추가
git commit -m "작업 내용" # 작업 내용 커밋(체크 포인트 생성)
git push origin 로컬브랜치:github저장위치

ex
git push upstream my-feature-branch:Dev # 내 로컬 브랜치 : my-feature-branch / github 내 브랜치 : Dev
```

---

### 5. PR (Pull Request)

- 작업 브랜치(feature/작업명)는 내 포크 저장소에서 생성
- 작업 완료 후 PR 보낼 때: \
  `기준(base): 원본 저장소의 develop` \
  `비교(compare): 내 포크 저장소의 feature/작업명`
- merge되면 원본의 develop 브랜치에 반영 `develop : Test서버`
- 각 기능들을 develop 브랜치에 반영하여 이상 없는지 확인!
#### 모든 기능구현 후 develop → main으로 병합 `Test서버에서 이상없음을 확인하여 main에 최종 병합`

---

### 6. 충돌 발생 시(Conflict...)

- 
- 충돌 방지를 위해 팀원의 PR내용이 merge되면 작업 중인 내용을 stash 혹은 commit 이용
```bash
git stash # 작업 내용 숨김 (임시 저장)
git stash list # stash 항목의 목록 확인
--> # stash@{0}: WIP on develop: 123456 Commit message
git stash show # 변경사항 확인
git stash apply # 가장 최근에 stash된 변경 사항을 작업 디렉토리에 다시 적용
git stash apply stash{n} # 특정(n번째) stash항목 적용, 0번째가 가장 최신 내용
git stash drop stash@{n} # 특정 stash항목 목록에서 제거
git stash pop stash@{n} # 특정 stash 적용 후 삭제
```

```bash
git commit 
```

---

### 7. PR 템플릿 (선택)

`.github/PULL_REQUEST_TEMPLATE.md`

```md
### 작업 내용
- 무엇을 했는가?

### 체크리스트
- [ ] 빌드 성공?
- [ ] 부분 작업 중 해당 아닌 파일이 있나?
```

---

### 8. 기타 와중 정리

- 작업 시 필요한 기법:
  - `git status` / `git diff` / `git log`
- 충돌 예정 파일은 지정 되지 않은 브랜치에서 작업
- 올라가기 전 `pull` 필요

---

