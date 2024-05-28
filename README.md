# 효성에프엠에스 풀스택 개발 전문가 양성과정 1기

## 2차 미니 프로젝트

## 0. 목차

-   [Preview](#1-Preview)
-   [Intro](#2-Intro)
-   [Directory](#3-Directory)
-   [TechStack](#4-TechStack)
-   [Convention](#5-Convention)
-   [UseCaseDiagram](#6-UseCaseDiagram)
-   [Requirements](#7-Requirements)
-   [FlowChart](#8-FlowChart)
-   [SequenceDiagram](#9-SequenceDiagram)
-   [ClassDiagram](#10-ClassDiagram)
-   [UnitTest](#11-UnitTest)
-   [Collaboration](#12-Collaboration)
-   [TroubleShooting](#13-TroubleShooting)
-   [후기](#14-후기)

## 1. Preview

<img src="https://github.com/rlatkd/match5/blob/main/assets/preview.gif">

## 2. Intro

-   프로젝트 이름: MATCH5
-   프로젝트 목적:
    -   1

## 3. Directory

<details>
<summary>디렉터리 구조</summary>

```
📁 match5
 ├──── 📁 .gitlab
 │      ├──── 📁 issue_templates
 │      │      └──── 📄 feature_request.md
 │      └──── 📁 merge_request_templates
 │      │      └──── 📄 default.md
 ├──── 📁 ci
 │      │──── 📄 .gitlab-ci.yml
 │      └──── 📄 deploy.sh
 ├──── 📁 sql
 │      └──── 📄 ini.sql
 --------------------------------------위의 디렉터리들은 별도의 위치에 있음
 └──── 📁 src/main
        ├──── 📁 java/site/match5
        │      ├──── 📁 domain
        │      │      └──── 📁 alarm
        │      │             │──── 📁 controller
        │      │             │      └──── 📄 AlarmController.java
        │      │             │                     :
        │      │             │──── 📁 dto
        │      │             │      └──── 📄 AlarmRes.java
        │      │             │──── 📁 repository
        │      │             │      └──── 📄 MatchingAlarmMapper.java
        │      │             └──── 📁 service
        │      │                    │──── 📄 MatchingAlarmService.java
        │      │                    └──── 📄 NotificationService.java
        │      │                 :
        │      ├──── 📁 global
        │      │      │──── 📁 common
        │      │      │      └──── 📄 Level.java
        │      │      │──── 📁 config
        │      │      │      │──── 📄 JasyptConfig.java
        │      │      │      │──── 📄 RestTemplateConfig.java
        │      │      │      │──── 📄 S3Config.java
        │      │      │      └──── 📄 WebConfig.java
        │      │      │──── 📁 exception
        │      │      │      │──── 📁 customException
        │      │      │      │      └──── 📄 BusinessException.java
        │      │      │      │──── 📁 errorCode
        │      │      │      │      └──── 📄 CommonErrorCode.java
        │      │      │      │                      :
        │      │      │      │──── 📄 ErrorResponse.java
        │      │      │      └──── 📄 ExceptionHandlerAdvice.java
        │      │      └──── 📁 validation
        │      │             │──── 📁 annotation
        │      │             │      └──── 📄 AlarmType.java
        │      │             │                      :
        │      │             └──── 📁 validator
        │      │                    └──── 📄 AlarmTypeValidator.java
        │      │                                    :
        │      ├──── 📁 interceptor
        │      │      │──── 📄 AuthInterceptor.java
        │      │      └──── 📄 KakaoInterceptor.java
        │      ├──── 📁 web/controller
        │      │      └──── 📄 AuthController.java
        │      │                       :
        │      ├──── 📁 adimin
        │      └──── 📄 Match5Application.java
        └──── 📁 resources
               ├──── 📁 mapper
               │      └──── 📁 alarm
               │             └──── 📄 MatchingAlarmMapper.xml
               │                 :
               ├──── 📁 static
               │      │──── 📁 css
               │      │      └──── 📁 auth
               │      │             └──── 📄 auth.style.css
               │      │                  :
               │      ├──── 📁 images
               │      │      └──── 📁 banner
               │      │             └──── 📄 loginBanner.png
               │      │                  :
               │      ├──── 📁 js
               │      │      └──── 📁 auth
               │      │             └──── 📄 auth.js
               │      │                  :
               │      └──── 📄 favicon.ico
               ├──── 📁 templates
               └──── 📄 application.yml
                              :
```

</details>

## 4. TechStack

<img src="https://github.com/rlatkd/match5/blob/main/assets/tech-stack.png">

<img src="https://github.com/rlatkd/match5/blob/main/assets/system-architecture.png">
<img src="https://github.com/rlatkd/match5/blob/main/assets/erd.png">
