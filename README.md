# graduation-project

## 개요
개발 인원 : 2 인
진행 기간 : 23.05.16 - 23.06.14
가까운 가족이나 지인들도 알아보고 힘들 정도로 심한 안면인식 장애를 갖고 있는 사람들을 위해 스마트 글래스를 통해 사람들을 인식하게 해주는 학습된 모델 파일을 저장하고 불러올 수 있는 서버를 만들기 위해 제작한 프로젝트입니다.

## 프로젝트 요약
본 프로젝트는 spring boot 프레임워크를 통해 서버를 구축하고, 학습된 모델 파일을 서버를 통해서 데이터 베이스에 저장하고 불러오는 역할을 수행해 줍니다. 서버를 구축하기 위해 AWS EC2 인스턴스를 생성해 Ubuntu 환경에서 본 프로젝트의 jar파일을 빌드하여 실행하였습니다. 또한 RDS를 사용해 MySQL 기반 데이터 베이스를 서버와 연결하여 데이터를 관리하였습니다.


## 프로젝트 아키텍쳐
<img src="https://github.com/Jiggy97/graduation-project/assets/79949843/33f94b0b-992d-4584-a02a-dad96169f6d6" width="800" height="600">



## 적용 기술
- Java
- SpringBoot
- Spring Data JPA
- MySQL
- AWS (EC2, RDS)
