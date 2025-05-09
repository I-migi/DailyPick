# 🎬 DailyPick v2 - 콘텐츠 감상 기록 & 소셜 토론 플랫폼

## 📌 프로젝트 소개

**DailyPick v2**는 유튜브, 애니메이션, 영화, 일본 만화 등 다양한 콘텐츠를 감상한 후  
나만의 감상 기록을 남기고, 필요할 땐 다시 꺼내볼 수 있도록 돕는 콘텐츠 아카이빙 플랫폼입니다.  
혼자 사용하는 일기/기록 용도로도 충분하며, 토론방, 추천 기능 등을 통해 커뮤니티로도 확장 가능합니다.

---

## 🎯 주요 기능

| 기능 | 설명 | 사용 API/기술 |
|------|------|-----------------|
| 🔍 콘텐츠 검색 | 유튜브 영상, 영화, 애니메이션 검색 및 메타정보 자동 연동 | YouTube Data API, TMDB API, AniList GraphQL |
| ✍ 감상 기록 | 감상 날짜, 감정, 별점, 태그, 한줄평 등 개인화 리뷰 저장 | Spring Boot + JPA |
| 🏷 태그/감정 분류 | 콘텐츠별 사용자 정의 태그 및 감정 선택 가능 | 다대다 관계 테이블 설계 |
| 📆 감상 캘린더 | 언제 어떤 콘텐츠를 감상했는지 달력 기반 정리 | FullCalendar + REST API |
| 🔍 기록 검색 | 키워드, 감정, 날짜, 콘텐츠 유형으로 기록 필터링 | Spring Pageable + 조건 검색 |
| 💬 토론 채팅 | 콘텐츠별 채팅방 생성, 사용자 간 실시간 감상 토론 | WebSocket (STOMP) |
| 🔥 HOT 콘텐츠 | 인기 있는 콘텐츠를 크롤링/추천하여 메인에 노출 | Selenium, 자동 스케줄링 |
| 🎁 오늘의 추천 | 무작위 콘텐츠 추천 or 취향 기반 추천 기능 | 추천 알고리즘 + API 조합 |
| 📊 감상 통계 | 월간 감상 수, 장르 비율, 감정 추이 등 통계 시각화 | Chart.js, 사용자별 통계 API |
| 🧠 감상 요약 (선택) | 감상 리뷰를 GPT로 요약해주는 AI 기능 | OpenAI GPT API (선택 적용) |

---

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot 3.x / Spring Security / JWT 인증
- JPA (Hibernate), RESTful API
- WebSocket (STOMP) for 채팅

### Frontend
- Thymeleaf (또는 React 전환 고려)
- HTML / CSS / JavaScript

### API 연동
- **YouTube Data API v3** (영상)
- **TMDB API** (영화)
- **AniList GraphQL API** (애니메이션)

### Database
- MySQL or MariaDB
- ERD: User, Content, Review, Tag, ChatRoom 등 관계형 설계

### 기타
- Selenium (인기 콘텐츠 수집용)
- Scheduler (자동 추천 및 핫 콘텐츠 갱신)
- Docker / Nginx / GCP VM 배포 환경

---

## 🧪 사용 예시

1. 유튜브에서 감명 깊은 영상을 보고, 한줄평과 감정 태그를 남긴다.
2. 넷플릭스에서 본 애니메이션 시리즈의 에피소드별 감상을 캘린더로 정리한다.
3. 다시 보고 싶은 영화의 과거 감상 기록을 검색해 복습한다.
4. 오늘의 추천 콘텐츠를 통해 새로운 콘텐츠를 발견한다.
5. 혼자 채팅방을 열어 '토론 메모'를 남기거나 감정 상태를 정리한다.

---

## 🌱 향후 확장

- 유저 팔로우 및 리뷰 공유 기능
- 유튜브 플레이리스트 자동 기록 기능
- 웹툰, 도서 등 콘텐츠 범위 확장
- 모바일 앱 버전 개발 (React Native / Flutter)
- GPT 기반 감상문 생성 or 감정 분석 기능 추가

---

## ✨ 목표

혼자서도 즐겁게 기록하고,  
누군가와 함께 얘기할 수 있는 플랫폼으로 성장시키는 것.

